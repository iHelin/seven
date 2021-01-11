package io.github.ihelin.seven.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;

/**
* pms_sku_info
*
* @author iHelin ihelin@outlook.com
* @since 2021-01-11 11:52:41
*/
@TableName("pms_sku_info")
public class SkuInfoEntity implements Serializable {
private static final long serialVersionUID = 1L;

    /**
    * skuId
    */
        @TableId
    private Long skuId;
    /**
    * spuId
    */
    private Long spuId;
    /**
    * skuÃû³Æ
    */
    private String skuName;
    /**
    * sku½éÉÜÃèÊö
    */
    private String skuDesc;
    /**
    * ËùÊô·ÖÀàid
    */
    private Long catalogId;
    /**
    * Æ·ÅÆid
    */
    private Long brandId;
    /**
    * Ä¬ÈÏÍ¼Æ¬
    */
    private String skuDefaultImg;
    /**
    * ±êÌâ
    */
    private String skuTitle;
    /**
    * ¸±±êÌâ
    */
    private String skuSubtitle;
    /**
    * ¼Û¸ñ
    */
    private BigDecimal price;
    /**
    * ÏúÁ¿
    */
    private Long saleCount;


    public Long getSkuId(){
        return skuId;
    }

    public void setSkuId(Long skuId){
        this.skuId = skuId;
    }

    public Long getSpuId(){
        return spuId;
    }

    public void setSpuId(Long spuId){
        this.spuId = spuId;
    }

    public String getSkuName(){
        return skuName;
    }

    public void setSkuName(String skuName){
        this.skuName = skuName;
    }

    public String getSkuDesc(){
        return skuDesc;
    }

    public void setSkuDesc(String skuDesc){
        this.skuDesc = skuDesc;
    }

    public Long getCatalogId(){
        return catalogId;
    }

    public void setCatalogId(Long catalogId){
        this.catalogId = catalogId;
    }

    public Long getBrandId(){
        return brandId;
    }

    public void setBrandId(Long brandId){
        this.brandId = brandId;
    }

    public String getSkuDefaultImg(){
        return skuDefaultImg;
    }

    public void setSkuDefaultImg(String skuDefaultImg){
        this.skuDefaultImg = skuDefaultImg;
    }

    public String getSkuTitle(){
        return skuTitle;
    }

    public void setSkuTitle(String skuTitle){
        this.skuTitle = skuTitle;
    }

    public String getSkuSubtitle(){
        return skuSubtitle;
    }

    public void setSkuSubtitle(String skuSubtitle){
        this.skuSubtitle = skuSubtitle;
    }

    public BigDecimal getPrice(){
        return price;
    }

    public void setPrice(BigDecimal price){
        this.price = price;
    }

    public Long getSaleCount(){
        return saleCount;
    }

    public void setSaleCount(Long saleCount){
        this.saleCount = saleCount;
    }

    @Override
    public String toString() {
        return "SkuInfoEntity{" +
        "skuId='" + skuId + '\'' +
        "spuId='" + spuId + '\'' +
        "skuName='" + skuName + '\'' +
        "skuDesc='" + skuDesc + '\'' +
        "catalogId='" + catalogId + '\'' +
        "brandId='" + brandId + '\'' +
        "skuDefaultImg='" + skuDefaultImg + '\'' +
        "skuTitle='" + skuTitle + '\'' +
        "skuSubtitle='" + skuSubtitle + '\'' +
        "price='" + price + '\'' +
        "saleCount='" + saleCount + '\'' +
        '}';
    }

}
