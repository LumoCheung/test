package cn.paypalm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/** 
 * <p> Description:  配置动态读取，针对于system.properties</p>
 * @Author zhangzilu
 * @Date [2017年8月24日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2017年8月24日	新建文件.
 * 
 * </pre>
 */
public class SystemConfigProperties {
	private static String propertiesFilePath = null;
	public static long appLastTime = 0;
	private static Properties properties=null;
    
    private static final String DEFAULT_CONFIG_FILE="system.properties";

	static {
		try {
			URL url = SystemConfigProperties.class.getClassLoader().getResource(DEFAULT_CONFIG_FILE); 
			propertiesFilePath = url.getPath(); 
			propertiesFilePath = java.net.URLDecoder.decode(propertiesFilePath, "utf-8");
	        upload();
		} catch (Exception e) {
			System.out.println("PaypalmSDK 初始化MerConfig发生异常");
			e.printStackTrace();
		}
	}
	
    public SystemConfigProperties()
    {
        upload();
    }

    public static String getValueAt(String strName)
    {
    	upload();
        try {
        	if(properties.containsKey(strName))
        		return new String(properties.getProperty(strName).getBytes("ISO-8859-1"),"UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
    }
    
    public static String getValueAt(String strName,String defaultValue)
    {
    	upload();
        try {
        	if(properties.containsKey(strName))
        		return new String(properties.getProperty(strName).getBytes("ISO-8859-1"),"UTF-8");
        	else {
        		return defaultValue;
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
    }

    private static void upload()
    {
    	try
        {
			File appFile = new File(propertiesFilePath);
			if(appLastTime==0||(appFile.lastModified()>appLastTime&&System.currentTimeMillis()>appFile.lastModified()+10000))
			{
				synchronized(SystemConfigProperties.class)
				{//同步
					if(appLastTime==0||appFile.lastModified()>appLastTime)
					{
						InputStream is = new FileInputStream(appFile);
						Properties temp = new Properties();
						temp.load(is);
						properties = temp;
						appLastTime = appFile.lastModified();
						if(properties!=null&&properties.entrySet()!=null)
						{
							System.out.println(String.format("加载配置完成，共%s项设置",properties.entrySet().size()));
						}
					}
				}
			}
			else
			{//数据未修改
				return;
			}
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return;
        }
    }


}
