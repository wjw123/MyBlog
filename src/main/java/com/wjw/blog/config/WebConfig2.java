package com.wjw.blog.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@EnableWebMvc 
@ComponentScan({"com.wjw.blog.controller","com.wjw.blog.dao"})
public class WebConfig2 extends WebMvcConfigurationSupport {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
/*        //将templates目录下的CSS、JS文件映射为静态资源，防止Spring把这些资源识别成thymeleaf模版
        registry.addResourceHandler("/views/**.js").addResourceLocations("classpath:/views/");
        registry.addResourceHandler("/views/**.css").addResourceLocations("classpath:/views/");*/
        //其他静态资源
        registry.addResourceHandler("/static/**").addResourceLocations("/WEB-INF/static/");
    }
}
