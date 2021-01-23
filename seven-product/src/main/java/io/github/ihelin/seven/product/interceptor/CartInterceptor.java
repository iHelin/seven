package io.github.ihelin.seven.product.interceptor;

import io.github.ihelin.seven.common.dto.MemberRsepVo;
import io.github.ihelin.seven.common.utils.CartConstant;
import io.github.ihelin.seven.common.utils.JsonUtils;
import io.github.ihelin.seven.common.utils.MemberServerConstant;
import io.github.ihelin.seven.product.vo.UserInfoTo;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @author iHelin
 */
public class CartInterceptor implements HandlerInterceptor {

    public static final ThreadLocal<UserInfoTo> THREAD_LOCAL = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        UserInfoTo userInfoTo = new UserInfoTo();
        HttpSession session = request.getSession();
        Object loginUser = session.getAttribute(MemberServerConstant.LOGIN_USER);
        if (loginUser != null) {
            // 用户登录了
            String loginUserString = JsonUtils.toJSONString(loginUser);
            MemberRsepVo user = JsonUtils.parseObject(loginUserString, MemberRsepVo.class);
            userInfoTo.setUsername(user.getUsername());
            userInfoTo.setUserId(user.getId());
        }
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if (CartConstant.TEMP_USER_COOKIE_NAME.equals(name)) {
                    userInfoTo.setUserKey(cookie.getValue());
                    userInfoTo.setTempUser(true);
                }
            }
        }
        // 如果没有临时用户 则分配一个临时用户
        if (StringUtils.isEmpty(userInfoTo.getUserKey())) {
            String userKey = UUID.randomUUID().toString().replace("-", "");
            userInfoTo.setUserKey(userKey);
        }
        THREAD_LOCAL.set(userInfoTo);
        return true;
    }

    /**
     * 执行完毕之后分配临时用户让浏览器保存
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
        UserInfoTo userInfoTo = THREAD_LOCAL.get();
        if (!userInfoTo.isTempUser()) {
            Cookie cookie = new Cookie(CartConstant.TEMP_USER_COOKIE_NAME, userInfoTo.getUserKey());
            // 设置这个cookie作用域 过期时间
            cookie.setDomain("seven.com");
            cookie.setMaxAge(CartConstant.TEMP_USER_COOKIE_TIME_OUT);
            response.addCookie(cookie);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        THREAD_LOCAL.remove();
    }
}
