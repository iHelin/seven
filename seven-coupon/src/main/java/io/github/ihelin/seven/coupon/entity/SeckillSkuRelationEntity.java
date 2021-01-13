package io.github.ihelin.seven.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author iHelin ihelin@outlook.com
 * @since 2021-01-05 12:40:34
 */
@TableName("sms_seckill_sku_relation")
public class SeckillSkuRelationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	private Long promotionId;
	private Long promotionSessionId;
	private Long skuId;
	private BigDecimal seckillPrice;
	private BigDecimal seckillCount;
	private BigDecimal seckillLimit;
	private Integer seckillSort;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(Long promotionId) {
		this.promotionId = promotionId;
	}

	public Long getPromotionSessionId() {
		return promotionSessionId;
	}

	public void setPromotionSessionId(Long promotionSessionId) {
		this.promotionSessionId = promotionSessionId;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public BigDecimal getSeckillPrice() {
		return seckillPrice;
	}

	public void setSeckillPrice(BigDecimal seckillPrice) {
		this.seckillPrice = seckillPrice;
	}

	public BigDecimal getSeckillCount() {
		return seckillCount;
	}

	public void setSeckillCount(BigDecimal seckillCount) {
		this.seckillCount = seckillCount;
	}

	public BigDecimal getSeckillLimit() {
		return seckillLimit;
	}

	public void setSeckillLimit(BigDecimal seckillLimit) {
		this.seckillLimit = seckillLimit;
	}

	public Integer getSeckillSort() {
		return seckillSort;
	}

	public void setSeckillSort(Integer seckillSort) {
		this.seckillSort = seckillSort;
	}
}
