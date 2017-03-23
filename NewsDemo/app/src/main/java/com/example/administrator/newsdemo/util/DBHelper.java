package com.example.administrator.newsdemo.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * 类的用途：
 * Created by Administrator on 2017/3/15.
 */

public class DBHelper extends SQLiteOpenHelper{

    public DBHelper(Context context) {
        super(context, "news.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String table_me="create table table_me(_id integer primary key autoincrement,name varchar,url varchar)";

        //创建表tab
        db.execSQL(table_me);

        String[] str_me = new String[]{"头条", "社会", "国内", "国际", "娱乐", "体育", "军事", "科技", "财经", "时尚"};
        ArrayList<String> list_url = new ArrayList<>();
        list_url.add("http://v.juhe.cn/toutiao/index?type=推荐&key=32b9973df2e6ee0c2bf094b61c7d7844");
        list_url.add("http://v.juhe.cn/toutiao/index?type=shehui&key=32b9973df2e6ee0c2bf094b61c7d7844");
        list_url.add("http://v.juhe.cn/toutiao/index?type=guonei&key=32b9973df2e6ee0c2bf094b61c7d7844");
        list_url.add("http://v.juhe.cn/toutiao/index?type=guoji&key=32b9973df2e6ee0c2bf094b61c7d7844");
        list_url.add("http://v.juhe.cn/toutiao/index?type=yule&key=32b9973df2e6ee0c2bf094b61c7d7844");
        list_url.add("http://v.juhe.cn/toutiao/index?type=tiyu&key=32b9973df2e6ee0c2bf094b61c7d7844");
        list_url.add("http://v.juhe.cn/toutiao/index?type=junshi&key=32b9973df2e6ee0c2bf094b61c7d7844");
        list_url.add("http://v.juhe.cn/toutiao/index?type=keji&key=32b9973df2e6ee0c2bf094b61c7d7844");
        list_url.add("http://v.juhe.cn/toutiao/index?type=caijing&key=32b9973df2e6ee0c2bf094b61c7d7844");
        list_url.add("http://v.juhe.cn/toutiao/index?type=shishang&key=32b9973df2e6ee0c2bf094b61c7d7844");
        //list_url.add("http://c.3g.163.com/nc/video/list/V9LG4CHOR/n/10-10.html");
        for (int i = 0; i < str_me.length; i++) {
            String sql = "insert into table_me(name,url) values(?,?)";
            db.execSQL(sql, new Object[]{str_me[i],list_url.get(i)});
        }

        //收藏的表
        String sql = "create table table_shou(_id integer primary key autoincrement,title varchar,url varchar)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
