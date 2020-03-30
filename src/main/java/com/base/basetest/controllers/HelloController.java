package com.base.basetest.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Value("${myperson.say}")
    private String sayHello;

    @RequestMapping("/hello")
    public String hello(){
        return sayHello;
    }
}
