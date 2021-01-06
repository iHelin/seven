package io.github.ihelin.seven.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.coupon.entity.HomeAdvEntity;

import java.util.Map;

/**
 * Ê×Ò³ÂÖ²¥¹ã¸æ
 *
 * @author iHelin
 * @email ihelin@outlook.com
 * @date 2021-01-05 12:40:34
 */
public interface HomeAdvService extends IService<HomeAdvEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

