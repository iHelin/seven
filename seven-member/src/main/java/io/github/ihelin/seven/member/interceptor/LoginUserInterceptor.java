package io.github.ihelin.seven.member.interceptor;

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
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        String uri = request.getRequestURI();
        // 这个请求直接放行
        boolean match1 = antPathMatcher.match("/member/**", uri);
        if (match1) {
            return true;
        }
        boolean match2 = antPathMatcher.match("/login.html", uri);
        if (match2) {
            return true;
        }
        if (antPathMatcher.match("/oauth2.0/**", uri)) {
            return true;
        }
        if (antPathMatcher.match("/register", uri)) {
            return true;
        }
        if (antPathMatcher.match("/sms/sendcode", uri)) {
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
