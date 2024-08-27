package com.se_backend.Projects.Main_interface_project.Main_interface_Controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.io.IOException;

@Configuration
public class MainInterfaceImagesShowController implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
            File directory = new File("src/main/resources/MainInterfaceImages");
            String path = null;
            try {
                path = directory.getCanonicalPath();
            }catch (IOException e){
                e.printStackTrace();
            }
            registry.addResourceHandler("/MainInterfaceImages/**").addResourceLocations("file:"+path+"/");
        }
}
