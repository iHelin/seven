package io.github.ihelin.seven.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * skuÍ¼Æ¬
 * 
 * @author iHelin
 * @email ihelin@outlook.com
 * @date 2021-01-04 22:13:30
 */
@TableName("pms_sku_images")
public class SkuImagesEntity implements Serializable {
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
	 * Í¼Æ¬µØÖ·
	 */
	private String imgUrl;
	/**
	 * ÅÅÐò
	 */
	private Integer imgSort;
	/**
	 * Ä¬ÈÏÍ¼[0 - ²»ÊÇÄ¬ÈÏÍ¼£¬1 - ÊÇÄ¬ÈÏÍ¼]
	 */
	private Integer defaultImg;

}
