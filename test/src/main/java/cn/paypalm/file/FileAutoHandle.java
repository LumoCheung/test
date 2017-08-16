package cn.paypalm.file;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.util.StringUtils;

/** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2017年8月16日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2017年8月16日	新建文件.
 * 
 * </pre>
 */
public class FileAutoHandle {

	/** 
	 * main
	 * 方法描述: 
	 * 逻辑描述: 
	 * @param args
	 * @throws IOException 
	 * @since Ver 1.00
	 */
	public static void main(String[] args) throws IOException {
		File f1=new File("/test-1.txt");
		File f2=new File("/test-2.txt");
		File out=new File("/out.txt");
		
		List<String> lines1=null,lines2=null;
		
		lines1=FileUtils.readLines(f1, "utf-8");
		lines2=FileUtils.readLines(f2,"utf-8");

		int k=0;
		try {
		for(int i=0;i<lines1.size();i++) {
			if(!lines1.get(i).trim().startsWith("[")) continue;
			while(lines2.get(k).trim().startsWith("##")||StringUtils.isEmpty(lines2.get(k).trim()))
				k++;
			
			String str1=lines1.get(i).trim();
			String str2=lines2.get(k).trim();
			String[] re=str2.split("：",-1);
			//1.get need to be replaced str
			String str=str1.substring(1, lines1.get(i).indexOf("]"));
			str1=str1.replaceFirst(str, re[0].trim());
			
			str1=str1.substring(0,str1.lastIndexOf("|")+2)+re[1].trim();
			
			lines1.set(i, str1);
			System.out.println(str1);
			k++;
		}
		}catch (Exception e) {
			System.out.println(">>"+lines2.get(k).trim()+"//"+lines2.get(k).trim().startsWith("##"));
		}
		
		FileUtils.writeLines(out, lines1);
	}

}
