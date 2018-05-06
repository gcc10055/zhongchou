package com.jsplay.hello.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/4/19.
 */
@RestController
public class DispatcherController {

    @RequestMapping("/test")
    public String test() {
        return "member service !";
    }
}
