package io.github.ihelin.seven.member.controller;

import io.github.ihelin.seven.common.exception.BizCodeEnum;
import io.github.ihelin.seven.common.utils.MemberServerConstant;
import io.github.ihelin.seven.common.utils.R;
import io.github.ihelin.seven.member.feign.OpenFeign;
import io.github.ihelin.seven.member.service.MemberService;
import io.github.ihelin.seven.member.vo.UserRegisterVo;
import org.apache.commons.lang3.StringUtils;
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
    private OpenFeign openFeign;

    @Autowired
    private MemberService memberService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping({"/login.html", "/", "/index", "/index.html"})
    public String loginPage(HttpSession session) {
        Object attribute = session.getAttribute(MemberServerConstant.LOGIN_USER);
        if (attribute == null) {
            return "login";
        }
        return "redirect:http://seven.com";
    }

//    @PostMapping("/login")
//    public String login(UserLoginVo userLoginVo, RedirectAttributes redirectAttributes, HttpSession session){
//        // 远程登录
//        R r = memberFeignService.login(userLoginVo);
//        if(r.getCode() == 0){
//            // 登录成功
//            MemberRsepVo rsepVo = r.getData("data", new TypeReference<MemberRsepVo>() {});
//            session.setAttribute(AuthServerConstant.LOGIN_USER, rsepVo);
//            log.info("\n欢迎 [" + rsepVo.getUsername() + "] 登录");
//            return "redirect:http://seven.com";
//        }else {
//            HashMap<String, String> error = new HashMap<>();
//            // 获取错误信息
//            error.put("msg", r.getData("msg",new TypeReference<String>(){}));
//            redirectAttributes.addFlashAttribute("errors", error);
//            return "redirect:http://auth.seven.com/login.html";
//        }
//    }

    @ResponseBody
    @GetMapping("/sms/snedcode")
    public R sendCode(String phone) {
        // TODO 接口防刷
        String redisCode = stringRedisTemplate.opsForValue().get(MemberServerConstant.SMS_CODE_CACHE_PREFIX + phone);
        if (null != redisCode && redisCode.length() > 0) {
            long cuuTime = Long.parseLong(redisCode.split("_")[1]);
            if (System.currentTimeMillis() - cuuTime < 60 * 1000) {
                return R.error(BizCodeEnum.SMS_CODE_EXCEPTION.getCode(), BizCodeEnum.SMS_CODE_EXCEPTION.getMsg());
            }
        }
        String code = UUID.randomUUID().toString().substring(0, 6);
        String redis_code = code + "_" + System.currentTimeMillis();
        // 缓存验证码
        stringRedisTemplate.opsForValue().set(MemberServerConstant.SMS_CODE_CACHE_PREFIX + phone, redis_code, 10, TimeUnit.MINUTES);
        try {
            return openFeign.sendCode(phone, code);
        } catch (Exception e) {
            logger.warn("远程调用错误",e);
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
            Map<String, String> errors = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, DefaultMessageSourceResolvable::getDefaultMessage));
            // addFlashAttribute 这个数据只取一次
            redirectAttributes.addFlashAttribute("errors", errors);
            return "redirect:http://auth.seven.com/reg.html";
        }
        // 开始注册 调用远程服务
        // 1.校验验证码
        String code = vo.getCode();

        String redis_code = stringRedisTemplate.opsForValue().get(MemberServerConstant.SMS_CODE_CACHE_PREFIX + vo.getPhone());
        if (StringUtils.isNotEmpty(redis_code)) {
            // 验证码通过
            if (code.equals(redis_code.split("_")[0])) {
                // 删除验证码
                stringRedisTemplate.delete(MemberServerConstant.SMS_CODE_CACHE_PREFIX + vo.getPhone());
                // 调用远程服务进行注册
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

}
