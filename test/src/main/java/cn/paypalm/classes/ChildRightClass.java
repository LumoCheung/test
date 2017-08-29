package cn.paypalm.classes;

 /** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2017年8月29日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2017年8月29日	新建文件.
 * 
 * </pre>
 */
public class ChildRightClass  extends AbstractClass{

	private static TestClass2 two=new TestClass2();
	
	private TestClass3 three=new TestClass3();
	
	//问题：静态变量和成员变量初始化时间？
	//静态变量和私有变量最大的区别在于初始化的次数。
	
	public ChildRightClass() {}
	
	public static void testStatic() {
		System.out.println("testStatic初始化");
	}
	
	public static void main(String[] args) {
		
		//测试目的： classload加载静态模块
		//1.先加载子类
		//2.静态成员变量在类加载的时候就已经加载了。子类的只加载一次。		
		
		new ChildLeftClass();
		
		new ChildRightClass();
	}
}
