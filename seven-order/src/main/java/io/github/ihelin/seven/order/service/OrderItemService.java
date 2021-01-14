package io.github.ihelin.seven.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.order.entity.OrderItemEntity;

import java.util.Map;

/**
 * oms_order_item
 *
 * @author iHelin ihelin@outlook.com
 * @since 2021-01-14 14:32:50
 */
public interface OrderItemService extends IService<OrderItemEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

