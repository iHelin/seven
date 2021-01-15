package io.github.ihelin.seven.product.feign;

import io.github.ihelin.seven.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author iHelin ihelin@outlook.com
 * @since 2021/1/15 下午4:53
 */
@FeignClient("seven-ware")
public interface WareFeign {

    @PostMapping("/ware/waresku/hasstock")
    R getSkuHasStock(@RequestBody List<Long> skuIds);

}
