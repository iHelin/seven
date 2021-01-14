package io.github.ihelin.seven.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.common.utils.Query;
import io.github.ihelin.seven.order.dao.OrderItemDao;
import io.github.ihelin.seven.order.entity.OrderItemEntity;
import io.github.ihelin.seven.order.service.OrderItemService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* oms_order_item
*
* @author iHelin ihelin@outlook.com
* @since 2021-01-14 14:32:50
*/
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemDao, OrderItemEntity> implements OrderItemService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderItemEntity> page = this.page(
                new Query<OrderItemEntity>().getPage(params),
                new QueryWrapper<OrderItemEntity>()
        );

        return new PageUtils(page);
    }

}