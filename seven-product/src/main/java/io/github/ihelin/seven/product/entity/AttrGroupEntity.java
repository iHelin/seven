package io.github.ihelin.seven.product.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.List;

/**
 * pms_attr_group
 *
 * @author iHelin ihelin@outlook.com
 * @since 2021-01-11 11:52:41
 */
@TableName("pms_attr_group")
public class AttrGroupEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ·Ö×éid
     */
    @TableId
    private Long attrGroupId;
    /**
     * ×éÃû
     */
    private String attrGroupName;
    /**
     * ÅÅÐò
     */
    private Integer sort;
    /**
     * ÃèÊö
     */
    private String descript;
    /**
     * ×éÍ¼±ê
     */
    private String icon;

    /**
     * ËùÊô·ÖÀàid
     */
    private Long catelogId;

    @TableField(exist = false)
    private List<Long> catelogIds;


    public Long getAttrGroupId() {
        return attrGroupId;
    }

    public void setAttrGroupId(Long attrGroupId) {
        this.attrGroupId = attrGroupId;
    }

    public String getAttrGroupName() {
        return attrGroupName;
    }

    public void setAttrGroupName(String attrGroupName) {
        this.attrGroupName = attrGroupName;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Long getCatelogId() {
        return catelogId;
    }

    public void setCatelogId(Long catelogId) {
        this.catelogId = catelogId;
    }

    public List<Long> getCatelogIds() {
        return catelogIds;
    }

    public void setCatelogIds(List<Long> catelogIds) {
        this.catelogIds = catelogIds;
    }

    @Override
    public String toString() {
        return "AttrGroupEntity{" +
                "attrGroupId='" + attrGroupId + '\'' +
                "attrGroupName='" + attrGroupName + '\'' +
                "sort='" + sort + '\'' +
                "descript='" + descript + '\'' +
                "icon='" + icon + '\'' +
                "catelogId='" + catelogId + '\'' +
                '}';
    }

}
