package io.github.ihelin.seven.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * skuÐÅÏ¢
 * 
 * @author iHelin
 * @email ihelin@outlook.com
 * @date 2021-01-04 22:13:30
 */
@TableName("pms_sku_info")
public class SkuInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * skuId
	 */
	@TableId
	private Long skuId;
	/**
	 * spuId
	 */
	private Long spuId;
	/**
	 * skuÃû³Æ
	 */
	private String skuName;
	/**
	 * sku½éÉÜÃèÊö
	 */
	private String skuDesc;
	/**
	 * ËùÊô·ÖÀàid
	 */
	private Long catalogId;
	/**
	 * Æ·ÅÆid
	 */
	private Long brandId;
	/**
	 * Ä¬ÈÏÍ¼Æ¬
	 */
	private String skuDefaultImg;
	/**
	 * ±êÌâ
	 */
	private String skuTitle;
	/**
	 * ¸±±êÌâ
	 */
	private String skuSubtitle;
	/**
	 * ¼Û¸ñ
	 */
	private BigDecimal price;
	/**
	 * ÏúÁ¿
	 */
	private Long saleCount;

}
