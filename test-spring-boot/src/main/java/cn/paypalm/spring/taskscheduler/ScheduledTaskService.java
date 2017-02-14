package cn.paypalm.spring.taskscheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2017年1月17日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2017年1月17日	新建文件.
 * 
 * </pre>
 */
@Service
public class ScheduledTaskService {
	
	private static final SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
	
	//@Scheduled(fixedRate = 5000) //fixedRate 固定时间间隔执行
	public void reportCurrentTime(){
		System.out.println("每五秒执行一次 "+sdf.format(new Date()));
	}
	
	//@Scheduled(cron = "0 44 18 ? * * ") // cron 是UNIX和Linux系统下的定时任务
	public void fixTimeExecutor(){
		System.out.println("在指定时间 "+sdf.format(new Date())+"执行");
	}
}
