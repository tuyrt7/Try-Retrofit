package com.tuyrt.httpdemo.util;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.util.List;
import java.util.Locale;

import static android.content.ContentValues.TAG;

/**
 * Created by futao on 2017/11/1.
 */

public class LocationUtils {

    public static Location getLocation(Context context) {
        // 获取位置管理服务
        String serviceName = Context.LOCATION_SERVICE;
        LocationManager locationManager = (LocationManager) context.getSystemService(serviceName);
        // 查找到服务信息
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE); // 高精度
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(Criteria.POWER_LOW); // 低功耗
        String provider = locationManager.getBestProvider(criteria, true); // 获取GPS信息

        return locationManager.getLastKnownLocation(provider);
    }

    // 获取地址信息
    public static  List<Address> getAddress(Context mContext ,Location location) {
        List<Address> result = null;
        try {
            if (location != null) {
                Geocoder gc = new Geocoder(mContext, Locale.getDefault());
                if (gc.isPresent()) {
                    result = gc.getFromLocation(location.getLatitude(),location.getLongitude(), 1);
                }else {
                    Log.i(TAG, "getAddress: gc isPresent false 。 ");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public interface LocListener {
        void result(String s);
    }
}
