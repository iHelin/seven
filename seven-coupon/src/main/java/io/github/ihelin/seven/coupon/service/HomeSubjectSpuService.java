package io.github.ihelin.seven.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.coupon.entity.HomeSubjectSpuEntity;

import java.util.Map;

/**
 * ×¨ÌâÉÌÆ·
 *
 * @author iHelin
 * @email ihelin@outlook.com
 * @date 2021-01-05 12:40:34
 */
public interface HomeSubjectSpuService extends IService<HomeSubjectSpuEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

