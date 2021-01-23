package io.github.ihelin.seven.order.feign;

import io.github.ihelin.seven.common.utils.R;
import io.github.ihelin.seven.order.vo.OrderItemVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("seven-product")
public interface ProductFeignService {

	@GetMapping("/product/spuinfo/skuId/{id}")
	R getSkuInfoBySkuId(@PathVariable("id") Long skuId);

	@GetMapping("/currentUserCartItems")
	List<OrderItemVo> getCurrentUserCartItems();
}
