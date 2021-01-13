package io.github.ihelin.seven.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.product.entity.ProductAttrValueEntity;
import io.github.ihelin.seven.product.vo.BaseAttrs;

import java.util.List;
import java.util.Map;

/**
 * pms_product_attr_value
 *
 * @author iHelin ihelin@outlook.com
 * @date 2021-01-11 11:52:41
 */
public interface ProductAttrValueService extends IService<ProductAttrValueEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveBaseAttr(Long spuInfoEntityId, List<BaseAttrs> baseAttrs);
}

