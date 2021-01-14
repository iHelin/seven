package io.github.ihelin.seven.coupon.controller;

import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.common.utils.R;
import io.github.ihelin.seven.coupon.entity.CouponSpuRelationEntity;
import io.github.ihelin.seven.coupon.service.CouponSpuRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * sms_coupon_spu_relation
 *
 * @author iHelin ihelin@outlook.com
 * @since 2021-01-14 14:19:56
 */
@RestController
@RequestMapping("coupon/couponspurelation")
public class CouponSpuRelationController {

    @Autowired
    private CouponSpuRelationService couponSpuRelationService;

    /**
     * 列表
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = couponSpuRelationService.queryPage(params);

        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		CouponSpuRelationEntity couponSpuRelation = couponSpuRelationService.getById(id);

        return R.ok().put("data", couponSpuRelation);
    }

    /**
     * 新增
     */
    @PostMapping("/save")
    public R save(@RequestBody CouponSpuRelationEntity couponSpuRelation){
		couponSpuRelationService.save(couponSpuRelation);
        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public R update(@RequestBody CouponSpuRelationEntity couponSpuRelation){
		couponSpuRelationService.updateById(couponSpuRelation);
        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		couponSpuRelationService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
