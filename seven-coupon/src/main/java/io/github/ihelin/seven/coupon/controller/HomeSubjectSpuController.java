package io.github.ihelin.seven.coupon.controller;

import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.common.utils.R;
import io.github.ihelin.seven.coupon.entity.HomeSubjectSpuEntity;
import io.github.ihelin.seven.coupon.service.HomeSubjectSpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * sms_home_subject_spu
 *
 * @author iHelin ihelin@outlook.com
 * @since 2021-01-14 14:19:56
 */
@RestController
@RequestMapping("coupon/homesubjectspu")
public class HomeSubjectSpuController {

    @Autowired
    private HomeSubjectSpuService homeSubjectSpuService;

    /**
     * 列表
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = homeSubjectSpuService.queryPage(params);

        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		HomeSubjectSpuEntity homeSubjectSpu = homeSubjectSpuService.getById(id);

        return R.ok().put("data", homeSubjectSpu);
    }

    /**
     * 新增
     */
    @PostMapping("/save")
    public R save(@RequestBody HomeSubjectSpuEntity homeSubjectSpu){
		homeSubjectSpuService.save(homeSubjectSpu);
        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public R update(@RequestBody HomeSubjectSpuEntity homeSubjectSpu){
		homeSubjectSpuService.updateById(homeSubjectSpu);
        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		homeSubjectSpuService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
