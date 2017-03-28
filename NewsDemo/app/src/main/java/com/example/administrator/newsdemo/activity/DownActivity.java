package com.example.administrator.newsdemo.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.newsdemo.R;
import com.example.administrator.newsdemo.bean.Down_Bean;
import com.example.administrator.newsdemo.util.NetUtils;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * 类的用途：
 * Created by Administrator on 2017/3/27.
 */

public class DownActivity extends Activity{

    private String url = "http://mapp.qzone.qq.com/cgi-bin/mapp/mapp_subcatelist_qq?yyb_cateid=-10&categoryName=%E8%85%BE%E8%AE%AF%E8%BD%AF%E4%BB%B6&pageNo=1&pageSize=20&type=app&platform=touch&network_type=unknown&resolution=412x732";
    private ListView listView;
    private ArrayList<Down_Bean> list_load = new ArrayList<>();
    private String url1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.download_activity);

        initView();

        getData();

    }

    private void getData() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(DownActivity.this, url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(responseString);
                    JSONArray jsonArray = jsonObject.getJSONArray("app");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject object = jsonArray.optJSONObject(i);
                        Gson g1=new Gson();
                        Down_Bean downLoad = g1.fromJson(object.toString(), Down_Bean.class);
                        Log.i("aaaa",downLoad.toString());
                        list_load.add(downLoad);
                    }
                    //listView.setAdapter(new ArrayAdapter<Down_Bean>(DownActivity.this,android.R.layout.simple_list_item_1,list_load));
                    listView.setAdapter(new MyAdapters());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.lv_down);
    }

    private class MyAdapters extends BaseAdapter{

        @Override
        public int getCount() {
            return list_load.size();
        }

        @Override
        public Object getItem(int position) {
            return list_load.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView=View.inflate(DownActivity.this,R.layout.down_item,null);
            TextView tv_down = (TextView) convertView.findViewById(R.id.tv_down);
            tv_down.setText(list_load.get(position).getName());
            Button button  = (Button) convertView.findViewById(R.id.bu_down);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean workIsAvailable = NetUtils.isNetWorkIsAvailable(DownActivity.this);
                    if (!workIsAvailable) {
                        Toast.makeText(DownActivity.this, "网络未连接，请设置网络", Toast.LENGTH_SHORT).show();
                        Intent intents = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                        startActivity(intents);
                    } else {
                        //连接成功下载
                        Toast.makeText(DownActivity.this, "网络连接成功", Toast.LENGTH_SHORT).show();
                        url1 = list_load.get(position).getUrl();
                        download();
                    }
                }
            });
            return convertView;
        }
    }

    //下载
    private void download() {
        final String[] items = {"wifi", "手机流量"};
        //参数-1 默认被选中的position
        new android.app.AlertDialog.Builder(this).setTitle("网络选择").setIcon(R.mipmap.ic_launcher).setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                switch (which) {
                    case 0:
                        //wifi下 下载apk
                        downloadApk();
                        break;
                    case 1:
                        //手机流量下提醒用户
                        boolean mobile = NetUtils.isMobile(DownActivity.this);
                        if (mobile) {
                            Toast.makeText(DownActivity.this, "现在未使用wifi,将耗用手机流量", Toast.LENGTH_SHORT).show();
                            Intent wifiSettingsIntent = new Intent("android.settings.WIFI_SETTINGS");
                            startActivity(wifiSettingsIntent);
                        }

                        break;
                }
                dialog.dismiss();
            }
        }).show();
    }

    private void downloadApk() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("版本更新");
        builder.setMessage("现在检测到新版本，是否更新？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //updateApk();
                showChoiseDialog(url1);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create();
        builder.show();
    }


    //获取版本名称
    private String getVersionName() {
        // 获取packagemanager的实例
        PackageManager packageManager = getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo info = null;
        try {
            info = packageManager.getPackageInfo(getPackageName(), 0);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String versionName = info.versionName;
        return versionName;

    }

    public String getVersionCode() {
        // 获取packagemanager的实例
        PackageManager packageManager = getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String versionCode = String.valueOf(packInfo.versionCode);
        return versionCode;
    }


    private void showChoiseDialog(final String url) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        android.app.AlertDialog dialog = null;

        builder.setTitle("版本更新");
        builder.setMessage("检测到新版本，是否下载更新？");
        builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //下载
                downLoadApk(url1);

            }
        });
        builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    private void downLoadApk(String url) {
        //String url = "http://192.168.190.1:8080/08web/app.apk";

        RequestParams params = new RequestParams(url1);
        //自定义保存路径 Environment.getExternalStorageDirectory() sdcard 根目录

        params.setSaveFilePath(Environment.getExternalStorageDirectory() + "/app/");
        //自动为文件命令
        params.setAutoRename(true);
        x.http().post(params, new Callback.ProgressCallback<File>() {

            private ProgressDialog dialog;

            //网络请求成功时回调
            @Override
            public void onSuccess(File result) {
                Toast.makeText(DownActivity.this, "下载成功", Toast.LENGTH_SHORT).show();
                //apk下载完成后 调用系统的安装方法
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(result), "application/vnd.android.package-archive");
                startActivity(intent);
               // Toast.makeText(DownActivity.this, "安装成功", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }

            //网络请求错误时回调
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            //网络请求取消的时候回调
            @Override
            public void onCancelled(CancelledException cex) {

            }

            //网络请求完成的时候回调
            @Override
            public void onFinished() {

            }

            //网络请求之前回调
            @Override
            public void onWaiting() {

            }

            //网络请求开始的时候回调
            @Override
            public void onStarted() {
                dialog =  new ProgressDialog(DownActivity.this);
                dialog.setTitle("正在下载");
                dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                dialog.setProgress(0);
                dialog.show();
            }

            //下载的时候不断回调的方法
            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                //文件总大小和当前进度
                dialog.setProgress((int) current);
                dialog.setMax((int) total);

            }
        });
    }
}
