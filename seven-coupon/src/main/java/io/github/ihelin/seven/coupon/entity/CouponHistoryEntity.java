package io.github.ihelin.seven.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
* sms_coupon_history
*
* @author iHelin ihelin@outlook.com
* @since 2021-01-14 14:19:56
*/
@TableName("sms_coupon_history")
public class CouponHistoryEntity implements Serializable {

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
    * »áÔ±id
    */
    private Long memberId;
    /**
    * »áÔ±Ãû×Ö
    */
    private String memberNickName;
    /**
    * »ñÈ¡·½Ê½[0-&gt;ºóÌ¨ÔùËÍ£»1-&gt;Ö÷¶¯ÁìÈ¡]
    */
    private Integer getType;
    /**
    * ´´½¨Ê±¼ä
    */
    private Date createTime;
    /**
    * Ê¹ÓÃ×´Ì¬[0-&gt;Î´Ê¹ÓÃ£»1-&gt;ÒÑÊ¹ÓÃ£»2-&gt;ÒÑ¹ýÆÚ]
    */
    private Integer useType;
    /**
    * Ê¹ÓÃÊ±¼ä
    */
    private Date useTime;
    /**
    * ¶©µ¥id
    */
    private Long orderId;
    /**
    * ¶©µ¥ºÅ
    */
    private Long orderSn;


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

    public Long getMemberId(){
        return memberId;
    }

    public void setMemberId(Long memberId){
        this.memberId = memberId;
    }

    public String getMemberNickName(){
        return memberNickName;
    }

    public void setMemberNickName(String memberNickName){
        this.memberNickName = memberNickName;
    }

    public Integer getGetType(){
        return getType;
    }

    public void setGetType(Integer getType){
        this.getType = getType;
    }

    public Date getCreateTime(){
        return createTime;
    }

    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    public Integer getUseType(){
        return useType;
    }

    public void setUseType(Integer useType){
        this.useType = useType;
    }

    public Date getUseTime(){
        return useTime;
    }

    public void setUseTime(Date useTime){
        this.useTime = useTime;
    }

    public Long getOrderId(){
        return orderId;
    }

    public void setOrderId(Long orderId){
        this.orderId = orderId;
    }

    public Long getOrderSn(){
        return orderSn;
    }

    public void setOrderSn(Long orderSn){
        this.orderSn = orderSn;
    }

    @Override
    public String toString() {
        return "CouponHistoryEntity{" +
        "id='" + id + '\'' +
        "couponId='" + couponId + '\'' +
        "memberId='" + memberId + '\'' +
        "memberNickName='" + memberNickName + '\'' +
        "getType='" + getType + '\'' +
        "createTime='" + createTime + '\'' +
        "useType='" + useType + '\'' +
        "useTime='" + useTime + '\'' +
        "orderId='" + orderId + '\'' +
        "orderSn='" + orderSn + '\'' +
        '}';
    }

}
