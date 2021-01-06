package io.github.ihelin.seven.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SevenGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SevenGatewayApplication.class, args);
    }

}
