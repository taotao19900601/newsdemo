package com.mt.newsdemo.weather.model;

import android.content.Context;

import com.mt.newsdemo.beans.WeatherBean;

import java.util.List;

/**
 * Created by meitao on 2016/12/17.
 */

public class WeatherModelImpl implements WeatherModel{


    @Override
    public void loadWeatherData(String cityName, LoadWeatherLintener listener) {

    }

    @Override
    public void loadLocation(Context context, LoadLocationLintener listener) {

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
