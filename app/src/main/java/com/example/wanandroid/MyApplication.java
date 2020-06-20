package com.example.wanandroid;

import android.app.Application;
import android.content.Context;

import com.example.wanandroid.util.ActivityUtil;

public class MyApplication extends Application {

    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        //注册Activity生命周期
        registerActivityLifecycleCallbacks(ActivityUtil.getActivityLifecycleCallbacks());
    }

    /**获取context对象
     *
     * @return
     */
    public static synchronized Context getContext() {
        return mContext;
    }

}
