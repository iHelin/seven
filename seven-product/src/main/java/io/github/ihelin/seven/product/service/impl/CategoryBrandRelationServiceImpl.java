package io.github.ihelin.seven.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.common.utils.Query;
import io.github.ihelin.seven.product.dao.BrandDao;
import io.github.ihelin.seven.product.dao.CategoryBrandRelationDao;
import io.github.ihelin.seven.product.dao.CategoryDao;
import io.github.ihelin.seven.product.entity.BrandEntity;
import io.github.ihelin.seven.product.entity.CategoryBrandRelationEntity;
import io.github.ihelin.seven.product.entity.CategoryEntity;
import io.github.ihelin.seven.product.service.CategoryBrandRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * pms_category_brand_relation
 *
 * @author iHelin ihelin@outlook.com
 * @date 2021-01-11 11:52:41
 */
@Service("categoryBrandRelationService")
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationDao, CategoryBrandRelationEntity> implements CategoryBrandRelationService {

    @Autowired
    private BrandDao brandDao;

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryBrandRelationEntity> page = this.page(
                new Query<CategoryBrandRelationEntity>().getPage(params),
                new QueryWrapper<CategoryBrandRelationEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveDetail(CategoryBrandRelationEntity categoryBrandRelation) {
        Long brandId = categoryBrandRelation.getBrandId();
        Long catelogId = categoryBrandRelation.getCatelogId();

        BrandEntity brandEntity = brandDao.selectById(brandId);
        CategoryEntity categoryEntity = categoryDao.selectById(catelogId);

        categoryBrandRelation.setBrandName(brandEntity.getName());
        categoryBrandRelation.setCatelogName(categoryEntity.getName());
        this.save(categoryBrandRelation);
    }

    @Override
    public void updateBrand(Long brandId, String brandName) {
        CategoryBrandRelationEntity relationEntity = new CategoryBrandRelationEntity();
        relationEntity.setBrandName(brandName);
        this.update(relationEntity, new UpdateWrapper<CategoryBrandRelationEntity>().eq("brand_id", brandId));
    }

    @Override
    public void updateCategory(Long catId, String categoryName) {
        this.baseMapper.updateCategory(catId, categoryName);
    }

}