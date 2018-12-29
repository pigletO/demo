package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 声明此类为配置类，继承WebMvcConfigurationSupport类，实现其中的方法，实现自定义配置springmvc
@Configuration
public class WebMVNConfiguration extends WebMvcConfigurationSupport {

    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {
        //super.addViewControllers(registry);
        registry.addViewController("support").setViewName("thymeleaf");
    }
}
