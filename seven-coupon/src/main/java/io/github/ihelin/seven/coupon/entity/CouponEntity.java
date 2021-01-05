package io.github.ihelin.seven.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * ÓÅ»ÝÈ¯ÐÅÏ¢
 * 
 * @author iHelin
 * @email ihelin@outlook.com
 * @date 2021-01-05 12:40:34
 */
//@Data
@TableName("sms_coupon")
public class CouponEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * ÓÅ»Ý¾íÀàÐÍ[0->È«³¡ÔùÈ¯£»1->»áÔ±ÔùÈ¯£»2->¹ºÎïÔùÈ¯£»3->×¢²áÔùÈ¯]
	 */
	private Integer couponType;
	/**
	 * ÓÅ»ÝÈ¯Í¼Æ¬
	 */
	private String couponImg;
	/**
	 * ÓÅ»Ý¾íÃû×Ö
	 */
	private String couponName;
	/**
	 * ÊýÁ¿
	 */
	private Integer num;
	/**
	 * ½ð¶î
	 */
	private BigDecimal amount;
	/**
	 * Ã¿ÈËÏÞÁìÕÅÊý
	 */
	private Integer perLimit;
	/**
	 * Ê¹ÓÃÃÅ¼÷
	 */
	private BigDecimal minPoint;
	/**
	 * ¿ªÊ¼Ê±¼ä
	 */
	private Date startTime;
	/**
	 * ½áÊøÊ±¼ä
	 */
	private Date endTime;
	/**
	 * Ê¹ÓÃÀàÐÍ[0->È«³¡Í¨ÓÃ£»1->Ö¸¶¨·ÖÀà£»2->Ö¸¶¨ÉÌÆ·]
	 */
	private Integer useType;
	/**
	 * ±¸×¢
	 */
	private String note;
	/**
	 * ·¢ÐÐÊýÁ¿
	 */
	private Integer publishCount;
	/**
	 * ÒÑÊ¹ÓÃÊýÁ¿
	 */
	private Integer useCount;
	/**
	 * ÁìÈ¡ÊýÁ¿
	 */
	private Integer receiveCount;
	/**
	 * ¿ÉÒÔÁìÈ¡µÄ¿ªÊ¼ÈÕÆÚ
	 */
	private Date enableStartTime;
	/**
	 * ¿ÉÒÔÁìÈ¡µÄ½áÊøÈÕÆÚ
	 */
	private Date enableEndTime;
	/**
	 * ÓÅ»ÝÂë
	 */
	private String code;
	/**
	 * ¿ÉÒÔÁìÈ¡µÄ»áÔ±µÈ¼¶[0->²»ÏÞµÈ¼¶£¬ÆäËû-¶ÔÓ¦µÈ¼¶]
	 */
	private Integer memberLevel;
	/**
	 * ·¢²¼×´Ì¬[0-Î´·¢²¼£¬1-ÒÑ·¢²¼]
	 */
	private Integer publish;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCouponType() {
		return couponType;
	}

	public void setCouponType(Integer couponType) {
		this.couponType = couponType;
	}

	public String getCouponImg() {
		return couponImg;
	}

	public void setCouponImg(String couponImg) {
		this.couponImg = couponImg;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getPerLimit() {
		return perLimit;
	}

	public void setPerLimit(Integer perLimit) {
		this.perLimit = perLimit;
	}

	public BigDecimal getMinPoint() {
		return minPoint;
	}

	public void setMinPoint(BigDecimal minPoint) {
		this.minPoint = minPoint;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getUseType() {
		return useType;
	}

	public void setUseType(Integer useType) {
		this.useType = useType;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getPublishCount() {
		return publishCount;
	}

	public void setPublishCount(Integer publishCount) {
		this.publishCount = publishCount;
	}

	public Integer getUseCount() {
		return useCount;
	}

	public void setUseCount(Integer useCount) {
		this.useCount = useCount;
	}

	public Integer getReceiveCount() {
		return receiveCount;
	}

	public void setReceiveCount(Integer receiveCount) {
		this.receiveCount = receiveCount;
	}

	public Date getEnableStartTime() {
		return enableStartTime;
	}

	public void setEnableStartTime(Date enableStartTime) {
		this.enableStartTime = enableStartTime;
	}

	public Date getEnableEndTime() {
		return enableEndTime;
	}

	public void setEnableEndTime(Date enableEndTime) {
		this.enableEndTime = enableEndTime;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getMemberLevel() {
		return memberLevel;
	}

	public void setMemberLevel(Integer memberLevel) {
		this.memberLevel = memberLevel;
	}

	public Integer getPublish() {
		return publish;
	}

	public void setPublish(Integer publish) {
		this.publish = publish;
	}
}
