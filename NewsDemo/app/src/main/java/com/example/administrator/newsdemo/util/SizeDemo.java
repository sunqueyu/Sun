package com.example.administrator.newsdemo.util;

import com.example.administrator.newsdemo.application.MyApp;

/**
 * 类的用途：
 * Created by Administrator on 2017/3/27.
 */

public class SizeDemo {
    /**
     * 根据手机的分辨率dp的单位转为px(像素)
     * @param px
     * @return
     */
    public static int px2dip(int px) {
        //获取像素密度
        float density = MyApp.getContext().getResources().getDisplayMetrics().density;
        int dip = (int) (px / density + 0.5f);
        return dip;
    }
}
