package io.github.ihelin.seven.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.order.entity.OrderItemEntity;

import java.util.Map;

/**
 * ¶©µ¥ÏîÐÅÏ¢
 *
 * @author iHelin
 * @email ihelin@outlook.com
 * @date 2021-01-05 14:16:31
 */
public interface OrderItemService extends IService<OrderItemEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

