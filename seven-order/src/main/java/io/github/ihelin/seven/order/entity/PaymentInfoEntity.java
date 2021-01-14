package io.github.ihelin.seven.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
* oms_payment_info
*
* @author iHelin ihelin@outlook.com
* @since 2021-01-14 14:32:51
*/
@TableName("oms_payment_info")
public class PaymentInfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
        @TableId
    private Long id;
    /**
    * ¶©µ¥ºÅ£¨¶ÔÍâÒµÎñºÅ£©
    */
    private String orderSn;
    /**
    * ¶©µ¥id
    */
    private Long orderId;
    /**
    * Ö§¸¶±¦½»Ò×Á÷Ë®ºÅ
    */
    private String alipayTradeNo;
    /**
    * Ö§¸¶×Ü½ð¶î
    */
    private BigDecimal totalAmount;
    /**
    * ½»Ò×ÄÚÈÝ
    */
    private String subject;
    /**
    * Ö§¸¶×´Ì¬
    */
    private String paymentStatus;
    /**
    * ´´½¨Ê±¼ä
    */
    private Date createTime;
    /**
    * È·ÈÏÊ±¼ä
    */
    private Date confirmTime;
    /**
    * »Øµ÷ÄÚÈÝ
    */
    private String callbackContent;
    /**
    * »Øµ÷Ê±¼ä
    */
    private Date callbackTime;


    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getOrderSn(){
        return orderSn;
    }

    public void setOrderSn(String orderSn){
        this.orderSn = orderSn;
    }

    public Long getOrderId(){
        return orderId;
    }

    public void setOrderId(Long orderId){
        this.orderId = orderId;
    }

    public String getAlipayTradeNo(){
        return alipayTradeNo;
    }

    public void setAlipayTradeNo(String alipayTradeNo){
        this.alipayTradeNo = alipayTradeNo;
    }

    public BigDecimal getTotalAmount(){
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount){
        this.totalAmount = totalAmount;
    }

    public String getSubject(){
        return subject;
    }

    public void setSubject(String subject){
        this.subject = subject;
    }

    public String getPaymentStatus(){
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus){
        this.paymentStatus = paymentStatus;
    }

    public Date getCreateTime(){
        return createTime;
    }

    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    public Date getConfirmTime(){
        return confirmTime;
    }

    public void setConfirmTime(Date confirmTime){
        this.confirmTime = confirmTime;
    }

    public String getCallbackContent(){
        return callbackContent;
    }

    public void setCallbackContent(String callbackContent){
        this.callbackContent = callbackContent;
    }

    public Date getCallbackTime(){
        return callbackTime;
    }

    public void setCallbackTime(Date callbackTime){
        this.callbackTime = callbackTime;
    }

    @Override
    public String toString() {
        return "PaymentInfoEntity{" +
        "id='" + id + '\'' +
        "orderSn='" + orderSn + '\'' +
        "orderId='" + orderId + '\'' +
        "alipayTradeNo='" + alipayTradeNo + '\'' +
        "totalAmount='" + totalAmount + '\'' +
        "subject='" + subject + '\'' +
        "paymentStatus='" + paymentStatus + '\'' +
        "createTime='" + createTime + '\'' +
        "confirmTime='" + confirmTime + '\'' +
        "callbackContent='" + callbackContent + '\'' +
        "callbackTime='" + callbackTime + '\'' +
        '}';
    }

}
