package io.github.ihelin.seven.common.dto.mq;

public class StockLockedTo {

    /**
     * 库存工作单id
     */
    private Long id;
    /**
     * 工作详情id
     */
    private StockDetailTo detailTo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StockDetailTo getDetailTo() {
        return detailTo;
    }

    public void setDetailTo(StockDetailTo detailTo) {
        this.detailTo = detailTo;
    }
}
