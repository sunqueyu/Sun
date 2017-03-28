package com.example.administrator.newsdemo.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.newsdemo.R;
import com.example.administrator.newsdemo.fragment.Frag_Video;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import java.util.ArrayList;

import static com.example.administrator.newsdemo.R.id.im_qq;
import static com.example.administrator.newsdemo.R.id.im_zone;
import static com.example.administrator.newsdemo.R.id.web;

/**
 * 类的用途：新闻详情，收藏，分享
 * Created by Administrator on 2017/3/11.
 */

public class NextActivity extends Activity {
    private static final String APP_ID = "1105602574"; //获取的APPID
    private ShareUiListener mIUiListener;
    private Tencent mTencent;
    private AlertDialog dialog;
    private View view;
    private ImageView im_share;
    private ImageView im_shou;
    //手势滑动，使用手势监视器这个对象GestureDetector final int RIGHT = 0;
    private GestureDetector gestureDetector;
    final int LEFT = 1;
    final int RIGHT = 0;
    private WebView webView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.next_activity);

        gestureDetector=new GestureDetector(NextActivity.this,onGestureListener);

        //传入参数APPID
        mTencent = Tencent.createInstance(APP_ID, NextActivity.this.getApplicationContext());

        Intent intent = getIntent();
        final String url = intent.getStringExtra("url");
        final String title = intent.getStringExtra("title");

        webView = (WebView) findViewById(web);
        webView.setWebViewClient(new WebViewClient());

        webView.loadUrl(url);

        im_shou = (ImageView) findViewById(R.id.im_shou);
        im_share = (ImageView) findViewById(R.id.im_share);

//        SharedPreferences preferences = getSharedPreferences("config", MODE_PRIVATE);
//        final SharedPreferences.Editor editor = preferences.edit();

        //分享
        im_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(NextActivity.this);
                dialog = builder.create();
                view = View.inflate(NextActivity.this, R.layout.dialog_item, null);
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
                        mTencent.shareToQQ(NextActivity.this, params, new ShareUiListener());
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
                        mTencent.shareToQzone(NextActivity.this, params, new ShareUiListener());
                    }
                });
            }
        });


        //收藏
        im_shou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sql = "select * from table_shou";
                boolean flag = true;
                Cursor cursor = MainActivity.database.rawQuery(sql, null);
                while(cursor.moveToNext()){
                    if(cursor.getString(1).equals(title)){
                        flag=false;
                        //im_shou.setSelected(true);
                       /* if(savedInstanceState==null){
                            boolean b = savedInstanceState.getBoolean("select",false);
                            im_shou.setSelected(b);
                        }*/
                        Toast.makeText(NextActivity.this, "已经收藏过了！", Toast.LENGTH_SHORT).show();
                    }
                }
                if(flag){
                    String sqls = "insert into table_shou(title,url) values(?,?)";
                    MainActivity.database.execSQL(sqls, new Object[]{title, url});
                    im_shou.setSelected(true);
                    Toast.makeText(NextActivity.this, "收藏成功！", Toast.LENGTH_SHORT).show();
                }

            }
        });

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

    private GestureDetector.OnGestureListener onGestureListener=new GestureDetector.SimpleOnGestureListener(){
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            //e1就是初始状态的MotionEvent对象，e2就是滑动了过后的MotionEvent对象
            //velocityX和velocityY就是滑动的速率
            float x = e2.getX() - e1.getX();//滑动后的x值减去滑动前的x值 就是滑动的横向水平距离(x)
            float y = e2.getY() - e1.getY();//滑动后的y值减去滑动前的y值 就是滑动的纵向垂直距离(y)

            if (x > 100) {
                doResult(RIGHT);
                Log.w("tag", "RIGHT>" + x);
            }
            //如果滑动的横向距离大于100，表明是左滑了(因为左滑为负数，所以距离大于100就是x值小于-100)
            if (x < -100) {
                Log.w("tag", "LEFT>" + x);
                doResult(LEFT);
            }

            return true;
        }
    };

    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println(" ACTION_DOWN");//手指在屏幕上按下
                //overridePendingTransition(R.anim.slide_up_in, R.anim.slide_up_out);
                break;
            case MotionEvent.ACTION_MOVE:
                System.out.println(" ACTION_MOVE");//手指正在屏幕上滑动

                break;
            case MotionEvent.ACTION_UP:
                System.out.println(" ACTION_UP");//手指从屏幕抬起了
                overridePendingTransition(R.anim.slide_up_in, R.anim.slide_up_out);
                break;
            default:
                break;
        }

        return gestureDetector.onTouchEvent(event);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {    //注意这里不能用ONTOUCHEVENT方法，不然无效的
        gestureDetector.onTouchEvent(ev);
        webView.onTouchEvent(ev);//这几行代码也要执行，将webview载入MotionEvent对象一下，况且用载入把，不知道用什么表述合适
        return super.dispatchTouchEvent(ev);
    }


    public void doResult(int action) {

        switch (action) {
            case RIGHT:
                System.out.println("go right");
                finish();
                break;
            case LEFT:
                Log.i(" ACTION_MOVE","go left");
                System.out.println("go left");
                break;
        }
    }


   /* @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("select",true);
    }*/
}
