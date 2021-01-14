package io.github.ihelin.seven.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
* sms_seckill_promotion
*
* @author iHelin ihelin@outlook.com
* @since 2021-01-14 14:19:56
*/
@TableName("sms_seckill_promotion")
public class SeckillPromotionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
        @TableId
    private Long id;
    /**
    * »î¶¯±êÌâ
    */
    private String title;
    /**
    * ¿ªÊ¼ÈÕÆÚ
    */
    private Date startTime;
    /**
    * ½áÊøÈÕÆÚ
    */
    private Date endTime;
    /**
    * ÉÏÏÂÏß×´Ì¬
    */
    private Integer status;
    /**
    * ´´½¨Ê±¼ä
    */
    private Date createTime;
    /**
    * ´´½¨ÈË
    */
    private Long userId;


    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
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

    public Date getCreateTime(){
        return createTime;
    }

    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    public Long getUserId(){
        return userId;
    }

    public void setUserId(Long userId){
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "SeckillPromotionEntity{" +
        "id='" + id + '\'' +
        "title='" + title + '\'' +
        "startTime='" + startTime + '\'' +
        "endTime='" + endTime + '\'' +
        "status='" + status + '\'' +
        "createTime='" + createTime + '\'' +
        "userId='" + userId + '\'' +
        '}';
    }

}
