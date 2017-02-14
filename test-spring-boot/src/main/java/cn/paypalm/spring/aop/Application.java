package cn.paypalm.spring.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2017年1月12日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2017年1月12日	新建文件.
 * 
 * </pre>
 */
@SpringBootApplication
//@ComponentScan("cn.paypalm.spring.aop")
@EnableAspectJAutoProxy
public class Application {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

		public static void main(String[] args) throws InterruptedException {

			ApplicationContext ctx = SpringApplication.run(Application.class, args);
//			ApplicationContext ctx=new AnnotationConfigApplicationContext(AopConfig.class);

			DemoAnnotationService demoAnnotationService=ctx.getBean(DemoAnnotationService.class);
			DemoMethodService demoMethodService=ctx.getBean(DemoMethodService.class);
			
			demoAnnotationService.add();
			
			demoMethodService.add();

			System.exit(0);
		}

}
