package com.jinkun.globalguide.common;

import android.app.Application;
import android.content.Context;

/**
 * Created by coderwjq on 2017/3/20.
 */

public class JKApplication extends Application {

    public static Context mContext = null;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();
    }
}
