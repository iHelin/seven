package io.github.ihelin.seven.order.config;

import io.github.ihelin.seven.order.interceptor.LoginUserInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MallWebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 放行支付宝回调请求
        registry.addInterceptor(new LoginUserInterceptor()).addPathPatterns("/**").excludePathPatterns("/payed/notify");
    }
}
