package cn.paypalm.text;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

/** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2017年5月10日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2017年5月10日	新建文件.
 * 
 * </pre>
 */
public class TextHandMain {
	
	public static void main(String[] args) throws IOException{
		
		List<String> lines=FileUtils.readLines(new File("/text/2017-5-10.txt"), "utf-8");
		List<String> lines2=new ArrayList<String>();
		
		for(String line:lines){
			lines2.add(line.substring(0,line.indexOf("=")));
//			lines2.add(line.substring(line.indexOf("，")<0?line.length():line.indexOf("，")+1));
//			if(lines.indexOf(line)%2==0)
//			lines2.add(line.substring(3,line.lastIndexOf("*/")));
		}
		
		FileUtils.writeLines(new File("/text/03.txt"), "utf-8", lines2, "\n");
	}

}
