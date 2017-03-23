package com.example.administrator.newsdemo.bean;

/**
 * 类的用途：
 * Created by Administrator on 2017/3/16.
 */

public class DB_Bean {
    private int id;
    private String name;
    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
        return "DB_Bean{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
