package io.github.ihelin.seven.member;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableFeignClients(basePackages = "io.github.ihelin.seven.member.feign")
@SpringBootApplication
@EnableTransactionManagement
public class SevenMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(SevenMemberApplication.class, args);
    }

    @Bean
    public PaginationInterceptor paginationInterceptor(){
        PaginationInterceptor interceptor = new PaginationInterceptor();
        interceptor.setOverflow(true);
        interceptor.setLimit(500);
        return interceptor;
    }
}
