package cn.paypalm.video;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;
import java.io.File;

/** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2018年1月2日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2018年1月2日	新建文件.
 * 
 * </pre>
 */
public class Test {
	
	public static void main(String[] args){
        File source = new File("D:\\\\video\\\\123.mp4");
        Encoder encoder = new Encoder();
        try {
             MultimediaInfo m = encoder.getInfo(source);
             long ls = m.getDuration();
             System.out.println("此视频时长为:"+ls/60000+"分"+(ls%(ls/60000*60000))/1000+"秒！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
