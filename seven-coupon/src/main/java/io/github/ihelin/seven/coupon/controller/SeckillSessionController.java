package io.github.ihelin.seven.coupon.controller;

import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.common.utils.R;
import io.github.ihelin.seven.coupon.entity.SeckillSessionEntity;
import io.github.ihelin.seven.coupon.service.SeckillSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * sms_seckill_session
 *
 * @author iHelin ihelin@outlook.com
 * @since 2021-01-14 14:19:56
 */
@RestController
@RequestMapping("coupon/seckillsession")
public class SeckillSessionController {

    @Autowired
    private SeckillSessionService seckillSessionService;

    /**
     * 列表
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = seckillSessionService.queryPage(params);

        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		SeckillSessionEntity seckillSession = seckillSessionService.getById(id);

        return R.ok().put("data", seckillSession);
    }

    /**
     * 新增
     */
    @PostMapping("/save")
    public R save(@RequestBody SeckillSessionEntity seckillSession){
		seckillSessionService.save(seckillSession);
        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public R update(@RequestBody SeckillSessionEntity seckillSession){
		seckillSessionService.updateById(seckillSession);
        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		seckillSessionService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
