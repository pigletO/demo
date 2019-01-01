package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 声明此类为配置类，继承WebMvcConfigurationSupport类，实现其中的方法，实现自定义配置springmvc
@Configuration
public class WebMVNConfiguration extends WebMvcConfigurationSupport {

    /**
     * 拓展webMVC中的视图控制器，向容器中添加指定请求和处理
     * @param registry
     */
    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {
        //super.addViewControllers(registry);
        registry.addViewController("support").setViewName("thymeleaf");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
    }
}
