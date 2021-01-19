package io.github.ihelin.seven.open;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SevenOpenApplication {

    public static void main(String[] args) {
        SpringApplication.run(SevenOpenApplication.class, args);
    }

}
