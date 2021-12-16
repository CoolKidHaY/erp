package com.SpringBoot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author HuangYan
 */
@Configuration
public class MyWebAppConfigurer implements WebMvcConfigurer {

    @Value("${upload.file.location:}")
    private String resourcePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String filePath = "file:" + resourcePath;
        registry.addResourceHandler("/images/**").addResourceLocations(filePath);
    }
}