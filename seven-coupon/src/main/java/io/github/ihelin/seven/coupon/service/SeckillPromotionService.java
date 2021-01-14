package io.github.ihelin.seven.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.coupon.entity.SeckillPromotionEntity;

import java.util.Map;

/**
 * sms_seckill_promotion
 *
 * @author iHelin ihelin@outlook.com
 * @since 2021-01-14 14:19:56
 */
public interface SeckillPromotionService extends IService<SeckillPromotionEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

