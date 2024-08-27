package com.se_backend.Projects.Video_project.Video_Controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.io.IOException;

@Configuration
public class Video_ShowController implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
            File directory = new File("src/main/resources/Video");
            String path = null;
            try {
                path = directory.getCanonicalPath();
            }catch (IOException e){
                e.printStackTrace();
            }
            registry.addResourceHandler("/Video/**").addResourceLocations("file:"+path+"/");
        }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/Video/**")//拦截所有请求
                .allowedOriginPatterns("*")
                .allowedOrigins("http://127.0.0.1")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedMethods("*");
    }
}
