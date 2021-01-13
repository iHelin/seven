package io.github.ihelin.seven.product.vo;

import java.math.BigDecimal;

/**
 * @author iHelin ihelin@outlook.com
 * @since 2021/1/13 下午3:11
 */
public class Bounds {

    private BigDecimal buyBounds;
    private BigDecimal growBounds;

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
