package io.github.ihelin.seven.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
* pms_sku_sale_attr_value
*
* @author iHelin ihelin@outlook.com
* @since 2021-01-11 11:52:40
*/
@TableName("pms_sku_sale_attr_value")
public class SkuSaleAttrValueEntity implements Serializable {
private static final long serialVersionUID = 1L;

    /**
    * id
    */
        @TableId
    private Long id;
    /**
    * sku_id
    */
    private Long skuId;
    /**
    * attr_id
    */
    private Long attrId;
    /**
    * ÏúÊÛÊôÐÔÃû
    */
    private String attrName;
    /**
    * ÏúÊÛÊôÐÔÖµ
    */
    private String attrValue;
    /**
    * Ë³Ðò
    */
    private Integer attrSort;


    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getSkuId(){
        return skuId;
    }

    public void setSkuId(Long skuId){
        this.skuId = skuId;
    }

    public Long getAttrId(){
        return attrId;
    }

    public void setAttrId(Long attrId){
        this.attrId = attrId;
    }

    public String getAttrName(){
        return attrName;
    }

    public void setAttrName(String attrName){
        this.attrName = attrName;
    }

    public String getAttrValue(){
        return attrValue;
    }

    public void setAttrValue(String attrValue){
        this.attrValue = attrValue;
    }

    public Integer getAttrSort(){
        return attrSort;
    }

    public void setAttrSort(Integer attrSort){
        this.attrSort = attrSort;
    }

    @Override
    public String toString() {
        return "SkuSaleAttrValueEntity{" +
        "id='" + id + '\'' +
        "skuId='" + skuId + '\'' +
        "attrId='" + attrId + '\'' +
        "attrName='" + attrName + '\'' +
        "attrValue='" + attrValue + '\'' +
        "attrSort='" + attrSort + '\'' +
        '}';
    }

}
