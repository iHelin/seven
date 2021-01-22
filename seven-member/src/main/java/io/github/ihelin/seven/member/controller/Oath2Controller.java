package io.github.ihelin.seven.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.ihelin.seven.common.dto.MemberRsepVo;
import io.github.ihelin.seven.common.utils.HttpUtils;
import io.github.ihelin.seven.common.utils.MemberServerConstant;
import io.github.ihelin.seven.member.service.MemberService;
import io.github.ihelin.seven.member.vo.SocialUser;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/oauth2.0")
public class Oath2Controller {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MemberService memberService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:http://auth.seven.com/login.html";
    }

    @GetMapping("/weibo/success")
    public String weiBo(@RequestParam("code") String code, HttpSession session) throws Exception {

        // 根据code换取 Access Token
        Map<String, String> map = new HashMap<>();
        map.put("client_id", "166097238");
        map.put("client_secret", "3a40e09beb620bbb1fe19deca09a2180");
        map.put("grant_type", "authorization_code");
        map.put("redirect_uri", "http://auth.seven.com/oauth2.0/weibo/success");
        map.put("code", code);
        Map<String, String> headers = new HashMap<>();
        HttpResponse response = HttpUtils.doPost("https://api.weibo.com", "/oauth2/access_token", "post", headers, null, map);
        if (response.getStatusLine().getStatusCode() == 200) {
            // 获取到了 Access Token
            String json = EntityUtils.toString(response.getEntity());
            SocialUser socialUser = objectMapper.readValue(json, SocialUser.class);

            // 相当于我们知道了当前是那个用户
            // 1.如果用户是第一次进来 自动注册进来(为当前社交用户生成一个会员信息 以后这个账户就会关联这个账号)
            MemberRsepVo memberEntity = memberService.login(socialUser);
            logger.info("\n欢迎 [" + memberEntity.getUsername() + "] 使用社交账号登录");
            // 第一次使用session 命令浏览器保存这个用户信息 JESSIONSEID 每次只要访问这个网站就会带上这个cookie
            // 在发卡的时候扩大session作用域 (指定域名为父域名)
            // TODO 1.默认发的当前域的session (需要解决子域session共享问题)
            // TODO 2.使用JSON的方式序列化到redis
            session.setAttribute(MemberServerConstant.LOGIN_USER, memberEntity);
            // 登录成功 跳回首页
            return "redirect:http://seven.com";
        } else {
            return "redirect:http://auth.seven.com/login.html";
        }
    }
}
