package io.github.ihelin.seven.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
* pms_attr_attrgroup_relation
*
* @author iHelin ihelin@outlook.com
* @since 2021-01-11 11:52:41
*/
@TableName("pms_attr_attrgroup_relation")
public class AttrAttrgroupRelationEntity implements Serializable {
private static final long serialVersionUID = 1L;

    /**
    * id
    */
        @TableId
    private Long id;
    /**
    * ÊôÐÔid
    */
    private Long attrId;
    /**
    * ÊôÐÔ·Ö×éid
    */
    private Long attrGroupId;
    /**
    * ÊôÐÔ×éÄÚÅÅÐò
    */
    private Integer attrSort;


    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getAttrId(){
        return attrId;
    }

    public void setAttrId(Long attrId){
        this.attrId = attrId;
    }

    public Long getAttrGroupId(){
        return attrGroupId;
    }

    public void setAttrGroupId(Long attrGroupId){
        this.attrGroupId = attrGroupId;
    }

    public Integer getAttrSort(){
        return attrSort;
    }

    public void setAttrSort(Integer attrSort){
        this.attrSort = attrSort;
    }

}
