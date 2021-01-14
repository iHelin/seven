package io.github.ihelin.seven.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
* oms_order
*
* @author iHelin ihelin@outlook.com
* @since 2021-01-14 14:32:50
*/
@TableName("oms_order")
public class OrderEntity implements Serializable {

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
    * ¶©µ¥ºÅ
    */
    private String orderSn;
    /**
    * Ê¹ÓÃµÄÓÅ»ÝÈ¯
    */
    private Long couponId;
    /**
    * create_time
    */
    private Date createTime;
    /**
    * ÓÃ»§Ãû
    */
    private String memberUsername;
    /**
    * ¶©µ¥×Ü¶î
    */
    private BigDecimal totalAmount;
    /**
    * Ó¦¸¶×Ü¶î
    */
    private BigDecimal payAmount;
    /**
    * ÔË·Ñ½ð¶î
    */
    private BigDecimal freightAmount;
    /**
    * ´ÙÏúÓÅ»¯½ð¶î£¨´ÙÏú¼Û¡¢Âú¼õ¡¢½×ÌÝ¼Û£©
    */
    private BigDecimal promotionAmount;
    /**
    * »ý·ÖµÖ¿Û½ð¶î
    */
    private BigDecimal integrationAmount;
    /**
    * ÓÅ»ÝÈ¯µÖ¿Û½ð¶î
    */
    private BigDecimal couponAmount;
    /**
    * ºóÌ¨µ÷Õû¶©µ¥Ê¹ÓÃµÄÕÛ¿Û½ð¶î
    */
    private BigDecimal discountAmount;
    /**
    * Ö§¸¶·½Ê½¡¾1-&gt;Ö§¸¶±¦£»2-&gt;Î¢ÐÅ£»3-&gt;ÒøÁª£» 4-&gt;»õµ½¸¶¿î£»¡¿
    */
    private Integer payType;
    /**
    * ¶©µ¥À´Ô´[0-&gt;PC¶©µ¥£»1-&gt;app¶©µ¥]
    */
    private Integer sourceType;
    /**
    * ¶©µ¥×´Ì¬¡¾0-&gt;´ý¸¶¿î£»1-&gt;´ý·¢»õ£»2-&gt;ÒÑ·¢»õ£»3-&gt;ÒÑÍê³É£»4-&gt;ÒÑ¹Ø±Õ£»5-&gt;ÎÞÐ§¶©µ¥¡¿
    */
    private Integer status;
    /**
    * ÎïÁ÷¹«Ë¾(ÅäËÍ·½Ê½)
    */
    private String deliveryCompany;
    /**
    * ÎïÁ÷µ¥ºÅ
    */
    private String deliverySn;
    /**
    * ×Ô¶¯È·ÈÏÊ±¼ä£¨Ìì£©
    */
    private Integer autoConfirmDay;
    /**
    * ¿ÉÒÔ»ñµÃµÄ»ý·Ö
    */
    private Integer integration;
    /**
    * ¿ÉÒÔ»ñµÃµÄ³É³¤Öµ
    */
    private Integer growth;
    /**
    * ·¢Æ±ÀàÐÍ[0-&gt;²»¿ª·¢Æ±£»1-&gt;µç×Ó·¢Æ±£»2-&gt;Ö½ÖÊ·¢Æ±]
    */
    private Integer billType;
    /**
    * ·¢Æ±Ì§Í·
    */
    private String billHeader;
    /**
    * ·¢Æ±ÄÚÈÝ
    */
    private String billContent;
    /**
    * ÊÕÆ±ÈËµç»°
    */
    private String billReceiverPhone;
    /**
    * ÊÕÆ±ÈËÓÊÏä
    */
    private String billReceiverEmail;
    /**
    * ÊÕ»õÈËÐÕÃû
    */
    private String receiverName;
    /**
    * ÊÕ»õÈËµç»°
    */
    private String receiverPhone;
    /**
    * ÊÕ»õÈËÓÊ±à
    */
    private String receiverPostCode;
    /**
    * Ê¡·Ý/Ö±Ï½ÊÐ
    */
    private String receiverProvince;
    /**
    * ³ÇÊÐ
    */
    private String receiverCity;
    /**
    * Çø
    */
    private String receiverRegion;
    /**
    * ÏêÏ¸µØÖ·
    */
    private String receiverDetailAddress;
    /**
    * ¶©µ¥±¸×¢
    */
    private String note;
    /**
    * È·ÈÏÊÕ»õ×´Ì¬[0-&gt;Î´È·ÈÏ£»1-&gt;ÒÑÈ·ÈÏ]
    */
    private Integer confirmStatus;
    /**
    * É¾³ý×´Ì¬¡¾0-&gt;Î´É¾³ý£»1-&gt;ÒÑÉ¾³ý¡¿
    */
    private Integer deleteStatus;
    /**
    * ÏÂµ¥Ê±Ê¹ÓÃµÄ»ý·Ö
    */
    private Integer useIntegration;
    /**
    * Ö§¸¶Ê±¼ä
    */
    private Date paymentTime;
    /**
    * ·¢»õÊ±¼ä
    */
    private Date deliveryTime;
    /**
    * È·ÈÏÊÕ»õÊ±¼ä
    */
    private Date receiveTime;
    /**
    * ÆÀ¼ÛÊ±¼ä
    */
    private Date commentTime;
    /**
    * ÐÞ¸ÄÊ±¼ä
    */
    private Date modifyTime;


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

