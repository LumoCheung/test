package cn.paypalm.concurrent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

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
public class SimpleConcurrentMap<K, V> implements Map<K, V> {

	final ReadWriteLock lock=new ReentrantReadWriteLock();
	final Lock r=lock.readLock();
	final Lock w=lock.writeLock();
	final Map<K, V> map;
	
	public SimpleConcurrentMap(Map<K, V> map) {
		this.map=map;
		if(map==null) throw new NullPointerException();
	}
	/**
	 * <p>Description:</p>
	 * @see java.util.Map#size() 
	 */ 
	@Override
	public int size() {
		r.lock();
        try {
            return map.size();
        } finally {
            r.unlock();
        }
	}

	/**
	 * <p>Description:</p>
	 * @see java.util.Map#isEmpty() 
	 */ 
	@Override
	public boolean isEmpty() {
		 r.lock();
	        try {
	            return map.isEmpty();
	        } finally {
	            r.unlock();
	        }
	}

	/**
	 * <p>Description:</p>
	 * @see java.util.Map#containsKey(java.lang.Object) 
	 */ 
	@Override
	public boolean containsKey(Object key) {
		r.lock();
		try{
			return map.containsKey(key);
		}finally{
			r.unlock();
		}
	}

	/**
	 * <p>Description:</p>
	 * @see java.util.Map#containsValue(java.lang.Object) 
	 */ 
	@Override
	public boolean containsValue(Object value) {
		r.lock();
        try {
            return map.containsValue(value);
        } finally {
            r.unlock();
        }
	}

	/**
	 * <p>Description:</p>
	 * @see java.util.Map#get(java.lang.Object) 
	 */ 
	@Override
	public V get(Object key) {
		r.lock();
        try {
            return map.get(key);
        } finally {
            r.unlock();
        }
	}

	/**
	 * <p>Description:</p>
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object) 
	 */ 
	@Override
	public V put(K key, V value) {
		w.lock();
        try {
            return map.put(key, value);
        } finally {
            w.unlock();
        }
	}

	/**
	 * <p>Description:</p>
	 * @see java.util.Map#remove(java.lang.Object) 
	 */ 
	@Override
	public V remove(Object key) {
		w.lock();
        try {
            return map.remove(key);
        } finally {
            w.unlock();
        }
	}

	/**
	 * <p>Description:</p>
	 * @see java.util.Map#putAll(java.util.Map) 
	 */ 
	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		 w.lock();
	        try {
	            map.putAll(m);
	        } finally {
	            w.unlock();
	        }
	}

	/**
	 * <p>Description:</p>
	 * @see java.util.Map#clear() 
	 */ 
	@Override
	public void clear() {
		w.lock();
		try{
			map.clear();
		}finally{
			w.unlock();
		}
	}

	/**
	 * <p>Description:</p>
	 * @see java.util.Map#keySet() 
	 */ 
	@Override
	public Set<K> keySet() {
		r.lock();
        try {
        	//why hashset?
            return new HashSet<K>(map.keySet());
        } finally {
            r.unlock();
        }
	}

	/**
	 * <p>Description:</p>
	 * @see java.util.Map#values() 
	 */ 
	@Override
	public Collection<V> values() {
		 r.lock();
	        try {
	        	//arraylist 保护原始Map的数据逻辑，防止不正确的修改导致原始Map发生数据错误。
	            return new ArrayList<V>(map.values());
	        } finally {
	            r.unlock();
	        }
	}

	/**
	 * <p>Description:</p>
	 * @see java.util.Map#entrySet() 
	 */ 
	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		//实现复杂 ConcurrentHashMap
		throw new UnsupportedOperationException();
	}

}
