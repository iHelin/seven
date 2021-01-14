package io.github.ihelin.seven.coupon.controller;

import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.common.utils.R;
import io.github.ihelin.seven.coupon.entity.HomeAdvEntity;
import io.github.ihelin.seven.coupon.service.HomeAdvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * sms_home_adv
 *
 * @author iHelin ihelin@outlook.com
 * @since 2021-01-14 14:19:56
 */
@RestController
@RequestMapping("coupon/homeadv")
public class HomeAdvController {

    @Autowired
    private HomeAdvService homeAdvService;

    /**
     * 列表
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = homeAdvService.queryPage(params);

        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		HomeAdvEntity homeAdv = homeAdvService.getById(id);

        return R.ok().put("data", homeAdv);
    }

    /**
     * 新增
     */
    @PostMapping("/save")
    public R save(@RequestBody HomeAdvEntity homeAdv){
		homeAdvService.save(homeAdv);
        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public R update(@RequestBody HomeAdvEntity homeAdv){
		homeAdvService.updateById(homeAdv);
        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		homeAdvService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
