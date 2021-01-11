package io.github.ihelin.seven.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
* pms_sku_images
*
* @author iHelin ihelin@outlook.com
* @since 2021-01-11 11:52:41
*/
@TableName("pms_sku_images")
public class SkuImagesEntity implements Serializable {
private static final long serialVersionUID = 1L;

    /**
    * id
    */
        @TableId
    private Long id;
    /**
    * sku_id
    */
    private Long skuId;
    /**
    * Í¼Æ¬µØÖ·
    */
    private String imgUrl;
    /**
    * ÅÅÐò
    */
    private Integer imgSort;
    /**
    * Ä¬ÈÏÍ¼[0 - ²»ÊÇÄ¬ÈÏÍ¼£¬1 - ÊÇÄ¬ÈÏÍ¼]
    */
    private Integer defaultImg;


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

    public String getImgUrl(){
        return imgUrl;
    }

    public void setImgUrl(String imgUrl){
        this.imgUrl = imgUrl;
    }

    public Integer getImgSort(){
        return imgSort;
    }

    public void setImgSort(Integer imgSort){
        this.imgSort = imgSort;
    }

    public Integer getDefaultImg(){
        return defaultImg;
    }

    public void setDefaultImg(Integer defaultImg){
        this.defaultImg = defaultImg;
    }

    @Override
    public String toString() {
        return "SkuImagesEntity{" +
        "id='" + id + '\'' +
        "skuId='" + skuId + '\'' +
        "imgUrl='" + imgUrl + '\'' +
        "imgSort='" + imgSort + '\'' +
        "defaultImg='" + defaultImg + '\'' +
        '}';
    }

}
