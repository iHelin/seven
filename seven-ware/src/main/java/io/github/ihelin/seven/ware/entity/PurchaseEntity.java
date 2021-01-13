package io.github.ihelin.seven.ware.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * ²É¹ºÐÅÏ¢
 * 
 * @author iHelin
 * @email ihelin@outlook.com
 * @date 2021-01-05 14:18:39
 */

@TableName("wms_purchase")
public class PurchaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ²É¹ºµ¥id
	 */
	@TableId
	private Long id;
	/**
	 * ²É¹ºÈËid
	 */
	private Long assigneeId;
	/**
	 * ²É¹ºÈËÃû
	 */
	private String assigneeName;
	/**
	 * ÁªÏµ·½Ê½
	 */
	private String phone;
	/**
	 * ÓÅÏÈ¼¶
	 */
	private Integer priority;
	/**
	 * ×´Ì¬
	 */
	private Integer status;
	/**
	 * ²Ö¿âid
	 */
	private Long wareId;
	/**
	 * ×Ü½ð¶î
	 */
	private BigDecimal amount;
	/**
	 * ´´½¨ÈÕÆÚ
	 */
	private Date createTime;
	/**
	 * ¸üÐÂÈÕÆÚ
	 */
	private Date updateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAssigneeId() {
		return assigneeId;
	}

	public void setAssigneeId(Long assigneeId) {
		this.assigneeId = assigneeId;
	}

	public String getAssigneeName() {
		return assigneeName;
	}

	public void setAssigneeName(String assigneeName) {
		this.assigneeName = assigneeName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getWareId() {
		return wareId;
	}

	public void setWareId(Long wareId) {
		this.wareId = wareId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
