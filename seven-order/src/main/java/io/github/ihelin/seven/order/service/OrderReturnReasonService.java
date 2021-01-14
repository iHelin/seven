package io.github.ihelin.seven.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.order.entity.OrderReturnReasonEntity;

import java.util.Map;

/**
 * oms_order_return_reason
 *
 * @author iHelin ihelin@outlook.com
 * @since 2021-01-14 14:32:51
 */
public interface OrderReturnReasonService extends IService<OrderReturnReasonEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

