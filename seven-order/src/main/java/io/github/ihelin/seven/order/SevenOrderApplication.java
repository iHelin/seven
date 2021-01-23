package io.github.ihelin.seven.order;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableFeignClients
@EnableRabbit
@EnableRedisHttpSession
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("io.github.ihelin.seven.order.dao")
public class SevenOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SevenOrderApplication.class, args);
    }

    @Bean
    public PaginationInterceptor paginationInterceptor(){
        PaginationInterceptor interceptor = new PaginationInterceptor();
        interceptor.setOverflow(true);
        interceptor.setLimit(500);
        return interceptor;
    }

}
