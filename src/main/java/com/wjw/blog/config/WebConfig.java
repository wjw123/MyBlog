package com.wjw.blog.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@Configuration
@EnableWebMvc  //����SpringMVC
@ComponentScan({"com.wjw.blog.controller","com.wjw.blog.dao"})
public class WebConfig extends WebMvcConfigurerAdapter{

	/*
	 * @Bean public ViewResolver internalViewResolver(){
	 * InternalResourceViewResolver viewResolver = new
	 * InternalResourceViewResolver("/WEB-INF/views/", ".jsp");
	 * viewResolver.setExposeContextBeansAsAttributes(true); return
	 * viewResolver; }
	 */
 
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
/*        //将templates目录下的CSS、JS文件映射为静态资源，防止Spring把这些资源识别成thymeleaf模版
        registry.addResourceHandler("/views/**.js").addResourceLocations("classpath:/views/");
        registry.addResourceHandler("/views/**.css").addResourceLocations("classpath:/views/");*/
        //其他静态资源
        registry.addResourceHandler("/static/**").addResourceLocations("/WEB-INF/static/");
    }
 
	/*@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource message = new ResourceBundleMessageSource();
		message.setBasename("welcome");
		message.setUseCodeAsDefaultMessage(true);
		return message;
	}*/
 
	// tiles�ļ�����
	/*
	 * @Bean public TilesConfigurer tilesConfigurer() { TilesConfigurer tiles =
	 * new TilesConfigurer(); tiles.setDefinitions(new String[] {
	 * "/WEB-INF/layout/tiles.xml" }); // ָ��tiles�ļ�λ��
	 * tiles.setCheckRefresh(true); return tiles; }
	 * 
	 * 
	 * @Bean public ViewResolver tilesViewResolver() { return new
	 * TilesViewResolver(); }
	 */
 
	@Bean // ��������ģ�����
	public ITemplateResolver templateResolver() {
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
		// ServletContextTemplateResolver
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(
				webApplicationContext.getServletContext());
		templateResolver.setPrefix("/WEB-INF/views/");
		templateResolver.setSuffix(".html");
		//����UTF-8��ʽ
		templateResolver.setCharacterEncoding("UTF-8");
		templateResolver.setTemplateMode(TemplateMode.HTML);
		return templateResolver;
	}
 
	@Bean 
	public TemplateEngine templateEngine(ITemplateResolver templateResolver) {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);
		return templateEngine;
	}
 
	@Bean 
	public ViewResolver viewResolver(TemplateEngine templateEngine) {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setContentType("text/html; charset=utf-8");
		viewResolver.setTemplateEngine(templateEngine);
		return viewResolver;
	}
	
	@Bean
	public MultipartResolver multipartResolver() throws IOException{
		return new StandardServletMultipartResolver();
	}
}
