package io.github.ihelin.seven.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.ihelin.seven.common.utils.JsonUtils;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.common.utils.Query;
import io.github.ihelin.seven.product.dao.CategoryDao;
import io.github.ihelin.seven.product.entity.CategoryEntity;
import io.github.ihelin.seven.product.service.CategoryBrandRelationService;
import io.github.ihelin.seven.product.service.CategoryService;
import io.github.ihelin.seven.product.vo.Catalog2Vo;
import io.github.ihelin.seven.product.vo.Catalog3Vo;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private RedissonClient redissonClient;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
            new Query<CategoryEntity>().getPage(params),
            new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listWithTree() {

        List<CategoryEntity> entities = list();
        List<CategoryEntity> level1 = entities.stream()
            .filter(item -> item.getParentCid() == 0L)
            .peek(item -> item.setChildren(getChildren(item, entities)))
            .sorted(Comparator.comparingInt(CategoryEntity::getSort))
            .collect(Collectors.toList());
        return level1;
    }

    @Override
    public void removeMenuByIds(List<Long> idList) {
        //TODO 检查当前删除的菜单，是否被别的地方引用
        baseMapper.deleteBatchIds(idList);
    }

    @Override
    public List<Long> findCatalogPath(Long catalogId) {
        List<Long> ids = new ArrayList<>();
        findParentPath(catalogId, ids);
        Collections.reverse(ids);
        return ids;
    }

    @Override
    @Transactional
    public void updateCascade(CategoryEntity category) {
        this.updateById(category);
        categoryBrandRelationService.updateCategory(category.getCatId(), category.getName());
    }

    @Override
    public List<CategoryEntity> getLevel1Categorys() {
        List<CategoryEntity> categoryEntityList = baseMapper.selectList(new QueryWrapper<CategoryEntity>().eq("parent_cid", 0));
        return categoryEntityList;
    }

    @Override
    public Map<String, List<Catalog2Vo>> getCatalogJson() {
        return getCatalogJsonWithRedissonLock();
    }

    public Map<String, List<Catalog2Vo>> getCatalogJsonWithRedissonLock() {
        String catalogJSONString = redisTemplate.opsForValue().get("catalogJSON");
        if (StringUtils.isEmpty(catalogJSONString)) {
            //加锁成功
            RLock lock = redissonClient.getLock("catalogJsonLock");
            lock.lock();
            try {
                catalogJSONString = redisTemplate.opsForValue().get("catalogJSON");
                if (StringUtils.isEmpty(catalogJSONString)) {
                    Map<String, List<Catalog2Vo>> catalogJsonFromDb = getCatalogJsonFromDb();
                    String catalogJson = JsonUtils.objectToJson(catalogJsonFromDb);
                    redisTemplate.opsForValue().set("catalogJSON", catalogJson, 1, TimeUnit.DAYS);
                    return catalogJsonFromDb;
                } else {
                    return JsonUtils.jsonToPojo(catalogJSONString, Map.class);
                }
            } finally {
                //删除锁 必须是原子操作
                lock.unlock();
            }
        }
        return JsonUtils.jsonToPojo(catalogJSONString, Map.class);
    }

    public Map<String, List<Catalog2Vo>> getCatalogJsonWithRedisLock() {
        String catalogJSONString = redisTemplate.opsForValue().get("catalogJSON");
        if (StringUtils.isEmpty(catalogJSONString)) {
            String uuid = UUID.randomUUID().toString();
            //加锁，原子操作
            Boolean lock = redisTemplate.opsForValue().setIfAbsent("lock", uuid, 30, TimeUnit.SECONDS);
            if (lock) {
                //加锁成功
                try {
                    catalogJSONString = redisTemplate.opsForValue().get("catalogJSON");
                    if (!StringUtils.isEmpty(catalogJSONString)) {
                        Map<String, List<Catalog2Vo>> catalogJsonFromDb = getCatalogJsonFromDb();
                        String catalogJson = JsonUtils.objectToJson(catalogJsonFromDb);
                        redisTemplate.opsForValue().set("catalogJSON", catalogJson, 1, TimeUnit.DAYS);
                        return catalogJsonFromDb;
                    } else {
                        return JsonUtils.jsonToPojo(catalogJSONString, Map.class);
                    }
                } finally {
                    //删除锁 必须是原子操作
                    String unLockScript = "if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";
                    redisTemplate.execute(new DefaultRedisScript<>(unLockScript, Long.class), Collections.singletonList("lock"), uuid);
//                    String lockValue = redisTemplate.opsForValue().get("lock");
//                    if (uuid.equals(lockValue)) {
//                        redisTemplate.delete("lock");
//                    }
                }
            } else {
                //加锁失败
                return getCatalogJsonWithRedisLock();
            }

        }
        return JsonUtils.jsonToPojo(catalogJSONString, Map.class);
    }

    public Map<String, List<Catalog2Vo>> getCatalogJsonWithLocalLock() {
        String catalogJSON = redisTemplate.opsForValue().get("catalogJSON");
        if (StringUtils.isEmpty(catalogJSON)) {
            synchronized (this) {
                catalogJSON = redisTemplate.opsForValue().get("catalogJSON");
                if (!StringUtils.isEmpty(catalogJSON)) {
                    return JsonUtils.jsonToPojo(catalogJSON, Map.class);
                }
                Map<String, List<Catalog2Vo>> catalogJsonFromDb = getCatalogJsonFromDb();
                String catalogJson = JsonUtils.objectToJson(catalogJsonFromDb);
                redisTemplate.opsForValue().set("catalogJSON", catalogJson, 1, TimeUnit.DAYS);
                return catalogJsonFromDb;
            }
        }
        return JsonUtils.jsonToPojo(catalogJSON, Map.class);
    }


    public Map<String, List<Catalog2Vo>> getCatalogJsonFromDb() {
        System.out.println("查询了数据库");
        List<CategoryEntity> entityList = baseMapper.selectList(null);
        // 查询所有一级分类
        List<CategoryEntity> level1 = getCategoryEntities(entityList, 0L);
        Map<String, List<Catalog2Vo>> parentCId = level1.stream().collect(Collectors.toMap(k -> k.getCatId().toString(), v -> {
            // 拿到每一个一级分类 然后查询他们的二级分类
            List<CategoryEntity> entities = getCategoryEntities(entityList, v.getCatId());
            List<Catalog2Vo> catalog2Vos = null;
            if (entities != null) {
                catalog2Vos = entities.stream().map(l2 -> {
                    Catalog2Vo catalog2Vo = new Catalog2Vo(v.getCatId().toString(), l2.getName(), l2.getCatId().toString(), null);
                    // 找当前二级分类的三级分类
                    List<CategoryEntity> level3 = getCategoryEntities(entityList, l2.getCatId());
                    // 三级分类有数据的情况下
                    if (level3 != null) {
                        List<Catalog3Vo> catalog3Vos = level3.stream().map(l3 -> new Catalog3Vo(l3.getCatId().toString(), l3.getName(), l2.getCatId().toString())).collect(Collectors.toList());
                        catalog2Vo.setCatalog3List(catalog3Vos);
                    }
                    return catalog2Vo;
                }).collect(Collectors.toList());
            }
            return catalog2Vos;
        }));
        return parentCId;
    }

    /**
     * 第一次查询的所有 CategoryEntity 然后根据 parent_cid去这里找
     */
    private List<CategoryEntity> getCategoryEntities(List<CategoryEntity> entityList, Long parent_cid) {
        return entityList.stream().filter(item -> item.getParentCid().equals(parent_cid)).collect(Collectors.toList());
    }

    private void findParentPath(Long catalogId, List<Long> ids) {
        ids.add(catalogId);
        CategoryEntity categoryEntity = this.getById(catalogId);
        if (categoryEntity.getParentCid() != 0) {
            findParentPath(categoryEntity.getParentCid(), ids);
        }
    }

    private List<CategoryEntity> getChildren(CategoryEntity root, List<CategoryEntity> all) {
        List<CategoryEntity> children = all.stream()
            .filter(item -> root.getCatId().equals(item.getParentCid()))
            .peek(item -> item.setChildren(getChildren(item, all)))
            .sorted(Comparator.comparingInt(CategoryEntity::getSort))
            .collect(Collectors.toList());
        return children;
    }

}