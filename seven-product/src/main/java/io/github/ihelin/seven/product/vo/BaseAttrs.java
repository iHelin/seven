package io.github.ihelin.seven.product.vo;

/**
 * @author iHelin ihelin@outlook.com
 * @since 2021/1/13 下午3:11
 */
public class BaseAttrs {

    private Long attrId;
    private String attrValues;
    private Integer showDesc;

    public Long getAttrId() {
        return attrId;
    }

    public void setAttrId(Long attrId) {
        this.attrId = attrId;
    }

    public String getAttrValues() {
        return attrValues;
    }

    public void setAttrValues(String attrValues) {
        this.attrValues = attrValues;
    }

    public Integer getShowDesc() {
        return showDesc;
    }

    public void setShowDesc(Integer showDesc) {
        this.showDesc = showDesc;
    }
}
