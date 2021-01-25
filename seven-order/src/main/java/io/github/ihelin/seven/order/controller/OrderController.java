package io.github.ihelin.seven.order.controller;

import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.common.utils.R;
import io.github.ihelin.seven.order.entity.OrderEntity;
import io.github.ihelin.seven.order.feign.WareFeign;
import io.github.ihelin.seven.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * oms_order
 *
 * @author iHelin ihelin@outlook.com
 * @since 2021-01-14 14:32:50
 */
@RestController
@RequestMapping("order/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private WareFeign wareFeign;

    /**
     * 查询运费
     *
     * @param addrId 地址id
     * @return
     */
    @GetMapping("/fare")
    public R getFare(Long addrId) {
        return wareFeign.getFare(addrId);
    }

    @GetMapping("/status/{orderSn}")
    public R getOrderStatus(@PathVariable String orderSn) {
        OrderEntity orderEntity = orderService.getOrderByOrderSn(orderSn);
        return R.ok().putData(orderEntity == null ? null : orderEntity.getStatus());
    }

    /**
     * 列表
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = orderService.queryPage(params);
        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        OrderEntity order = orderService.getById(id);

        return R.ok().put("data", order);
    }

    /**
     * 新增
     */
    @PostMapping("/save")
    public R save(@RequestBody OrderEntity order) {
        orderService.save(order);
        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public R update(@RequestBody OrderEntity order) {
        orderService.updateById(order);
        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        orderService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
