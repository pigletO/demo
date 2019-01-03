package com.example.demo.config;

import com.example.demo.component.LocaleResolver;
import com.example.demo.component.LoginHandlerInterceptor;
import com.example.demo.entity.Humen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import java.util.Locale;

// 声明此类为配置类，实现WebMvcConfigurer类，实现其中的方法，实现拓展配置springmvc
// 区别于WebMvcConfigurationSupport类，继承WebMvcConfigurationSupport类，会springboot对MVC的自动配置！！
@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 默认“/”请求会在静态资源默认目录下寻找index.html，再次配置index请求映射到templates/index.html
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
    }

    // 添加地址栏请求静态资源的映射
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        /*registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/asserts/css/**").addResourceLocations("classpath:/static/asserts/css/");
        registry.addResourceHandler("/asserts/js/**").addResourceLocations("classpath:/static/asserts/js/");
        registry.addResourceHandler("/asserts/img/**").addResourceLocations("classpath:/static/asserts/img/");*/
    }

    // 添加springmvc拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // webjars请求下的静态资源不需要排除，springboot已经做好了静态映射
        // 拦截/** 项目的所有请求，但要把请求登录页面和登录执行的RequestMapping除外
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/","/index","/index.html","/user/dashboard","/static/**");
       //registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/main.html");

    }

    // 此类为配置类优先于@Compent加载，所以加载此类时，容器中未存在Humen类，所以一定执行
    // 经测试，先给spring注入此类，再进行配置文件赋值配置并不冲突，可以正常赋值
    //给spring注入一个Humen组件
    @Bean
    @ConditionalOnBean(Humen.class)
    public Humen returnHumen(){
        Humen humen = new Humen();
        System.out.println(humen.toString());
        return humen;
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
       return new WebMvcConfigurer(){
           @Override
           public void addViewControllers(ViewControllerRegistry registry) {
               // 登录后进行重定向到指定html，以防止表单的重复提交
               registry.addViewController("/main.html").setViewName("dashboard");
           }
          /* @Override
           public void addInterceptors(InterceptorRegistry registry) {
               registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/","index","index.html","/user/dashboard");
           }*/
       };
    }

    @Bean
    public LocaleResolver localeResolver() {
       return new LocaleResolver();
    }
}
