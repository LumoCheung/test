package redis.redisson.test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.redisson.Config;
import org.redisson.Redisson;
import org.redisson.RedissonClient;
import org.redisson.core.RLock;

/** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2016年5月12日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2016年5月12日	新建文件.
 * 
 * </pre>
 */
public class RedissionTest {
	
	public void doMyBusiness(Object t) {		
		// 1.初始化  
        Config config = new Config();  
        config.setUseLinuxNativeEpoll(false);
        //config.useSingleServer().setConnectionPoolSize(10).setAddress("192.168.31.42:6379");
        config.useClusterServers().addNodeAddress("192.168.31.18:6379","192.168.31.11:6379","192.168.31.25:6379");
        try {
			System.out.println(config.toJSON());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //config.setConnectionPoolSize(10);  
        //config.addAddress("192.168.31.42:6379");  
        RedissonClient redisson = Redisson.create(config);  
        System.out.println("reids连接成功...");
        
        for(int i=0;i<10;i++){
        	new Thread(new ThreadTest(redisson)).start();
        }
}
	public static void main(String[] args){
		new RedissionTest().doMyBusiness("");
	}
	
	public class ThreadTest implements Runnable{
		private RedissonClient redisson;
		public ThreadTest(RedissonClient redisson) {
			this.redisson=redisson;
		}

		/**
		 * <p>Description:</p>
		 * @see java.lang.Runnable#run() 
		 */ 
		@Override
		public void run() {
			RLock lock = redisson.getLock("jiweiteshudemingzi");
			 try {
				 //lock.lock();
		            if (lock.tryLock(2, 3,TimeUnit.SECONDS)) {
		            	System.out.println(Thread.currentThread().getName()+"获取lock");
						Thread.sleep(200);
		            } else {
		                System.out.println("获取锁失败");
		            }
		        } catch (InterruptedException e) {
		        	Thread.currentThread().interrupt();
				} finally {
		        	System.out.println(Thread.currentThread().getName()+"释放锁");
		            lock.unlock();
		        }
			
		}
		
	}

}
