package com.example.demo.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Data
@Component
// 指定此Bean中的属性在配置文件中以humen为前缀进行配置
@ConfigurationProperties(prefix = "humen")
// 指定配置文件的路径，默认为全局配置文件
@PropertySource(value = "classpath:/properties/humen.properties")
public class Humen {

    public String name;

    public String sex;

    public int age;

    public Map<String, Object> parent;

    public List<String> hobby;

    public String type;

    public Pet pet;
}
