package io.github.ihelin.seven.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
* pms_attr
*
* @author iHelin ihelin@outlook.com
* @since 2021-01-11 11:52:41
*/
@TableName("pms_attr")
public class AttrEntity implements Serializable {
private static final long serialVersionUID = 1L;

    /**
    * ÊôÐÔid
    */
        @TableId
    private Long attrId;
    /**
    * ÊôÐÔÃû
    */
    private String attrName;
    /**
    * ÊÇ·ñÐèÒª¼ìË÷[0-²»ÐèÒª£¬1-ÐèÒª]
    */
    private Integer searchType;
    /**
    * ÊôÐÔÍ¼±ê
    */
    private String icon;
    /**
    * ¿ÉÑ¡ÖµÁÐ±í[ÓÃ¶ººÅ·Ö¸ô]
    */
    private String valueSelect;
    /**
    * ÊôÐÔÀàÐÍ[0-ÏúÊÛÊôÐÔ£¬1-»ù±¾ÊôÐÔ£¬2-¼ÈÊÇÏúÊÛÊôÐÔÓÖÊÇ»ù±¾ÊôÐÔ]
    */
    private Integer attrType;
    /**
    * ÆôÓÃ×´Ì¬[0 - ½ûÓÃ£¬1 - ÆôÓÃ]
    */
    private Long enable;
    /**
    * ËùÊô·ÖÀà
    */
    private Long catelogId;
    /**
    * ¿ìËÙÕ¹Ê¾¡¾ÊÇ·ñÕ¹Ê¾ÔÚ½éÉÜÉÏ£»0-·ñ 1-ÊÇ¡¿£¬ÔÚskuÖÐÈÔÈ»¿ÉÒÔµ÷Õû
    */
    private Integer showDesc;


    public Long getAttrId(){
        return attrId;
    }

    public void setAttrId(Long attrId){
        this.attrId = attrId;
    }

    public String getAttrName(){
        return attrName;
    }

    public void setAttrName(String attrName){
        this.attrName = attrName;
    }

    public Integer getSearchType(){
        return searchType;
    }

    public void setSearchType(Integer searchType){
        this.searchType = searchType;
    }

    public String getIcon(){
        return icon;
    }

    public void setIcon(String icon){
        this.icon = icon;
    }

    public String getValueSelect(){
        return valueSelect;
    }

    public void setValueSelect(String valueSelect){
        this.valueSelect = valueSelect;
    }

    public Integer getAttrType(){
        return attrType;
    }

    public void setAttrType(Integer attrType){
        this.attrType = attrType;
    }

    public Long getEnable(){
        return enable;
    }

    public void setEnable(Long enable){
        this.enable = enable;
    }

    public Long getCatelogId(){
        return catelogId;
    }

    public void setCatelogId(Long catelogId){
        this.catelogId = catelogId;
    }

    public Integer getShowDesc(){
        return showDesc;
    }

    public void setShowDesc(Integer showDesc){
        this.showDesc = showDesc;
    }

    @Override
    public String toString() {
        return "AttrEntity{" +
        "attrId='" + attrId + '\'' +
        "attrName='" + attrName + '\'' +
        "searchType='" + searchType + '\'' +
        "icon='" + icon + '\'' +
        "valueSelect='" + valueSelect + '\'' +
        "attrType='" + attrType + '\'' +
        "enable='" + enable + '\'' +
        "catelogId='" + catelogId + '\'' +
        "showDesc='" + showDesc + '\'' +
        '}';
    }

}
