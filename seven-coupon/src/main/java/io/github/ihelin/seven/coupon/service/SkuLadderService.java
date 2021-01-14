package io.github.ihelin.seven.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.coupon.entity.SkuLadderEntity;

import java.util.Map;

/**
 * sms_sku_ladder
 *
 * @author iHelin ihelin@outlook.com
 * @since 2021-01-14 14:19:56
 */
public interface SkuLadderService extends IService<SkuLadderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

