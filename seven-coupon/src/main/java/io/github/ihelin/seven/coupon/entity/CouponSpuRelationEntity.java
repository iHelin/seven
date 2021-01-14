package io.github.ihelin.seven.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
* sms_coupon_spu_relation
*
* @author iHelin ihelin@outlook.com
* @since 2021-01-14 14:19:56
*/
@TableName("sms_coupon_spu_relation")
public class CouponSpuRelationEntity implements Serializable {

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
    * spu_id
    */
    private Long spuId;
    /**
    * spu_name
    */
    private String spuName;


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

    public Long getSpuId(){
        return spuId;
    }

    public void setSpuId(Long spuId){
        this.spuId = spuId;
    }

    public String getSpuName(){
        return spuName;
    }

    public void setSpuName(String spuName){
        this.spuName = spuName;
    }

    @Override
    public String toString() {
        return "CouponSpuRelationEntity{" +
        "id='" + id + '\'' +
        "couponId='" + couponId + '\'' +
        "spuId='" + spuId + '\'' +
        "spuName='" + spuName + '\'' +
        '}';
    }

}
