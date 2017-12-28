package test.netty;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

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
public class TimerServerHandler extends ChannelHandlerAdapter {
	@Override
	public void channelRead(ChannelHandlerContext ctx,Object msg) throws Exception{
		ByteBuf buf=(ByteBuf)msg;
		byte[] req=new byte[buf.readableBytes()];
		buf.readBytes(req);
		String body=new String(req,"utf-8");
		System.out.println("order:"+body);
		String currentTime="query".equalsIgnoreCase(body)?new Date(System.currentTimeMillis()).toString():"bad";
		ByteBuf resp=Unpooled.copiedBuffer(currentTime.getBytes());
		ctx.write(resp);
	}
	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx){
		ctx.flush();
	}
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause){
		ctx.close();
	}
	
}
