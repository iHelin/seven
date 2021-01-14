package io.github.ihelin.seven.ware.vo;

import java.util.List;

/**
 * @author iHelin ihelin@outlook.com
 * @since 2021/1/14 上午11:54
 */
public class PurchaseDoneVo {

    private Long id;

    private List<PurchaseDoneItemVo> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<PurchaseDoneItemVo> getItems() {
        return items;
    }

    public void setItems(List<PurchaseDoneItemVo> items) {
        this.items = items;
    }
}
