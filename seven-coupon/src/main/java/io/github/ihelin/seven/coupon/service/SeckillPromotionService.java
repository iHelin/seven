package io.github.ihelin.seven.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.coupon.entity.SeckillPromotionEntity;

import java.util.Map;

/**
 * ÃëÉ±»î¶¯
 *
 * @author iHelin
 * @email ihelin@outlook.com
 * @date 2021-01-05 12:40:34
 */
public interface SeckillPromotionService extends IService<SeckillPromotionEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

