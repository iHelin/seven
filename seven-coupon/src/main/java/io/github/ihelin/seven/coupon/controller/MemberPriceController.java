package io.github.ihelin.seven.coupon.controller;

import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.common.utils.R;
import io.github.ihelin.seven.coupon.entity.MemberPriceEntity;
import io.github.ihelin.seven.coupon.service.MemberPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * sms_member_price
 *
 * @author iHelin ihelin@outlook.com
 * @since 2021-01-14 14:19:56
 */
@RestController
@RequestMapping("coupon/memberprice")
public class MemberPriceController {

    @Autowired
    private MemberPriceService memberPriceService;

    /**
     * 列表
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = memberPriceService.queryPage(params);

        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		MemberPriceEntity memberPrice = memberPriceService.getById(id);

        return R.ok().put("data", memberPrice);
    }

    /**
     * 新增
     */
    @PostMapping("/save")
    public R save(@RequestBody MemberPriceEntity memberPrice){
		memberPriceService.save(memberPrice);
        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public R update(@RequestBody MemberPriceEntity memberPrice){
		memberPriceService.updateById(memberPrice);
        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		memberPriceService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
