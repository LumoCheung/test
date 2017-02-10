package cn.paypalm;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class TestSpringBootApplication {

	public static void main(String[] args) {
//		SpringApplication.run(TestSpringBootApplication.class, args);
		//关闭banner第一种方式
		/*SpringApplication app=new SpringApplication(TestSpringBootApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);*/
		
		//关闭banner第二种方式
		new SpringApplicationBuilder(TestSpringBootApplication.class).bannerMode(Banner.Mode.OFF).run(args);
	}
}
