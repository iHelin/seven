package io.github.ihelin.seven.common.dto;

import java.util.List;

/**
 * @author iHelin ihelin@outlook.com
 * @since 2021/1/12 下午2:48
 */
public class AttrRespVo extends AttrVo {

    private String catalogName;

    private String groupName;

    private List<Long> catalogPath;

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Long> getCatalogPath() {
        return catalogPath;
    }

    public void setCatalogPath(List<Long> catalogPath) {
        this.catalogPath = catalogPath;
    }
}
