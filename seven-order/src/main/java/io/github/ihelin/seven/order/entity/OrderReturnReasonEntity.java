package io.github.ihelin.seven.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;


/**
 * ÍË»õÔ­Òò
 * 
 * @author iHelin
 * @email ihelin@outlook.com
 * @date 2021-01-05 14:16:31
 */

@TableName("oms_order_return_reason")
public class OrderReturnReasonEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * ÍË»õÔ­ÒòÃû
	 */
	private String name;
	/**
	 * ÅÅÐò
	 */
	private Integer sort;
	/**
	 * ÆôÓÃ×´Ì¬
	 */
	private Integer status;
	/**
	 * create_time
	 */
	private Date createTime;

}
