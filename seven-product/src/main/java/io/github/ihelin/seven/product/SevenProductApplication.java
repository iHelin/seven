package io.github.ihelin.seven.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author iHelin
 * @since 2021/1/4 22:34
 */
@MapperScan("io.github.ihelin.seven.product.dao")
@SpringBootApplication
public class SevenProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(SevenProductApplication.class);
    }
}
