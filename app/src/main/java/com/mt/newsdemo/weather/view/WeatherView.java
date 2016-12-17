package com.mt.newsdemo.weather.view;

import com.mt.newsdemo.beans.WeatherBean;

import java.util.List;

/**
 * Created by meitao on 2016/12/17.
 */

public interface WeatherView {
    void showProgress();

    void hideProgress();

    void showWeatherLayout();

    void setCity(String city);

    void setToday(String data);

    void setTemperature(String temperature);

    void setWind(String wind);

    void setWeather(String weather);

    void setWeatherImage(int res);

    void setWeatherData(List<WeatherBean> lists);

    void showErrorToast(String msg);
}
