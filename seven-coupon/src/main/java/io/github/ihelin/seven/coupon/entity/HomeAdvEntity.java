package io.github.ihelin.seven.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
* sms_home_adv
*
* @author iHelin ihelin@outlook.com
* @since 2021-01-14 14:19:56
*/
@TableName("sms_home_adv")
public class HomeAdvEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
        @TableId
    private Long id;
    /**
    * Ãû×Ö
    */
    private String name;
    /**
    * Í¼Æ¬µØÖ·
    */
    private String pic;
    /**
    * ¿ªÊ¼Ê±¼ä
    */
    private Date startTime;
    /**
    * ½áÊøÊ±¼ä
    */
    private Date endTime;
    /**
    * ×´Ì¬
    */
    private Integer status;
    /**
    * µã»÷Êý
    */
    private Integer clickCount;
    /**
    * ¹ã¸æÏêÇéÁ¬½ÓµØÖ·
    */
    private String url;
    /**
    * ±¸×¢
    */
    private String note;
    /**
    * ÅÅÐò
    */
    private Integer sort;
    /**
    * ·¢²¼Õß
    */
    private Long publisherId;
    /**
    * ÉóºËÕß
    */
    private Long authId;


    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPic(){
        return pic;
    }

    public void setPic(String pic){
        this.pic = pic;
    }

    public Date getStartTime(){
        return startTime;
    }

    public void setStartTime(Date startTime){
        this.startTime = startTime;
    }

    public Date getEndTime(){
        return endTime;
    }

    public void setEndTime(Date endTime){
        this.endTime = endTime;
    }

    public Integer getStatus(){
        return status;
    }

    public void setStatus(Integer status){
        this.status = status;
    }

    public Integer getClickCount(){
        return clickCount;
    }

    public void setClickCount(Integer clickCount){
        this.clickCount = clickCount;
    }

    public String getUrl(){
        return url;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public String getNote(){
        return note;
    }

    public void setNote(String note){
        this.note = note;
    }

    public Integer getSort(){
        return sort;
    }

    public void setSort(Integer sort){
        this.sort = sort;
    }

    public Long getPublisherId(){
        return publisherId;
    }

    public void setPublisherId(Long publisherId){
        this.publisherId = publisherId;
    }

    public Long getAuthId(){
        return authId;
    }

    public void setAuthId(Long authId){
        this.authId = authId;
    }

    @Override
    public String toString() {
        return "HomeAdvEntity{" +
        "id='" + id + '\'' +
        "name='" + name + '\'' +
        "pic='" + pic + '\'' +
        "startTime='" + startTime + '\'' +
        "endTime='" + endTime + '\'' +
        "status='" + status + '\'' +
        "clickCount='" + clickCount + '\'' +
        "url='" + url + '\'' +
        "note='" + note + '\'' +
        "sort='" + sort + '\'' +
        "publisherId='" + publisherId + '\'' +
        "authId='" + authId + '\'' +
        '}';
    }

}
