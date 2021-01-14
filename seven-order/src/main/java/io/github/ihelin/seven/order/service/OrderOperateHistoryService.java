package io.github.ihelin.seven.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.order.entity.OrderOperateHistoryEntity;

import java.util.Map;

/**
 * oms_order_operate_history
 *
 * @author iHelin ihelin@outlook.com
 * @since 2021-01-14 14:32:50
 */
public interface OrderOperateHistoryService extends IService<OrderOperateHistoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

