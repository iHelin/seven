package io.github.ihelin.seven.product.vo;

import java.math.BigDecimal;

/**
 * @author iHelin ihelin@outlook.com
 * @since 2021/1/13 下午3:12
 */
public class MemberPrice {

    private Long id;
    private String name;
    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
