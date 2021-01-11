package io.github.ihelin.seven.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
* pms_spu_images
*
* @author iHelin ihelin@outlook.com
* @since 2021-01-11 11:52:40
*/
@TableName("pms_spu_images")
public class SpuImagesEntity implements Serializable {
private static final long serialVersionUID = 1L;

    /**
    * id
    */
        @TableId
    private Long id;
    /**
    * spu_id
    */
    private Long spuId;
    /**
    * Í¼Æ¬Ãû
    */
    private String imgName;
    /**
    * Í¼Æ¬µØÖ·
    */
    private String imgUrl;
    /**
    * Ë³Ðò
    */
    private Integer imgSort;
    /**
    * ÊÇ·ñÄ¬ÈÏÍ¼
    */
    private Integer defaultImg;


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

    public String getImgName(){
        return imgName;
    }

    public void setImgName(String imgName){
        this.imgName = imgName;
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
        return "SpuImagesEntity{" +
        "id='" + id + '\'' +
        "spuId='" + spuId + '\'' +
        "imgName='" + imgName + '\'' +
        "imgUrl='" + imgUrl + '\'' +
        "imgSort='" + imgSort + '\'' +
        "defaultImg='" + defaultImg + '\'' +
        '}';
    }

}
