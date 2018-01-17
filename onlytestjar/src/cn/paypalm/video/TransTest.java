package cn.paypalm.video;

import it.sauronsoftware.jave.*;
import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import it.sauronsoftware.jave.FFMPEGLocator;
import it.sauronsoftware.jave.InputFormatException;
import it.sauronsoftware.jave.VideoAttributes;

import java.io.File;

public class TransTest {

	public static void main(String[] args) throws IllegalArgumentException, InputFormatException, EncoderException {
		FFMPEGLocator locator = new FFMPEGLocator() {
            @Override  
            protected String getFFMPEGExecutablePath() {  
                // <ffmpeg_path>是你的ffmpeg.exe路径  
                return Contant.ppmpgePath;
            }  
        }; 
        Encoder encoder = new Encoder(locator);
		File source = new File(Contant.sourceFileName);
        File target = new File(Contant.targetFileName);
        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("libmp3lame");
//    audio.setBitRate(new Integer(64000));
//    audio.setChannels(new Integer(1));
//    audio.setSamplingRate(new Integer(22050));
        it.sauronsoftware.jave.VideoAttributes video = new VideoAttributes();
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
