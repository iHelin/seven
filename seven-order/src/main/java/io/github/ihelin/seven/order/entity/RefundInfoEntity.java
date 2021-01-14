package io.github.ihelin.seven.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;

/**
* oms_refund_info
*
* @author iHelin ihelin@outlook.com
* @since 2021-01-14 14:32:51
*/
@TableName("oms_refund_info")
public class RefundInfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
        @TableId
    private Long id;
    /**
    * ÍË¿îµÄ¶©µ¥
    */
    private Long orderReturnId;
    /**
    * ÍË¿î½ð¶î
    */
    private BigDecimal refund;
    /**
    * ÍË¿î½»Ò×Á÷Ë®ºÅ
    */
    private String refundSn;
    /**
    * ÍË¿î×´Ì¬
    */
    private Integer refundStatus;
    /**
    * ÍË¿îÇþµÀ[1-Ö§¸¶±¦£¬2-Î¢ÐÅ£¬3-ÒøÁª£¬4-»ã¿î]
    */
    private Integer refundChannel;
    /**
    * 
    */
    private String refundContent;


    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getOrderReturnId(){
        return orderReturnId;
    }

    public void setOrderReturnId(Long orderReturnId){
        this.orderReturnId = orderReturnId;
    }

    public BigDecimal getRefund(){
        return refund;
    }

    public void setRefund(BigDecimal refund){
        this.refund = refund;
    }

    public String getRefundSn(){
        return refundSn;
    }

    public void setRefundSn(String refundSn){
        this.refundSn = refundSn;
    }

    public Integer getRefundStatus(){
        return refundStatus;
    }

    public void setRefundStatus(Integer refundStatus){
        this.refundStatus = refundStatus;
    }

    public Integer getRefundChannel(){
        return refundChannel;
    }

    public void setRefundChannel(Integer refundChannel){
        this.refundChannel = refundChannel;
    }

    public String getRefundContent(){
        return refundContent;
    }

    public void setRefundContent(String refundContent){
        this.refundContent = refundContent;
    }

    @Override
    public String toString() {
        return "RefundInfoEntity{" +
        "id='" + id + '\'' +
        "orderReturnId='" + orderReturnId + '\'' +
        "refund='" + refund + '\'' +
        "refundSn='" + refundSn + '\'' +
        "refundStatus='" + refundStatus + '\'' +
        "refundChannel='" + refundChannel + '\'' +
        "refundContent='" + refundContent + '\'' +
        '}';
    }

}
