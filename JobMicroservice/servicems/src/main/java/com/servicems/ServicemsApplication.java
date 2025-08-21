package com.servicems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServicemsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicemsApplication.class, args);
	}

}
