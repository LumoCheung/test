package test.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import io.netty.bootstrap.ServerBootstrap;

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
public class BIOServer {
	
	public static void main(String[] args) throws IOException{
		int port=8080;
		if(args !=null && args.length>0){
			try {
				port=Integer.valueOf(args[0]);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		ServerSocket server=null;
		try {
			server=new ServerSocket(port);
			System.out.println("port:"+port);
			Socket socket=null;
			while(true){
				socket=server.accept();
				new Thread(new ServerHandler(socket)).start();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally{
			if(server!=null){
				System.out.println("close");
				server.close();
				server=null;
			}
		}
	}

}
