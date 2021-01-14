package io.github.ihelin.seven.order.controller;

import io.github.ihelin.seven.common.utils.PageUtils;
import io.github.ihelin.seven.common.utils.R;
import io.github.ihelin.seven.order.entity.PaymentInfoEntity;
import io.github.ihelin.seven.order.service.PaymentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * oms_payment_info
 *
 * @author iHelin ihelin@outlook.com
 * @since 2021-01-14 14:32:51
 */
@RestController
@RequestMapping("order/paymentinfo")
public class PaymentInfoController {

    @Autowired
    private PaymentInfoService paymentInfoService;

    /**
     * 列表
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = paymentInfoService.queryPage(params);

        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		PaymentInfoEntity paymentInfo = paymentInfoService.getById(id);

        return R.ok().put("data", paymentInfo);
    }

    /**
     * 新增
     */
    @PostMapping("/save")
    public R save(@RequestBody PaymentInfoEntity paymentInfo){
		paymentInfoService.save(paymentInfo);
        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public R update(@RequestBody PaymentInfoEntity paymentInfo){
		paymentInfoService.updateById(paymentInfo);
        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		paymentInfoService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
