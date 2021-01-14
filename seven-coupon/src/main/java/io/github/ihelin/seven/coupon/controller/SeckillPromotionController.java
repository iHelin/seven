package io.github.ihelin.seven.coupon.controller;

import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.common.utils.R;
import io.github.ihelin.seven.coupon.entity.SeckillPromotionEntity;
import io.github.ihelin.seven.coupon.service.SeckillPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * sms_seckill_promotion
 *
 * @author iHelin ihelin@outlook.com
 * @since 2021-01-14 14:19:56
 */
@RestController
@RequestMapping("coupon/seckillpromotion")
public class SeckillPromotionController {

    @Autowired
    private SeckillPromotionService seckillPromotionService;

    /**
     * 列表
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = seckillPromotionService.queryPage(params);

        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		SeckillPromotionEntity seckillPromotion = seckillPromotionService.getById(id);

        return R.ok().put("data", seckillPromotion);
    }

    /**
     * 新增
     */
    @PostMapping("/save")
    public R save(@RequestBody SeckillPromotionEntity seckillPromotion){
		seckillPromotionService.save(seckillPromotion);
        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public R update(@RequestBody SeckillPromotionEntity seckillPromotion){
		seckillPromotionService.updateById(seckillPromotion);
        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		seckillPromotionService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
