package io.github.ihelin.seven.product;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author iHelin
 * @since 2021/1/4 22:34
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("io.github.ihelin.seven.product.dao")
@EnableFeignClients(basePackages = "io.github.ihelin.seven.product.feign")
public class SevenProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(SevenProductApplication.class);
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor interceptor = new PaginationInterceptor();
        interceptor.setOverflow(true);
        interceptor.setLimit(500);
        return interceptor;
    }
}
