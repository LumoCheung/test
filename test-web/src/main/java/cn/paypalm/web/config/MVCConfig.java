package cn.paypalm.web.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import cn.paypalm.web.interceptor.DemoInterceptor;

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
		registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");
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
}
