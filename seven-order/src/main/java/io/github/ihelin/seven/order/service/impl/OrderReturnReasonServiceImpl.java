package io.github.ihelin.seven.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.common.utils.Query;
import io.github.ihelin.seven.order.dao.OrderReturnReasonDao;
import io.github.ihelin.seven.order.entity.OrderReturnReasonEntity;
import io.github.ihelin.seven.order.service.OrderReturnReasonService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* oms_order_return_reason
*
* @author iHelin ihelin@outlook.com
* @since 2021-01-14 14:32:51
*/
@Service
public class OrderReturnReasonServiceImpl extends ServiceImpl<OrderReturnReasonDao, OrderReturnReasonEntity> implements OrderReturnReasonService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderReturnReasonEntity> page = this.page(
                new Query<OrderReturnReasonEntity>().getPage(params),
                new QueryWrapper<OrderReturnReasonEntity>()
        );

        return new PageUtils(page);
    }

}