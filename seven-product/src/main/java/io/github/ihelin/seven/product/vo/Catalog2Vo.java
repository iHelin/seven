package io.github.ihelin.seven.product.vo;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Title: Catalog2Vo</p>
 * Description：
 * date：2020/6/9 14:41
 */
public class Catalog2Vo implements Serializable {

    private String id;

    private String name;

    private String catalog1Id;

    private List<Catalog3Vo> catalog3List;

    public Catalog2Vo(String id, String name, String catalog1Id, List<Catalog3Vo> catalog3List) {
        this.id = id;
        this.name = name;
        this.catalog1Id = catalog1Id;
        this.catalog3List = catalog3List;
    }

    public Catalog2Vo() {
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

    public String getCatalog1Id() {
        return catalog1Id;
    }

    public void setCatalog1Id(String catalog1Id) {
        this.catalog1Id = catalog1Id;
    }

    public List<Catalog3Vo> getCatalog3List() {
        return catalog3List;
    }

    public void setCatalog3List(List<Catalog3Vo> catalog3List) {
        this.catalog3List = catalog3List;
    }
}
