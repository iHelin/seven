package io.github.ihelin.seven.gateway.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * Ê×Ò³ÂÖ²¥¹ã¸æ
 * 
 * @author iHelin
 * @email ihelin@outlook.com
 * @date 2021-01-05 12:40:34
 */
//@Data
@TableName("sms_home_adv")
public class HomeAdvEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * Ãû×Ö
	 */
	private String name;
	/**
	 * Í¼Æ¬µØÖ·
	 */
	private String pic;
	/**
	 * ¿ªÊ¼Ê±¼ä
	 */
	private Date startTime;
	/**
	 * ½áÊøÊ±¼ä
	 */
	private Date endTime;
	/**
	 * ×´Ì¬
	 */
	private Integer status;
	/**
	 * µã»÷Êý
	 */
	private Integer clickCount;
	/**
	 * ¹ã¸æÏêÇéÁ¬½ÓµØÖ·
	 */
	private String url;
	/**
	 * ±¸×¢
	 */
	private String note;
	/**
	 * ÅÅÐò
	 */
	private Integer sort;
	/**
	 * ·¢²¼Õß
	 */
	private Long publisherId;
	/**
	 * ÉóºËÕß
	 */
	private Long authId;

}
