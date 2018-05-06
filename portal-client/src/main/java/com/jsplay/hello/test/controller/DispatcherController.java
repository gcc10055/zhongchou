package com.jsplay.hello.test.controller;

import com.jsplay.hello.test.client.PortalClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/4/21.
 */
@RestController
public class DispatcherController {
    @Autowired
    private PortalClient portalClient;

    @RequestMapping("/test")
    public String test() {
        return portalClient.test();
    }
}
