package com.jk.dayu.jkapp.TipsModule;

import java.io.Serializable;

import lombok.Data;



@Data
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
}
