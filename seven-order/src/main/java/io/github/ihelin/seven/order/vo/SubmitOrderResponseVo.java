package io.github.ihelin.seven.order.vo;

import io.github.ihelin.seven.order.entity.OrderEntity;

public class SubmitOrderResponseVo {

	private OrderEntity orderEntity;

	/**
	 * 错误状态码： 0----成功
	 */
	private Integer code;

	public OrderEntity getOrderEntity() {
		return orderEntity;
	}

	public void setOrderEntity(OrderEntity orderEntity) {
		this.orderEntity = orderEntity;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
}
