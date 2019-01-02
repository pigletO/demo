package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

// 声明此类为配置类，实现WebMvcConfigurer类，实现其中的方法，实现拓展配置springmvc
// 区别于WebMvcConfigurationSupport类，继承WebMvcConfigurationSupport类，会springboot对MVC的自动配置！！
@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("support").setViewName("thymeleaf");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /*registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/asserts/css/**").addResourceLocations("classpath:/static/asserts/css/");
        registry.addResourceHandler("/asserts/js/**").addResourceLocations("classpath:/static/asserts/js/");
        registry.addResourceHandler("/asserts/img/**").addResourceLocations("classpath:/static/asserts/img/");*/
    }
}
