package io.github.ihelin.seven.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;

/**
* sms_sku_full_reduction
*
* @author iHelin ihelin@outlook.com
* @since 2021-01-14 14:19:56
*/
@TableName("sms_sku_full_reduction")
public class SkuFullReductionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
        @TableId
    private Long id;
    /**
    * spu_id
    */
    private Long skuId;
    /**
    * Âú¶àÉÙ
    */
    private BigDecimal fullPrice;
    /**
    * ¼õ¶àÉÙ
    */
    private BigDecimal reducePrice;
    /**
    * ÊÇ·ñ²ÎÓëÆäËûÓÅ»Ý
    */
    private Integer addOther;


    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getSkuId(){
        return skuId;
    }

    public void setSkuId(Long skuId){
        this.skuId = skuId;
    }

    public BigDecimal getFullPrice(){
        return fullPrice;
    }

    public void setFullPrice(BigDecimal fullPrice){
        this.fullPrice = fullPrice;
    }

    public BigDecimal getReducePrice(){
        return reducePrice;
    }

    public void setReducePrice(BigDecimal reducePrice){
        this.reducePrice = reducePrice;
    }

    public Integer getAddOther(){
        return addOther;
    }

    public void setAddOther(Integer addOther){
        this.addOther = addOther;
    }

    @Override
    public String toString() {
        return "SkuFullReductionEntity{" +
        "id='" + id + '\'' +
        "skuId='" + skuId + '\'' +
        "fullPrice='" + fullPrice + '\'' +
        "reducePrice='" + reducePrice + '\'' +
        "addOther='" + addOther + '\'' +
        '}';
    }

}
