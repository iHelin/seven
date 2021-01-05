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

}
