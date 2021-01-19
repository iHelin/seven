package io.github.ihelin.seven.product.vo;


import java.util.List;

public class SpuItemAttrGroup{
	private String groupName;

	private List<SpuBaseAttrVo> attrs;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public List<SpuBaseAttrVo> getAttrs() {
		return attrs;
	}

	public void setAttrs(List<SpuBaseAttrVo> attrs) {
		this.attrs = attrs;
	}
}