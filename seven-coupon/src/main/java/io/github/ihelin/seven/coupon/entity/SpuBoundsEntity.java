package io.github.ihelin.seven.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;

/**
* sms_spu_bounds
*
* @author iHelin ihelin@outlook.com
* @since 2021-01-14 14:19:57
*/
@TableName("sms_spu_bounds")
public class SpuBoundsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
        @TableId
    private Long id;
    /**
    * 
    */
    private Long spuId;
    /**
    * ³É³¤»ý·Ö
    */
    private BigDecimal growBounds;
    /**
    * ¹ºÎï»ý·Ö
    */
    private BigDecimal buyBounds;
    /**
    * ÓÅ»ÝÉúÐ§Çé¿ö[1111£¨ËÄ¸ö×´Ì¬Î»£¬´ÓÓÒµ½×ó£©;0 - ÎÞÓÅ»Ý£¬³É³¤»ý·ÖÊÇ·ñÔùËÍ;1 - ÎÞÓÅ»Ý£¬¹ºÎï»ý·ÖÊÇ·ñÔùËÍ;2 - ÓÐÓÅ»Ý£¬³É³¤»ý·ÖÊÇ·ñÔùËÍ;3 - ÓÐÓÅ»Ý£¬¹ºÎï»ý·ÖÊÇ·ñÔùËÍ¡¾×´Ì¬Î»0£º²»ÔùËÍ£¬1£ºÔùËÍ¡¿]
    */
    private Integer work;


    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getSpuId(){
        return spuId;
    }

    public void setSpuId(Long spuId){
        this.spuId = spuId;
    }

    public BigDecimal getGrowBounds(){
        return growBounds;
    }

    public void setGrowBounds(BigDecimal growBounds){
        this.growBounds = growBounds;
    }

    public BigDecimal getBuyBounds(){
        return buyBounds;
    }

    public void setBuyBounds(BigDecimal buyBounds){
        this.buyBounds = buyBounds;
    }

    public Integer getWork(){
        return work;
    }

    public void setWork(Integer work){
        this.work = work;
    }

    @Override
    public String toString() {
        return "SpuBoundsEntity{" +
        "id='" + id + '\'' +
        "spuId='" + spuId + '\'' +
        "growBounds='" + growBounds + '\'' +
        "buyBounds='" + buyBounds + '\'' +
        "work='" + work + '\'' +
        '}';
    }

}
