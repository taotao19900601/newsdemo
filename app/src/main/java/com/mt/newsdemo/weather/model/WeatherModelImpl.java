package com.mt.newsdemo.weather.model;

import android.content.Context;
import android.location.LocationManager;

import com.mt.newsdemo.beans.WeatherBean;
import com.mt.newsdemo.utils.OkHttpUtil;

import java.util.List;

/**
 * Created by meitao on 2016/12/17.
 */

public class WeatherModelImpl implements WeatherModel{


    @Override
    public void loadWeatherData(String cityName, LoadWeatherLintener listener) {


    }

    @Override
    public void loadLocation(Context context, final LoadLocationLintener listener) {
        // 获取当前用户的经纬度
        LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        // 将定位到的经纬度 通过网络请求来 当前用户所在城市的名称
        OkHttpUtil.get("", new OkHttpUtil.ResultCallBack<String>() {
            @Override
            public void onSuccess(String response) {
                // 将定位到城市的名称回掉到 presenter 中的 onsuccess ()
                if(listener != null)
                    // 解析response

                    listener.onSuccess("");
            }

            @Override
            public void onFailure(Exception e) {

            }
        });


    }

    public interface LoadWeatherLintener{
        void onSuccess(List<WeatherBean> list);
        void onFailure(String msg, Exception e);
    }

    public interface LoadLocationLintener{
        void onSuccess(String cityName);
        void onFailure(String msg, Exception e);
    }

}
