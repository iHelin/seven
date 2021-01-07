package io.github.ihelin.seven.generator;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("io.github.ihelin.seven.generator.dao")
public class GeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeneratorApplication.class, args);
	}
}
