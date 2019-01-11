package com.example.demo.component;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {
    // 响应请求前执行以下方法
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object loginUser = request.getSession().getAttribute("loginUser");
        if(loginUser == null){
            // 直接取request，进行setAttribute
            request.setAttribute("msg","用户未登录，请先登录！");
            // 没有拿到已经登录的用户名，转发到登录界面，****此处只能用转发，做服务器内转发的时候从第一次请求到响应都共用同一个request和response，重定向的时候是多次request和response对象
            request.getRequestDispatcher("/index").forward(request,response);
            // 重定向
            //response.sendRedirect("/index");
            // 不放行
            return false;
        }
        // 已取到已登录用户名称，放行，允许请求访问地址
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
