package cn.paypalm.automailstat;

/** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2016年5月19日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2016年5月19日	新建文件.
 * 
 * </pre>
 */
public class Test {
	
	public static void main(String[] args){
		try {
			if(args.length<=0)
				Sendmail.send("","");
			else if(args.length>1)
				Sendmail.send(args[0],args[1]);
			else {
				Sendmail.send(args[0], null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
