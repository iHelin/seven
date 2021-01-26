package io.github.ihelin.seven.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.ihelin.seven.common.dto.MemberRsepVo;
import io.github.ihelin.seven.common.exception.BizCodeEnum;
import io.github.ihelin.seven.common.utils.HttpUtils;
import io.github.ihelin.seven.common.utils.MemberServerConstant;
import io.github.ihelin.seven.common.utils.R;
import io.github.ihelin.seven.member.feign.OpenFeign;
import io.github.ihelin.seven.member.service.MemberService;
import io.github.ihelin.seven.member.vo.SocialUser;
import io.github.ihelin.seven.member.vo.UserLoginVo;
import io.github.ihelin.seven.member.vo.UserRegisterVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author iHelin
 * @since 2021/1/19 21:46
 */
@Controller
public class LoginController {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OpenFeign openFeign;

    @Autowired
    private MemberService memberService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping({"/login.html"})
    public String loginPage(HttpSession session) {
        Object attribute = session.getAttribute(MemberServerConstant.LOGIN_USER);
        if (attribute == null) {
            return "login";
        }
        return "redirect:http://seven.com";
    }

    @PostMapping("/login")
    public String login(UserLoginVo userLoginVo, RedirectAttributes redirectAttributes, HttpSession session) {
        MemberRsepVo memberRsepVo = memberService.login(userLoginVo);
        if (memberRsepVo != null) {
            // 登录成功
            session.setAttribute(MemberServerConstant.LOGIN_USER, memberRsepVo);
            logger.info("\n欢迎 [" + memberRsepVo.getUsername() + "] 登录");
            return "redirect:http://seven.com";
        } else {
            HashMap<String, String> error = new HashMap<>();
            error.put("msg", "用户名密码不匹配");
            redirectAttributes.addFlashAttribute("errors", error);
            return "redirect:http://auth.seven.com/login.html";
        }
    }

    @ResponseBody
    @GetMapping("/sms/sendcode")
    public R sendCode(String phone) {
        // TODO 接口防刷
        String redisCode = stringRedisTemplate.opsForValue().get(MemberServerConstant.SMS_CODE_CACHE_PREFIX + phone);
        if (StringUtils.isNotEmpty(redisCode)) {
            long sendTime = Long.parseLong(redisCode.split("_")[1]);
            if (System.currentTimeMillis() - sendTime < 60 * 1000) {
                return R.error(BizCodeEnum.SMS_CODE_EXCEPTION.getCode(), BizCodeEnum.SMS_CODE_EXCEPTION.getMsg());
            }
        }
        String code = UUID.randomUUID().toString().substring(0, 6);
        redisCode = code + "_" + System.currentTimeMillis();
        // 缓存验证码
        stringRedisTemplate.opsForValue().set(MemberServerConstant.SMS_CODE_CACHE_PREFIX + phone, redisCode, 10, TimeUnit.MINUTES);
        try {
            return openFeign.sendCode(phone, code);
        } catch (Exception e) {
            logger.warn("远程调用sms错误", e);
        }
        return R.ok();
    }

    /**
     * TODO 重定向携带数据,利用session原理 将数据放在sessoin中 取一次之后删掉
     * <p>
     * TODO 1. 分布式下的session问题
     * 校验
     * RedirectAttributes redirectAttributes ： 模拟重定向带上数据
     */
    @PostMapping("/register")
    public String register(@Valid UserRegisterVo vo, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            // 将错误属性与错误信息一一封装
            Map<String, String> errors = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField,
                DefaultMessageSourceResolvable::getDefaultMessage));
            // addFlashAttribute 这个数据只取一次
            redirectAttributes.addFlashAttribute("errors", errors);
            return "redirect:http://auth.seven.com/reg.html";
        }
        // 开始注册 调用远程服务
        // 1.校验验证码
        String code = vo.getCode();

        String redisCode = stringRedisTemplate.opsForValue().get(MemberServerConstant.SMS_CODE_CACHE_PREFIX + vo.getPhone());
        if (StringUtils.isNotEmpty(redisCode)) {
            // 验证码通过
            if (code.equals(redisCode.split("_")[0])) {
                // 删除验证码
                stringRedisTemplate.delete(MemberServerConstant.SMS_CODE_CACHE_PREFIX + vo.getPhone());
                memberService.register(vo);
                // 成功
                return "redirect:http://auth.seven.com/login.html";
            } else {
                Map<String, String> errors = new HashMap<>();
                errors.put("code", "验证码错误");
                // addFlashAttribute 这个数据只取一次
                redirectAttributes.addFlashAttribute("errors", errors);
                return "redirect:http://auth.seven.com/reg.html";
            }
        } else {
            Map<String, String> errors = new HashMap<>();
            errors.put("code", "验证码错误");
            // addFlashAttribute 这个数据只取一次
            redirectAttributes.addFlashAttribute("errors", errors);
            return "redirect:http://auth.seven.com/reg.html";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:http://auth.seven.com/login.html";
    }

    @GetMapping("/oauth2.0/weibo/success")
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
            MemberRsepVo memberRsepVo = memberService.login(socialUser);
            logger.info("\n欢迎 [" + memberRsepVo.getUsername() + "] 使用社交账号登录");
            // 第一次使用session 命令浏览器保存这个用户信息 JESSIONSEID 每次只要访问这个网站就会带上这个cookie
            // 在发卡的时候扩大session作用域 (指定域名为父域名)
            // 1.默认发的当前域的session (需要解决子域session共享问题) io.github.ihelin.seven.member.config.SessionConfig.cookieSerializer
            // 2.使用JSON的方式序列化到redis io.github.ihelin.seven.member.config.SessionConfig.springSessionDefaultRedisSerializer
            session.setAttribute(MemberServerConstant.LOGIN_USER, memberRsepVo);
            // 登录成功 跳回首页
            return "redirect:http://seven.com";
        } else {
            return "redirect:http://auth.seven.com/login.html";
        }
    }

}
