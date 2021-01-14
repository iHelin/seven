package io.github.ihelin.seven.coupon.controller;

import io.github.ihelin.seven.common.dto.SkuReductionDTO;
import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.common.utils.R;
import io.github.ihelin.seven.coupon.entity.SkuFullReductionEntity;
import io.github.ihelin.seven.coupon.service.SkuFullReductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * sms_sku_full_reduction
 *
 * @author iHelin ihelin@outlook.com
 * @since 2021-01-14 14:19:56
 */
@RestController
@RequestMapping("coupon/skufullreduction")
public class SkuFullReductionController {

    @Autowired
    private SkuFullReductionService skuFullReductionService;

    @PostMapping("/saveinfo")
    public R saveReduction(@RequestBody SkuReductionDTO skuReductionDTO){
        skuFullReductionService.saveReduction(skuReductionDTO);
        return R.ok();
    }

    /**
     * 列表
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = skuFullReductionService.queryPage(params);

        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		SkuFullReductionEntity skuFullReduction = skuFullReductionService.getById(id);

        return R.ok().put("data", skuFullReduction);
    }

    /**
     * 新增
     */
    @PostMapping("/save")
    public R save(@RequestBody SkuFullReductionEntity skuFullReduction){
		skuFullReductionService.save(skuFullReduction);
        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public R update(@RequestBody SkuFullReductionEntity skuFullReduction){
		skuFullReductionService.updateById(skuFullReduction);
        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		skuFullReductionService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
