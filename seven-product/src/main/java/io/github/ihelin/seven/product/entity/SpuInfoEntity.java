package io.github.ihelin.seven.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
* pms_spu_info
*
* @author iHelin ihelin@outlook.com
* @since 2021-01-11 11:52:40
*/
@TableName("pms_spu_info")
public class SpuInfoEntity implements Serializable {
private static final long serialVersionUID = 1L;

    /**
    * ÉÌÆ·id
    */
        @TableId
    private Long id;
    /**
    * ÉÌÆ·Ãû³Æ
    */
    private String spuName;
    /**
    * ÉÌÆ·ÃèÊö
    */
    private String spuDescription;
    /**
    * ËùÊô·ÖÀàid
    */
    private Long catalogId;
    /**
    * Æ·ÅÆid
    */
    private Long brandId;
    /**
    * 
    */
    private BigDecimal weight;
    /**
    * ÉÏ¼Ü×´Ì¬[0 - ÏÂ¼Ü£¬1 - ÉÏ¼Ü]
    */
    private Integer publishStatus;
    /**
    * 
    */
    private Date createTime;
    /**
    * 
    */
    private Date updateTime;


    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getSpuName(){
        return spuName;
    }

    public void setSpuName(String spuName){
        this.spuName = spuName;
    }

    public String getSpuDescription(){
        return spuDescription;
    }

    public void setSpuDescription(String spuDescription){
        this.spuDescription = spuDescription;
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

    public BigDecimal getWeight(){
        return weight;
    }

    public void setWeight(BigDecimal weight){
        this.weight = weight;
    }

    public Integer getPublishStatus(){
        return publishStatus;
    }

    public void setPublishStatus(Integer publishStatus){
        this.publishStatus = publishStatus;
    }

    public Date getCreateTime(){
        return createTime;
    }

    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    public Date getUpdateTime(){
        return updateTime;
    }

    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "SpuInfoEntity{" +
        "id='" + id + '\'' +
        "spuName='" + spuName + '\'' +
        "spuDescription='" + spuDescription + '\'' +
        "catalogId='" + catalogId + '\'' +
        "brandId='" + brandId + '\'' +
        "weight='" + weight + '\'' +
        "publishStatus='" + publishStatus + '\'' +
        "createTime='" + createTime + '\'' +
        "updateTime='" + updateTime + '\'' +
        '}';
    }

}
