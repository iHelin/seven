package io.github.ihelin.seven.common.dto;

/**
 * @author iHelin ihelin@outlook.com
 * @since 2021/1/15 下午4:46
 */
public class SkuHasStockVo {

    private Long skuId;
    private Boolean hasStock;

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Boolean getHasStock() {
        return hasStock;
    }

    public void setHasStock(Boolean hasStock) {
        this.hasStock = hasStock;
    }
}
