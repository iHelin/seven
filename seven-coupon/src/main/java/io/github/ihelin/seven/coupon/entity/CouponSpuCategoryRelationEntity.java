package io.github.ihelin.seven.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
* sms_coupon_spu_category_relation
*
* @author iHelin ihelin@outlook.com
* @since 2021-01-14 14:19:56
*/
@TableName("sms_coupon_spu_category_relation")
public class CouponSpuCategoryRelationEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
        @TableId
    private Long id;
    /**
    * ÓÅ»ÝÈ¯id
    */
    private Long couponId;
    /**
    * ²úÆ··ÖÀàid
    */
    private Long categoryId;
    /**
    * ²úÆ··ÖÀàÃû³Æ
    */
    private String categoryName;


    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getCouponId(){
        return couponId;
    }

    public void setCouponId(Long couponId){
        this.couponId = couponId;
    }

    public Long getCategoryId(){
        return categoryId;
    }

    public void setCategoryId(Long categoryId){
        this.categoryId = categoryId;
    }

    public String getCategoryName(){
        return categoryName;
    }

    public void setCategoryName(String categoryName){
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "CouponSpuCategoryRelationEntity{" +
        "id='" + id + '\'' +
        "couponId='" + couponId + '\'' +
        "categoryId='" + categoryId + '\'' +
        "categoryName='" + categoryName + '\'' +
        '}';
    }

}
