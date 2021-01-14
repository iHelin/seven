package io.github.ihelin.seven.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
* sms_seckill_session
*
* @author iHelin ihelin@outlook.com
* @since 2021-01-14 14:19:56
*/
@TableName("sms_seckill_session")
public class SeckillSessionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
        @TableId
    private Long id;
    /**
    * ³¡´ÎÃû³Æ
    */
    private String name;
    /**
    * Ã¿ÈÕ¿ªÊ¼Ê±¼ä
    */
    private Date startTime;
    /**
    * Ã¿ÈÕ½áÊøÊ±¼ä
    */
    private Date endTime;
    /**
    * ÆôÓÃ×´Ì¬
    */
    private Integer status;
    /**
    * ´´½¨Ê±¼ä
    */
    private Date createTime;


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

    @Override
    public String toString() {
        return "SeckillSessionEntity{" +
        "id='" + id + '\'' +
        "name='" + name + '\'' +
        "startTime='" + startTime + '\'' +
        "endTime='" + endTime + '\'' +
        "status='" + status + '\'' +
        "createTime='" + createTime + '\'' +
        '}';
    }

}
