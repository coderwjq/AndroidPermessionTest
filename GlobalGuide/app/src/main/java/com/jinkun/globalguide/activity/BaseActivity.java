package com.jinkun.globalguide.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jinkun.globalguide.util.ActivityManager;

/**
 * Created by coderwjq on 2017/3/20.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityManager.getInstance().addActivity(this);

        initView();
        initData();
        initEvent();
    }

    /**
     * 初始化事件监听
     */
    public abstract void initEvent();

    /**
     * 初始化数据
     */
    public abstract void initData();

    /**
     * 初始化控件
     */
    public abstract void initView();

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
