package cn.paypalm.spring.taskexecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

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
@Configuration
@ComponentScan("cn.paypalm.spring.taskexecutor")
@EnableAsync //开启异步任务的支持
public class TaskExecutorConfig implements AsyncConfigurer {

	/**
	 * <p>Description:</p>
	 * @see org.springframework.scheduling.annotation.AsyncConfigurer#getAsyncExecutor() 
	 */ 
	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor taskExecutor=new ThreadPoolTaskExecutor();
		taskExecutor.setCorePoolSize(5);
		taskExecutor.setMaxPoolSize(10);
		taskExecutor.setQueueCapacity(25);
		taskExecutor.initialize();
		
		return taskExecutor;
	}

	/**
	 * <p>Description:</p>
	 * @see org.springframework.scheduling.annotation.AsyncConfigurer#getAsyncUncaughtExceptionHandler() 
	 */ 
	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return null;
	}

}
