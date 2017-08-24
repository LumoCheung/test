package cn.paypalm.dubbo;

import static org.hamcrest.CoreMatchers.nullValue;

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
public class DubboStartUp {
	
	public static void main(String[] args) throws InterruptedException {
		
		//com.alibaba.dubbo.container.Main.main(args);
		new Thread(new DubboStartUp().new Start()).start();
		
		while(true) {
			Thread.sleep(10000);
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
	
	public class Start implements Runnable{

		/**
		 * <p>Description:</p>
		 * @see java.lang.Runnable#run() 
		 */ 
		@Override
		public void run() {
			com.alibaba.dubbo.container.Main.main(new String[] {});
		}
		
	}
}
