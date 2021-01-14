package io.github.ihelin.seven.order.controller;

import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.common.utils.R;
import io.github.ihelin.seven.order.entity.RefundInfoEntity;
import io.github.ihelin.seven.order.service.RefundInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * oms_refund_info
 *
 * @author iHelin ihelin@outlook.com
 * @since 2021-01-14 14:32:51
 */
@RestController
@RequestMapping("order/refundinfo")
public class RefundInfoController {

    @Autowired
    private RefundInfoService refundInfoService;

    /**
     * 列表
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = refundInfoService.queryPage(params);

        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		RefundInfoEntity refundInfo = refundInfoService.getById(id);

        return R.ok().put("data", refundInfo);
    }

    /**
     * 新增
     */
    @PostMapping("/save")
    public R save(@RequestBody RefundInfoEntity refundInfo){
		refundInfoService.save(refundInfo);
        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public R update(@RequestBody RefundInfoEntity refundInfo){
		refundInfoService.updateById(refundInfo);
        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		refundInfoService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
