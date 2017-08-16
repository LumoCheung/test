package cn.paypalm.file;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FileUtils;

 /** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2015年7月3日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路 2015年7月3日	新建文件.
 * 
 * </pre>
 */
public class FileAuto {
	
	public static void main(String[] agrs) throws UnknownHostException
	{
		if(agrs.length>1)
		{
			String encode="utf-8";
			//读取样本文件
			String path=FileAuto.class.getClassLoader().getResource("model.txt").getPath();
			File inFile=new File(path);
			List<String> lines=null;
			try {
				lines = FileUtils.readLines(inFile, encode);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			List<String> outlines=new ArrayList<String>();
			//生成超大文件
			if(agrs[0].equals("1"))
			{
				int sum=Integer.parseInt(agrs[1]);
				for(int i=0;i<sum;i++)
				{
					String line = lines.get(i%lines.size());
					String[] params=line.split(",",-1);
					params[0]=String.valueOf(i);
					line = join(params,",");
                    outlines.add(line);
				}
				File outfFile=new File("./out/"+agrs[2]+".txt");
				try {
					FileUtils.writeLines(outfFile, encode, outlines);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//生成多个文件
			else {
				int sum=Integer.parseInt(agrs[1]);
				for(int i=0;i<sum;i++)
				{
					Random r=new Random();
					int total=r.nextInt(lines.size())+1;
					for(int ii=0;ii<total;ii++)
					{
						String line = lines.get(ii%lines.size());
						String[] params=line.split(",",-1);
						params[0]=String.valueOf(ii);
						line = join(params,",");
	                    outlines.add(line);
					}
					File outfFile=new File("./out/批量文件-"+i+".txt");
					try {
						FileUtils.writeLines(outfFile, encode, outlines);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}			
		}		
	}
	public static String join(String[] strings,String regex){
        if (strings == null || strings.length==0 || isEmpty(regex)){
            return "";
        }
        StringBuffer joinResult = new StringBuffer();
        for (String string: strings){
            if (!joinResult.toString().equals("")){
                joinResult.append(regex);
            }
            joinResult.append(string);
        }
        return joinResult.toString();
    }
	
	 public static boolean isEmpty(Object strObj) {
	        if (null == strObj || strObj.toString().trim().length() < 1) {
	            return true;
	        } else {
	            return false;
	        }
	    }
}
