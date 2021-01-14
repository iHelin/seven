package io.github.ihelin.seven.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.order.entity.OrderEntity;

import java.util.Map;

/**
 * oms_order
 *
 * @author iHelin ihelin@outlook.com
 * @since 2021-01-14 14:32:50
 */
public interface OrderService extends IService<OrderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

