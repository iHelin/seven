package io.github.ihelin.seven.ware.feign;

import io.github.ihelin.seven.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author iHelin ihelin@outlook.com
 * @since 2021/1/14 下午12:36
 */
@FeignClient("seven-product")
public interface ProductFeign {

    @GetMapping("/product/skuinfo/info/{skuId}")
    R info(@PathVariable("skuId") Long skuId);

}
