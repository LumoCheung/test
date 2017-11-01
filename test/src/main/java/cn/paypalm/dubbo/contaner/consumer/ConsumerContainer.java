package cn.paypalm.dubbo.contaner.consumer;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.alibaba.dubbo.container.Container;
import com.alibaba.dubbo.rpc.RpcConstants;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.service.EchoService;

import cn.paypalm.dubbo.DubboServiceCacheService;
import cn.paypalm.dubbo.api.TestCallBackService;
import cn.paypalm.dubbo.api.TestMockService;
import cn.paypalm.dubbo.api.TestService;
import cn.paypalm.dubbo.api.TestStubService;
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
				
				try {
					((TestService)test).Test("我在门后假装你人还没走");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//stub 测试				
				test=UContext.getContext().getBean("consumerStub");				
				((TestStubService)test).test("夜半清醒的烛火不忍苛责我");//篱笆外的古道我牵着你走过
				
				//mock测试
				test=UContext.getContext().getBean("consumerMock");				
				((TestMockService)test).test("你走之后酒暖回忆思念瘦");//犹记得那年我们还很年幼
				
				test=UContext.getContext().getBean("consumerMock2");				
				((TestMockService)test).test("花开就一次成熟我却错过");//水向东流时间怎么偷
				
				//ReferenceConfig 缓存				
				TestService t=DubboServiceCacheService.getService();
				try {
					t.Test("岁月在墙上剥落看见小时候");//荒烟蔓草的年头 就连分手都很沉默
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//回声测试
				Object s=((EchoService)t).$echo("ok");
				assert("ok".equals(s));
				
				//事件通知，异步调用
				test=UContext.getContext().getBean("consumerCall");				
				System.out.println(((TestCallBackService)test).call("谁在用琵琶弹奏一曲东风破"));//犹记得那年我们还很年幼
				Future<String> f=RpcContext.getContext().getFuture();//返回为void的会直接报错
				try {
					f.get();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				break;
			}	
		}
		
	}

}
