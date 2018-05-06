package com.jsplay.hello;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@MapperScan("com.jsplay.hello.**.dao")
@EnableDiscoveryClient
@SpringBootApplication
public class MemberServiceServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MemberServiceServerApplication.class, args);
	}
}
