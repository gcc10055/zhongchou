package com.jsplay.hello.test.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Administrator on 2018/4/20.
 */
@FeignClient("eureka-member-service")
public interface MemberClient {
    @GetMapping("/test")
    String test();
}
