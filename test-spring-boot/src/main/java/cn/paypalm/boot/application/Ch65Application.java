package cn.paypalm.boot.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.paypalm.lib.service.HelloService;

/** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2017年2月10日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2017年2月10日	新建文件.
 * 
 * </pre>
 */
@RestController
//@SpringBootApplication
public class Ch65Application {
	@Autowired
	HelloService helloService;
	
	@RequestMapping("/test65")
	String index2(){
		return helloService.sayHello();
	}
}
