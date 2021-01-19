package io.github.ihelin.seven.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.product.entity.SkuSaleAttrValueEntity;
import io.github.ihelin.seven.product.vo.ItemSaleAttrVo;

import java.util.List;
import java.util.Map;

/**
 * pms_sku_sale_attr_value
 *
 * @author iHelin ihelin@outlook.com
 * @date 2021-01-11 11:52:40
 */
public interface SkuSaleAttrValueService extends IService<SkuSaleAttrValueEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<ItemSaleAttrVo> getSaleAttrsBuSpuId(Long spuId);

    List<String> getSkuSaleAttrValuesAsStringList(Long skuId);
}

