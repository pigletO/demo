package com.example.demo.component;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class LocaleResolver implements org.springframework.web.servlet.LocaleResolver {
    /**
     * 实现LocaleResolver接口，使springmvc的localresolver失效，实现切换国际化
     * @param httpServletRequest
     * @return
     */
    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        //String l = httpServletRequest.getAttribute("l").toString();
        String l = httpServletRequest.getParameter("l");
        Locale locale = Locale.getDefault();
        if(!StringUtils.isEmpty(l)){
            // 按照_分割http请求地址中的zh_CN、en_US等
            String [] splits = l.split("_");
            locale = new Locale(splits[0], splits[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
