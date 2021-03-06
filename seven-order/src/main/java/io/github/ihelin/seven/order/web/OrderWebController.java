package io.github.ihelin.seven.order.web;

import io.github.ihelin.seven.common.exception.NoStockException;
import io.github.ihelin.seven.order.service.OrderService;
import io.github.ihelin.seven.order.vo.OrderConfirmVo;
import io.github.ihelin.seven.order.vo.OrderSubmitVo;
import io.github.ihelin.seven.order.vo.SubmitOrderResponseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.concurrent.ExecutionException;

@Controller
public class OrderWebController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderService orderService;

    @GetMapping("/toTrade")
    public String toTrade(Model model) throws ExecutionException, InterruptedException {
        OrderConfirmVo confirmVo = orderService.confirmOrder();
        model.addAttribute("orderConfirmData", confirmVo);
        return "confirm";
    }

    /**
     * 下单功能
     */
    @PostMapping("/submitOrder")
    public String submitOrder(OrderSubmitVo submitVo, Model model, RedirectAttributes redirectAttributes) {
        try {
            SubmitOrderResponseVo responseVo = orderService.submitOrder(submitVo);
            // 下单失败回到订单重新确认订单信息
            if (responseVo.getCode() == 0) {
                // 下单成功取支付选项
                model.addAttribute("submitOrderResp", responseVo);
                return "pay";
            } else {
                String msg = "下单失败，";
                switch (responseVo.getCode()) {
                    case 1:
                        msg += "订单信息过期,请刷新在提交";
                        break;
                    case 2:
                        msg += "订单商品价格发送变化,请确认后再次提交";
                        break;
                    case 3:
                        msg += "商品库存不足";
                        break;
                }
                redirectAttributes.addAttribute("msg", msg);
                return "redirect:http://order.seven.com/toTrade";
            }
        } catch (Exception e) {
            logger.warn("submitOrder error", e);
            if (e instanceof NoStockException) {
                String message = e.getMessage();
                redirectAttributes.addAttribute("msg", message);
            }
            return "redirect:http://order.seven.com/toTrade";
        }
    }
}
