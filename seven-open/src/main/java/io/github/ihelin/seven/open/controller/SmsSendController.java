package io.github.ihelin.seven.open.controller;

import io.github.ihelin.seven.common.utils.R;
import io.github.ihelin.seven.open.component.SmsComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sms")
public class SmsSendController {

    @Autowired
    private SmsComponent smsComponent;

    /**
     * 提供给别的服务进行调用的
     */
    @ResponseBody
    @GetMapping("/sendcode")
    public R sendCode(String phone, String code) {
        smsComponent.sendSmsCode(phone, code);
        return R.ok();
    }
}
