package io.github.ihelin.seven.product.vo;

/**
 * <p>Title: Catalog3Vo</p>
 * Description：
 * date：2020/6/9 14:42
 */
public class Catalog3Vo {

	private String id;

	private String name;

	private String catalog2Id;

	public Catalog3Vo(String id, String name, String catalog2Id) {
		this.id = id;
		this.name = name;
		this.catalog2Id = catalog2Id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCatalog2Id() {
		return catalog2Id;
	}

	public void setCatalog2Id(String catalog2Id) {
		this.catalog2Id = catalog2Id;
	}
}
