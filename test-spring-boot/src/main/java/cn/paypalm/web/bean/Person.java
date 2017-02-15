package cn.paypalm.web.bean;

 /** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2017年2月15日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2017年2月15日	新建文件.
 * 
 * </pre>
 */
public class Person {
	
	private  String name;
	private Integer age;
	/**
	 * @param name
	 * @param age
	 */
	public Person(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the age
	 */
	public Integer getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(Integer age) {
		this.age = age;
	}
	
}
