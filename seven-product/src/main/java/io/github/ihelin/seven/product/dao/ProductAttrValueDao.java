package io.github.ihelin.seven.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.ihelin.seven.product.entity.ProductAttrValueEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * pms_product_attr_value
 * 
 * @author iHelin ihelin@outlook.com
 * @date 2021-01-11 11:52:41
 */
@Mapper
public interface ProductAttrValueDao extends BaseMapper<ProductAttrValueEntity> {
	
}
