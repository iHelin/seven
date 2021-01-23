package io.github.ihelin.seven.ware.feign;

import io.github.ihelin.seven.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("seven-order")
public interface OrderFeign {

	/**
	 * 查询订单状态
	 */
	@GetMapping("/order/order/status/{orderSn}")
	R getOrderStatus(@PathVariable("orderSn") String orderSn);
}
