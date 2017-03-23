package com.example.administrator.newsdemo.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.newsdemo.R;
import com.example.administrator.newsdemo.bean.Shou_Bean;

import java.util.ArrayList;
import java.util.List;


/**
 * 类的用途：
 * Created by Administrator on 2017/3/21.
 */

public class ShouActivity extends Activity {

    private ListView lv_yes;
    private List<Shou_Bean> list = new ArrayList<>();
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.no_item);

        initView();

        if (list != null) {
            getData();
        }
    }

    private void getData() {
        String sql = "select * from table_shou";
        Cursor cursor = MainActivity.database.rawQuery(sql, null);
        while ((cursor.moveToNext())) {
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String url = cursor.getString(2);
            Shou_Bean shou_bean = new Shou_Bean();
            shou_bean.setId(id);
            shou_bean.setTitle(title);
            shou_bean.setUrl(url);
            list.add(shou_bean);
        }

        adapter = new MyAdapter();
        lv_yes.setAdapter(adapter);

        //条目点击跳转到详情
        lv_yes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ShouActivity.this, NextActivity.class);
                intent.putExtra("url",list.get(position).getUrl());
                startActivity(intent);
            }
        });

        //长按删除
        lv_yes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ShouActivity.this);
                builder.setTitle("取消收藏");
                builder.setMessage("是否确认取消收藏此条消息");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int id = list.get(position).getId();
                        list.remove(position);
                        adapter.notifyDataSetChanged();
                        String sql = "delete from table_shou where _id = ?";
                        MainActivity.database.execSQL(sql,new Object[]{id});
                    }
                });

                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.create().show();

                return false;
            }
        });
    }

    private void initView() {
        lv_yes = (ListView) findViewById(R.id.lv_yes);
    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = View.inflate(ShouActivity.this, R.layout.shou_item, null);
            TextView tv = (TextView) convertView.findViewById(R.id.tv);
            tv.setText(list.get(position).getTitle());
            return convertView;
        }
    }
}
