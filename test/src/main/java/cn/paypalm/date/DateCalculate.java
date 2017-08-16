package cn.paypalm.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

 /** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2015年8月7日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路 2015年8月7日	新建文件.
 * 
 * </pre>
 */
public class DateCalculate {
	
	public static void main(String[] args) throws ParseException
	{
		String set="2015-10-15";
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		if(args.length>0)			
			date=sdf.parse(args[0]);
		else {
			date=sdf.parse(set);
		}
		Date date2;
		if(args.length<2)
		{
			date2=new Date();
		}
		else {
			date2=sdf.parse(args[1]);
		}
		int days=(int) ((date.getTime()-date2.getTime())/1000/60/60/24);
		System.out.println("还有"+days+"天");
	}

}
