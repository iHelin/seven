package io.github.ihelin.seven.gateway.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * ÉÌÆ·»áÔ±¼Û¸ñ
 * 
 * @author iHelin
 * @email ihelin@outlook.com
 * @date 2021-01-05 12:40:34
 */
//@Data
@TableName("sms_member_price")
public class MemberPriceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * sku_id
	 */
	private Long skuId;
	/**
	 * »áÔ±µÈ¼¶id
	 */
	private Long memberLevelId;
	/**
	 * »áÔ±µÈ¼¶Ãû
	 */
	private String memberLevelName;
	/**
	 * »áÔ±¶ÔÓ¦¼Û¸ñ
	 */
	private BigDecimal memberPrice;
	/**
	 * ¿É·ñµþ¼ÓÆäËûÓÅ»Ý[0-²»¿Éµþ¼ÓÓÅ»Ý£¬1-¿Éµþ¼Ó]
	 */
	private Integer addOther;

}
