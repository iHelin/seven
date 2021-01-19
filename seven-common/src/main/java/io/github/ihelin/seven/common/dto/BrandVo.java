package io.github.ihelin.seven.common.dto;

/**
 * @author iHelin ihelin@outlook.com
 * @since 2021/1/13 下午1:31
 */
public class BrandVo {

    private Long brandId;
    private String brandName;

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
