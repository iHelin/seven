package io.github.ihelin.seven.order.listener;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import io.github.ihelin.seven.order.config.AlipayTemplate;
import io.github.ihelin.seven.order.service.OrderService;
import io.github.ihelin.seven.order.vo.PayAsyncVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class OrderPayedListener {

    @Autowired
    private OrderService orderService;

    @Autowired
    private AlipayTemplate alipayTemplate;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @PostMapping("/payed/notify")
    public String handleAliPayed(PayAsyncVo vo, HttpServletRequest request) throws UnsupportedEncodingException, AlipayApiException {
        logger.info("\n收到支付宝最后的通知数据：\n" + vo);
//		Map<String, String[]> result = request.getParameterMap();
//		String map = "";
//		for (String key : result.keySet()) {
//			map += key + "-->" + request.getParameter(key) + "\n";
//		}
        // 验签
        Map<String, String> params = new HashMap<>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (String name : requestParams.keySet()) {
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                    : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
//			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        // 只要我们收到了支付宝给我们的异步通知 验签成功 我们就要给支付宝返回success
        if (AlipaySignature.rsaCheckV1(params, alipayTemplate.getAlipay_public_key(), alipayTemplate.getCharset(), alipayTemplate.getSign_type())) {
            return orderService.handlePayResult(vo);
        }
        logger.warn("\n受到恶意验签攻击");
        return "fail";
    }
}
