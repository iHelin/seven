package io.github.ihelin.seven.order.vo;

public class SkuStockVo {
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
