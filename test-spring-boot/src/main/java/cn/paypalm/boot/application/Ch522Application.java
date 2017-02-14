package cn.paypalm.boot.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.paypalm.boot.config.ConfigBean;

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
public class Ch522Application {
	
	@Value("${book.author}")
	private String bookAuthor;
	
	@Value("${book.name}")
	private String bookName;
	
	@Autowired
	private ConfigBean bean;
	
	@RequestMapping("/")
	String index(){
		return "book name is : "+bookName +"  and book author is : "+bookAuthor;
	}
	
	@RequestMapping("/index")
	String index2(){
		return "bean : book name is : "+bean.getName() +"  and book author is : "+bean.getAuthor();
	}
	
	/*public static void main(String[] args){
		SpringApplication.run(Ch522Application.class, args);
	}*/

}
