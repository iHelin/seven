package io.github.ihelin.seven.coupon.controller;

import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.common.utils.R;
import io.github.ihelin.seven.coupon.entity.HomeSubjectEntity;
import io.github.ihelin.seven.coupon.service.HomeSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * sms_home_subject
 *
 * @author iHelin ihelin@outlook.com
 * @since 2021-01-14 14:19:56
 */
@RestController
@RequestMapping("coupon/homesubject")
public class HomeSubjectController {

    @Autowired
    private HomeSubjectService homeSubjectService;

    /**
     * 列表
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = homeSubjectService.queryPage(params);

        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		HomeSubjectEntity homeSubject = homeSubjectService.getById(id);

        return R.ok().put("data", homeSubject);
    }

    /**
     * 新增
     */
    @PostMapping("/save")
    public R save(@RequestBody HomeSubjectEntity homeSubject){
		homeSubjectService.save(homeSubject);
        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public R update(@RequestBody HomeSubjectEntity homeSubject){
		homeSubjectService.updateById(homeSubject);
        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		homeSubjectService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
