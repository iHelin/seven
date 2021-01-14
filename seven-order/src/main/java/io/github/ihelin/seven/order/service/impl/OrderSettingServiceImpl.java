package io.github.ihelin.seven.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.common.utils.Query;
import io.github.ihelin.seven.order.dao.OrderSettingDao;
import io.github.ihelin.seven.order.entity.OrderSettingEntity;
import io.github.ihelin.seven.order.service.OrderSettingService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* oms_order_setting
*
* @author iHelin ihelin@outlook.com
* @since 2021-01-14 14:32:51
*/
@Service
public class OrderSettingServiceImpl extends ServiceImpl<OrderSettingDao, OrderSettingEntity> implements OrderSettingService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderSettingEntity> page = this.page(
                new Query<OrderSettingEntity>().getPage(params),
                new QueryWrapper<OrderSettingEntity>()
        );

        return new PageUtils(page);
    }

}