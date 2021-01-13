package io.github.ihelin.seven.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.product.entity.SkuInfoEntity;

import java.util.Map;

/**
 * pms_sku_info
 *
 * @author iHelin ihelin@outlook.com
 * @date 2021-01-11 11:52:41
 */
public interface SkuInfoService extends IService<SkuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPageByCondition(Map<String, Object> params);
}

