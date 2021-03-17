package com.SpringBoot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("com.SpringBoot.mapper")
public class SpringBootErpApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(SpringBootErpApplication.class, args);
	}
	
	@Override

	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {

	return builder.sources(SpringBootErpApplication.class);

	}
}
