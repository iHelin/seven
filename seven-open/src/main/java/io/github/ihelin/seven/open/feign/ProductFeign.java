package io.github.ihelin.seven.open.feign;

import io.github.ihelin.seven.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author iHelin
 * @since 2021/1/19 11:58
 */
@FeignClient("seven-product")
public interface ProductFeign {

    @GetMapping("/product/attr/info/{attrId}")
    R getAttrInfo(@PathVariable("attrId") Long attrId);

    @GetMapping("/product/brand/infos")
    R getBrands(@RequestParam List<Long> brandIds);
}
