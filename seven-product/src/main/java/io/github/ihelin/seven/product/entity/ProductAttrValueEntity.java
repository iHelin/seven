package io.github.ihelin.seven.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
* pms_product_attr_value
*
* @author iHelin ihelin@outlook.com
* @since 2021-01-11 11:52:41
*/
@TableName("pms_product_attr_value")
public class ProductAttrValueEntity implements Serializable {
private static final long serialVersionUID = 1L;

    /**
    * id
    */
        @TableId
    private Long id;
    /**
    * ÉÌÆ·id
    */
    private Long spuId;
    /**
    * ÊôÐÔid
    */
    private Long attrId;
    /**
    * ÊôÐÔÃû
    */
    private String attrName;
    /**
    * ÊôÐÔÖµ
    */
    private String attrValue;
    /**
    * Ë³Ðò
    */
    private Integer attrSort;
    /**
    * ¿ìËÙÕ¹Ê¾¡¾ÊÇ·ñÕ¹Ê¾ÔÚ½éÉÜÉÏ£»0-·ñ 1-ÊÇ¡¿
    */
    private Integer quickShow;


    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getSpuId(){
        return spuId;
    }

    public void setSpuId(Long spuId){
        this.spuId = spuId;
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

    public Integer getQuickShow(){
        return quickShow;
    }

    public void setQuickShow(Integer quickShow){
        this.quickShow = quickShow;
    }

    @Override
    public String toString() {
        return "ProductAttrValueEntity{" +
        "id='" + id + '\'' +
        "spuId='" + spuId + '\'' +
        "attrId='" + attrId + '\'' +
        "attrName='" + attrName + '\'' +
        "attrValue='" + attrValue + '\'' +
        "attrSort='" + attrSort + '\'' +
        "quickShow='" + quickShow + '\'' +
        '}';
    }

}
