package io.github.ihelin.seven.order.vo;

import java.math.BigDecimal;

public class OrderSubmitVo {

	private Long addrId;

	private Integer payType;

	// 无需要购买的商品 去购物车再获取一遍
	// 优惠

	/**
	 * 防重令牌
	 */
	private String orderToken;

	/**
	 * 应付价格
	 */
	private BigDecimal payPrice;

	private String note;
	// 用户相关信息 直接去session里面取


	public Long getAddrId() {
		return addrId;
	}

	public void setAddrId(Long addrId) {
		this.addrId = addrId;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public String getOrderToken() {
		return orderToken;
	}

	public void setOrderToken(String orderToken) {
		this.orderToken = orderToken;
	}

	public BigDecimal getPayPrice() {
		return payPrice;
	}

	public void setPayPrice(BigDecimal payPrice) {
		this.payPrice = payPrice;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
