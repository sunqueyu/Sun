package com.example.administrator.newsdemo.bean;

/**
 * 类的用途：
 * Created by Administrator on 2017/3/27.
 */

public class Down_Bean {
    private String name;
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Down_Bean{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
