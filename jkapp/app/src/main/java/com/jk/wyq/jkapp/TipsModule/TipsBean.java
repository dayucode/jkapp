package com.jk.wyq.jkapp.TipsModule;

import java.io.Serializable;

/**
 * Created by wangyuqi on 2018/4/1.
 */

public class TipsBean implements Serializable{
    public String title;
    public String source;
    public String time;
    public String content;
    public String url;

    public TipsBean(String title,String source,String time,String content,String url){
        this.title = title;
        this.source = source;
        this.time = time;
        this.content = content;
        this.url = url;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
