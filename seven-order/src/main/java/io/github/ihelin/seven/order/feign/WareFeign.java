package io.github.ihelin.seven.order.feign;

import io.github.ihelin.seven.common.utils.R;
import io.github.ihelin.seven.order.vo.WareSkuLockVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("seven-ware")
public interface WareFeign {

	@PostMapping("/ware/waresku/hasstock")
	R getSkuHasStock(@RequestBody List<Long> skuIds);

	@GetMapping("/ware/wareinfo/fare")
	R getFare(@RequestParam("addrId") Long addrId);

	/**
	 * 锁定库存
	 */
	@PostMapping("/ware/waresku/lock/order")
	R orderLockStock(@RequestBody WareSkuLockVo vo);
}
