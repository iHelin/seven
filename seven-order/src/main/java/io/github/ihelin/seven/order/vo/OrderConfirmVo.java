package io.github.ihelin.seven.order.vo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class OrderConfirmVo {

    /**
     * 收获地址
     */
    List<MemberAddressVo> address;
    /**
     * 所有选中的购物项
     */
    List<OrderItemVo> items;
    /**
     * 积分信息
     */
    private Integer integration;
    /**
     * 防重令牌
     */
    private String orderToken;
    Map<Long, Boolean> stocks;

    /**
     * 获取商品总价格
     */
    public BigDecimal getTotal() {
        BigDecimal sum = BigDecimal.ZERO;
        if (items != null) {
            for (OrderItemVo item : items) {
                sum = sum.add(item.getPrice().multiply(BigDecimal.valueOf(item.getCount())));
            }
        }
        return sum;
    }

    /**
     * 应付的价格
     */
    public BigDecimal getPayPrice() {
        return getTotal();
    }

    public Integer getCount() {
        Integer i = 0;
        if (items != null) {
            for (OrderItemVo item : items) {
                i += item.getCount();
            }
        }
        return i;
    }

    public List<MemberAddressVo> getAddress() {
        return address;
    }

    public void setAddress(List<MemberAddressVo> address) {
        this.address = address;
    }

    public List<OrderItemVo> getItems() {
        return items;
    }

    public void setItems(List<OrderItemVo> items) {
        this.items = items;
    }

    public Integer getIntegration() {
        return integration;
    }

    public void setIntegration(Integer integration) {
        this.integration = integration;
    }

    public String getOrderToken() {
        return orderToken;
    }

    public void setOrderToken(String orderToken) {
        this.orderToken = orderToken;
    }

    public Map<Long, Boolean> getStocks() {
        return stocks;
    }

    public void setStocks(Map<Long, Boolean> stocks) {
        this.stocks = stocks;
    }
}
