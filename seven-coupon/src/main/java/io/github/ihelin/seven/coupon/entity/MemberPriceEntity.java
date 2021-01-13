package io.github.ihelin.seven.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author iHelin ihelin@outlook.com
 * @since 2021-01-05 12:40:34
 */
@TableName("sms_member_price")
public class MemberPriceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	private Long skuId;
	private Long memberLevelId;
	private String memberLevelName;
	private BigDecimal memberPrice;
	private Integer addOther;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public Long getMemberLevelId() {
		return memberLevelId;
	}

	public void setMemberLevelId(Long memberLevelId) {
		this.memberLevelId = memberLevelId;
	}

	public String getMemberLevelName() {
		return memberLevelName;
	}

	public void setMemberLevelName(String memberLevelName) {
		this.memberLevelName = memberLevelName;
	}

	public BigDecimal getMemberPrice() {
		return memberPrice;
	}

	public void setMemberPrice(BigDecimal memberPrice) {
		this.memberPrice = memberPrice;
	}

	public Integer getAddOther() {
		return addOther;
	}

	public void setAddOther(Integer addOther) {
		this.addOther = addOther;
	}
}
