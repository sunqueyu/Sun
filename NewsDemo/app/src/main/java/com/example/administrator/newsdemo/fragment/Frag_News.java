package com.example.administrator.newsdemo.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.newsdemo.R;
import com.example.administrator.newsdemo.activity.NextActivity;
import com.example.administrator.newsdemo.application.MyApp;
import com.example.administrator.newsdemo.bean.Info;
import com.example.administrator.newsdemo.util.SizeDemo;
import com.google.gson.Gson;
import com.liaoinstan.springview.container.AliFooter;
import com.liaoinstan.springview.container.AliHeader;
import com.liaoinstan.springview.container.RotationHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

import static android.R.attr.lines;
import static android.R.attr.path;

/**
 * 类的用途：新闻界面
 * Created by Administrator on 2017/3/13.
 */

public class Frag_News extends Fragment {
    private View view;
    private ListView listView;
    private String path;
    private List<Info.ResultBean.DataBean> list;
    private Handler handler = new Handler();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view==null){
            view = inflater.inflate(R.layout.frag_news, null);
        }
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if(viewGroup!=null){
            viewGroup.removeView(view);
        }
        Bundle bundle = getArguments();
        path = bundle.getString("path");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final SpringView sv = (SpringView) view.findViewById(R.id.spring);
        listView = (ListView) view.findViewById(R.id.lv_news);

        sv.setType(SpringView.Type.FOLLOW);
        sv.setHeader(new RotationHeader(getActivity()));
        sv.setFooter(new AliFooter(getActivity()));

        //SpringView的监听
        sv.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sv.onFinishFreshAndLoad();
                    }
                }, 2000);
            }

            @Override
            public void onLoadmore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sv.onFinishFreshAndLoad();
                    }
                }, 2000);
            }
        });

        MyTask task = new MyTask();
        task.execute(path);


    }

    private class MyTask extends AsyncTask<String, Integer, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            HttpClient client = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(path);
            try {
                HttpResponse response = client.execute(httpGet);
                if (response.getStatusLine().getStatusCode() == 200) {
                    publishProgress(10);
                    InputStream inputStream = response.getEntity().getContent();
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    byte[] by = new byte[1024];
                    int len = 0;
                    while ((len = inputStream.read(by)) != -1) {
                        outputStream.write(by, 0, len);
                    }
                    outputStream.close();
                    inputStream.close();
                    String s = outputStream.toString();
                    return s;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Gson gson = new Gson();
            Info info = gson.fromJson(s, Info.class);
            list = info.getResult().getData();

            listView.setAdapter(new MyAdapter());
            // Log.i("dfd",list.toString());

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getActivity(), NextActivity.class);
                    intent.putExtra("title", list.get(position).getTitle());
                    intent.putExtra("url", list.get(position).getUrl());
                    startActivity(intent);
                    //getActivity().overridePendingTransition(R.anim.slide_up_stop, R.anim.slide_up_in);
                }
            });
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
    }

    private class MyAdapter extends BaseAdapter {
        final int type1 = 0;
        final int type2 = 1;
        final int type3 = 2;

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
            ViewHolder1 holder1 = null;
            ViewHolder2 holder2 = null;
            ViewHolder3 holder3 = null;
            int types = getItemViewType(position);
            if (convertView == null) {
                switch (types) {
                    case 0:
                        convertView = View.inflate(getActivity(), R.layout.list_item1, null);
                        holder1 = new ViewHolder1();
                        holder1.image_pic1 = (ImageView) convertView.findViewById(R.id.image_pic1);
                        holder1.text_title1 = (TextView) convertView.findViewById(R.id.text_title1);
                        holder1.author_name1 = (TextView) convertView.findViewById(R.id.author_name1);
                        holder1.category1 = (TextView) convertView.findViewById(R.id.category1);
                        holder1.date1 = (TextView) convertView.findViewById(R.id.date1);
                        convertView.setTag(holder1);
                        break;

                    case 1:
                        convertView = View.inflate(getActivity(), R.layout.list_item2, null);
                        holder2 = new ViewHolder2();
                        holder2.image_pic2 = (ImageView) convertView.findViewById(R.id.image_pic2);
                        holder2.image_pic22 = (ImageView) convertView.findViewById(R.id.image_pic22);
                        holder2.text_title2 = (TextView) convertView.findViewById(R.id.text_title2);
                        holder2.author_name2 = (TextView) convertView.findViewById(R.id.author_name2);
                        holder2.category2 = (TextView) convertView.findViewById(R.id.category2);
                        holder2.date2 = (TextView) convertView.findViewById(R.id.date2);
                        convertView.setTag(holder2);
                        break;

                    case 2:
                        convertView = View.inflate(getActivity(), R.layout.list_item3, null);
                        holder3 = new ViewHolder3();
                        holder3.image_pic3 = (ImageView) convertView.findViewById(R.id.image_pic3);
                        holder3.image_pic32 = (ImageView) convertView.findViewById(R.id.image_pic32);
                        holder3.image_pic33 = (ImageView) convertView.findViewById(R.id.image_pic33);
                        holder3.text_title3 = (TextView) convertView.findViewById(R.id.text_title3);
                        holder3.author_name3 = (TextView) convertView.findViewById(R.id.author_name3);
                        holder3.category3 = (TextView) convertView.findViewById(R.id.category3);
                        holder3.date3 = (TextView) convertView.findViewById(R.id.date3);
                        convertView.setTag(holder3);
                        break;
                }
            } else {
                switch (types) {
                    case 0:
                        holder1 = (ViewHolder1) convertView.getTag();
                        break;

                    case 1:
                        holder2 = (ViewHolder2) convertView.getTag();
                        break;

                    case 2:
                        holder3 = (ViewHolder3) convertView.getTag();
                        break;
                }
            }

            switch (types) {
                case 0:
                    ImageLoader.getInstance().displayImage(list.get(position).getThumbnail_pic_s(), holder1.image_pic1, options());
                    holder1.text_title1.setText(list.get(position).getTitle());
                    holder1.author_name1.setText(list.get(position).getAuthor_name());
                    holder1.category1.setText(list.get(position).getCategory());
                    holder1.date1.setText(list.get(position).getDate());
                    if(MyApp.fontInt==1){
                        holder1.text_title1.setTextSize(SizeDemo.px2dip(20));
                    }else if(MyApp.fontInt==2){
                        holder1.text_title1.setTextSize(SizeDemo.px2dip(30));
                    }else if(MyApp.fontInt==3){
                        holder1.text_title1.setTextSize(SizeDemo.px2dip(40));
                    }
                    break;

                case 1:
                    ImageLoader.getInstance().displayImage(list.get(position).getThumbnail_pic_s(), holder2.image_pic2, options());
                    ImageLoader.getInstance().displayImage(list.get(position).getThumbnail_pic_s02(), holder2.image_pic2, options());
                    holder2.text_title2.setText(list.get(position).getTitle());
                    holder2.author_name2.setText(list.get(position).getAuthor_name());
                    holder2.category2.setText(list.get(position).getCategory());
                    holder2.date2.setText(list.get(position).getDate());
                    if(MyApp.fontInt==1){
                        holder2.text_title2.setTextSize(SizeDemo.px2dip(20));
                    }else if(MyApp.fontInt==2){
                        holder2.text_title2.setTextSize(SizeDemo.px2dip(30));
                    }else if(MyApp.fontInt==3){
                        holder2.text_title2.setTextSize(SizeDemo.px2dip(40));
                    }
                    break;

                case 2:
                    ImageLoader.getInstance().displayImage(list.get(position).getThumbnail_pic_s(), holder3.image_pic3, options());
                    ImageLoader.getInstance().displayImage(list.get(position).getThumbnail_pic_s02(), holder3.image_pic32, options());
                    ImageLoader.getInstance().displayImage(list.get(position).getThumbnail_pic_s03(), holder3.image_pic33, options());
                    holder3.text_title3.setText(list.get(position).getTitle());
                    holder3.author_name3.setText(list.get(position).getAuthor_name());
                    holder3.category3.setText(list.get(position).getCategory());
                    holder3.date3.setText(list.get(position).getDate());
                    if(MyApp.fontInt==1){
                        holder3.text_title3.setTextSize(SizeDemo.px2dip(20));
                    }else if(MyApp.fontInt==2){
                        holder3.text_title3.setTextSize(SizeDemo.px2dip(30));
                    }else if(MyApp.fontInt==3){
                        holder3.text_title3.setTextSize(SizeDemo.px2dip(40));
                    }
                    break;
            }


            return convertView;
        }

        @Override
        public int getViewTypeCount() {
            return 3;
        }

        @Override
        public int getItemViewType(int position) {
            if (list.get(position).getThumbnail_pic_s() != null && list.get(position).getThumbnail_pic_s() == null && list.get(position).getThumbnail_pic_s() == null) {
                return type1;
            } else if (list.get(position).getThumbnail_pic_s() != null && list.get(position).getThumbnail_pic_s() != null && list.get(position).getThumbnail_pic_s() == null) {
                return type2;
            } else if (list.get(position).getThumbnail_pic_s() != null && list.get(position).getThumbnail_pic_s() != null && list.get(position).getThumbnail_pic_s() != null) {
                return type3;
            }
            return type1;
        }


        class ViewHolder1 {
            ImageView image_pic1;
            TextView text_title1;
            TextView category1;
            TextView author_name1;
            TextView date1;
        }

        class ViewHolder2 {
            ImageView image_pic2;
            ImageView image_pic22;
            TextView text_title2;
            TextView category2;
            TextView author_name2;
            TextView date2;
        }

        class ViewHolder3 {
            ImageView image_pic3;
            ImageView image_pic32;
            ImageView image_pic33;
            TextView text_title3;
            TextView category3;
            TextView author_name3;
            TextView date3;
        }

        private DisplayImageOptions options() {
            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    /*.showImageOnLoading(imageId)
                    .showImageForEmptyUri(imageId)*/
                    .cacheInMemory(true)
                    .cacheOnDisk(true).build();
            return options;
        }
    }
}