package com.jinkun.globalguide.service;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;

import com.litesuits.android.log.Log;

public class GPSService extends Service {
    private static final String TAG = GPSService.class.getName();
    private LocationManager mLocationManager;

    public GPSService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "GPSService onCreate...");
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int hasLocationPermession = this.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION);

        if (hasLocationPermession == PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "已获取ACCESS_FINE_LOCATION权限");
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, mLocationListener);
            mLocationManager.addGpsStatusListener(mGpsStatusListener);
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            Log.e(TAG, location.toString());
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    private GpsStatus.Listener mGpsStatusListener = new GpsStatus.Listener() {
        @Override
        public void onGpsStatusChanged(int event) {

        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mLocationManager != null) {
            // 服务注销时，解除监听
            if (mLocationListener != null) {
                mLocationManager.removeUpdates(mLocationListener);
            }

            if (mGpsStatusListener != null) {
                mLocationManager.removeGpsStatusListener(mGpsStatusListener);
            }
        }
    }
}
