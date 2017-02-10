package cn.paypalm.projectlombok.bean;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2017年1月11日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2017年1月11日	新建文件.
 * 
 * </pre>
 */
@Data
@NoArgsConstructor
public class UserBean {
	
	//@Setter
	private String name;
	
	private String pwd;

}
