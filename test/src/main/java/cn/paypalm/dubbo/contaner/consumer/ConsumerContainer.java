package cn.paypalm.dubbo.contaner.consumer;

import com.alibaba.dubbo.container.Container;

import cn.paypalm.automailstat.Test;
import cn.paypalm.dubbo.api.TestService;
import cn.paypalm.util.UContext;

/** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2017年8月24日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2017年8月24日	新建文件.
 * 
 * </pre>
 */
public class ConsumerContainer implements Container{

	/**
	 * <p>Description:</p>
	 * @see com.alibaba.dubbo.container.Container#start() 
	 */ 
	@Override
	public void start() {
		TestService test=UContext.getContext().getBean("consumer",TestService.class);
		
		new Thread(new Consumer(test)).start();
		
	}

	/**
	 * <p>Description:</p>
	 * @see com.alibaba.dubbo.container.Container#stop() 
	 */ 
	@Override
	public void stop() {
		
		
	}
	
	protected class Consumer implements Runnable{
		private TestService testService;

		public Consumer(TestService testService) {
			this.testService=testService;
		}
		/**
		 * <p>Description:</p>
		 * @see java.lang.Runnable#run() 
		 */ 
		@Override
		public void run() {
			testService.Test("你说离开我知道很简单");
		}
		
	}

}
