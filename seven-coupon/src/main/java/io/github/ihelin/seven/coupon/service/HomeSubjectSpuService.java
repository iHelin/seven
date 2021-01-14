package io.github.ihelin.seven.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.coupon.entity.HomeSubjectSpuEntity;

import java.util.Map;

/**
 * sms_home_subject_spu
 *
 * @author iHelin ihelin@outlook.com
 * @since 2021-01-14 14:19:56
 */
public interface HomeSubjectSpuService extends IService<HomeSubjectSpuEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

