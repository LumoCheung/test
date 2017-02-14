package cn.paypalm.boot;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"cn.paypalm.boot","cn.paypalm.lib"})
public class TestSpringBootApplication {

	public static void main(String[] args) {
//		SpringApplication.run(TestSpringBootApplication.class, args);
		//关闭banner第一种方式
		/*SpringApplication app=new SpringApplication(TestSpringBootApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);*/
		
		//关闭banner第二种方式
		new SpringApplicationBuilder(TestSpringBootApplication.class).bannerMode(Banner.Mode.OFF).run(args);
		
		/** 备注：运行时 添加参数 --debug ,会打印自动配置的报告 
		 * HelloServiceAutoConfigure matched:
      		- @ConditionalOnClass found required class 'cn.paypalm.lib.service.HelloService'; @ConditionalOnMissingClass did not find unwanted class (OnClassCondition)
      		- @ConditionalOnProperty (hello.enabled) matched (OnPropertyCondition)
		 **/
	}
}
