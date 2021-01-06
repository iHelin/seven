package io.github.ihelin.seven.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.coupon.entity.SeckillSessionEntity;

import java.util.Map;

/**
 * ÃëÉ±»î¶¯³¡´Î
 *
 * @author iHelin
 * @email ihelin@outlook.com
 * @date 2021-01-05 12:40:34
 */
public interface SeckillSessionService extends IService<SeckillSessionEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

