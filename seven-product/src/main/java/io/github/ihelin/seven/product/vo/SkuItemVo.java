package io.github.ihelin.seven.product.vo;

import io.github.ihelin.seven.product.entity.SkuImagesEntity;
import io.github.ihelin.seven.product.entity.SkuInfoEntity;
import io.github.ihelin.seven.product.entity.SpuInfoDescEntity;

import java.util.List;

/**
 * @author iHelin
 * @since 2021/1/19 16:44
 */
public class SkuItemVo {
    /**
     * 基本信息
     */
    private SkuInfoEntity info;

    private Boolean hasStock = true;

    /**
     * 图片信息
     */
    private List<SkuImagesEntity> images;

    /**
     * 销售属性组合
     */
    private List<ItemSaleAttrVo> saleAttr;

    /**
     * 介绍
     */
    private SpuInfoDescEntity desc;

    /**
     * 参数规格信息
     */
    private List<SpuItemAttrGroup> groupAttrs;

    /**
     * 秒杀信息
     */
    private SeckillInfoVo seckillInfoVo;

    public SkuInfoEntity getInfo() {
        return info;
    }

    public void setInfo(SkuInfoEntity info) {
        this.info = info;
    }

    public boolean isHasStock() {
        return hasStock;
    }

    public void setHasStock(boolean hasStock) {
        this.hasStock = hasStock;
    }

    public List<SkuImagesEntity> getImages() {
        return images;
    }

    public void setImages(List<SkuImagesEntity> images) {
        this.images = images;
    }

    public List<ItemSaleAttrVo> getSaleAttr() {
        return saleAttr;
    }

    public void setSaleAttr(List<ItemSaleAttrVo> saleAttr) {
        this.saleAttr = saleAttr;
    }

    public SpuInfoDescEntity getDesc() {
        return desc;
    }

    public void setDesc(SpuInfoDescEntity desc) {
        this.desc = desc;
    }

    public List<SpuItemAttrGroup> getGroupAttrs() {
        return groupAttrs;
    }

    public void setGroupAttrs(List<SpuItemAttrGroup> groupAttrs) {
        this.groupAttrs = groupAttrs;
    }

    public SeckillInfoVo getSeckillInfoVo() {
        return seckillInfoVo;
    }

    public void setSeckillInfoVo(SeckillInfoVo seckillInfoVo) {
        this.seckillInfoVo = seckillInfoVo;
    }
}
