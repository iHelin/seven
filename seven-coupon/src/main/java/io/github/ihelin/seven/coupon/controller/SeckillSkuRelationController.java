package io.github.ihelin.seven.coupon.controller;

import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.common.utils.R;
import io.github.ihelin.seven.coupon.entity.SeckillSkuRelationEntity;
import io.github.ihelin.seven.coupon.service.SeckillSkuRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * sms_seckill_sku_relation
 *
 * @author iHelin ihelin@outlook.com
 * @since 2021-01-14 14:19:56
 */
@RestController
@RequestMapping("coupon/seckillskurelation")
public class SeckillSkuRelationController {

    @Autowired
    private SeckillSkuRelationService seckillSkuRelationService;

    /**
     * 列表
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = seckillSkuRelationService.queryPage(params);

        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		SeckillSkuRelationEntity seckillSkuRelation = seckillSkuRelationService.getById(id);

        return R.ok().put("data", seckillSkuRelation);
    }

    /**
     * 新增
     */
    @PostMapping("/save")
    public R save(@RequestBody SeckillSkuRelationEntity seckillSkuRelation){
		seckillSkuRelationService.save(seckillSkuRelation);
        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public R update(@RequestBody SeckillSkuRelationEntity seckillSkuRelation){
		seckillSkuRelationService.updateById(seckillSkuRelation);
        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		seckillSkuRelationService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
