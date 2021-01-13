package io.github.ihelin.seven.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.product.entity.BrandEntity;
import io.github.ihelin.seven.product.entity.CategoryBrandRelationEntity;

import java.util.List;
import java.util.Map;

/**
 * pms_category_brand_relation
 *
 * @author iHelin ihelin@outlook.com
 * @date 2021-01-11 11:52:41
 */
public interface CategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveDetail(CategoryBrandRelationEntity categoryBrandRelation);

    void updateBrand(Long brandId, String brandName);

    void updateCategory(Long catId, String categoryName);

    List<BrandEntity> getBrandsByCatId(Long catId);
}

