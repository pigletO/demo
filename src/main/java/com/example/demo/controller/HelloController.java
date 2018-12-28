package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/*@RestController
@EnableAutoConfiguration*/
@Controller
public class HelloController {


    @ResponseBody //配合Controller使用
    @RequestMapping("/hello")
    public String hello(){
        return "myFirst SpringBoot Demo!!!!!";
    }

    @RequestMapping("/abc")
    public String thymeleaf(Map<String,Object> map){
        map.put("list", Arrays.asList("item1","item2","item3","item4"));
        map.put("div1", "<h1>div1's value</h1>");
        return "thymeleaf";
    }
}
