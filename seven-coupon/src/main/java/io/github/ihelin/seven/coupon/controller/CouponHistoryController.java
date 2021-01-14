package io.github.ihelin.seven.coupon.controller;

import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.common.utils.R;
import io.github.ihelin.seven.coupon.entity.CouponHistoryEntity;
import io.github.ihelin.seven.coupon.service.CouponHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * sms_coupon_history
 *
 * @author iHelin ihelin@outlook.com
 * @since 2021-01-14 14:19:56
 */
@RestController
@RequestMapping("coupon/couponhistory")
public class CouponHistoryController {

    @Autowired
    private CouponHistoryService couponHistoryService;

    /**
     * 列表
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = couponHistoryService.queryPage(params);

        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		CouponHistoryEntity couponHistory = couponHistoryService.getById(id);

        return R.ok().put("data", couponHistory);
    }

    /**
     * 新增
     */
    @PostMapping("/save")
    public R save(@RequestBody CouponHistoryEntity couponHistory){
		couponHistoryService.save(couponHistory);
        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public R update(@RequestBody CouponHistoryEntity couponHistory){
		couponHistoryService.updateById(couponHistory);
        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		couponHistoryService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
