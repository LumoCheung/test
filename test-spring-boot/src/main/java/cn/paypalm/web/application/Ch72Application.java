package cn.paypalm.web.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.paypalm.web.bean.Person;

/** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2017年2月15日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2017年2月15日	新建文件.
 * 
 * </pre>
 */
@Controller
public class Ch72Application {
	
	@RequestMapping("/test72")
	public String index(Model model){
		Person single=new Person("aa", 11);
		
		List<Person> people=new ArrayList<Person>();
		people.add(new Person("xx", 22));
		people.add(new Person("yy", 33));
		people.add(new Person("zz", 44));
		
		model.addAttribute("singlePerson", single);
		model.addAttribute("people", people);
		
		return "index";
	}
}
