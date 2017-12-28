package test.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

 /** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2015年12月25日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2015年12月25日	新建文件.
 * 
 * </pre>
 */
public class ServerHandler implements Runnable {
	private Socket socket;
	
	public ServerHandler(Socket socket){
		this.socket=socket;
	}
	/**
	 * <p>Title: run</p>
	 * <p>Description:</p> 
	 * @see java.lang.Runnable#run() 
	 */ 
	@Override
	public void run() {
		BufferedReader in=null;
		PrintWriter out=null;
		try {
			in =new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			out=new PrintWriter(this.socket.getOutputStream());
			String currentTime=null;
			String body=null;
			while(true){
				body=in.readLine();
				if(body==null){
					break;
				}
				System.out.println("body:"+body);
				currentTime="query".equalsIgnoreCase(body)?new Date(System.currentTimeMillis()).toString():"bad";
				System.out.println(currentTime);
			}
		} catch (IOException e) {
			if(in!=null){
				try {
					in.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(out!=null){
					out.close();
					out=null;
				}
				if(this.socket!=null){
					try {
						this.socket.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					this.socket=null;
				}
			}
				
		}
		
	}

}
