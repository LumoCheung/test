package cn.paypalm.quartz;

import org.springframework.stereotype.Component;

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
@Component
public class AnotherBean {
	public void printAnotherMessage(){  
        System.out.println(Thread.currentThread().getName()+"://I am AnotherBean. I am called by Quartz jobBean using CronTriggerFactoryBean");  
    }  
}
