package io.github.ihelin.seven.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.order.entity.OrderSettingEntity;

import java.util.Map;

/**
 * ¶©µ¥ÅäÖÃÐÅÏ¢
 *
 * @author iHelin
 * @email ihelin@outlook.com
 * @date 2021-01-05 14:16:31
 */
public interface OrderSettingService extends IService<OrderSettingEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

