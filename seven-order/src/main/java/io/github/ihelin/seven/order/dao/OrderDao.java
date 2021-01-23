package io.github.ihelin.seven.order.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.ihelin.seven.order.entity.OrderEntity;
import org.apache.ibatis.annotations.Param;

/**
 * oms_order
 * 
 * @author iHelin ihelin@outlook.com
 * @since 2021-01-14 14:32:50
 */
public interface OrderDao extends BaseMapper<OrderEntity> {

    void updateOrderStatus(@Param("orderSn") String orderSn, @Param("code") Integer code);
}
