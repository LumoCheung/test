package cn.paypalm.io;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

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
public class NioNonBlockingTcpClient {
	
	protected static SocketChannel socketChannel;
    private static Charset charset = Charset.forName("utf8");
	
	public static void main(String[] args) throws IOException {
		request("localhost", 8022);
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					if(!socketChannel.isConnected()) {
						socketChannel.finishConnect();
					}
					
					write("一盏离愁孤灯伫立在窗口");
					System.out.println("写入成功");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
		
		select();
	}
	

    public static void request(String host, int port) throws IOException {
        socketChannel = SocketChannel.open();
        socketChannel.socket().setSoTimeout(5000);
        SocketAddress remote = new InetSocketAddress(host, port);
        socketChannel.configureBlocking(false);
        socketChannel.connect(remote);
        socketChannel.register(NioNonBlockingHttpClient.selector,
                        SelectionKey.OP_CONNECT|SelectionKey.OP_READ|SelectionKey.OP_WRITE);
    }
    
	public static void write(String name) throws IOException {
        socketChannel.write(charset.encode(name));
		System.out.println(socketChannel);
        
        //解释：阻塞io从流中获取数据，即out/inputstream，使用流时会校验当前模式不是非阻塞的。
        //非阻塞io从通道获取数据，即channel(SelectionKey key)，必须和buffer搭配，selector会校验当前模式不是阻塞的。
		
		//解释2：nio和io都是通过socket做的连接，所有客户端和服务端可以同时使用io或nio，可以不统一。
		//但是channel和stream怎么做到相互转换的？
//		OutputStream out=socketChannel.socket().getOutputStream();
//		
//		out.write(name.getBytes("utf-8"));
//		out.flush();
    }
	
	public static void write(String name,SelectionKey key) throws IOException {
      SocketChannel channel = (SocketChannel) key.channel();
//      InetSocketAddress remote = (InetSocketAddress) socketChannel.socket().getRemoteSocketAddress();
//      String host = remote.getHostName();

//      String request = HttpUtil.compositeRequest(host);
//      System.out.println(request);

      channel.write(charset.encode(name));
      key.interestOps(SelectionKey.OP_READ);
  }
	
	public static void select() throws IOException{
		
		Selector selector=NioNonBlockingHttpClient.selector;
		
        while (selector.select() > 0){
        		Set<SelectionKey> keys = selector.selectedKeys();

                Iterator<SelectionKey> it = keys.iterator();

                while (it.hasNext()){

                    SelectionKey key = it.next();
                    it.remove();

                    if (key.isConnectable()){
                        NioNonBlockingHttpClient.connect(key);
                    }
                    else if (key.isReadable()){
                        NioNonBlockingHttpClient.receive(key);
                    }/*else if(key.isWritable()) {
                    	write("旧地如重游月圆更寂寞",key);
                    }*/
                }
        }
    }
}
