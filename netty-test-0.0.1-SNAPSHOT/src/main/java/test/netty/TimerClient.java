package test.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

 /** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2016年1月4日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2016年1月4日	新建文件.
 * 
 * </pre>
 */
public class TimerClient {
	public void connect(int port,String host) throws Exception{
		EventLoopGroup group=new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
			.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel ch){
					ch.pipeline().addLast(new TimerClientHandler());
				}
			});
			ChannelFuture f=b.connect(host,port).sync();
			f.channel().closeFuture().sync();
		} 
		finally{
			group.shutdownGracefully();
		}
	}
	
	public static void main(String[] args) throws Exception{
		int port = 8090;
		new TimerClient().connect(port, "127.0.0.1");
	}
}