    public String getOrderSn(){
        return orderSn;
    }

    public void setOrderSn(String orderSn){
        this.orderSn = orderSn;
    }

    public Long getCouponId(){
        return couponId;
    }

    public void setCouponId(Long couponId){
        this.couponId = couponId;
    }

    public Date getCreateTime(){
        return createTime;
    }

    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    public String getMemberUsername(){
        return memberUsername;
    }

    public void setMemberUsername(String memberUsername){
        this.memberUsername = memberUsername;
    }

    public BigDecimal getTotalAmount(){
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount){
        this.totalAmount = totalAmount;
    }

    public BigDecimal getPayAmount(){
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount){
        this.payAmount = payAmount;
    }

    public BigDecimal getFreightAmount(){
        return freightAmount;
    }

    public void setFreightAmount(BigDecimal freightAmount){
        this.freightAmount = freightAmount;
    }

    public BigDecimal getPromotionAmount(){
        return promotionAmount;
    }

    public void setPromotionAmount(BigDecimal promotionAmount){
        this.promotionAmount = promotionAmount;
    }

    public BigDecimal getIntegrationAmount(){
        return integrationAmount;
    }

    public void setIntegrationAmount(BigDecimal integrationAmount){
        this.integrationAmount = integrationAmount;
    }

    public BigDecimal getCouponAmount(){
        return couponAmount;
    }

    public void setCouponAmount(BigDecimal couponAmount){
        this.couponAmount = couponAmount;
    }

    public BigDecimal getDiscountAmount(){
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount){
        this.discountAmount = discountAmount;
    }

    public Integer getPayType(){
        return payType;
    }

    public void setPayType(Integer payType){
        this.payType = payType;
    }

    public Integer getSourceType(){
        return sourceType;
    }

    public void setSourceType(Integer sourceType){
        this.sourceType = sourceType;
    }

    public Integer getStatus(){
        return status;
    }

    public void setStatus(Integer status){
        this.status = status;
    }

    public String getDeliveryCompany(){
        return deliveryCompany;
    }

    public void setDeliveryCompany(String deliveryCompany){
        this.deliveryCompany = deliveryCompany;
    }

    public String getDeliverySn(){
        return deliverySn;
    }

    public void setDeliverySn(String deliverySn){
        this.deliverySn = deliverySn;
    }

    public Integer getAutoConfirmDay(){
        return autoConfirmDay;
    }

    public void setAutoConfirmDay(Integer autoConfirmDay){
        this.autoConfirmDay = autoConfirmDay;
    }

    public Integer getIntegration(){
        return integration;
    }

    public void setIntegration(Integer integration){
        this.integration = integration;
    }

    public Integer getGrowth(){
        return growth;
    }

    public void setGrowth(Integer growth){
        this.growth = growth;
    }

    public Integer getBillType(){
        return billType;
    }

    public void setBillType(Integer billType){
        this.billType = billType;
    }

    public String getBillHeader(){
        return billHeader;
    }

    public void setBillHeader(String billHeader){
        this.billHeader = billHeader;
    }

    public String getBillContent(){
        return billContent;
    }

    public void setBillContent(String billContent){
        this.billContent = billContent;
    }

    public String getBillReceiverPhone(){
        return billReceiverPhone;
    }

    public void setBillReceiverPhone(String billReceiverPhone){
        this.billReceiverPhone = billReceiverPhone;
    }

    public String getBillReceiverEmail(){
        return billReceiverEmail;
    }

    public void setBillReceiverEmail(String billReceiverEmail){
        this.billReceiverEmail = billReceiverEmail;
    }

