package com.example.xhs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(scanBasePackages = "com.example.xhs")
@MapperScan("com.example.xhs.dao")
public class XhsApplication {

	public static void main(String[] args) {
		SpringApplication.run(XhsApplication.class, args);
	}

}
