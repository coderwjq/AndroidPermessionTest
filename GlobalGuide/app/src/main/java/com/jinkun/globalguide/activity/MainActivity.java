package com.jinkun.globalguide.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;

import com.jinkun.globalguide.R;
import com.jinkun.globalguide.service.GPSService;
import com.litesuits.common.assist.Toastor;

public class MainActivity extends BaseActivity {

    private final int REQUEST_CODE_ASK_PERMESSION = 1;
    private Toastor mToaster = null;

    @Override
    public void initEvent() {
        checkUserPermission();
    }

    private void checkUserPermission() {
        int hasLocationPermession = this.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION);

        if (hasLocationPermession != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_ASK_PERMESSION);
        }

        startGPSService();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMESSION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 允许权限
                    startGPSService();
                } else {
                    mToaster.showSingletonToast("权限被拒绝，无法使用GPS服务");
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    /**
     * 开启GPS定位服务
     */
    private void startGPSService() {
        Intent service = new Intent();
        service.setClass(this, GPSService.class);
        startService(service);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        mToaster = new Toastor(this);
    }
}
