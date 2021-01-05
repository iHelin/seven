package io.github.ihelin.seven.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.ihelin.common.utils.PageUtils;
import io.github.ihelin.seven.product.entity.SkuInfoEntity;

import java.util.Map;

/**
 * skuÐÅÏ¢
 *
 * @author iHelin
 * @email ihelin@outlook.com
 * @date 2021-01-04 22:13:30
 */
public interface SkuInfoService extends IService<SkuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

