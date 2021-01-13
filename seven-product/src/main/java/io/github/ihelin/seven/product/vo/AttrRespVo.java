package io.github.ihelin.seven.product.vo;

import java.util.List;

/**
 * @author iHelin ihelin@outlook.com
 * @since 2021/1/12 下午2:48
 */
public class AttrRespVo extends AttrVo {

    private String catelogName;

    private String groupName;

    private List<Long> catelogPath;

    public String getCatelogName() {
        return catelogName;
    }

    public void setCatelogName(String catelogName) {
        this.catelogName = catelogName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Long> getCatelogPath() {
        return catelogPath;
    }

    public void setCatelogPath(List<Long> catelogPath) {
        this.catelogPath = catelogPath;
    }
}
