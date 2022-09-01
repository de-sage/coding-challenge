package com.dev.codingchallenge.userportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class UserPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserPortalApplication.class, args);
	}

}
