package cn.paypalm.concurrent;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2016年5月9日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2016年5月9日	新建文件.
 * 
 * </pre>
 */
public class ObjectCache<T> {
	
	public interface ObjectFactory<T>{
		T makeObject();
	}
	
	class Node{
		T obj;
		Node next;
	}
	
	final int capacity;
	final ObjectFactory<T> factory;
	final Lock lock=new ReentrantLock();
	final Semaphore semaphore;
	private Node head;
	private Node tail;
	
	public ObjectCache(int capacity,ObjectFactory<T> factory){
		this.capacity=capacity;
		this.factory=factory;
		this.semaphore=new Semaphore(this.capacity);
		this.head=null;
		this.tail=null;
	}
	
	public T getObject() throws InterruptedException{
		semaphore.acquire();
		return getNextObject();
	}
	
	private T getNextObject(){
		lock.lock();
		try{
			if(head==null){
				return factory.makeObject();
			}
			else{
				Node ret=head;
				head=head.next;
				if(head==null){
					tail=null;
				}
				ret.next=null;//help GC
				return ret.obj;
			}
		}finally{
			lock.unlock();
		}
	}
	
	private void returnObjToPool(T obj){
		lock.lock();
		try{
			Node node=new Node();
			node.obj=obj;
			if(head==null){
				head=tail=node;
			}else{
				tail.next=node;
				tail=node;
			}
		}finally{
			lock.unlock();
		}
	}
	
	public void returnObj(T obj){
		returnObjToPool(obj);
		semaphore.release();
	}

}
