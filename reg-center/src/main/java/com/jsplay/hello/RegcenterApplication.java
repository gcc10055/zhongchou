package com.jsplay.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 启用服务发现，等同于创建一个服务注册中心
 */
@EnableEurekaServer
@SpringBootApplication
public class RegcenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegcenterApplication.class, args);
	}
}
