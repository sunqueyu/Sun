package com.example.administrator.newsdemo.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.newsdemo.R;
import com.example.administrator.newsdemo.activity.MainActivity;
import com.example.administrator.newsdemo.bean.Media_Bean;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import maqian.baidu.com.xlistviewlibrary.XListView;


/**
 * 类的用途：视频收藏下载分享
 * Created by Administrator on 2017/3/17.
 */

public class Frag_Video extends Fragment {

    private View view;
    private XListView xlv_video;
    private List<Media_Bean> list = new ArrayList<>();
    private MediaAdapter adapter;
    private MainActivity activity;
    private int a = 0;
    private String[] st = {"V9LG4CHOR", "V9LG4E6VR", "00850FRB"};
    private static final String APP_ID = "1105602574"; //获取的APPID
    private ShareUiListener mIUiListener;
    private Tencent mTencent;
    private AlertDialog dialog;

    private Handler handler = new Handler();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_video, null);
        activity = (MainActivity) getActivity();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        xlv_video = (XListView) view.findViewById(R.id.xlv_video);
        //传入参数APPID
        mTencent = Tencent.createInstance(APP_ID, getActivity().getApplicationContext());
        xlv_video.setPullLoadEnable(true);
        xlv_video.setPullRefreshEnable(true);

        getDatas(a);

        xlv_video.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                getDatas(a);
                loadTime();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        xlv_video.stopRefresh();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
                a++;
                getDatas(a);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        xlv_video.stopLoadMore();
                    }
                }, 2000);
            }
        });
    }

    private void loadTime() {
        long millis = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:ss:mm");
        Date date = new Date(millis);
        String time = sdf.format(date);
        xlv_video.setRefreshTime(time);
    }

    private void getDatas(int a) {
        String http_url = "http://c.3g.163.com/nc/video/list/" + st[a] + "/n/10-10.html";
        getData(http_url);
    }


    private void getData(String url) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(getActivity(), url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(activity, "加载失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Gson gson = new Gson();
                try {
                    JSONObject obj = new JSONObject(responseString);
                    //获得key键
                    Iterator<String> keys = obj.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();//每个key
                        JSONArray jsonArray = obj.optJSONArray(next);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.optJSONObject(i);

                            Media_Bean bean = gson.fromJson(object.toString(), Media_Bean.class);
                            list.add(bean);
                            Log.i("video", list.toString());
                        }
                    }
                    if (adapter == null) {
                        adapter = new MediaAdapter();
                        xlv_video.setAdapter(new MediaAdapter());
                    } else {
                        adapter.notifyDataSetChanged();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private class MediaAdapter extends BaseAdapter {

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
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(getActivity(), R.layout.video_item, null);
                holder = new ViewHolder();
                holder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
                holder.jcv = (JCVideoPlayerStandard) convertView.findViewById(R.id.jcv);
                holder.topicName = (TextView) convertView.findViewById(R.id.topicName);
                holder.playCount = (TextView) convertView.findViewById(R.id.playcount);
                holder.tv_share = (ImageView) convertView.findViewById(R.id.share);
                holder.tv_shou = (ImageView) convertView.findViewById(R.id.shou);
                holder.down = (ImageView) convertView.findViewById(R.id.down);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tv_title.setText(list.get(position).getTitle());
            boolean up = holder.jcv.setUp(list.get(position).getMp4_url(), JCVideoPlayer.SCREEN_LAYOUT_LIST, "");
            if (up) {
                Glide.with(getActivity()).load(list.get(position).getCover()).into(holder.jcv.thumbImageView);
            }
            holder.topicName.setText(list.get(position).getTopicName());

            holder.playCount.setText(list.get(position).getPlayCount() + "万次播放");

            holder.tv_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    dialog = builder.create();
                    view = View.inflate(getActivity(), R.layout.dialog_item, null);
                    dialog.setView(view);
                    ImageView im_qq = (ImageView) view.findViewById(R.id.im_qq);
                    ImageView im_zone = (ImageView) view.findViewById(R.id.im_zone);
                    dialog.show();

                    im_qq.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            final Bundle params = new Bundle();
                            params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);//分享的类型
                            params.putString(QQShare.SHARE_TO_QQ_TITLE, "然了个然CSDN博客");//分享标题
                            params.putString(QQShare.SHARE_TO_QQ_SUMMARY, "不管是怎样的过程,最终目的还是那个理想的结果。");//要分享的内容摘要
                            params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, "http://blog.csdn.net/sandyran");//内容地址
                            params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, "http://avatar.csdn.net/B/3/F/1_sandyran.jpg");//分享的图片URL
                            params.putString(QQShare.SHARE_TO_QQ_APP_NAME, "测试");//应用名称
                            mTencent.shareToQQ(getActivity(), params, new ShareUiListener());
                        }
                    });

                    im_zone.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            int QzoneType = QzoneShare.SHARE_TO_QZONE_TYPE_NO_TYPE;
                            Bundle params = new Bundle();
                            params.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE, QzoneType);
                            params.putString(QzoneShare.SHARE_TO_QQ_TITLE, "然了个然CSDN博客");//分享标题
                            params.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, "不管是怎样的过程,最终目的还是那个理想的结果。");//分享的内容摘要
                            params.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, "http://blog.csdn.net/sandyran/article/details/53204529");//分享的链接
                            //分享的图片, 以ArrayList<String>的类型传入，以便支持多张图片（注：图片最多支持9张图片，多余的图片会被丢弃）
                            ArrayList<String> imageUrls = new ArrayList<String>();
                            imageUrls.add("http://avatar.csdn.net/B/3/F/1_sandyran.jpg");//添加一个图片地址
                            params.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, imageUrls);//分享的图片URL
                            mTencent.shareToQzone(getActivity(), params, new ShareUiListener());
                        }
                    });
                }
            });

            //收藏
            holder.tv_shou.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String sql = "insert into table_shou(title,url) values(?,?)";
                    MainActivity.database.execSQL(sql, new Object[]{list.get(position).getTitle(), list.get(position).getMp4_url()});
                    holder.tv_shou.setSelected(true);
                    Toast.makeText(getActivity(), "收藏成功！", Toast.LENGTH_SHORT).show();
                }
            });

            //下载
            holder.down.setOnClickListener(new View.OnClickListener() {

                private AlertDialog dialog;

                @Override
                public void onClick(View v) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    dialog = builder.create();
                    View vv = View.inflate(getActivity(), R.layout.dialog_video, null);
                    dialog.setView(vv);
                    final ProgressBar pb = (ProgressBar) vv.findViewById(R.id.pb);
                    TextView bu_sure = (TextView) vv.findViewById(R.id.bu_sure);
                    TextView bu_cancel = (TextView) vv.findViewById(R.id.bu_cancel);
                    dialog.show();

                    bu_sure.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            RequestParams params = new RequestParams(list.get(position).getMp4_url());
                            params.setSaveFilePath(Environment.getExternalStorageDirectory() + "/" + list.get(position).getTitle() + ".mp4");
                            params.setUseCookie(true);
                            params.setAutoResume(true);//支持断点下载
                            params.setAutoRename(false);

                            x.http().get(params, new Callback.ProgressCallback<File>() {
                                @Override
                                public void onSuccess(File result) {
                                    Toast.makeText(activity, "onSuccess" + result.toString(), Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }

                                @Override
                                public void onError(Throwable ex, boolean isOnCallback) {

                                }

                                @Override
                                public void onCancelled(CancelledException cex) {

                                }

                                @Override
                                public void onFinished() {

                                }

                                @Override
                                public void onWaiting() {

                                }

                                @Override
                                public void onStarted() {

                                }

                                @Override
                                public void onLoading(long total, long current, boolean isDownloading) {
                                    pb.setMax((int) total);
                                    pb.setProgress((int) current);
                                }
                            });
                        }
                    });
                    bu_cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                }
            });
            return convertView;
        }

        class ViewHolder {
            TextView tv_title;
            JCVideoPlayerStandard jcv;
            TextView topicName;
            TextView playCount;
            ImageView tv_shou;
            ImageView tv_share;
            ImageView down;
        }
    }

    /**
     * 自定义监听器实现IUiListener，需要3个方法
     * onComplete完成 onError错误 onCancel取消
     */
    private class ShareUiListener implements IUiListener {

        @Override
        public void onComplete(Object response) {
            //分享成功

        }

        @Override
        public void onError(UiError uiError) {
            //分享失败

        }

        @Override
        public void onCancel() {
            //分享取消

        }
    }

    /**
     * 回调
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (null != mTencent) {
            mTencent.onActivityResult(requestCode, resultCode, data);
        }
    }
}
