package io.github.ihelin.seven.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.common.utils.Query;
import io.github.ihelin.seven.order.dao.OrderReturnApplyDao;
import io.github.ihelin.seven.order.entity.OrderReturnApplyEntity;
import io.github.ihelin.seven.order.service.OrderReturnApplyService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* oms_order_return_apply
*
* @author iHelin ihelin@outlook.com
* @since 2021-01-14 14:32:51
*/
@Service
public class OrderReturnApplyServiceImpl extends ServiceImpl<OrderReturnApplyDao, OrderReturnApplyEntity> implements OrderReturnApplyService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderReturnApplyEntity> page = this.page(
                new Query<OrderReturnApplyEntity>().getPage(params),
                new QueryWrapper<OrderReturnApplyEntity>()
        );

        return new PageUtils(page);
    }

}