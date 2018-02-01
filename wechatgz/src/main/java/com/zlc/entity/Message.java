package com.zlc.entity;

/**
 * @author zhangzilu02
 * @create 2018/1/15
 **/
public class Message extends BaseMessage{
    private String Content;

    private String MsgId;

    public String getContent() {

        return Content;

    }

    public void setContent(String content) {

        Content = content;

    }

    public String getMsgId() {

        return MsgId;

    }

    public void setMsgId(String msgId) {

        MsgId = msgId;

    }

    public Message() {
    }


}
