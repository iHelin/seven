package io.github.ihelin.seven.ware.vo;

import java.math.BigDecimal;

public class FareVo {

	private MemberAddressVo memberAddressVo;

	private BigDecimal fare;

	public MemberAddressVo getMemberAddressVo() {
		return memberAddressVo;
	}

	public void setMemberAddressVo(MemberAddressVo memberAddressVo) {
		this.memberAddressVo = memberAddressVo;
	}

	public BigDecimal getFare() {
		return fare;
	}

	public void setFare(BigDecimal fare) {
		this.fare = fare;
	}
}
