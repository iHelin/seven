package io.github.ihelin.seven.order.vo;

import java.util.List;

public class WareSkuLockVo {

	/**
	 * 订单号
	 */
	private String orderSn;

	/**
	 * 要锁住的所有库存信息
	 */
	private List<OrderItemVo> locks;

	public String getOrderSn() {
		return orderSn;
	}

	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}

	public List<OrderItemVo> getLocks() {
		return locks;
	}

	public void setLocks(List<OrderItemVo> locks) {
		this.locks = locks;
	}
}
