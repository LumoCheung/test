package cn.paypalm.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
public class NioServer {
	
	static Selector selector;
    private static Charset charset = Charset.forName("utf8");
    private ExecutorService pool=Executors.newCachedThreadPool();
	
	static {
		try {
			selector=Selector.open();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException {
		ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
		serverSocketChannel.configureBlocking(false);
		serverSocketChannel.socket().bind(new InetSocketAddress(8000));
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		new NioServer().listerner();
	}
	
	public void listerner() throws IOException {
		while (true) {
			selector.select();			
			Iterator<SelectionKey> iteratorKey=selector.selectedKeys().iterator();
			
			while (iteratorKey.hasNext()) {
				final SelectionKey key=iteratorKey.next();//这里1.8版本可以不加final关键字修饰
				iteratorKey.remove();				
				pool.execute(new Runnable() {
					
					@Override
					public void run() {
						try {
							handle(key);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
				
			}
			
		}
	}
	
	public static void handle(SelectionKey key) throws IOException {
		if(key.isAcceptable()) {
			System.out.println("新的连接");
			ServerSocketChannel server=(ServerSocketChannel) key.channel();
			//获得与客户端的连接
			SocketChannel channel = server.accept();
			channel.configureBlocking(false);
			channel.register(selector, SelectionKey.OP_READ);
			
		}else if(key.isReadable()) {
			SocketChannel channel = (SocketChannel) key.channel();
			
			ByteBuffer buffer=ByteBuffer.allocate(1024);
			int read=channel.read(buffer);
			if(read>0) {
				buffer.flip();
				System.out.println(charset.decode(buffer).toString());
			}
		}
	}

}
