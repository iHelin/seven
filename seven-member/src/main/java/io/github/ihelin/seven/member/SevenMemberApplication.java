package io.github.ihelin.seven.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "io.github.ihelin.seven.member.feign")
@SpringBootApplication
public class SevenMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(SevenMemberApplication.class, args);
    }

}
