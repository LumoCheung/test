package cn.paypalm.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.CountDownLatch;

/** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2017年8月31日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2017年8月31日	新建文件.
 * 
 * </pre>
 */
public class IOClient {
	
	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		Socket socket=new Socket("localhost", 8022);
		
		OutputStream out=socket.getOutputStream();
		InputStream in =socket.getInputStream();
		
		CountDownLatch latch=new CountDownLatch(1);
		new Thread(new Send(out,latch)).start();
		byte[] buffer=new byte[1024];
		
		/*if(in.available()>0) {
			latch.countDown();
			System.out.println(latch.getCount());
		}*/
		while(true) {
			if(in.available()>0) {
				int i=in.read(buffer);
				while(i>-1) {
					System.out.println(new String(buffer,0,i,"utf-8"));
					i=in.read(buffer);
				}
			}
			else {
				Thread.sleep(100);
			}
		}
	}

	private static class Send implements Runnable{
		private OutputStream out;
		private CountDownLatch latch;
		public Send(OutputStream out,CountDownLatch lacth) {
			this.out=out;		
			this.latch=lacth;
		}
		/**
		 * <p>Description:</p>
		 * @see java.lang.Runnable#run() 
		 */ 
		@Override
		public void run() {
			try {
//				latch.await();
				out.write("一壶漂泊浪迹天涯难入喉".getBytes("utf-8"));
				out.flush();
				System.out.println("一壶漂泊浪迹天涯难入喉");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
