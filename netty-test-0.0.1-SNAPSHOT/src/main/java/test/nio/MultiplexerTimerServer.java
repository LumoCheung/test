package test.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
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
public class MultiplexerTimerServer implements Runnable{
	private Selector selectors;
	
	private ServerSocketChannel servChannel;
	
	private volatile boolean stop;
	
	public MultiplexerTimerServer(int port){
		try {
			selectors=Selector.open();
			servChannel=ServerSocketChannel.open();
			servChannel.configureBlocking(false);
			servChannel.socket().bind(new InetSocketAddress(port),1024);
			servChannel.register(selectors, SelectionKey.OP_ACCEPT);
			System.out.println("server in start in port:"+port);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public void stop(){
		this.stop=true;
	}

	/**
	 * <p>Title: run</p>
	 * <p>Description:</p> 
	 * @see java.lang.Runnable#run() 
	 */ 
	@Override
	public void run() {
		while(!stop){
			try {
				selectors.select(1000);
				Set<SelectionKey> selectionKeys=selectors.selectedKeys();
				Iterator<SelectionKey> it=selectionKeys.iterator();
				SelectionKey key =null;
				while(it.hasNext()){
					key=it.next();
					it.remove();
					//TODO handleInput(key)
					handleInput(key);
				}
				
			} catch (Throwable t) {
				t.printStackTrace();
			}			
		}
		if(selectors!=null){
			try {
				selectors.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void handleInput(SelectionKey key) throws IOException{
		if(key.isValid()){
			//处理新接入的请求消息
			if(key.isAcceptable()){
				ServerSocketChannel ssc=(ServerSocketChannel) key.channel();
				SocketChannel sc=ssc.accept();
				sc.configureBlocking(false);
				sc.register(selectors, SelectionKey.OP_READ);
			}
			if(key.isReadable()){
				SocketChannel sc=(SocketChannel) key.channel();
				ByteBuffer readBuffer=ByteBuffer.allocate(1024);
				int readBytes=sc.read(readBuffer);
				if(readBytes>0){
					readBuffer.flip();
					byte[] bytes=new byte[readBuffer.remaining()];
					readBuffer.get(bytes);
					String body=new String(bytes,"utf-8");
					System.out.println("server receive:"+body);
					String currentTime="query".equalsIgnoreCase(body)?new Date(System.currentTimeMillis()).toString():"bad";
					//TODO doWrite()
					doWrite(sc, currentTime);
				}
				else if (readBytes<0) {
					key.cancel();
					sc.close();
				}
			}
		}
	}
	
	private void doWrite(SocketChannel channel,String response) throws IOException{
		if(response!=null&&response.trim().length()>0){
			byte[] bytes=response.getBytes();
			ByteBuffer writeBuffer=ByteBuffer.allocate(bytes.length);
			writeBuffer.put(bytes);
			writeBuffer.flip();
			channel.write(writeBuffer);
		}
	}

}
