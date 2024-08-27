package com.se_backend.Interceptor;

import com.se_backend.DB.DB_Mapper.IPWhiteListMapper;
import com.se_backend.DB.DB_object.IPWhiteList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Objects;

@Component
public class IPInterceptor implements HandlerInterceptor {
    @Autowired
    private IPWhiteListMapper ipWhiteListMapper;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ArrayList<IPWhiteList> WhileList = ipWhiteListMapper.GetAllWhiteList();
        for (IPWhiteList IPTable:WhileList
             ) {
            if(Objects.equals(IPTable.getIP(), request.getRemoteAddr()))
                return true;
        }
        System.out.println("非法IP地址！"+request.getRemoteAddr());
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
