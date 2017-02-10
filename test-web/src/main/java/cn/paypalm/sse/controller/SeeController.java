package cn.paypalm.sse.controller;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2017年2月8日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2017年2月8日	新建文件.
 * 
 * </pre>
 */
@Controller
public class SeeController {
	
	@ResponseBody
	@RequestMapping(value="/push",produces="text/event-stream")//服务器SSE支持
	public String push(){
		Random r=new Random();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "data:Testing 1,2,3"+r.nextInt()+"\n\n";
	}

}
