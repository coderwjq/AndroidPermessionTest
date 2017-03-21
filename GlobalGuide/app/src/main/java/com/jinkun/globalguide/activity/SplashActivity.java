package com.jinkun.globalguide.activity;

import android.content.Intent;

import com.jinkun.globalguide.R;
import com.jinkun.globalguide.common.JKApplication;
import com.jinkun.globalguide.util.ActivityManager;

public class SplashActivity extends BaseActivity {
    @Override
    public void initView() {
        setContentView(R.layout.activity_splash);
    }

    @Override
    public void initData() {
    }

    @Override
    public void initEvent() {
        startMainActivity();
    }

    private void startMainActivity() {
        Intent intent = new Intent();
        intent.setClass(JKApplication.mContext, MainActivity.class);
        startActivity(intent);

        ActivityManager.getInstance().removeCurrentActivity();
    }

}
