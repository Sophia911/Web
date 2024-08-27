package com.se_backend.Projects.example_project_images.ImagesController;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.io.IOException;

@Configuration
public class ImagesShowController implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
            File directory = new File("src/resources/images");
            String path = null;
            try {
                path = directory.getCanonicalPath();
            }catch (IOException e){
                e.printStackTrace();
            }
            registry.addResourceHandler("/images/**").addResourceLocations("file:"+path+"/");
        }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/images/**")//拦截所有请求
                .allowedOriginPatterns("*")
                .allowedOrigins("http://81.68.84.163")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedMethods("*");
    }
}
