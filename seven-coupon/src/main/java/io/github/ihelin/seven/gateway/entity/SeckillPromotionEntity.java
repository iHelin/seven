package io.github.ihelin.seven.gateway.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * ÃëÉ±»î¶¯
 * 
 * @author iHelin
 * @email ihelin@outlook.com
 * @date 2021-01-05 12:40:34
 */
//@Data
@TableName("sms_seckill_promotion")
public class SeckillPromotionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * »î¶¯±êÌâ
	 */
	private String title;
	/**
	 * ¿ªÊ¼ÈÕÆÚ
	 */
	private Date startTime;
	/**
	 * ½áÊøÈÕÆÚ
	 */
	private Date endTime;
	/**
	 * ÉÏÏÂÏß×´Ì¬
	 */
	private Integer status;
	/**
	 * ´´½¨Ê±¼ä
	 */
	private Date createTime;
	/**
	 * ´´½¨ÈË
	 */
	private Long userId;

}
