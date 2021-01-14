package io.github.ihelin.seven.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.coupon.entity.SpuBoundsEntity;

import java.util.Map;

/**
 * sms_spu_bounds
 *
 * @author iHelin ihelin@outlook.com
 * @since 2021-01-14 14:19:57
 */
public interface SpuBoundsService extends IService<SpuBoundsEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

