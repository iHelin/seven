package io.github.ihelin.seven.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.common.utils.Query;
import io.github.ihelin.seven.product.dao.SkuInfoDao;
import io.github.ihelin.seven.product.entity.SkuImagesEntity;
import io.github.ihelin.seven.product.entity.SkuInfoEntity;
import io.github.ihelin.seven.product.entity.SpuInfoDescEntity;
import io.github.ihelin.seven.product.service.*;
import io.github.ihelin.seven.product.vo.ItemSaleAttrVo;
import io.github.ihelin.seven.product.vo.SkuItemVo;
import io.github.ihelin.seven.product.vo.SpuItemAttrGroupVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * pms_sku_info
 *
 * @author iHelin ihelin@outlook.com
 * @date 2021-01-11 11:52:41
 */
@Service("skuInfoService")
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoDao, SkuInfoEntity> implements SkuInfoService {

    @Autowired
    private SkuImagesService skuImagesService;

    @Autowired
    private SpuInfoDescService spuInfoDescService;

    @Autowired
    private AttrGroupService attrGroupService;

    @Autowired
    private SkuSaleAttrValueService skuSaleAttrValueService;

//    @Autowired
//    private SeckillFeign seckillFeign;

    /**
     * 自定义线程串池
     */
    @Autowired
    private ThreadPoolExecutor executor;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuInfoEntity> page = this.page(
            new Query<SkuInfoEntity>().getPage(params),
            new QueryWrapper<SkuInfoEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPageByCondition(Map<String, Object> params) {
        QueryWrapper<SkuInfoEntity> wrapper = new QueryWrapper<>();
        String key = (String) params.get("key");
        if (StringUtils.isNotEmpty(key)) {
            wrapper.and(w -> {
                w.eq("sku_id", key).or().like("sku_name", key).or().like("sku_title", key);
            });
        }
        String catalogId = (String) params.get("catalogId");
        if (StringUtils.isNotEmpty(catalogId)) {
            wrapper.eq("catalog_id", catalogId);
        }
        String brandId = (String) params.get("brandId");
        if (StringUtils.isNotEmpty(brandId)) {
            wrapper.eq("brand_id", brandId);
        }
        String min = (String) params.get("min");
        if (StringUtils.isNotEmpty(min)) {
            wrapper.ge("price", min);
        }
        String max = (String) params.get("max");
        if (StringUtils.isNotEmpty(max) && !"0".equalsIgnoreCase(max)) {
            wrapper.le("price", max);
        }

        IPage<SkuInfoEntity> page = this.page(new Query<SkuInfoEntity>().getPage(params), wrapper);

        return new PageUtils(page);
    }

    @Override
    public List<SkuInfoEntity> getSkusBySpuId(Long spuId) {
        return this.list(new QueryWrapper<SkuInfoEntity>().eq("spu_id", spuId));

    }

    @Override
    public SkuItemVo item(Long skuId) throws ExecutionException, InterruptedException {
        SkuItemVo skuItemVo = new SkuItemVo();

        CompletableFuture<SkuInfoEntity> baseInfoFuture = CompletableFuture.supplyAsync(() -> {
            //1 sku基本信息
            SkuInfoEntity info = getById(skuId);
            skuItemVo.setInfo(info);
            return info;
        }, executor);

        CompletableFuture<Void> imageFuture = CompletableFuture.runAsync(() -> {
            //2 sku图片信息
            List<SkuImagesEntity> images = skuImagesService.getImagesBySkuId(skuId);
            skuItemVo.setImages(images);
        }, executor);

        CompletableFuture<Void> saleAttrFuture = baseInfoFuture.thenAcceptAsync(res -> {
            //3 获取spu销售属性组合
            List<ItemSaleAttrVo> saleAttrVos = skuSaleAttrValueService.getSaleAttrsBuSpuId(res.getSpuId());
            skuItemVo.setSaleAttr(saleAttrVos);
        }, executor);

        CompletableFuture<Void> descFuture = baseInfoFuture.thenAcceptAsync(res -> {
            //4 获取spu介绍
            SpuInfoDescEntity desc = spuInfoDescService.getById(res.getSpuId());
            skuItemVo.setDesc(desc);
        }, executor);

        CompletableFuture<Void> baseAttrFuture = baseInfoFuture.thenAcceptAsync(res -> {
            //5 获取spu规格参数信息
            List<SpuItemAttrGroupVo> attrGroups = attrGroupService.getAttrGroupWithAttrsBySpuId(res.getSpuId(), res.getCatalogId());
            skuItemVo.setGroupAttrs(attrGroups);
        }, executor);

        // 6.查询当前sku是否参与秒杀优惠
//        CompletableFuture<Void> secKillFuture = CompletableFuture.runAsync(() -> {
//            R skuSeckillInfo = seckillFeignService.getSkuSeckillInfo(skuId);
//            if (skuSeckillInfo.getCode() == 0) {
//                SeckillInfoVo seckillInfoVo = skuSeckillInfo.getData(new TypeReference<SeckillInfoVo>() {
//                });
//                skuItemVo.setSeckillInfoVo(seckillInfoVo);
//            }
//        }, executor);

        // 等待所有任务都完成再返回
        CompletableFuture.allOf(imageFuture, saleAttrFuture, descFuture, baseAttrFuture).get();
        return skuItemVo;
    }

}