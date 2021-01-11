package io.github.ihelin.seven.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.ihelin.seven.product.entity.CategoryBrandRelationEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * pms_category_brand_relation
 * 
 * @author iHelin ihelin@outlook.com
 * @date 2021-01-11 11:52:41
 */
@Mapper
public interface CategoryBrandRelationDao extends BaseMapper<CategoryBrandRelationEntity> {
	
}
