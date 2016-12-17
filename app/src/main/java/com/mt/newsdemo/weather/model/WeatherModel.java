package com.mt.newsdemo.weather.model;

import android.content.Context;

/**
 * Created by meitao on 2016/12/17.
 */

public interface WeatherModel {

    public void loadWeatherData(String cityName, WeatherModelImpl.LoadWeatherLintener listener);

    public void loadLocation(Context context, WeatherModelImpl.LoadLocationLintener listener);

}
