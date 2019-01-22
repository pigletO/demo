package com.example.demo.controller;

import com.example.demo.dao.EmployeeDao;
import com.example.demo.entity.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;

/*@RestController
@EnableAutoConfiguration*/
@Controller
public class HelloController {

    Logger logger = LoggerFactory.getLogger(HelloController.class);

    @ResponseBody //配合Controller使用
    @RequestMapping("/hello")
    public String hello(){
        logger.info("执行hello请求！");
        /*return "myFirst SpringBoot Demo!!!!!";*/
        return UUID.randomUUID().toString().substring(0, 16);
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
    @PostMapping(value = "/user/dashboard")
    public String turnToDashboard(String Username, String Password, Map<String,Object> map, HttpSession session){
        if("123".equals(Password)) {
            // 向Session中添加参数，以判断是否有用户已经登录
            session.setAttribute("loginUser", Username);
            // 为防止表单重复提交，进行重定向，'/'代表请求根地址，不带'/'即在当前地址后继续追加
            return "redirect:/main.html";
        }else
            map.put("msg", "用户名或密码不正确！");
        return "index";
    }

}