    public String getReceiverName(){
        return receiverName;
    }

    public void setReceiverName(String receiverName){
        this.receiverName = receiverName;
    }

    public String getReceiverPhone(){
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone){
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverPostCode(){
        return receiverPostCode;
    }

    public void setReceiverPostCode(String receiverPostCode){
        this.receiverPostCode = receiverPostCode;
    }

    public String getReceiverProvince(){
        return receiverProvince;
    }

    public void setReceiverProvince(String receiverProvince){
        this.receiverProvince = receiverProvince;
    }

    public String getReceiverCity(){
        return receiverCity;
    }

    public void setReceiverCity(String receiverCity){
        this.receiverCity = receiverCity;
    }

    public String getReceiverRegion(){
        return receiverRegion;
    }

    public void setReceiverRegion(String receiverRegion){
        this.receiverRegion = receiverRegion;
    }

    public String getReceiverDetailAddress(){
        return receiverDetailAddress;
    }

    public void setReceiverDetailAddress(String receiverDetailAddress){
        this.receiverDetailAddress = receiverDetailAddress;
    }

    public String getNote(){
        return note;
    }

    public void setNote(String note){
        this.note = note;
    }

    public Integer getConfirmStatus(){
        return confirmStatus;
    }

    public void setConfirmStatus(Integer confirmStatus){
        this.confirmStatus = confirmStatus;
    }

    public Integer getDeleteStatus(){
        return deleteStatus;
    }

    public void setDeleteStatus(Integer deleteStatus){
        this.deleteStatus = deleteStatus;
    }

    public Integer getUseIntegration(){
        return useIntegration;
    }

    public void setUseIntegration(Integer useIntegration){
        this.useIntegration = useIntegration;
    }

    public Date getPaymentTime(){
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime){
        this.paymentTime = paymentTime;
    }

    public Date getDeliveryTime(){
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime){
        this.deliveryTime = deliveryTime;
    }

    public Date getReceiveTime(){
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime){
        this.receiveTime = receiveTime;
    }

    public Date getCommentTime(){
        return commentTime;
    }

    public void setCommentTime(Date commentTime){
        this.commentTime = commentTime;
    }

    public Date getModifyTime(){
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime){
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
        "id='" + id + '\'' +
        "memberId='" + memberId + '\'' +
        "orderSn='" + orderSn + '\'' +
        "couponId='" + couponId + '\'' +
        "createTime='" + createTime + '\'' +
        "memberUsername='" + memberUsername + '\'' +
        "totalAmount='" + totalAmount + '\'' +
        "payAmount='" + payAmount + '\'' +
        "freightAmount='" + freightAmount + '\'' +
        "promotionAmount='" + promotionAmount + '\'' +
        "integrationAmount='" + integrationAmount + '\'' +
        "couponAmount='" + couponAmount + '\'' +
        "discountAmount='" + discountAmount + '\'' +
        "payType='" + payType + '\'' +
        "sourceType='" + sourceType + '\'' +
        "status='" + status + '\'' +
        "deliveryCompany='" + deliveryCompany + '\'' +
        "deliverySn='" + deliverySn + '\'' +
        "autoConfirmDay='" + autoConfirmDay + '\'' +
        "integration='" + integration + '\'' +
        "growth='" + growth + '\'' +
        "billType='" + billType + '\'' +
        "billHeader='" + billHeader + '\'' +
        "billContent='" + billContent + '\'' +
        "billReceiverPhone='" + billReceiverPhone + '\'' +
        "billReceiverEmail='" + billReceiverEmail + '\'' +
        "receiverName='" + receiverName + '\'' +
        "receiverPhone='" + receiverPhone + '\'' +
        "receiverPostCode='" + receiverPostCode + '\'' +
        "receiverProvince='" + receiverProvince + '\'' +
        "receiverCity='" + receiverCity + '\'' +
        "receiverRegion='" + receiverRegion + '\'' +
        "receiverDetailAddress='" + receiverDetailAddress + '\'' +
        "note='" + note + '\'' +
        "confirmStatus='" + confirmStatus + '\'' +
        "deleteStatus='" + deleteStatus + '\'' +
        "useIntegration='" + useIntegration + '\'' +
        "paymentTime='" + paymentTime + '\'' +
        "deliveryTime='" + deliveryTime + '\'' +
        "receiveTime='" + receiveTime + '\'' +
        "commentTime='" + commentTime + '\'' +
        "modifyTime='" + modifyTime + '\'' +
        '}';
    }

}
