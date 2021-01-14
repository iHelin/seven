package io.github.ihelin.seven.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.order.entity.OrderReturnApplyEntity;

import java.util.Map;

/**
 * oms_order_return_apply
 *
 * @author iHelin ihelin@outlook.com
 * @since 2021-01-14 14:32:51
 */
public interface OrderReturnApplyService extends IService<OrderReturnApplyEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

