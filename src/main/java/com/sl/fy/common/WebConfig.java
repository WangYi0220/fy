package com.sl.fy.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**").addResourceLocations("file:/root/fy/img/");
    }

    public void addInterceptors(InterceptorRegistry registry){
        //List<String> list = new ArrayList<String>();
        //list.add("/img/*");
        //registry.addInterceptor(new MyInterceptor()).addPathPatterns(list);
    }
}
