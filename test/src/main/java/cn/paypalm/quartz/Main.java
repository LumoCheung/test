package cn.paypalm.quartz;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2017年8月2日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2017年8月2日	新建文件.
 * 
 * </pre>
 */
public class Main {

	/** 
	 * main
	 * 方法描述: 
	 * 逻辑描述: 
	 * @param args
	 * @since Ver 1.00
	 */
	public static void main(String[] args) {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("quartz-context.xml");  
	}

}
