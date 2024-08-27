package com.se_backend.Projects.example_project_mp4.Mp4Controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.io.IOException;

@Configuration
public class Mp4ShowController implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
            File directory = new File("src/resources/mp4s");
            String path = null;
            try {
                path = directory.getCanonicalPath();
            }catch (IOException e){
                e.printStackTrace();
            }
            registry.addResourceHandler("/mp4s/**").addResourceLocations("file:"+path+"/");
        }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/mp4s/**")//拦截所有请求
                .allowedOriginPatterns("*")
                .allowedOrigins("http://81.68.84.163")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedMethods("*");
    }
}
