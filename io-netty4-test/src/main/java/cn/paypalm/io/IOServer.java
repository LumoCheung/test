package cn.paypalm.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

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
public class IOServer {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		ServerSocket serverSocket=new ServerSocket(8022);

		System.out.println( serverSocket.getInetAddress()+"/"+serverSocket.getLocalPort()+" started");
	
		while(true) {
			Socket socket=serverSocket.accept();
			InputStream in=socket.getInputStream();
			OutputStream out =socket.getOutputStream();
			
			out.write(("连接成功，"+new Date().getTime()+socket.getPort()+"/"+socket.getInetAddress()).getBytes("utf-8"));
			out.flush();
			System.out.println("连接成功，"+new Date().getTime()+"/"+socket.getPort()+"/"+socket.getInetAddress());
	
			new Thread(new ClientHander(in, out)).start();
		}
	}
	
	private static class ClientHander implements Runnable{
		
		private InputStream in;
		private OutputStream out;
		
		public ClientHander(InputStream in ,OutputStream out) {
			this.in=in;
			this.out=out;
		}
		
		/**
		 * <p>Description:</p>
		 * @see java.lang.Runnable#run() 
		 */ 
		@Override
		public void run() {
			while(true) {
				try {
				if(in.available()>0) {
					byte[] buffer=new byte[1024];
					int i=in.read(buffer);
					while(i>-1) {
						System.out.println(new String(buffer,0,i,"utf-8"));
						out.write(("传输完成，"+new Date().getTime()).getBytes("utf-8"));
						out.flush();
						i=in.read(buffer);
					}
					
				}else {
					Thread.sleep(1000);
				}
				}catch (Exception e) {
					e.getStackTrace();
					break;
				}
			}
		}
		
	}

}
