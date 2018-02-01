package com.zlc.servlet;

import com.zlc.util.CheckUtil;
import com.zlc.util.MessageUtil;
import com.zlc.util.UrlContant;
import com.zlc.util.WeiXinUtil;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

/**
 * @author zhangzilu02
 * @create 2018/1/15
 **/
public class WechatServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(WechatServlet.class);
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        log.debug("signature={},timestamp={},nonce={},echostr={}", signature, timestamp, nonce, echostr);
        PrintWriter out = response.getWriter();
        if (CheckUtil.checkSignature(signature, timestamp, nonce)) {
            //如果校验成功，将得到的随机字符串原路返回
            out.print(echostr);
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String message = null;
        try {
            //将request请求，传到Message工具类的转换方法中，返回接收到的Map对象
            Map<String, String> map = MessageUtil.xmlToMap(request);
            log.debug("入参：{}",map);
            //从集合中，获取XML各个节点的内容
            String ToUserName = map.get("ToUserName");
            String FromUserName = map.get("FromUserName");
            String CreateTime = map.get("CreateTime");
            String MsgType = map.get("MsgType");
            String Content = map.get("Content");
            String MsgId = map.get("MsgId");
            if (MsgType.equals(MessageUtil.MESSAGE_TEXT)) {//判断消息类型是否是文本消息(text)
                if (Content.equals("1")) {
                    message = MessageUtil.initText(ToUserName, FromUserName,
                            "帝高阳之苗裔兮，朕皇考曰伯庸。");
                } else if (Content.equals("2")) {
                    message = MessageUtil.initText(ToUserName, FromUserName,
                            "路漫漫其修远兮，吾将上下而求索。");
                } else if (Content.equals("3")) {
                    message = MessageUtil.initNewsMessage(ToUserName, FromUserName);
                } else if (Content.equals("xxx")) {
                    message = MessageUtil.initText(ToUserName, FromUserName,
                            WeiXinUtil.upload(WeiXinUtil.getAccessToken().getToken()
                                    ,MessageUtil.MESSAGE_IMAGE, UrlContant.LIN_SHI_SU_CAI_URL
                                    ,"/Users/zlc/Documents/图片/img-8ad5639f3e101672e6ebb8e31e2dde48.jpg"));
                } else if (Content.equals("?") || Content.equals("？")) {
                    message = MessageUtil.initText(ToUserName, FromUserName,
                            MessageUtil.menuText());
                } else {
                    message = MessageUtil.initText(ToUserName, FromUserName, "您好，" + FromUserName + "\n我是：" + ToUserName
                            + "\n您发送的消息类型为：" + MsgType + "\n您发送的时间为" + CreateTime
                            + "\n我回复的时间为：" + new Date().getTime() + "您发送的内容是" + Content);
                }
            }else if(MsgType.equals(MessageUtil.MESSAGE_EVENT)){//判断是否为事件类型
                //从集合中，或许是哪一种事件传入
                String eventType = map.get("Event");
                //关注事件
                if (eventType.equals(MessageUtil.MESSAGE_SUBSCRIBE)) {
                    message = MessageUtil.initText(ToUserName, FromUserName,
                            MessageUtil.menuText());
                }
            } else{
                message=MessageUtil.initText(ToUserName, FromUserName, "您好，" + FromUserName + "\n我是：" + ToUserName
                        + "\n您发送的消息类型为：" + MsgType + "\n您发送的时间为" + CreateTime
                        + "\n我回复的时间为：" + new Date().getTime() + "您发送的内容是" + Content);
            }
        } catch (DocumentException e) {
            log.error("出现异常", e);
        }
        log.debug(message);
        out.print(message); //返回转换后的XML字符串
        out.close();
    }
}
