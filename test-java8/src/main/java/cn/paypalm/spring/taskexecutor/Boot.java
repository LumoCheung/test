package cn.paypalm.spring.taskexecutor;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import cn.paypalm.spring.taskscheduler.TaskSchedulerConfig;

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
public class Boot {
	
	public static void main(String[] args){
		
		/*AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(TaskExecutorConfig.class);
		
		AsyncTaskExecutor executor=ctx.getBean(AsyncTaskExecutor.class);
		
		for (int i = 0; i < 10; i++) {
			executor.executeTask(i);
			executor.executeTaskPlus(i);
		}
		
		ctx.close();*/
		
		
		AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(TaskSchedulerConfig.class);
		
		
	}

}
