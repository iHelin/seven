package io.github.ihelin.seven.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.common.utils.Query;
import io.github.ihelin.seven.order.dao.PaymentInfoDao;
import io.github.ihelin.seven.order.entity.PaymentInfoEntity;
import io.github.ihelin.seven.order.service.PaymentInfoService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* oms_payment_info
*
* @author iHelin ihelin@outlook.com
* @since 2021-01-14 14:32:51
*/
@Service
public class PaymentInfoServiceImpl extends ServiceImpl<PaymentInfoDao, PaymentInfoEntity> implements PaymentInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PaymentInfoEntity> page = this.page(
                new Query<PaymentInfoEntity>().getPage(params),
                new QueryWrapper<PaymentInfoEntity>()
        );

        return new PageUtils(page);
    }

}