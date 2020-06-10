package com.example.demo.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/hello")
//    @ResponseBody
    public String hello() {
        return "hello.html";
    }

}
