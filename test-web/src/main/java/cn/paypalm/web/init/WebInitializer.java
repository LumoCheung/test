package cn.paypalm.web.init;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import cn.paypalm.sse.config.SseConfig;
import cn.paypalm.web.config.MVCConfig;

import java.util.EnumSet;

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
public class WebInitializer  implements WebApplicationInitializer{
	
	private static final Logger logger=LoggerFactory.getLogger(WebInitializer.class);

	/**
	 * <p>Description:</p>
	 * @see org.springframework.web.WebApplicationInitializer#onStartup(javax.servlet.ServletContext) 
	 */ 
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext ctx=new AnnotationConfigWebApplicationContext();
//		ctx.setConfigLocation("classpath:spring-mvc.xml");//配置spring.xml
		ctx.register(MVCConfig.class);
//		ctx.register(SseConfig.class);
		ctx.setServletContext(servletContext);

		/*spring 编码*/
        FilterRegistration.Dynamic encodeFilter = servletContext.addFilter("encodingFilter",new CharacterEncodingFilter());
        encodeFilter.setInitParameter("encoding","UTF-8");
        encodeFilter.setInitParameter("forceEncoding","true");
        encodeFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST,DispatcherType.FORWARD,DispatcherType.INCLUDE),
                false,"/");

        // Register and map the dispatcher servlet
        DispatcherServlet dispatcherServlet = new DispatcherServlet(ctx);
//        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);//for NoHandlerFoundException，用于解决404页面问题
        javax.servlet.ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", dispatcherServlet);
		servlet.addMapping("/");
		servlet.setLoadOnStartup(1);
		//异步方法支持
		servlet.setAsyncSupported(true);
		
		logger.info("启动成功");
	}

}
