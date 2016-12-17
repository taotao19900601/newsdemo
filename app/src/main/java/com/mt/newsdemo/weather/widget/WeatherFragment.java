package com.mt.newsdemo.weather.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mt.newsdemo.R;
import com.mt.newsdemo.beans.WeatherBean;
import com.mt.newsdemo.weather.view.WeatherView;

import java.util.List;

/**
 * Created by meitao on 2016/12/17.
 */

public class WeatherFragment extends Fragment implements WeatherView {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_weather, null, false);

        return view;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showWeatherLayout() {

    }

    @Override
    public void setCity(String city) {

    }

    @Override
    public void setToday(String data) {

    }

    @Override
    public void setTemperature(String temperature) {

    }

    @Override
    public void setWind(String wind) {

    }

    @Override
    public void setWeather(String weather) {

    }

    @Override
    public void setWeatherImage(int res) {

    }

    @Override
    public void setWeatherData(List<WeatherBean> lists) {

    }

    @Override
    public void showErrorToast(String msg) {

    }
}
