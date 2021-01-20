package io.github.ihelin.seven.member.feign;

import io.github.ihelin.seven.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author iHelin
 * @since 2021/1/20 20:41
 */
@FeignClient("seven-open")
public interface OpenFeign {

    @GetMapping("/sms/sendcode")
    R sendCode(@RequestParam String phone, @RequestParam String code);
}
