package com.se_backend.Projects.About_page_project;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.io.IOException;
@Configuration
public class AboutImgShowController implements WebMvcConfigurer{
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        File directory = new File("src/main/resources/AboutImages");
        String path = null;
        try {
            path = directory.getCanonicalPath();
        }catch (IOException e){
            e.printStackTrace();
        }
        registry.addResourceHandler("/AboutImages/**").addResourceLocations("file:"+path+"/");
    }
}
