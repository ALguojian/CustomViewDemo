package com.alguojian.customviewdemo;

import android.app.Application;

import com.socks.library.KLog;

/**
 * ${Descript}
 *
 * @author alguojian
 * @date 2018/7/20
 */
public class Myapp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        KLog.init(true,"asdfghjkl");
    }
}
