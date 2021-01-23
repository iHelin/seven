package io.github.ihelin.seven.order.interceptor;

import io.github.ihelin.seven.common.dto.MemberRsepVo;
import io.github.ihelin.seven.common.utils.JsonUtils;
import io.github.ihelin.seven.common.utils.MemberServerConstant;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginUserInterceptor implements HandlerInterceptor {

    public static final ThreadLocal<MemberRsepVo> THREAD_LOCAL = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        // 这个请求直接放行
        boolean match = new AntPathMatcher().match("/order/order/status/**", uri);
        if (match) {
            return true;
        }
        HttpSession session = request.getSession();
        Object loginUser = session.getAttribute(MemberServerConstant.LOGIN_USER);
        if (loginUser != null) {
            String loginUserString = JsonUtils.toJSONString(loginUser);
            MemberRsepVo memberRsepVo = JsonUtils.parseObject(loginUserString, MemberRsepVo.class);
            THREAD_LOCAL.set(memberRsepVo);
            return true;
        } else {
            // 没登录就去登录
            session.setAttribute("msg", MemberServerConstant.NOT_LOGIN);
            response.sendRedirect("http://auth.seven.com/login.html");
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        THREAD_LOCAL.remove();
    }
}
