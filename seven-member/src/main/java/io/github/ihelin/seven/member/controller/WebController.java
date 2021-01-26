package io.github.ihelin.seven.member.controller;

import io.github.ihelin.seven.common.utils.R;
import io.github.ihelin.seven.member.feign.OrderFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

@Controller
public class WebController {

    @Autowired
    private OrderFeign orderFeign;

    @GetMapping("/memberOrder.html")
    public String memberOrderPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                  Model model) {
        // 这里可以获取到支付宝给我们传来的所有数据
        // 查出当前登录用户的所有订单
        HashMap<String, Object> page = new HashMap<>();
        page.put("page", pageNum);
        R r = orderFeign.listWithItem(page);
        model.addAttribute("orders", r);
        return "orderList";
    }
}
