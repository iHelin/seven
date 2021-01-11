package io.github.ihelin.seven.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.github.ihelin.seven.common.valid.AddGroup;
import io.github.ihelin.seven.common.valid.UpdateGroup;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * Æ·ÅÆ
 *
 * @author iHelin ihelin@outlook.com
 * @since 2021-01-04 22:13:30
 */
@TableName("pms_brand")
public class BrandEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * brandId
     */
    @TableId
    @Null(groups = AddGroup.class)
    @NotNull(groups = UpdateGroup.class)
    private Long brandId;

    /**
     * name
     */
    @NotBlank(groups = {AddGroup.class, UpdateGroup.class})
    private String name;

    /**
     * logo
     */
    @URL(groups = {AddGroup.class, UpdateGroup.class})
    @NotEmpty(groups = {AddGroup.class, UpdateGroup.class})
    private String logo;

    /**
     * descript
     */
    private String descript;

    /**
     * showStatus
     */
    @NotNull(groups = {AddGroup.class, UpdateGroup.class})
    private Integer showStatus;

    /**
     * firstLetter
     */
    @NotEmpty(groups = {AddGroup.class, UpdateGroup.class})
    @Pattern(regexp = "^[a-zA-Z]$", groups = {AddGroup.class, UpdateGroup.class})
    private String firstLetter;

    /**
     * sort
     */
    @Min(value = 0, groups = {AddGroup.class, UpdateGroup.class})
    @NotNull(groups = {AddGroup.class, UpdateGroup.class})
    private Integer sort;

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public Integer getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(Integer showStatus) {
        this.showStatus = showStatus;
    }

    public String getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
