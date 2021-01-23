package io.github.ihelin.seven.order.feign;

import io.github.ihelin.seven.order.vo.MemberAddressVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("seven-member")
public interface MemberFeign {

	@GetMapping("/member/memberreceiveaddress/{memberId}/addresses")
	List<MemberAddressVo> getAddress(@PathVariable("memberId") Long memberId);

}
