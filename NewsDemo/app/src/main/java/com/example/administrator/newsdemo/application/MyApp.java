package com.example.administrator.newsdemo.application;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.xutils.x;

/**
 * 类的用途：
 * Created by Administrator on 2017/3/12.
 */

public class MyApp extends Application {

    public static int fontInt = 0;
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(false);

        initImage();

        context=getApplicationContext();

    }

    private void initImage() {
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .memoryCacheExtraOptions(480, 800)
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .build();
        ImageLoader.getInstance().init(configuration);
    }

    public static Context getContext(){
        return context;
    }
}
