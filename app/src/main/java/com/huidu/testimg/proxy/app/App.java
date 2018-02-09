package com.huidu.testimg.proxy.app;

import android.app.Application;

import com.huidu.testimg.proxy.loadimg.LoadImg;

/**
 * Created by Administrator on 2018/2/8 0008.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LoadImg.getInstance().init();
    }
}
