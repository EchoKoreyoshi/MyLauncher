package com.itwwy.mylauncher;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

/**
 * @author ：vee
 * @package ：com.itwwy.mylauncher
 * @date ：2019/4/6
 * @describe :
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
    }
}
