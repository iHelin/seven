package io.github.ihelin.seven.ware.feign;

import io.github.ihelin.seven.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("seven-member")
public interface MemberFeign {

	@RequestMapping("/member/memberreceiveaddress/info/{id}")
	R addrInfo(@PathVariable("id") Long id);
}
