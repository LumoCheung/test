package cn.paypalm.io;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.netty.bootstrap.ServerBootstrap;

/** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2017年11月22日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2017年11月22日	新建文件.
 * 
 * </pre>
 */
public class NettyServer {
	
	public static  void main(String[] args) {
		//服务启动类
		ServerBootstrap bootstrap=new ServerBootstrap();
		//boss监听接口和客户端分发
		ExecutorService boss=Executors.newCachedThreadPool();
		//worker负责客户端数据的读写
		ExecutorService worker=Executors.newCachedThreadPool();
		//设置nio服务端工厂，核心代码
//		bootstrap.setFactory(new nio(boss, worker));
	}
	
	

}
