package io.github.ihelin.seven.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
* pms_category_brand_relation
*
* @author iHelin ihelin@outlook.com
* @since 2021-01-11 11:52:41
*/
@TableName("pms_category_brand_relation")
public class CategoryBrandRelationEntity implements Serializable {
private static final long serialVersionUID = 1L;

    /**
    * 
    */
        @TableId
    private Long id;
    /**
    * Æ·ÅÆid
    */
    private Long brandId;
    /**
    * ·ÖÀàid
    */
    private Long catelogId;
    /**
    * 
    */
    private String brandName;
    /**
    * 
    */
    private String catelogName;


    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getBrandId(){
        return brandId;
    }

    public void setBrandId(Long brandId){
        this.brandId = brandId;
    }

    public Long getCatelogId(){
        return catelogId;
    }

    public void setCatelogId(Long catelogId){
        this.catelogId = catelogId;
    }

    public String getBrandName(){
        return brandName;
    }

    public void setBrandName(String brandName){
        this.brandName = brandName;
    }

    public String getCatelogName(){
        return catelogName;
    }

    public void setCatelogName(String catelogName){
        this.catelogName = catelogName;
    }

    @Override
    public String toString() {
        return "CategoryBrandRelationEntity{" +
        "id='" + id + '\'' +
        "brandId='" + brandId + '\'' +
        "catelogId='" + catelogId + '\'' +
        "brandName='" + brandName + '\'' +
        "catelogName='" + catelogName + '\'' +
        '}';
    }

}
