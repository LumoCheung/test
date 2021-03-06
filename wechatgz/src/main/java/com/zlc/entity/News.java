package com.zlc.entity;

/**
 * @author zhangzilu02
 * @create 2018/2/1
 **/
public class News {
    /** 标题 */
    private String Title;
    /** 描述 */
    private String Description;
    /** 图片地址 */
    private String PicUrl;
    /** 图文消息跳转地址 */
    private String Url;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
