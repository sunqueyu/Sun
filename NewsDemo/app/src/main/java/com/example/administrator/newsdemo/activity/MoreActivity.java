package com.example.administrator.newsdemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.newsdemo.R;
import com.example.administrator.newsdemo.bean.DB_Bean;

import java.util.ArrayList;

import static com.example.administrator.newsdemo.activity.MainActivity.database;

/**
 * 类的用途：
 * Created by Administrator on 2017/3/15.
 */

public class MoreActivity extends Activity {

    private ArrayList<DB_Bean> list_me;
    private ArrayList<String> list_more;
    private MyGrid1 grid1;
    private MyGrid2 grid2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_activity);

        initView();
    }

    private void initView() {
        ImageView im_back = (ImageView) findViewById(R.id.im_back);
        GridView grid_me = (GridView) findViewById(R.id.grid_me);
        GridView grid_more = (GridView) findViewById(R.id.grid_more);

        /*DBHelper helper = new DBHelper(this);
        database = helper.getWritableDatabase();*/


        String[] str_more = new String[]{"时尚", "育儿", "搞笑", "数码", "美食", "电影", "养生", "手机", "旅游", "宠物", "情感", "家居", "教育", "三农", "孕产", "文化", "游戏", "股票"};


        //更多频道
        list_more = new ArrayList<>();
        for (int i = 0; i < str_more.length; i++) {
            list_more.add(str_more[i]);
            Log.i("more", list_more.toString());
        }

        //我的频道
        String sql = "select * from table_me";
        list_me = new ArrayList<>();
        Cursor cursor = MainActivity.database.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(1);
            String url = cursor.getString(2);
            DB_Bean db_bean = new DB_Bean();
            db_bean.setName(name);
            db_bean.setUrl(url);
            list_me.add(db_bean);
        }


        grid1 = new MyGrid1();
        grid_me.setAdapter(grid1);
        grid2 = new MyGrid2();
        grid_more.setAdapter(grid2);


        //我的频道的点击事件
        grid_me.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MoreActivity.this, MainActivity.class);
                intent.putExtra("positon", position);
                startActivity(intent);
            }
        });


        //更多频道的点击事件
        grid_more.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String sql_add = "insert into table_me (name,url) values(?,?)";
                database.execSQL(sql_add, new Object[]{list_more.get(position), "http://v.juhe.cn/toutiao/index?type=" + list_more.get(position) + "&key=32b9973df2e6ee0c2bf094b61c7d7844"});
                DB_Bean db_bean = new DB_Bean();
                db_bean.setName(list_more.get(position));
                db_bean.setUrl("http://v.juhe.cn/toutiao/index?type=" + list_more.get(position) + "&key=32b9973df2e6ee0c2bf094b61c7d7844");
                list_me.add(db_bean);
                grid1.notifyDataSetChanged();
                list_more.remove(position);
                grid2.notifyDataSetChanged();
            }
        });


        //点X返回
        im_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoreActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private class MyGrid1 extends BaseAdapter {

        @Override
        public int getCount() {
            return list_me.size();
        }

        @Override
        public Object getItem(int position) {
            return list_me.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = View.inflate(MoreActivity.this, R.layout.grid_item, null);
            TextView buu = (TextView) convertView.findViewById(R.id.buu);
            buu.setText(list_me.get(position).getName());
            return convertView;
        }
    }

    private class MyGrid2 extends BaseAdapter {

        @Override
        public int getCount() {
            return list_more.size();
        }

        @Override
        public Object getItem(int position) {
            return list_more.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = View.inflate(MoreActivity.this, R.layout.grid_item, null);
            TextView buu = (TextView) convertView.findViewById(R.id.buu);
            buu.setText(list_more.get(position));
            return convertView;
        }
    }
}
