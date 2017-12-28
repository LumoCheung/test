package test.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

 /** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2015年12月31日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2015年12月31日	新建文件.
 * 
 * </pre>
 */
public class TimerServer {
	public void bind(int port) throws Exception{
		//Reactor线程组
		EventLoopGroup bossGroup=new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			//NIO辅助启动类
			ServerBootstrap b=new ServerBootstrap();
			b.group(bossGroup, workerGroup)
				//设置channel为NioServerSocketChannel，相当于ServerSocketChannel
				.channel(NioServerSocketChannel.class)
				//设置tcp参数
				.option(ChannelOption.SO_BACKLOG, 1024)
				//绑定IO事件处理类，Reactor类的handler
				.childHandler(new ChildChannelHandler());
			ChannelFuture f=b.bind(port).sync();
			f.channel().closeFuture().sync();
		} 
		finally{
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
		
	}

	private class ChildChannelHandler extends ChannelInitializer<SocketChannel>{

		/**
		 * <p>Title: initChannel</p>
		 * <p>Description:</p>
		 * @param arg0
		 * @throws Exception 
		 * @see io.netty.channel.ChannelInitializer#initChannel(io.netty.channel.Channel) 
		 */ 
		@Override
		protected void initChannel(SocketChannel arg0) throws Exception {
			arg0.pipeline().addLast(new TimerServerHandler());
			}
		
	}
	
	public static void main(String[] args) throws Exception{
		int port=8090;		
		new TimerServer().bind(port);
	}
}
