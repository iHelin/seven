package io.github.ihelin.seven.coupon.controller;

import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.common.utils.R;
import io.github.ihelin.seven.coupon.entity.SeckillSkuNoticeEntity;
import io.github.ihelin.seven.coupon.service.SeckillSkuNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * sms_seckill_sku_notice
 *
 * @author iHelin ihelin@outlook.com
 * @since 2021-01-14 14:19:56
 */
@RestController
@RequestMapping("coupon/seckillskunotice")
public class SeckillSkuNoticeController {

    @Autowired
    private SeckillSkuNoticeService seckillSkuNoticeService;

    /**
     * 列表
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = seckillSkuNoticeService.queryPage(params);

        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		SeckillSkuNoticeEntity seckillSkuNotice = seckillSkuNoticeService.getById(id);

        return R.ok().put("data", seckillSkuNotice);
    }

    /**
     * 新增
     */
    @PostMapping("/save")
    public R save(@RequestBody SeckillSkuNoticeEntity seckillSkuNotice){
		seckillSkuNoticeService.save(seckillSkuNotice);
        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public R update(@RequestBody SeckillSkuNoticeEntity seckillSkuNotice){
		seckillSkuNoticeService.updateById(seckillSkuNotice);
        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		seckillSkuNoticeService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
