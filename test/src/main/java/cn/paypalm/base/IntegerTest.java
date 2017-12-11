package cn.paypalm.base;

import java.lang.reflect.Field;

/** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2017年11月8日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2017年11月8日	新建文件.
 * 
 * </pre>
 */
public class IntegerTest {
	
	private final int param=34;//在这里设定值就会被优化为常量,debug可以看到该值被更改，反射真是一个破坏机制啊
	private static final Integer param2=22;

	public IntegerTest(int param) {
//		this.param=param;
		}
	
	public static void main(String[] args) {
//      Integer aa= 1;
//      Integer bb = 2;

      Integer aa = 1000;
      Integer bb = 2000;

      int a = 1;
      int b = 2;

//      int a = 1000;
//      int b = 2000;
      System.out.println("a=" + a + ", b=" + b);//a or b will not be packaged
//      swap(a, b);
//      swap2(aa, bb);
      System.out.println(aa+"/"+bb);
      System.out.println("a="+Integer.valueOf(a)+", b="+Integer.valueOf(b));
      Integer i = 1;//integer
      System.out.println("i="+i);
      
      //
      try {
		Field field = IntegerTest.class.getDeclaredField("param2");
		IntegerTest test=new IntegerTest(1);
        field.setAccessible(true);
		System.out.println(test);
		field.set(test, 2);
		System.out.println(test);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
  }
  private static void swap(Integer a,Integer b){
      try {
          Field field = Integer.class.getDeclaredField("value");
          field.setAccessible(true);
          int temp = a;
          field.set(a,b);
          field.set(b,new Integer(temp));
          field.set(a, 22);//反射可以修改final值！
      }catch (Exception e){
          e.printStackTrace();
      }
  }
  private static void swap2(Integer a,Integer b){
      Integer t=a;
      a=b;
      b=t;
      System.out.println("a=" + a + ", b=" + b);//a or b will not be packaged
  }

/**
 * <p>Description:</p>
 * @see java.lang.Object#toString() 
 */ 
@Override
public String toString() {
	return "IntegerTest [param=" + param + "]";
}
  
  

}
