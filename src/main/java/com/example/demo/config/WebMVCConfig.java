package com.example.demo.config;

import com.example.demo.entity.Humen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

// 声明此类为配置类，实现WebMvcConfigurer类，实现其中的方法，实现拓展配置springmvc
// 区别于WebMvcConfigurationSupport类，继承WebMvcConfigurationSupport类，会springboot对MVC的自动配置！！
@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 默认“/”请求会在静态资源默认目录下寻找index.html，再次配置index请求映射到templates/index.html
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
}
