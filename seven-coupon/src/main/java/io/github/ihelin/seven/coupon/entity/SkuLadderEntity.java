package io.github.ihelin.seven.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;

/**
* sms_sku_ladder
*
* @author iHelin ihelin@outlook.com
* @since 2021-01-14 14:19:56
*/
@TableName("sms_sku_ladder")
public class SkuLadderEntity implements Serializable {

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
    * Âú¼¸¼þ
    */
    private Integer fullCount;
    /**
    * ´ò¼¸ÕÛ
    */
    private BigDecimal discount;
    /**
    * ÕÛºó¼Û
    */
    private BigDecimal price;
    /**
    * ÊÇ·ñµþ¼ÓÆäËûÓÅ»Ý[0-²»¿Éµþ¼Ó£¬1-¿Éµþ¼Ó]
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

    public Integer getFullCount(){
        return fullCount;
    }

    public void setFullCount(Integer fullCount){
        this.fullCount = fullCount;
    }

    public BigDecimal getDiscount(){
        return discount;
    }

    public void setDiscount(BigDecimal discount){
        this.discount = discount;
    }

    public BigDecimal getPrice(){
        return price;
    }

    public void setPrice(BigDecimal price){
        this.price = price;
    }

    public Integer getAddOther(){
        return addOther;
    }

    public void setAddOther(Integer addOther){
        this.addOther = addOther;
    }

    @Override
    public String toString() {
        return "SkuLadderEntity{" +
        "id='" + id + '\'' +
        "skuId='" + skuId + '\'' +
        "fullCount='" + fullCount + '\'' +
        "discount='" + discount + '\'' +
        "price='" + price + '\'' +
        "addOther='" + addOther + '\'' +
        '}';
    }

}
