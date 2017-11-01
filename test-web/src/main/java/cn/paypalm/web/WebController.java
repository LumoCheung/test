package cn.paypalm.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
@Controller
public class WebController {
	@RequestMapping("/index")
	public String hello(){
		return "index";
	}
	
	@ResponseBody
	@RequestMapping(value="/data",produces="text/html;charset=utf-8")
	public String get(HttpServletRequest request) {
		//汉字测试
		return "汉字测试";
	}
	
}
