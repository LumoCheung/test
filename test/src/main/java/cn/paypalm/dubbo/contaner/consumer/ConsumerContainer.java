package cn.paypalm.dubbo.contaner.consumer;

import com.alibaba.dubbo.container.Container;

import cn.paypalm.dubbo.api.TestService;
import cn.paypalm.util.UContext;

/** 
 * <p> Description:  还没有测试：2017年8月24日19:11:02</p>
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
		
		new Thread(new Consumer()).start();
		
	}

	/**
	 * <p>Description:</p>
	 * @see com.alibaba.dubbo.container.Container#stop() 
	 */ 
	@Override
	public void stop() {
		
		
	}
	
	protected class Consumer implements Runnable{
		/**
		 * <p>Description:</p>
		 * @see java.lang.Runnable#run() 
		 */ 
		@Override
		public void run() {
			while(true) {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				Object test=null;
				try {
					test=UContext.getContext().getBean("consumer");
				}catch (Exception e) {
					continue;
				}
				
				if(test==null) {
					continue;
				}
				
				((TestService)test).Test("你说离开我知道很简单");
				break;
			}	
		}
		
	}

}
