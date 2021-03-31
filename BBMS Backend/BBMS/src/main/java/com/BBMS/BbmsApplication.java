package com.BBMS;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@CrossOrigin(origins="*")
@SpringBootApplication
@EnableSwagger2
public class BbmsApplication {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().build();
	}

	public static void main(String[] args) {
		SpringApplication.run(BbmsApplication.class, args);
	}
}
