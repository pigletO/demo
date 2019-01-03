package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/*@RestController
@EnableAutoConfiguration*/
@Controller
public class HelloController {

    Logger logger = LoggerFactory.getLogger(HelloController.class);

    @ResponseBody //配合Controller使用
    @RequestMapping("/hello")
    public String hello(){
        logger.info("执行hello请求！");
        return "myFirst SpringBoot Demo!!!!!";
    }

    @RequestMapping("/abc")
    public String thymeleaf(Map<String,Object> map){
        logger.info("执行abc请求！");
        map.put("list", Arrays.asList("item1","item2","item3","item4"));
        map.put("div1", "<h1>div1's value</h1>");
        return "thymeleaf";
    }

    //@RequestMapping(value = "/{id}/index",method = RequestMethod.GET)
    @GetMapping(value = "/{id}/index")
    public String welComePage(@PathVariable(value = "id") String id){
        logger.info("执行/"+id+"/index请求！");
        System.out.println(id);
        return "index";
    }

    /**
     * 从index页面跳转dashboard页面
     * @param Username
     * @param Password
     * @param map
     * @return
     */
    //@RequestMapping(value = "/dashboard",method = RequestMethod.POST)
    @PostMapping(value = "/dashboard")
    public String turnToDashboard(String Username,String Password,Map<String,Object> map){
        if("abc".equals(Username)&&"123".equals(Password))
            return "dashboard";
        else
            map.put("msg", "用户名或密码不正确！");
        return "index";
    }

    /**
     * 从dashboard页面跳转list页面
     * @return
     */
    //@RequestMapping(value = "/list",method = RequestMethod.GET)
    @GetMapping(value = "/list")
    public String turnToList(){

        return "list";
    }

}
