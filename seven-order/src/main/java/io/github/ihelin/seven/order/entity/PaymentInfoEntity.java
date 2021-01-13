package io.github.ihelin.seven.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * Ö§¸¶ÐÅÏ¢±í
 * 
 * @author iHelin
 * @email ihelin@outlook.com
 * @date 2021-01-05 14:16:31
 */

@TableName("oms_payment_info")
public class PaymentInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * ¶©µ¥ºÅ£¨¶ÔÍâÒµÎñºÅ£©
	 */
	private String orderSn;
	/**
	 * ¶©µ¥id
	 */
	private Long orderId;
	/**
	 * Ö§¸¶±¦½»Ò×Á÷Ë®ºÅ
	 */
	private String alipayTradeNo;
	/**
	 * Ö§¸¶×Ü½ð¶î
	 */
	private BigDecimal totalAmount;
	/**
	 * ½»Ò×ÄÚÈÝ
	 */
	private String subject;
	/**
	 * Ö§¸¶×´Ì¬
	 */
	private String paymentStatus;
	/**
	 * ´´½¨Ê±¼ä
	 */
	private Date createTime;
	/**
	 * È·ÈÏÊ±¼ä
	 */
	private Date confirmTime;
	/**
	 * »Øµ÷ÄÚÈÝ
	 */
	private String callbackContent;
	/**
	 * »Øµ÷Ê±¼ä
	 */
	private Date callbackTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderSn() {
		return orderSn;
	}

	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getAlipayTradeNo() {
		return alipayTradeNo;
	}

	public void setAlipayTradeNo(String alipayTradeNo) {
		this.alipayTradeNo = alipayTradeNo;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}

	public String getCallbackContent() {
		return callbackContent;
	}

	public void setCallbackContent(String callbackContent) {
		this.callbackContent = callbackContent;
	}

	public Date getCallbackTime() {
		return callbackTime;
	}

	public void setCallbackTime(Date callbackTime) {
		this.callbackTime = callbackTime;
	}
}
