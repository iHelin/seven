package io.github.ihelin.seven.order.controller;

import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.common.utils.R;
import io.github.ihelin.seven.order.entity.OrderItemEntity;
import io.github.ihelin.seven.order.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * oms_order_item
 *
 * @author iHelin ihelin@outlook.com
 * @since 2021-01-14 14:32:50
 */
@RestController
@RequestMapping("order/orderitem")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    /**
     * 列表
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = orderItemService.queryPage(params);

        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		OrderItemEntity orderItem = orderItemService.getById(id);

        return R.ok().put("data", orderItem);
    }

    /**
     * 新增
     */
    @PostMapping("/save")
    public R save(@RequestBody OrderItemEntity orderItem){
		orderItemService.save(orderItem);
        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public R update(@RequestBody OrderItemEntity orderItem){
		orderItemService.updateById(orderItem);
        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		orderItemService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
