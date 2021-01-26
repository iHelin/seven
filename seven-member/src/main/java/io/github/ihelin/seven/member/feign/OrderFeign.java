package io.github.ihelin.seven.member.feign;

import io.github.ihelin.seven.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient("seven-order")
public interface OrderFeign {

	@PostMapping("/order/order/listWithItem")
	R listWithItem(@RequestBody Map<String, Object> params);
}
