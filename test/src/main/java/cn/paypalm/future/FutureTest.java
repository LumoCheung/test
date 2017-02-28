package cn.paypalm.future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/** 
 * <p> Description: 了解Future的作用 ，例子来源：https://www.oschina.net/question/54100_83333</p>
 * @Author zhangzilu
 * @Date [2017年2月28日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2017年2月28日	新建文件.
 * 
 * </pre>
 */
public class FutureTest {
	/**
	 * 
	 * <p> Description: 实现Callable接口，类似有返回值的Runnable，需要调用返回值使用Future </p>
	 * @Author zhangzilu
	 * @Date [2017年2月28日] 
	 * @Version V1.0 
	 * @修改记录  
	 * <pre>
	 * 版本号   修改人  修改时间     修改内容描述
	 * ----------------------------------------
	 * V1.0		张子路	 2017年2月28日	新建文件.
	 * 
	 * </pre>
	 */
	public static class Task implements Callable<String>{

		/**
		 * <p>Description:</p>
		 * @see java.util.concurrent.Callable#call() 
		 */ 
		@Override
		public String call() throws Exception {
			String tid = String.valueOf(Thread.currentThread().getId());
            System.out.printf("Thread#%s : in call\n", tid);
            return tid;
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException{
		
		ExecutorService service= Executors.newCachedThreadPool();
		List<Future<String>> list=new ArrayList<Future<String>>();
		
		for(int i=0;i<100;i++)
			list.add(service.submit(new Task()));
		
		for(Future<String> f:list){
			//Future 的get() 就是阻塞获得线程的返回值的方法
			System.out.println(f.get());
		}
		
	}
	
}
