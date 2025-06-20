package com.example.scribbly.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class UsersConfiguration implements WebMvcConfigurer {
	
	@Value("${project.upload.path1}")
    private String uploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // /users/** 요청 → 실제 파일 경로로 매핑
        registry.addResourceHandler("/users/**").addResourceLocations("file:///" + uploadPath + "/");
    }
}
