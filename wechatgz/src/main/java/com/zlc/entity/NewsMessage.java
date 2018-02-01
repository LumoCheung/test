package com.zlc.entity;

import java.util.List;

/**
 * @author zhangzilu02
 * @create 2018/2/1
 **/
public class NewsMessage extends BaseMessage {
    /** 图文消息条数 */
    private int ArticleCount;
    /** 多条图文消息 */
    private List<News> Articles;

    public int getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(int articleCount) {
        ArticleCount = articleCount;
    }

    public List<News> getArticles() {
        return Articles;
    }

    public void setArticles(List<News> articles) {
        Articles = articles;
    }
}
