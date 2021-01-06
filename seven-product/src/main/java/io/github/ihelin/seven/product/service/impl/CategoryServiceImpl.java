package io.github.ihelin.seven.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.ihelin.common.utils.PageUtils;
import io.github.ihelin.common.utils.Query;
import io.github.ihelin.seven.product.dao.CategoryDao;
import io.github.ihelin.seven.product.entity.CategoryEntity;
import io.github.ihelin.seven.product.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

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
                .peek(item-> item.setChildren(getChildren(item,entities)))
                .sorted(Comparator.comparingInt(CategoryEntity::getSort))
                .collect(Collectors.toList());
        return level1;
    }

    private List<CategoryEntity> getChildren(CategoryEntity root,List<CategoryEntity> all){
        List<CategoryEntity> children = all.stream()
                .filter(item -> root.getCatId().equals(item.getParentCid()))
                .peek(item-> item.setChildren(getChildren(item,all)))
                .sorted(Comparator.comparingInt(CategoryEntity::getSort))
                .collect(Collectors.toList());
        return children;
    }

}