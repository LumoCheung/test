package com.zlc.util;

import com.thoughtworks.xstream.XStream;
import com.zlc.entity.*;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author zhangzilu02
 * @create 2018/1/15
 **/
public class MessageUtil {
    public static final String MESSAGE_TEXT = "text";

    public static final String MESSAGE_IMAGE = "image";

    public static final String MESSAGE_VOICE = "voice";

    public static final String MESSAGE_VIDEO = "video";

    public static final String MESSAGE_SHORTVIDEO = "shortvideo";

    public static final String MESSAGE_LINK = "link";

    public static final String MESSAGE_LOCATION = "location";

    public static final String MESSAGE_EVENT = "event";

    public static final String MESSAGE_SUBSCRIBE = "subscribe";

    public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";

    public static final String MESSAGE_CLICK = "CLICK";

    public static final String MESSAGE_VIEW = "VIEW";

    public static final String MESSAGE_SCAN = "SCAN";

    public static final String MESSAGE_NEWS="news";
    /**

     * 新建方法，将接收到的XML格式，转化为Map对象

     * @param request 将request对象，通过参数传入

     * @return 返回转换后的Map对象

     */

    public static Map<String, String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException {

        Map<String, String> map = new HashMap<String, String>();

        //从dom4j的jar包中，拿到SAXReader对象。

        SAXReader reader = new SAXReader();

        InputStream is = request.getInputStream();//从request中，获取输入流

        Document doc =  reader.read(is);//从reader对象中,读取输入流


        Element root = doc.getRootElement();//获取XML文档的根元素

        List<Element> list = root.elements();//获得根元素下的所有子节点

        for (Element e : list) {

            map.put(e.getName(), e.getText());//遍历list对象，并将结果保存到集合中

        }



        is.close();

        return map;

    }



    /**

     * 将文本消息对象转化成XML格式

     * @param message 文本消息对象

     * @return 返回转换后的XML格式

     */

    public static String objectToXml(Message message){

        XStream xs = new XStream();

        //由于转换后xml根节点默认为class类，需转化为<xml>

        xs.alias("xml", message.getClass());

        return xs.toXML(message);

    }

    /**

     * 将文本消息对象转成XML

     * @param Message

     * @return

     */

    public static String MessageToXml(Message Message){

        XStream xstream = new XStream();

//将xml的根节点替换成<xml>  默认为Message的包名

        xstream.alias("xml", Message.getClass());

        return xstream.toXML(Message);

    }

    /**

     * 拼接关注主菜单

     */

    public static String menuText(){

        StringBuffer sb = new StringBuffer();

        sb.append("欢迎关注三米半公众号，请选择:\n\n");

        sb.append("1、养蛙。\n");

        sb.append("2、割草。\n\n");

        sb.append("回复？调出主菜单。\n\n");

        return sb.toString();

    }

    /**

     * 初始化回复消息

     */

    public static String initText(String toUSerName,String fromUserName,String content){

        Message text = new Message();

        text.setFromUserName(toUSerName);//原来【接收消息用户】变为回复时【发送消息用户】

        text.setToUserName(fromUserName);

        text.setMsgType(MESSAGE_TEXT);

        text.setCreateTime(new Date().getTime());

        text.setContent(content);

        return MessageUtil.MessageToXml(text);

    }

    /**
     * 将图文消息对象转成XML
     * @param
     * @return
     */
    public static String messageToXml(NewsMessage newsMessage){
        XStream xstream = new XStream();
        //将xml的根节点替换成<xml>  默认为NewsMessage的包名
        xstream.alias("xml", newsMessage.getClass());
        //同理，将每条图文消息News类的报名，替换为<item>标签
        xstream.alias("item", new News().getClass());
        return xstream.toXML(newsMessage);
    }

    /**
     * 将图文消息对象转成XML
     * @param
     * @return
     */
    public static String messageToXml(ImageMesssage messsage){
        XStream xstream = new XStream();
        //将xml的根节点替换成<xml>  默认为NewsMessage的包名
        xstream.alias("xml", messsage.getClass());
        return xstream.toXML(messsage);
    }


    /**
     * 初始化图文消息
     */
    public static String initNewsMessage(String toUSerName,String fromUserName){
        List<News> newsList = new ArrayList<News>();
        NewsMessage newsMessage = new NewsMessage();
        //组建一条图文↓ ↓ ↓
        News newsItem = new News();
        newsItem.setTitle("名余曰正则兮，字余曰灵均。\n");
        newsItem.setDescription("日月忽其不淹兮，春与秋其代序。");
        newsItem.setPicUrl("http://mmbiz.qpic.cn/mmbiz_jpg/qkfJ57xpRDiaicXZfSaNIaCAOV3XaIpbxicI29vv2SrKAr86CibjfYppPa47ia2ZNxvd51XbWwiahWB8MZjx0htdKy6A/0");
        newsItem.setUrl("https://www.baidu.com");
        newsList.add(newsItem);

        //组装图文消息相关信息
        newsMessage.setToUserName(fromUserName);
        newsMessage.setFromUserName(toUSerName);
        newsMessage.setCreateTime(new Date().getTime());
        newsMessage.setMsgType(MESSAGE_NEWS);
        newsMessage.setArticles(newsList);
        newsMessage.setArticleCount(newsList.size());

        //调用newsMessageToXml将图文消息转化为XML结构并返回
        return MessageUtil.messageToXml(newsMessage);
    }

    /**
     * 初始化图消息
     */
    public static String initImageMessage(String toUSerName,String fromUserName){
        ImageMesssage message=new ImageMesssage();
        Image image=new Image();
        image.setMediaId("g36-QxZTMmso8Onse17Ao3LXiX3yJXG-L_LoGo95hwE");

        //组装图文消息相关信息
        message.setToUserName(fromUserName);
        message.setFromUserName(toUSerName);
        message.setCreateTime(new Date().getTime());
        message.setMsgType(MESSAGE_IMAGE);
        message.setImage(image);

        //调用newsMessageToXml将图文消息转化为XML结构并返回
        return MessageUtil.messageToXml(message);
    }
}
