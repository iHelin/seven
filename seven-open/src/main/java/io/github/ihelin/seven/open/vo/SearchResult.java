package io.github.ihelin.seven.open.vo;

import io.github.ihelin.seven.common.dto.SkuEsModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author iHelin
 * @since 2021/1/18 12:05
 */
public class SearchResult {

    private List<SkuEsModel> products;
    private Integer pageNum;
    private Long total;
    private Integer totalPages;
    private List<Integer> pageNavs;

    private List<BrandVo> brands;
    private List<CatalogVo> catalogs;
    private List<AttrVo> attrs;

    private List<NavVo> navs = new ArrayList<>();
    private List<Long> attrIds = new ArrayList<>();

    public List<Long> getAttrIds() {
        return attrIds;
    }

    public void setAttrIds(List<Long> attrIds) {
        this.attrIds = attrIds;
    }

    public List<NavVo> getNavs() {
        return navs;
    }

    public void setNavs(List<NavVo> navs) {
        this.navs = navs;
    }

    public List<Integer> getPageNavs() {
        return pageNavs;
    }

    public void setPageNavs(List<Integer> pageNavs) {
        this.pageNavs = pageNavs;
    }

    public List<SkuEsModel> getProducts() {
        return products;
    }

    public void setProducts(List<SkuEsModel> products) {
        this.products = products;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<BrandVo> getBrands() {
        return brands;
    }

    public void setBrands(List<BrandVo> brands) {
        this.brands = brands;
    }

    public List<CatalogVo> getCatalogs() {
        return catalogs;
    }

    public void setCatalogs(List<CatalogVo> catalogs) {
        this.catalogs = catalogs;
    }

    public List<AttrVo> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<AttrVo> attrs) {
        this.attrs = attrs;
    }

    public static class BrandVo {
        private Long brandId;
        private String brandName;
        private String brandImg;

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

        public String getBrandImg() {
            return brandImg;
        }

        public void setBrandImg(String brandImg) {
            this.brandImg = brandImg;
        }
    }

    public static class AttrVo {
        private Long attrId;
        private String attrName;
        private List<String> attrValue;

        public Long getAttrId() {
            return attrId;
        }

        public void setAttrId(Long attrId) {
            this.attrId = attrId;
        }

        public String getAttrName() {
            return attrName;
        }

        public void setAttrName(String attrName) {
            this.attrName = attrName;
        }

        public List<String> getAttrValue() {
            return attrValue;
        }

        public void setAttrValue(List<String> attrValue) {
            this.attrValue = attrValue;
        }
    }

    public static class CatalogVo {
        private Long catalogId;
        private String catalogName;

        public Long getCatalogId() {
            return catalogId;
        }

        public void setCatalogId(Long catalogId) {
            this.catalogId = catalogId;
        }

        public String getCatalogName() {
            return catalogName;
        }

        public void setCatalogName(String catalogName) {
            this.catalogName = catalogName;
        }
    }

    public static class NavVo {
        private String navName;
        private String navValue;
        private String link;

        public String getNavName() {
            return navName;
        }

        public void setNavName(String navName) {
            this.navName = navName;
        }

        public String getNavValue() {
            return navValue;
        }

        public void setNavValue(String navValue) {
            this.navValue = navValue;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }
    }
}
