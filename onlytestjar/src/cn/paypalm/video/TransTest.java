package cn.paypalm.video;

import java.io.File;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import it.sauronsoftware.jave.FFMPEGLocator;
import it.sauronsoftware.jave.InputFormatException;
import it.sauronsoftware.jave.VideoAttributes;

public class TransTest {
	static String sourceFileName="D:////aaa - 副本.avi";
	static String targetFileName="D:////bbb-a.mp4";
	
	public static void main(String[] args) throws IllegalArgumentException, InputFormatException, EncoderException {
		FFMPEGLocator locator = new FFMPEGLocator() {  
            @Override  
            protected String getFFMPEGExecutablePath() {  
                // <ffmpeg_path>是你的ffmpeg.exe路径  
                return "D:\\\\Java\\ffmpeg-3.4.1\\bin\\ffmpeg.exe";  
            }  
        }; 
        Encoder encoder = new Encoder(locator);
		File source = new File(sourceFileName);
        File target = new File(targetFileName);
        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("libmp3lame");
//    audio.setBitRate(new Integer(64000));
//    audio.setChannels(new Integer(1));
//    audio.setSamplingRate(new Integer(22050));
        VideoAttributes video = new VideoAttributes();
//        video.setCodec("libxvid");// 转MP4
//        video.setCodec("mpegvideo");
//        video.setTag("avc");
//    video.setBitRate(new Integer(180000));// 180kb/s比特率
//        video.setFrameRate(new Integer(1));// 1f/s帧频，1是目前测试比较清楚的，越大越模糊
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setFormat("mp4");// 转MP4
        attrs.setAudioAttributes(audio);
        attrs.setVideoAttributes(video);
//        Encoder encoder = new Encoder();
        encoder.encode(source, target, attrs);
	}
}
