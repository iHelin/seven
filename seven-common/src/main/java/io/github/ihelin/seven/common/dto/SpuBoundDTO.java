package io.github.ihelin.seven.common.dto;

import java.math.BigDecimal;

/**
 * @author iHelin ihelin@outlook.com
 * @since 2021/1/13 下午5:02
 */
public class SpuBoundDTO {

    private Long spuId;
    private BigDecimal buyBounds;
    private BigDecimal growBounds;

    public Long getSpuId() {
        return spuId;
    }

    public void setSpuId(Long spuId) {
        this.spuId = spuId;
    }

    public BigDecimal getBuyBounds() {
        return buyBounds;
    }

    public void setBuyBounds(BigDecimal buyBounds) {
        this.buyBounds = buyBounds;
    }

    public BigDecimal getGrowBounds() {
        return growBounds;
    }

    public void setGrowBounds(BigDecimal growBounds) {
        this.growBounds = growBounds;
    }
}
