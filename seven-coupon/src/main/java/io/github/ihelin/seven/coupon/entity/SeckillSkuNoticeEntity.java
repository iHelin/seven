package io.github.ihelin.seven.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
* sms_seckill_sku_notice
*
* @author iHelin ihelin@outlook.com
* @since 2021-01-14 14:19:56
*/
@TableName("sms_seckill_sku_notice")
public class SeckillSkuNoticeEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
        @TableId
    private Long id;
    /**
    * member_id
    */
    private Long memberId;
    /**
    * sku_id
    */
    private Long skuId;
    /**
    * »î¶¯³¡´Îid
    */
    private Long sessionId;
    /**
    * ¶©ÔÄÊ±¼ä
    */
    private Date subcribeTime;
    /**
    * ·¢ËÍÊ±¼ä
    */
    private Date sendTime;
    /**
    * Í¨Öª·½Ê½[0-¶ÌÐÅ£¬1-ÓÊ¼þ]
    */
    private Integer noticeType;


    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getMemberId(){
        return memberId;
    }

    public void setMemberId(Long memberId){
        this.memberId = memberId;
    }

    public Long getSkuId(){
        return skuId;
    }

    public void setSkuId(Long skuId){
        this.skuId = skuId;
    }

    public Long getSessionId(){
        return sessionId;
    }

    public void setSessionId(Long sessionId){
        this.sessionId = sessionId;
    }

    public Date getSubcribeTime(){
        return subcribeTime;
    }

    public void setSubcribeTime(Date subcribeTime){
        this.subcribeTime = subcribeTime;
    }

    public Date getSendTime(){
        return sendTime;
    }

    public void setSendTime(Date sendTime){
        this.sendTime = sendTime;
    }

    public Integer getNoticeType(){
        return noticeType;
    }

    public void setNoticeType(Integer noticeType){
        this.noticeType = noticeType;
    }

    @Override
    public String toString() {
        return "SeckillSkuNoticeEntity{" +
        "id='" + id + '\'' +
        "memberId='" + memberId + '\'' +
        "skuId='" + skuId + '\'' +
        "sessionId='" + sessionId + '\'' +
        "subcribeTime='" + subcribeTime + '\'' +
        "sendTime='" + sendTime + '\'' +
        "noticeType='" + noticeType + '\'' +
        '}';
    }

}
