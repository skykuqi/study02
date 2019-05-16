package com.sky.listexample;

/**
 * @author 施 凯 沅
 * @version 0.0.1
 * 新闻类
 */
public class News {
    //新闻的标题
    private String newsTiele;
    //新闻的内容
    private String newsContent;

    public News setNewsTiele(String newsTiele) {
        this.newsTiele = newsTiele;
        return this;
    }

    public News setNewsContent(String newsContent) {
        this.newsContent = newsContent;
        return this;
    }

    public String getNewsTiele() {
        return newsTiele;
    }

    public String getNewsContent() {
        return newsContent;
    }
}
