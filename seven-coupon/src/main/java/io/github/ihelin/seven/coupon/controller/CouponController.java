package io.github.ihelin.seven.coupon.controller;

import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.common.utils.R;
import io.github.ihelin.seven.coupon.entity.CouponEntity;
import io.github.ihelin.seven.coupon.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * sms_coupon
 *
 * @author iHelin ihelin@outlook.com
 * @since 2021-01-14 14:19:56
 */
@RestController
@RequestMapping("coupon/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    /**
     * 列表
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = couponService.queryPage(params);

        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        CouponEntity coupon = couponService.getById(id);

        return R.ok().put("data", coupon);
    }

    /**
     * 新增
     */
    @PostMapping("/save")
    public R save(@RequestBody CouponEntity coupon) {
        couponService.save(coupon);
        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public R update(@RequestBody CouponEntity coupon) {
        couponService.updateById(coupon);
        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        couponService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
