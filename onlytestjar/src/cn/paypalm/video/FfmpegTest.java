package cn.paypalm.video;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.FFMPEGLocator;
import it.sauronsoftware.jave.MultimediaInfo;

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
public class FfmpegTest {
	
	public static void main(String[] args) {  
        // *.mp4，*.flv，*..3gp格式均可,其他自行测试  
        // <vedio_path>是你的视频文件路径  
//        File source = new File("D:\\\\aaa.avi");
        File source = new File(Contant.targetFileName);
        FFMPEGLocator locator = new FFMPEGLocator() {
            @Override  
            protected String getFFMPEGExecutablePath() {  
                // <ffmpeg_path>是你的ffmpeg.exe路径  
//                return "D:\\\\Java\\ffmpeg-3.4.1\\bin\\ffmpeg.exe";
                return Contant.ppmpgePath;
            }
        }; 
        Encoder encoder = new Encoder(locator);
//        Encoder encoder = new Encoder();
        try {  
            MultimediaInfo m = encoder.getInfo(source);  
            long ls = m.getDuration();  
            // 输出毫秒数  
            System.out.println(ls);  
            // 输出0时0分0秒0毫秒的格式  
            System.out.println("此视频时长为:" + ls / (60 * 60 * 1000) + "时" + (ls % (60 * 60 * 1000)) / 60000 + "分"  
                    + ((ls % (60 * 60 * 1000)) % 60000) / 1000 + "秒" + (((ls % (60 * 60 * 1000)) % 60000) % 1000)  
                    + "毫秒！");  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
		
		/*getVideoTime("http://videoclass.oss-cn-hangzhou.aliyuncs.com/video/lesson001.mpg",   
                "D:\\test\\onlytestjar\\lib\\ffmpeg.exe");  */
    }  
	
	/** 
     * 获取视频总时间 
     * @param video_path    视频路径
     * @param ffmpeg_path   ffmpeg路径 
     * @return 
     */  
    public static int getVideoTime(String video_path, String ffmpeg_path) {  
        List<String> commands = new java.util.ArrayList<String>();  
        commands.add(ffmpeg_path);  
        commands.add("-i");  
        commands.add(video_path);  
        try {  
            ProcessBuilder builder = new ProcessBuilder();  
            builder.command(commands);  
            final Process p = builder.start();  
              
            //从输入流中读取视频信息  
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream()));  
            StringBuffer sb = new StringBuffer();  
            String line = "";  
            while ((line = br.readLine()) != null) {  
                sb.append(line);  
            }  
            br.close();  
              
            //从视频信息中解析时长  
            String regexDuration = "Duration: (.*?), start: (.*?), bitrate: (\\d*) kb\\/s";  
            Pattern pattern = Pattern.compile(regexDuration);  
            Matcher m = pattern.matcher(sb.toString());  
            if (m.find()) {  
                int time = getTimelen(m.group(1));  
                System.out.println(video_path+",视频时长："+time+", 开始时间："+m.group(2)+",比特率："+m.group(3)+"kb/s");  
                return time;  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
          
        return 0;  
    }  
      
    //格式:"00:00:10.68"  
    private static int getTimelen(String timelen){  
        int min=0;  
        String strs[] = timelen.split(":");  
        if (strs[0].compareTo("0") > 0) {  
            min+=Integer.valueOf(strs[0])*60*60;//秒  
        }  
        if(strs[1].compareTo("0")>0){  
            min+=Integer.valueOf(strs[1])*60;  
        }  
        if(strs[2].compareTo("0")>0){  
            min+=Math.round(Float.valueOf(strs[2]));  
        }  
        return min;  
    }

}
