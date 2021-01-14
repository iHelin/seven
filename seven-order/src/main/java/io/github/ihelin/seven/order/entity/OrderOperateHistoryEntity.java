package io.github.ihelin.seven.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
* oms_order_operate_history
*
* @author iHelin ihelin@outlook.com
* @since 2021-01-14 14:32:50
*/
@TableName("oms_order_operate_history")
public class OrderOperateHistoryEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
        @TableId
    private Long id;
    /**
    * ¶©µ¥id
    */
    private Long orderId;
    /**
    * ²Ù×÷ÈË[ÓÃ»§£»ÏµÍ³£»ºóÌ¨¹ÜÀíÔ±]
    */
    private String operateMan;
    /**
    * ²Ù×÷Ê±¼ä
    */
    private Date createTime;
    /**
    * ¶©µ¥×´Ì¬¡¾0-&gt;´ý¸¶¿î£»1-&gt;´ý·¢»õ£»2-&gt;ÒÑ·¢»õ£»3-&gt;ÒÑÍê³É£»4-&gt;ÒÑ¹Ø±Õ£»5-&gt;ÎÞÐ§¶©µ¥¡¿
    */
    private Integer orderStatus;
    /**
    * ±¸×¢
    */
    private String note;


    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getOrderId(){
        return orderId;
    }

    public void setOrderId(Long orderId){
        this.orderId = orderId;
    }

    public String getOperateMan(){
        return operateMan;
    }

    public void setOperateMan(String operateMan){
        this.operateMan = operateMan;
    }

    public Date getCreateTime(){
        return createTime;
    }

    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    public Integer getOrderStatus(){
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus){
        this.orderStatus = orderStatus;
    }

    public String getNote(){
        return note;
    }

    public void setNote(String note){
        this.note = note;
    }

    @Override
    public String toString() {
        return "OrderOperateHistoryEntity{" +
        "id='" + id + '\'' +
        "orderId='" + orderId + '\'' +
        "operateMan='" + operateMan + '\'' +
        "createTime='" + createTime + '\'' +
        "orderStatus='" + orderStatus + '\'' +
        "note='" + note + '\'' +
        '}';
    }

}
