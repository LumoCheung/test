package cn.paypalm.web.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import cn.paypalm.web.interceptor.DemoInterceptor;

import java.util.Properties;

/** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2017年1月19日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2017年1月19日	新建文件.
 * 
 * </pre>
 */
@Configuration
@EnableWebMvc
@ComponentScan("cn.paypalm.web")
public class MVCConfig extends WebMvcConfigurerAdapter {
	@Bean
	public InternalResourceViewResolver viewResolver(){
		InternalResourceViewResolver viewResolver=new InternalResourceViewResolver();
		viewResolver.setPrefix("/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setViewClass(JstlView.class);
		return viewResolver;
	}

//	@Bean
	/**
	 * 如果是用户请求了一个不存在的页面，没有对应的@RequestMapping，此时Spring的DispatcherServlet就会处理掉返回404，不会进入任何一个controller
	 * 这个问题暂时无法解决//2017年12月28日15:42:05
	 */
	public SimpleMappingExceptionResolver exceptionResolver() {
		SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
		Properties exceptionMappings = new Properties();
		exceptionMappings.put("java.lang.Exception", "500");
		exceptionMappings.put("java.lang.RuntimeException", "500");
		exceptionResolver.setExceptionMappings(exceptionMappings);
		Properties statusCodes = new Properties();
		statusCodes.put("404", "404");
		statusCodes.put("500", "500");
		exceptionResolver.setStatusCodes(statusCodes);
		return exceptionResolver;
	}
	
	@Resource
	private DemoInterceptor demoInterceptor;
	
	/**
	 * 
	 * <p>Description:添加静态资源映射</p>
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#addResourceHandlers(org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry)
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry){
		//addResourceLocations 指的是文件放置的目录，addResourceHandler 指的是文件暴露的目录
//		registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");
		registry.addResourceHandler("/**").addResourceLocations("/");
	}
	
	/**
	 * 
	 * <p>Description:拦截器</p>
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry)
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry){
		registry.addInterceptor(demoInterceptor);
	}
	
	/**
	 * 
	 * <p>Description:</p>
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#addViewControllers(org.springframework.web.servlet.config.annotation.ViewControllerRegistry)
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("/index");
		registry.addViewController("/index").setViewName("/index");
		//这里不是配置404页面，而是直接映射某个url为直接状态异常
//		registry.addViewController("/404.html").setStatusCode(HttpStatus.NOT_FOUND);
		registry.addStatusController("/404", HttpStatus.FORBIDDEN);
	}
}
