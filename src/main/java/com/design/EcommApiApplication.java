package com.design;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
//@EnableEurekaClient
public class EcommApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommApiApplication.class, args);
	}

}
