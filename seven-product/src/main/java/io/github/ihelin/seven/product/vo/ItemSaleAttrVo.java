package io.github.ihelin.seven.product.vo;


import java.util.List;

public class ItemSaleAttrVo {
    private Long attrId;

    private String attrName;

	private List<AttrValueWithSkuIdVo> attrValues;

	public Long getAttrId() {
		return attrId;
	}

	public void setAttrId(Long attrId) {
		this.attrId = attrId;
	}

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public List<AttrValueWithSkuIdVo> getAttrValues() {
		return attrValues;
	}

	public void setAttrValues(List<AttrValueWithSkuIdVo> attrValues) {
		this.attrValues = attrValues;
	}
}