package io.github.ihelin.seven.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.order.entity.PaymentInfoEntity;

import java.util.Map;

/**
 * oms_payment_info
 *
 * @author iHelin ihelin@outlook.com
 * @since 2021-01-14 14:32:51
 */
public interface PaymentInfoService extends IService<PaymentInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

