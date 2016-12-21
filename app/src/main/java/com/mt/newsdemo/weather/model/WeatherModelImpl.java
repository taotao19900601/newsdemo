package com.mt.newsdemo.weather.model;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.text.TextUtils;

import com.mt.newsdemo.beans.WeatherBean;
import com.mt.newsdemo.commons.Urls;
import com.mt.newsdemo.utils.LogUtil;
import com.mt.newsdemo.utils.OkHttpUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by meitao on 2016/12/17.
 */

public class WeatherModelImpl implements WeatherModel {
    private static final String TAG = "WeatherModelImpl";

    @Override
    public void loadWeatherData(String cityName, final LoadWeatherLintener listener) {
            try{
                String url = Urls.WEATHER+ URLEncoder.encode(cityName,"UTF-8");
                OkHttpUtil.get(url, new OkHttpUtil.ResultCallBack<String>() {
                    @Override
                    public void onSuccess(String response) {
                        // 解析json
                        LogUtil.d(TAG,"11111111111"+response);
                        // 数据回调
                        listener.onSuccess(null);
                    }

                    @Override
                    public void onFailure(Exception e) {
                        LogUtil.d(TAG,"222222222"+e);
                    }
                });
            }
            catch (UnsupportedEncodingException e){
                e.printStackTrace();
            }



    }

    @Override
    public void loadLocation(Context context, final LoadLocationLintener listener) {
        // 获取当前用户的经纬度
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                listener.onFailure("location fail", null);
                return;
            }

        }
        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if (location == null) {
            listener.onFailure("location fail", null);
            return;
        }

        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        String locationUrl = getLocationURL(latitude, longitude);
        // 将定位到的经纬度 通过网络请求来 当前用户所在城市的名称
        OkHttpUtil.get(locationUrl, new OkHttpUtil.ResultCallBack<String>() {
            @Override
            public void onSuccess(String response) {
                // 将定位到城市的名称回掉到 presenter 中的 onsuccess ()
                if (listener != null)
                    // 解析response
                LogUtil.e(TAG, response);

                    listener.onSuccess("");
            }

            @Override
            public void onFailure(Exception e) {

            }
        });


    }

    public String getLocationURL(double latitude, double longitude) {
        StringBuffer sb = new StringBuffer(Urls.INTERFACE_LOCATION);
        sb.append("?output=json").append("&referer=32D45CBEEC107315C553AD1131915D366EEF79B4");
        sb.append("&location=").append(latitude).append(",").append(longitude);
        LogUtil.d(TAG, sb.toString());
        return sb.toString();
    }

    public interface LoadWeatherLintener {
        void onSuccess(List<WeatherBean> list);

        void onFailure(String msg, Exception e);
    }

    public interface LoadLocationLintener {
        void onSuccess(String cityName);

        void onFailure(String msg, Exception e);
    }

}
