package io.github.ihelin.seven.order.controller;

import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.common.utils.R;
import io.github.ihelin.seven.order.entity.OrderSettingEntity;
import io.github.ihelin.seven.order.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * oms_order_setting
 *
 * @author iHelin ihelin@outlook.com
 * @since 2021-01-14 14:32:51
 */
@RestController
@RequestMapping("order/ordersetting")
public class OrderSettingController {

    @Autowired
    private OrderSettingService orderSettingService;

    /**
     * 列表
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = orderSettingService.queryPage(params);

        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		OrderSettingEntity orderSetting = orderSettingService.getById(id);

        return R.ok().put("data", orderSetting);
    }

    /**
     * 新增
     */
    @PostMapping("/save")
    public R save(@RequestBody OrderSettingEntity orderSetting){
		orderSettingService.save(orderSetting);
        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public R update(@RequestBody OrderSettingEntity orderSetting){
		orderSettingService.updateById(orderSetting);
        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		orderSettingService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
