package io.github.ihelin.seven.common.exception;

/**
 * <p>Title: NotStockException</p>
 * Description：
 * date：2020/7/2 11:43
 */
public class NoStockException extends RuntimeException {

    private Long skuId;

    public NoStockException(Long skuId) {
        super(skuId + "号商品没有足够的库存");
		this.skuId = skuId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }
}
