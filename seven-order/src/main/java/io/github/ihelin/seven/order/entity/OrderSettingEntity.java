package io.github.ihelin.seven.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;


/**
 * ¶©µ¥ÅäÖÃÐÅÏ¢
 * 
 * @author iHelin
 * @email ihelin@outlook.com
 * @date 2021-01-05 14:16:31
 */

@TableName("oms_order_setting")
public class OrderSettingEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * ÃëÉ±¶©µ¥³¬Ê±¹Ø±ÕÊ±¼ä(·Ö)
	 */
	private Integer flashOrderOvertime;
	/**
	 * Õý³£¶©µ¥³¬Ê±Ê±¼ä(·Ö)
	 */
	private Integer normalOrderOvertime;
	/**
	 * ·¢»õºó×Ô¶¯È·ÈÏÊÕ»õÊ±¼ä£¨Ìì£©
	 */
	private Integer confirmOvertime;
	/**
	 * ×Ô¶¯Íê³É½»Ò×Ê±¼ä£¬²»ÄÜÉêÇëÍË»õ£¨Ìì£©
	 */
	private Integer finishOvertime;
	/**
	 * ¶©µ¥Íê³Éºó×Ô¶¯ºÃÆÀÊ±¼ä£¨Ìì£©
	 */
	private Integer commentOvertime;
	/**
	 * »áÔ±µÈ¼¶¡¾0-²»ÏÞ»áÔ±µÈ¼¶£¬È«²¿Í¨ÓÃ£»ÆäËû-¶ÔÓ¦µÄÆäËû»áÔ±µÈ¼¶¡¿
	 */
	private Integer memberLevel;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getFlashOrderOvertime() {
		return flashOrderOvertime;
	}

	public void setFlashOrderOvertime(Integer flashOrderOvertime) {
		this.flashOrderOvertime = flashOrderOvertime;
	}

	public Integer getNormalOrderOvertime() {
		return normalOrderOvertime;
	}

	public void setNormalOrderOvertime(Integer normalOrderOvertime) {
		this.normalOrderOvertime = normalOrderOvertime;
	}

	public Integer getConfirmOvertime() {
		return confirmOvertime;
	}

	public void setConfirmOvertime(Integer confirmOvertime) {
		this.confirmOvertime = confirmOvertime;
	}

	public Integer getFinishOvertime() {
		return finishOvertime;
	}

	public void setFinishOvertime(Integer finishOvertime) {
		this.finishOvertime = finishOvertime;
	}

	public Integer getCommentOvertime() {
		return commentOvertime;
	}

	public void setCommentOvertime(Integer commentOvertime) {
		this.commentOvertime = commentOvertime;
	}

	public Integer getMemberLevel() {
		return memberLevel;
	}

	public void setMemberLevel(Integer memberLevel) {
		this.memberLevel = memberLevel;
	}
}
