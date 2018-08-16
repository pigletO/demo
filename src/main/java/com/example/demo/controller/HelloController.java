package com.example.demo.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/*@RestController
@EnableAutoConfiguration*/
@Controller
public class HelloController {


    @ResponseBody //配合Controller使用
    @RequestMapping("/hello")
    public String hello(){
        return "myFirst SpringBoot Demo!!!!!";
    }
}
