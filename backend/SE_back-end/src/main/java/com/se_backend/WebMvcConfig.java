package com.se_backend;


import com.se_backend.Interceptor.IPInterceptor;
import com.se_backend.Interceptor.PowerInterceptor;
import com.se_backend.Interceptor.RequestBodyFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
    @Autowired
    private PowerInterceptor powerInterceptor;
    @Autowired
    private IPInterceptor ipInterceptor;
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")//拦截所有请求
                .allowedOriginPatterns("*")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedMethods("*");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(ipInterceptor).addPathPatterns("/Test","/Test/*");
        registry.addInterceptor(powerInterceptor).addPathPatterns("/Test","/Test/*",
                "/ContentManagement/getVerificationCode",
                "/ContentManagement/checkVerificationCode",
                "/ContentManagement/Video/*",
                "/ContentManagement/GetRole",
                "/PersonalArticles/addPersonalArticles");
    }
    @Bean
    public FilterRegistrationBean setLogServiceFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        RequestBodyFilter requestBodyFilter = new RequestBodyFilter();
        registrationBean.setFilter(requestBodyFilter);
        registrationBean.setName("interceptor filter body params");
        registrationBean.addUrlPatterns("/Test","/Test/*",
                "/ContentManagement/getVerificationCode",
                "/ContentManagement/checkVerificationCode",
                "/ContentManagement/Video/*",
                "/ContentManagement/GetRole",
                "/PersonalArticles/addPersonalArticles");
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
