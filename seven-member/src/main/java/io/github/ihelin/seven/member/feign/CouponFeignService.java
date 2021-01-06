package io.github.ihelin.seven.member.feign;

import io.github.ihelin.seven.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author iHelin
 * @since 2021/1/5 15:10
 */
@FeignClient("seven-coupon")
public interface CouponFeignService {

    @GetMapping("/coupon/coupon/member/list")
    R memberCoupons();
}
