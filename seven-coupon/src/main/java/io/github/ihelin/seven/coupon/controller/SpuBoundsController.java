package io.github.ihelin.seven.coupon.controller;

import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.common.utils.R;
import io.github.ihelin.seven.coupon.entity.SpuBoundsEntity;
import io.github.ihelin.seven.coupon.service.SpuBoundsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * sms_spu_bounds
 *
 * @author iHelin ihelin@outlook.com
 * @since 2021-01-14 14:19:57
 */
@RestController
@RequestMapping("coupon/spubounds")
public class SpuBoundsController {

    @Autowired
    private SpuBoundsService spuBoundsService;

    /**
     * 列表
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = spuBoundsService.queryPage(params);

        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		SpuBoundsEntity spuBounds = spuBoundsService.getById(id);

        return R.ok().put("data", spuBounds);
    }

    /**
     * 新增
     */
    @PostMapping("/save")
    public R save(@RequestBody SpuBoundsEntity spuBounds){
		spuBoundsService.save(spuBounds);
        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public R update(@RequestBody SpuBoundsEntity spuBounds){
		spuBoundsService.updateById(spuBounds);
        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		spuBoundsService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
