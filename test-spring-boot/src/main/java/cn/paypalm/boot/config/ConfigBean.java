package cn.paypalm.boot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2017年2月10日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2017年2月10日	新建文件.
 * 
 * </pre>
 */
@Component
@ConfigurationProperties(prefix="book")  //locations 指定位置，prefix指定前缀
public class ConfigBean {
	
	private String name;
	
	private String author;

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
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	
}
