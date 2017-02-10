package cn.paypalm.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class MerConfig{
	private static String propertiesFilePath = null;
	public static long appLastTime = 0;
	private static Properties properties=null;
    

	static {
		try {
			URL url = MerConfig.class.getClassLoader().getResource("java-mail.properties"); 
			propertiesFilePath = url.getPath(); 
			propertiesFilePath = java.net.URLDecoder.decode(propertiesFilePath, "utf-8");
	        init();
		} catch (Exception e) {
			System.out.println("java mail 初始化Config发生异常");
			e.printStackTrace();
		}
	}
	
    public MerConfig()
    {
        init();
    }

    public static String getValueAt(String strName)
    {
    	init();
        try {
        	if(properties.containsKey(strName))
        		return new String(properties.getProperty(strName).getBytes("ISO-8859-1"),"UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
    }

    private static void init()
    {
    	try
        {
			File appFile = new File(propertiesFilePath);
			if(appLastTime==0||(appFile.lastModified()>appLastTime&&System.currentTimeMillis()>appFile.lastModified()+10000))
			{
				synchronized(MerConfig.class)
				{//同步
					if(appLastTime==0||appFile.lastModified()>appLastTime)
					{
						System.out.println("java mail  加载properties");
						InputStream is = new FileInputStream(appFile);
						Properties temp = new Properties();
						temp.load(is);
						properties = temp;
						appLastTime = appFile.lastModified();
						if(properties!=null&&properties.entrySet()!=null)
						{
							System.out.println("java mail  加载properties完成，共"+properties.entrySet().size()+"项设置");
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
            System.out.println("java mail 读取配置信息出错");
            e.printStackTrace();
            return;
        }
    }
}
