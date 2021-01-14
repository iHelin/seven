package io.github.ihelin.seven.product.feign;

import io.github.ihelin.seven.common.dto.SkuReductionDTO;
import io.github.ihelin.seven.common.dto.SpuBoundDTO;
import io.github.ihelin.seven.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author iHelin ihelin@outlook.com
 * @since 2021/1/13 下午4:58
 */
@FeignClient("seven-coupon")
public interface CouponFeign {
    
    @PostMapping("/coupon/spubounds/save")
    R saveSpuBounds(@RequestBody SpuBoundDTO spuBoundDTO);

    @PostMapping("/coupon/skufullreduction/saveinfo")
    R saveReduction(@RequestBody SkuReductionDTO skuReductionDTO);
}
