package io.github.ihelin.seven.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * Æ·ÅÆ
 *
 * @author iHelin ihelin@outlook.com
 * @since  2021-01-04 22:13:30
 */
@TableName("pms_brand")
public class BrandEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * brandId
     */
    @TableId
    private Long brandId;

    /**
     * name
     */
    @NotBlank
    private String name;

    /**
     * logo
     */
    @URL
	@NotEmpty
    private String logo;

    /**
     * descript
     */
    private String descript;

    /**
     * showStatus
     */
	@NotNull
    private Integer showStatus;
    /**
     * firstLetter
     */
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z]$")
    private String firstLetter;
    /**
     * sort
     */
    @Min(0)
	@NotNull
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
