package cn.paypalm.collection;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2015年11月4日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2015年11月4日	新建文件.
 * 
 * </pre>
 */
public class Test {
	class DemoData{
	       public volatile int value1 = 1;
	       volatile int value2 = 2;
	       protected volatile int value3 = 3;
	       private volatile int value4 = 4;
	   }
	    AtomicIntegerFieldUpdater<DemoData> getUpdater(String fieldName) {
	        return AtomicIntegerFieldUpdater.newUpdater(DemoData.class, fieldName);
	    }
	    void doit() {
	        DemoData data = new DemoData();
	        System.out.println("1 ==> "+getUpdater("value1").getAndSet(data, 10));
	        System.out.println("3 ==> "+getUpdater("value2").incrementAndGet(data));
	        System.out.println("2 ==> "+getUpdater("value3").decrementAndGet(data));
	        System.out.println("true ==> "+getUpdater("value4").compareAndSet(data, 4, 5));
	    }
	    public static void main(String[] args) {
	    	Test  demo = new Test ();
	        demo.doit();
	    }

}
