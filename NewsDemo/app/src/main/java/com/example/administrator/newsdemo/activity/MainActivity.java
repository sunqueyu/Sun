package com.example.administrator.newsdemo.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.newsdemo.R;
import com.example.administrator.newsdemo.bean.DB_Bean;
import com.example.administrator.newsdemo.fragment.Frag_News;
import com.example.administrator.newsdemo.fragment.Frag_Video;
import com.example.administrator.newsdemo.util.DBHelper;
import com.example.administrator.newsdemo.util.NetUtils;
import com.example.administrator.newsdemo.util.Utils;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private static final String APP_ID = "1105602574";//官方获取的APPID
    private Tencent mTencent;
    private BaseUiListener mIUiListener;
    private UserInfo mUserInfo;
    private String[] textt;
    private LinearLayout lin_no;
    private LinearLayout lin_success;
    private ImageView pic_title;
    private TextView text_nickname;
    private int[] pics;
    private String title_url = "http://ic.snssdk.com/article/category/get/v2/?user_city=%E5%AE%89%E9%98%B3&bd_latitude=4.9E-324&bd_longitude=4.9E-324&bd_loc_time=1465099837&categories=%5B%22video%22%2C%22news_hot%22%2C%22news_local%22%2C%22news_society%22%2C%22subscription%22%2C%22news_entertainment%22%2C%22news_tech%22%2C%22news_car%22%2C%22news_sports%22%2C%22news_finance%22%2C%22news_military%22%2C%22news_world%22%2C%22essay_joke%22%2C%22image_funny%22%2C%22image_ppmm%22%2C%22news_health%22%2C%22positive%22%2C%22jinritemai%22%2C%22news_house%22%5D&version=17375902057%7C14%7C1465030267&iid=4471477475&device_id=17375902057&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=Samsung+Galaxy+S3+-+4.3+-+API+18+-+720x1280&os_api=18&os_version=4.3&openudid=7036bc89d44f680c";
    //存放Fragment的集合
    private ArrayList<Fragment> list_f;
    private TabLayout tab;
    private ViewPager vp;
    private ImageButton bu;
    private CheckBox cb_night;
    //默认的日间模式
    private int theme = R.style.AppTheme;
    private ArrayList<String> list_t;
    private ImageView bu_image;
    private ArrayList<DB_Bean> list;
    public static SQLiteDatabase database;
    private CheckBox cb_download;
    private Frag_Video frag_video;
    //调用系统相册相机上传头像
    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    private static final int CROP_SMALL_PICTURE = 2;
    private static final int REQUESTCODE_PICK = 1;
    protected static Uri tempUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //恢复数据 做判空
        if (savedInstanceState != null) {
            theme = savedInstanceState.getInt("theme");
            //设置主题 此方法必须在setContentView()之前调用
            setTheme(theme);

        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //传入参数APPID和全局Context上下文
        mTencent = Tencent.createInstance(APP_ID, MainActivity.this.getApplicationContext());

        initView();


        SMSSDK.initSDK(this, "1c1087dc1f190", "433f07e7ffe20332d31edfaef94fd266");

        //点击对应频道跳转到当前标题下对应的页面
        Intent intent = getIntent();
        int positon = intent.getIntExtra("positon", -1);
        vp.setCurrentItem(positon);

        //调用系统相机和相册更改和设置头像
        pic_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChoosePicDialog();
            }
        });
    }

    /**
     * 显示修改头像的对话框
     */
    protected void showChoosePicDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("设置头像");
        String[] items = {"选择本地照片", "拍照"};
        builder.setNegativeButton("取消", null);
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case CHOOSE_PICTURE: // 选择本地照片
                        Intent pickIntent = new Intent(Intent.ACTION_PICK, null);
                        // 如果朋友们要限制上传到服务器的图片类型时可以直接写如：image/jpeg 、 image/png等的类型
                        pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                        startActivityForResult(pickIntent, REQUESTCODE_PICK);
                        break;
                    case TAKE_PICTURE: // 拍照
                        Intent openCameraIntent = new Intent(
                                MediaStore.ACTION_IMAGE_CAPTURE);
                        tempUri = Uri.fromFile(new File(Environment
                                .getExternalStorageDirectory(), "image.jpg"));
                        // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
                        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
                        startActivityForResult(openCameraIntent, TAKE_PICTURE);
                        break;
                }
            }
        });
        builder.create().show();
    }

   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }*/

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    protected void startPhotoZoom(Uri uri) {
        if (uri == null) {
            Log.i("tag", "The uri is not exist.");
        }
        tempUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_SMALL_PICTURE);
    }

    /**
     * 保存裁剪之后的图片数据
     */
    protected void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            photo = Utils.toRoundBitmap(photo, tempUri); // 这个时候的图片已经被处理成圆形的了
            pic_title.setImageBitmap(photo);
            bu.setImageBitmap(photo);
            uploadPic(photo);
        }
    }

    private void uploadPic(Bitmap bitmap) {
        // 上传至服务器
        // ... 可以在这里把Bitmap转换成file，然后得到file的url，做文件上传操作
        // 注意这里得到的图片已经是圆形图片了
        // bitmap是没有做个圆形处理的，但已经被裁剪了

        String imagePath = Utils.savePhoto(bitmap, Environment
                .getExternalStorageDirectory().getAbsolutePath(), String
                .valueOf(System.currentTimeMillis()));
        Log.e("imagePath", imagePath + "");
        if (imagePath != null) {
            // 拿着imagePath上传了
            // ...
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.account_icon_mobile:
                //打开注册页面
                RegisterPage registerPage = new RegisterPage();
                registerPage.setRegisterCallback(new EventHandler() {
                    public void afterEvent(int event, int result, Object data) {
                        // 解析注册结果
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            @SuppressWarnings("unchecked")
                            HashMap<String, Object> phoneMap = (HashMap<String, Object>) data;
                            String country = (String) phoneMap.get("country");
                            String phone = (String) phoneMap.get("phone");

                            // 提交用户信息（此方法可以不调用）
                            //  registerUser(country, phone);
                        }
                    }
                });
                registerPage.show(MainActivity.this);
                lin_no.setVisibility(View.GONE);
                lin_success.setVisibility(View.VISIBLE);


                break;

            case R.id.account_icon_qzone:
                /**通过这句代码，SDK实现了QQ的登录，这个方法有三个参数，第一个参数是context上下文，第二个参数SCOPO 是一个String类型的字符串，表示一些权限
                 官方文档中的说明：应用需要获得哪些API的权限，由“，”分隔。例如：SCOPE = “get_user_info,add_t”；所有权限用“all”
                 第三个参数，是一个事件监听器，IUiListener接口的实例，这里用的是该接口的实现类 */
                mIUiListener = new BaseUiListener();
                //all表示获取所有权限
                mTencent.login(MainActivity.this, "all", mIUiListener);
                break;

            case R.id.cb_night:
                //切换日夜间模式
                theme = (theme == R.style.AppTheme) ? R.style.NightAppTheme : R.style.AppTheme;
                //重新创建
                MainActivity.this.recreate();

                break;

            case R.id.bu_image:
                Intent intent = new Intent(MainActivity.this, MoreActivity.class);
                startActivity(intent);
                finish();
                break;

            case R.id.cb_download:
                boolean workIsAvailable = NetUtils.isNetWorkIsAvailable(MainActivity.this);
                if (!workIsAvailable) {
                    Toast.makeText(MainActivity.this, "网络未连接，请设置网络", Toast.LENGTH_SHORT).show();
                    Intent intents = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                    startActivity(intents);
                } else {
                    //连接成功下载
                    Toast.makeText(MainActivity.this, "网络连接成功", Toast.LENGTH_SHORT).show();

                    download();
                }
                break;
        }
    }

    //fragment的适配器
    private class MyTabTitleAdapter extends FragmentPagerAdapter {

        public MyTabTitleAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list_f.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return list.get(position).getName();
        }
    }

    private void initView() {
        //侧拉按钮
        bu = (ImageButton) findViewById(R.id.bu);
        tab = (TabLayout) findViewById(R.id.tab);
        vp = (ViewPager) findViewById(R.id.vp);
        bu_image = (ImageView) findViewById(R.id.bu_image);

        bu_image.setOnClickListener(this);

        //存放数据库数据
        list = new ArrayList<>();
        String sql = "select * from table_me";
        DBHelper helper = new DBHelper(this);
        database = helper.getWritableDatabase();
        Cursor cursor = database.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(1);
            String url = cursor.getString(2);
            DB_Bean db_bean = new DB_Bean();
            db_bean.setName(name);
            db_bean.setUrl(url);
            list.add(db_bean);
        }

        DB_Bean db_bean = new DB_Bean();
        db_bean.setName("视频");
        list.add(db_bean);
        //遍历集合 创建fragment
        list_f = new ArrayList<>();
        for (int i = 0; i < list.size() - 1; i++) {
            Frag_News frag = new Frag_News();
            Bundle bundle = new Bundle();
            bundle.putString("path", list.get(i).getUrl());
            frag.setArguments(bundle);
            list_f.add(frag);
        }
        if (frag_video == null) {
            frag_video = new Frag_Video();
            list_f.add(frag_video);
        }

        MyTabTitleAdapter adapter = new MyTabTitleAdapter(getSupportFragmentManager());
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);
        vp.setAdapter(adapter);
        tab.setupWithViewPager(vp);
        tab.setTabsFromPagerAdapter(adapter);


        final SlidingMenu menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        menu.setBehindOffset(100);
        menu.attachToActivity(MainActivity.this, SlidingMenu.SLIDING_CONTENT);
        menu.setMenu(R.layout.menu_layout);
        bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu.toggle();
            }
        });

        lin_success = (LinearLayout) menu.findViewById(R.id.lin_success);
        pic_title = (ImageView) menu.findViewById(R.id.pic_title);
        text_nickname = (TextView) menu.findViewById(R.id.text_nickname);
        lin_no = (LinearLayout) menu.findViewById(R.id.lin_no);
        cb_night = (CheckBox) menu.findViewById(R.id.cb_night);
        cb_download = (CheckBox) menu.findViewById(R.id.cb_download);

        cb_night.setOnClickListener(this);
        cb_download.setOnClickListener(this);

        ImageView account_icon_qzone = (ImageView) menu.findViewById(R.id.account_icon_qzone);
        ImageView account_icon_weibo = (ImageView) menu.findViewById(R.id.account_icon_weibo);
        ImageView account_icon_mobile = (ImageView) menu.findViewById(R.id.account_icon_mobile);
        ListView list_menu = (ListView) menu.findViewById(R.id.list_menu);
        account_icon_mobile.setOnClickListener(this);
        account_icon_qzone.setOnClickListener(this);

        textt = new String[]{"好友动态", "我的话题", "收藏", "活动", "商城", "反馈", "我要爆料"};
        pics = new int[]{R.mipmap.dynamicicon_leftdrawer, R.mipmap.topicicon_leftdrawer, R.mipmap.ic_action_favor_on_normal, R.mipmap.activityicon_leftdrawer, R.mipmap.sellicon_leftdrawer, R.mipmap.feedbackicon_leftdrawer, R.mipmap.icon_diagnose_ok};
        list_menu.setAdapter(new MyAdapter());

        //创建一般对话框
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("退出登录");
        builder.setMessage("是否确定退出应用");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                lin_no.setVisibility(View.VISIBLE);
                lin_success.setVisibility(View.GONE);
                bu.setImageResource(R.mipmap.mine_tabbar_normal);
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                builder.create().dismiss();
            }
        });

        pic_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.create().show();
            }
        });

        //夜间模式
        cb_night.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    cb_night.setText("日间模式");
                } else {
                    cb_night.setText("夜间模式");
                }


            }
        });


        //侧拉菜单的点击事件
        list_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 2) {
                    Intent intent = new Intent(MainActivity.this, ShouActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    //侧拉菜单的适配器
    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return textt.length;
        }

        @Override
        public Object getItem(int position) {
            return textt[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = View.inflate(MainActivity.this, R.layout.list_menu_item, null);
            TextView texts = (TextView) convertView.findViewById(R.id.texts);
            ImageView image_menu = (ImageView) convertView.findViewById(R.id.image_menu);
            texts.setText(textt[position]);
            image_menu.setImageResource(pics[position]);
            return convertView;
        }
    }

    /**
     * 自定义监听器实现IUiListener接口后，需要实现的3个方法
     * onComplete完成 onError错误 onCancel取消
     */
    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object response) {
            Toast.makeText(MainActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "response:" + response);
            JSONObject obj = (JSONObject) response;
            try {
                String openID = obj.getString("openid");
                String accessToken = obj.getString("access_token");
                String expires = obj.getString("expires_in");
                mTencent.setOpenId(openID);
                mTencent.setAccessToken(accessToken, expires);
                QQToken qqToken = mTencent.getQQToken();
                mUserInfo = new UserInfo(getApplicationContext(), qqToken);
                mUserInfo.getUserInfo(new IUiListener() {
                    @Override
                    public void onComplete(Object response) {
                        Log.e(TAG, "登录成功" + response.toString());
                        ImageOptions options = new ImageOptions.Builder()
                                .setCircular(true).build();
                        JSONObject res = (JSONObject) response;
                        String nickname = res.optString("nickname");
                        String figureurl_qq_1 = res.optString("figureurl_qq_1");
                        Log.e(TAG, nickname + "      " + figureurl_qq_1);
                        text_nickname.setText(nickname);

                        x.image().bind(pic_title, figureurl_qq_1, options);
                        x.image().bind(bu, figureurl_qq_1, options);
                        lin_no.setVisibility(View.GONE);
                        lin_success.setVisibility(View.VISIBLE);

                    }

                    @Override
                    public void onError(UiError uiError) {
                        Log.e(TAG, "登录失败" + uiError.toString());
                    }

                    @Override
                    public void onCancel() {
                        Log.e(TAG, "登录取消");

                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(UiError uiError) {
            Toast.makeText(MainActivity.this, "授权失败", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onCancel() {
            Toast.makeText(MainActivity.this, "授权取消", Toast.LENGTH_SHORT).show();

        }

    }

    /**
     * 在调用Login的Activity或者Fragment中重写onActivityResult方法
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_LOGIN) {
            Tencent.onActivityResultData(requestCode, resultCode, data, mIUiListener);
        }

        if (resultCode == RESULT_OK) { // 如果返回码是可以用的
            switch (requestCode) {
                case TAKE_PICTURE:
                    startPhotoZoom(tempUri); // 开始对图片进行裁剪处理
                    break;
                case CHOOSE_PICTURE:
                    startPhotoZoom(data.getData()); // 开始对图片进行裁剪处理
                    break;
                case CROP_SMALL_PICTURE:
                    if (data != null) {
                        setImageToView(data); // 让刚才选择裁剪得到的图片显示在界面上
                    }
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
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
                        boolean mobile = NetUtils.isMobile(MainActivity.this);
                        if (mobile) {
                            Toast.makeText(MainActivity.this, "现在未使用wifi,将耗用手机流量", Toast.LENGTH_SHORT).show();
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
                updateApk();
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

    //版本更新
    private void updateApk() {
        RequestParams params = new RequestParams("http://mapp.qzone.qq.com/cgi-bin/mapp/mapp_subcatelist_qq?yyb_cateid=-10&categoryName=%E8%85%BE%E8%AE%AF%E8%BD%AF%E4%BB%B6&pageNo=1&pageSize=20&type=app&platform=touch&network_type=unknown&resolution=412x732");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("xxx", result.toString());
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("app");
                    JSONObject jo = jsonArray.getJSONObject(0);
                    //url  apk地址
                    String url = jo.getString("url");
                    String version = jo.getString("versionName");
                    Log.i("xxx", "url:" + url + ",versionName:" + version);
                    String versionName = getVersionName();
                    //判断versionName
                /*if (version.compareTo(versionName) > 0) {
                    showChoiseDialog(url);
                }*/
                    showChoiseDialog(url);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
        });

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


    private void showChoiseDialog(String url) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        android.app.AlertDialog dialog = null;

        builder.setTitle("版本更新");
        builder.setMessage("检测到新版本，是否下载更新？");
        builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //下载
                downLoadApk();

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

    private void downLoadApk() {
        //String url = "http://192.168.190.1:8080/08web/app.apk";

        RequestParams params = new RequestParams("http://mapp.qzone.qq.com/cgi-bin/mapp/mapp_subcatelist_qq?yyb_cateid=-10&categoryName=%E8%85%BE%E8%AE%AF%E8%BD%AF%E4%BB%B6&pageNo=1&pageSize=20&type=app&platform=touch&network_type=unknown&resolution=412x732");
        //自定义保存路径 Environment.getExternalStorageDirectory() sdcard 根目录

        params.setSaveFilePath(Environment.getExternalStorageDirectory() + "/app/");
        //自动为文件命令
        params.setAutoRename(true);
        x.http().post(params, new Callback.ProgressCallback<File>() {

            //网络请求成功时回调
            @Override
            public void onSuccess(File result) {
                Toast.makeText(MainActivity.this, "下载成功", Toast.LENGTH_SHORT).show();
                //apk下载完成后 调用系统的安装方法
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(result), "application/vnd.android.package-archive");
                startActivity(intent);
                Toast.makeText(MainActivity.this, "安装成功", Toast.LENGTH_SHORT).show();

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

            }

            //下载的时候不断回调的方法
            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                //文件总大小和当前进度
                Log.i("xxx", total + "," + current);

            }
        });
    }

    //保存数据
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("theme", theme);
    }

    //恢复数据
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        theme = savedInstanceState.getInt("theme");
    }
}
