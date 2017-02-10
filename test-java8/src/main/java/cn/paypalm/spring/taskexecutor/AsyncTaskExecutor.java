package cn.paypalm.spring.taskexecutor;

import org.springframework.scheduling.annotation.Async;
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
@Async //类中所有方法都是异步方法，这里的方法会被自动注入线程池服务
public class AsyncTaskExecutor {
	//@Async
	public void executeTask(Integer i){
		System.out.println("执行异步任务->"+i);
	}
	//@Async
	public void executeTaskPlus(Integer i){
		System.out.println("执行+1异步任务->"+(i+1));
	}
}
