package cn.paypalm.lib.autoconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.paypalm.lib.properties.HelloServiceProperties;
import cn.paypalm.lib.service.HelloService;

/** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2017年2月14日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2017年2月14日	新建文件.
 * 
 * </pre>
 */

@Configuration
@EnableConfigurationProperties(HelloServiceProperties.class)
@ConditionalOnClass(HelloService.class)
@ConditionalOnProperty(prefix="hello",value="enabled",matchIfMissing=true)
public class HelloServiceAutoConfigure {
	
	@Autowired
	private HelloServiceProperties helloServiceProperties;
	
	@Bean
	@ConditionalOnMissingBean(HelloService.class)
	public HelloService helloService(){
		HelloService helloService=new HelloService();
		helloService.setMsg(helloServiceProperties.getMsg());
		return helloService;
	}
	
}
