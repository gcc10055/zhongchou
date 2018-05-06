package com.jsplay.hello.test.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Administrator on 2018/4/21.
 */
@FeignClient("eureka-member-service")
public interface PortalClient {
    @GetMapping("/test")
    String test();
}
