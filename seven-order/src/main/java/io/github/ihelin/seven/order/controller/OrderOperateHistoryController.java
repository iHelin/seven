package io.github.ihelin.seven.order.controller;

import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.common.utils.R;
import io.github.ihelin.seven.order.entity.OrderOperateHistoryEntity;
import io.github.ihelin.seven.order.service.OrderOperateHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;



/**
 * ¶©µ¥²Ù×÷ÀúÊ·¼ÇÂ¼
 *
 * @author iHelin
 * @email ihelin@outlook.com
 * @date 2021-01-05 14:16:31
 */
@RestController
@RequestMapping("order/orderoperatehistory")
public class OrderOperateHistoryController {
    @Autowired
    private OrderOperateHistoryService orderOperateHistoryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    // @RequiresPermissions("order:orderoperatehistory:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = orderOperateHistoryService.queryPage(params);

        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("order:orderoperatehistory:info")
    public R info(@PathVariable("id") Long id){
		OrderOperateHistoryEntity orderOperateHistory = orderOperateHistoryService.getById(id);

        return R.ok().put("orderOperateHistory", orderOperateHistory);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("order:orderoperatehistory:save")
    public R save(@RequestBody OrderOperateHistoryEntity orderOperateHistory){
		orderOperateHistoryService.save(orderOperateHistory);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("order:orderoperatehistory:update")
    public R update(@RequestBody OrderOperateHistoryEntity orderOperateHistory){
		orderOperateHistoryService.updateById(orderOperateHistory);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("order:orderoperatehistory:delete")
    public R delete(@RequestBody Long[] ids){
		orderOperateHistoryService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
