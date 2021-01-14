package io.github.ihelin.seven.coupon.controller;

import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.common.utils.R;
import io.github.ihelin.seven.coupon.entity.SkuLadderEntity;
import io.github.ihelin.seven.coupon.service.SkuLadderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * sms_sku_ladder
 *
 * @author iHelin ihelin@outlook.com
 * @since 2021-01-14 14:19:56
 */
@RestController
@RequestMapping("coupon/skuladder")
public class SkuLadderController {

    @Autowired
    private SkuLadderService skuLadderService;

    /**
     * 列表
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = skuLadderService.queryPage(params);

        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		SkuLadderEntity skuLadder = skuLadderService.getById(id);

        return R.ok().put("data", skuLadder);
    }

    /**
     * 新增
     */
    @PostMapping("/save")
    public R save(@RequestBody SkuLadderEntity skuLadder){
		skuLadderService.save(skuLadder);
        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public R update(@RequestBody SkuLadderEntity skuLadder){
		skuLadderService.updateById(skuLadder);
        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		skuLadderService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
