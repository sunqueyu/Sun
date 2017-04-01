package administrator.example.com.testxutilsexpandable.application;

import android.app.Application;

import org.xutils.x;

/**
 * 类的用途：
 * Created by Administrator on 2017/3/8.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        x.Ext.init(this);
        x.Ext.setDebug(true);

    }
}
