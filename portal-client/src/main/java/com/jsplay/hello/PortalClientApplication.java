package com.jsplay.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@ServletComponentScan
@SpringBootApplication
public class PortalClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortalClientApplication.class, args);
	}
}
