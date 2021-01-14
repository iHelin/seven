package io.github.ihelin.seven.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;

/**
* oms_order_item
*
* @author iHelin ihelin@outlook.com
* @since 2021-01-14 14:32:50
*/
@TableName("oms_order_item")
public class OrderItemEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
        @TableId
    private Long id;
    /**
    * order_id
    */
    private Long orderId;
    /**
    * order_sn
    */
    private String orderSn;
    /**
    * spu_id
    */
    private Long spuId;
    /**
    * spu_name
    */
    private String spuName;
    /**
    * spu_pic
    */
    private String spuPic;
    /**
    * Æ·ÅÆ
    */
    private String spuBrand;
    /**
    * ÉÌÆ··ÖÀàid
    */
    private Long categoryId;
    /**
    * ÉÌÆ·sku±àºÅ
    */
    private Long skuId;
    /**
    * ÉÌÆ·skuÃû×Ö
    */
    private String skuName;
    /**
    * ÉÌÆ·skuÍ¼Æ¬
    */
    private String skuPic;
    /**
    * ÉÌÆ·sku¼Û¸ñ
    */
    private BigDecimal skuPrice;
    /**
    * ÉÌÆ·¹ºÂòµÄÊýÁ¿
    */
    private Integer skuQuantity;
    /**
    * ÉÌÆ·ÏúÊÛÊôÐÔ×éºÏ£¨JSON£©
    */
    private String skuAttrsVals;
    /**
    * ÉÌÆ·´ÙÏú·Ö½â½ð¶î
    */
    private BigDecimal promotionAmount;
    /**
    * ÓÅ»ÝÈ¯ÓÅ»Ý·Ö½â½ð¶î
    */
    private BigDecimal couponAmount;
    /**
    * »ý·ÖÓÅ»Ý·Ö½â½ð¶î
    */
    private BigDecimal integrationAmount;
    /**
    * ¸ÃÉÌÆ·¾­¹ýÓÅ»ÝºóµÄ·Ö½â½ð¶î
    */
    private BigDecimal realAmount;
    /**
    * ÔùËÍ»ý·Ö
    */
    private Integer giftIntegration;
    /**
    * ÔùËÍ³É³¤Öµ
    */
    private Integer giftGrowth;


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

    public String getOrderSn(){
        return orderSn;
    }

    public void setOrderSn(String orderSn){
        this.orderSn = orderSn;
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

    public String getSpuPic(){
        return spuPic;
    }

    public void setSpuPic(String spuPic){
        this.spuPic = spuPic;
    }

    public String getSpuBrand(){
        return spuBrand;
    }

    public void setSpuBrand(String spuBrand){
        this.spuBrand = spuBrand;
    }

    public Long getCategoryId(){
        return categoryId;
    }

    public void setCategoryId(Long categoryId){
        this.categoryId = categoryId;
    }

    public Long getSkuId(){
        return skuId;
    }

    public void setSkuId(Long skuId){
        this.skuId = skuId;
    }

    public String getSkuName(){
        return skuName;
    }

    public void setSkuName(String skuName){
        this.skuName = skuName;
    }

    public String getSkuPic(){
        return skuPic;
    }

    public void setSkuPic(String skuPic){
        this.skuPic = skuPic;
    }

    public BigDecimal getSkuPrice(){
        return skuPrice;
    }

    public void setSkuPrice(BigDecimal skuPrice){
        this.skuPrice = skuPrice;
    }

    public Integer getSkuQuantity(){
        return skuQuantity;
    }

    public void setSkuQuantity(Integer skuQuantity){
        this.skuQuantity = skuQuantity;
    }

    public String getSkuAttrsVals(){
        return skuAttrsVals;
    }

    public void setSkuAttrsVals(String skuAttrsVals){
        this.skuAttrsVals = skuAttrsVals;
    }

    public BigDecimal getPromotionAmount(){
        return promotionAmount;
    }

    public void setPromotionAmount(BigDecimal promotionAmount){
        this.promotionAmount = promotionAmount;
    }

    public BigDecimal getCouponAmount(){
        return couponAmount;
    }

    public void setCouponAmount(BigDecimal couponAmount){
        this.couponAmount = couponAmount;
    }

    public BigDecimal getIntegrationAmount(){
        return integrationAmount;
    }

    public void setIntegrationAmount(BigDecimal integrationAmount){
        this.integrationAmount = integrationAmount;
    }

    public BigDecimal getRealAmount(){
        return realAmount;
    }

    public void setRealAmount(BigDecimal realAmount){
        this.realAmount = realAmount;
    }

    public Integer getGiftIntegration(){
        return giftIntegration;
    }

    public void setGiftIntegration(Integer giftIntegration){
        this.giftIntegration = giftIntegration;
    }

    public Integer getGiftGrowth(){
        return giftGrowth;
    }

    public void setGiftGrowth(Integer giftGrowth){
        this.giftGrowth = giftGrowth;
    }

    @Override
    public String toString() {
        return "OrderItemEntity{" +
        "id='" + id + '\'' +
        "orderId='" + orderId + '\'' +
        "orderSn='" + orderSn + '\'' +
        "spuId='" + spuId + '\'' +
        "spuName='" + spuName + '\'' +
        "spuPic='" + spuPic + '\'' +
        "spuBrand='" + spuBrand + '\'' +
        "categoryId='" + categoryId + '\'' +
        "skuId='" + skuId + '\'' +
        "skuName='" + skuName + '\'' +
        "skuPic='" + skuPic + '\'' +
        "skuPrice='" + skuPrice + '\'' +
        "skuQuantity='" + skuQuantity + '\'' +
        "skuAttrsVals='" + skuAttrsVals + '\'' +
        "promotionAmount='" + promotionAmount + '\'' +
        "couponAmount='" + couponAmount + '\'' +
        "integrationAmount='" + integrationAmount + '\'' +
        "realAmount='" + realAmount + '\'' +
        "giftIntegration='" + giftIntegration + '\'' +
        "giftGrowth='" + giftGrowth + '\'' +
        '}';
    }

}
