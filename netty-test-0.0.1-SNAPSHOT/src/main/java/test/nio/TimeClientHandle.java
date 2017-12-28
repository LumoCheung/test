package test.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

 /** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2015年12月27日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2015年12月27日	新建文件.
 * 
 * </pre>
 */
public class TimeClientHandle implements Runnable{
	private String host;
	private int port;
	private Selector selector;
	private SocketChannel socketChannel;
	private volatile boolean stop;
	
	public TimeClientHandle(String host,int port){
		this.port=port;
		this.host=host==null?"127.0.0.1":host;
		try {
			selector=Selector.open();
			socketChannel=SocketChannel.open();
			socketChannel.configureBlocking(false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		
	}
	/**
	 * <p>Title: run</p>
	 * <p>Description:</p> 
	 * @see java.lang.Runnable#run() 
	 */ 
	@Override
	public void run() {
		//doConnect()
		try {
			doConnect();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		while(!stop){
			try {
				selector.select(1000);
				Set<SelectionKey> selectionKeys=selector.selectedKeys();
				Iterator<SelectionKey> it=selectionKeys.iterator();
				SelectionKey key =null;
				while(it.hasNext()){
					key=it.next();
					it.remove();
					//TODO handleInput(key)
					handleInput(key);
				}
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
		if(selector!=null){
			try {
				selector.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void handleInput(SelectionKey key) throws IOException{
		if(key.isValid()){
			SocketChannel sc=(SocketChannel) key.channel();
			if(key.isConnectable()){
				if(sc.finishConnect()){
					sc.register(selector, SelectionKey.OP_READ);
					//TODO doWrite(sc)
					doWrite(sc);
				}
				else
					System.exit(1);
			}
			if(key.isReadable()){
				ByteBuffer readBuffer=ByteBuffer.allocate(1024);
				int readBytes=sc.read(readBuffer);
				if(readBytes>0){
					readBuffer.flip();
					byte[] bytes=new byte[readBuffer.remaining()];
					readBuffer.get(bytes);
					String body=new String(bytes,"utf-8");
					System.err.println("Now is "+body);
					this.stop=true;
				}
				else if (readBytes<0) {
					key.cancel();
					sc.close();
				}
			}
		}
	}
	
	private void doConnect() throws IOException{
		if(socketChannel.connect(new InetSocketAddress(host, port))){
			socketChannel.register(selector, SelectionKey.OP_READ);
			//TODO doWrite(socketChannel)
			doWrite(socketChannel);
		}
		else
			socketChannel.register(selector, SelectionKey.OP_CONNECT);
	}
	
	private void doWrite(SocketChannel sc) throws IOException{
		byte[] req="query".getBytes();
		ByteBuffer writeBuffer=ByteBuffer.allocate(req.length);
		writeBuffer.put(req);
		writeBuffer.flip();
		sc.write(writeBuffer);
		if(!writeBuffer.hasRemaining()){
			System.out.println("Send order 2 server succeed.");
		}
	}
}
