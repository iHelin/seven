package io.github.ihelin.seven.ware.vo;

import java.util.List;

/**
 * @author iHelin ihelin@outlook.com
 * @since 2021/1/14 上午10:26
 */
public class MergeVo {

    private Long purchaseId;
    private List<Long> items;

    public Long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public List<Long> getItems() {
        return items;
    }

    public void setItems(List<Long> items) {
        this.items = items;
    }
}
