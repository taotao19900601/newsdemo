package com.mt.newsdemo.weather.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.mt.newsdemo.beans.WeatherBean;
import com.mt.newsdemo.utils.LogUtil;
import com.mt.newsdemo.utils.ToolsUtil;
import com.mt.newsdemo.weather.model.WeatherModel;
import com.mt.newsdemo.weather.model.WeatherModelImpl;
import com.mt.newsdemo.weather.view.WeatherView;

import java.util.List;

/**
 * Created by meitao on 2016/12/17.
 */

public class WeatherPresenterImpl implements WeatherPresenter,WeatherModelImpl.LoadWeatherLintener{
    private WeatherModel mWeatherModel;
    private WeatherView mWeatherView;
    private Context mContext;
    WeatherModelImpl.LoadWeatherLintener mListener;

    public WeatherPresenterImpl(WeatherView weatherView, Context ctx) {
        mWeatherModel = new WeatherModelImpl();
        mContext = ctx;
        mWeatherView = weatherView;
    }

    WeatherModelImpl.LoadLocationLintener linstener = new WeatherModelImpl.LoadLocationLintener() {
        @Override
        public void onSuccess(String cityName) {
            LogUtil.d("WeatherPresenterImpl","获取成功");
            mWeatherView.setCity(cityName);
            mWeatherModel.loadWeatherData(cityName ,WeatherPresenterImpl.this);
        }

        @Override
        public void onFailure(String msg, Exception e) {

        }
    };
    @Override
    public void loadWeatherData() {
        if (ToolsUtil.isNetworkAvailable(mContext)) {
            mWeatherModel.loadLocation(mContext, linstener);



        }

    }


    @Override
    public void onSuccess(List<WeatherBean> list) {
        if(list != null && list.size()>0){
            WeatherBean bean= list.remove(0);
            mWeatherView.setWeather(bean.getWeather());
            mWeatherView.setTemperature(bean.getTemperature());
            mWeatherView.setToday(bean.getDate());
            mWeatherView.setWind(bean.getWind());
            mWeatherView.setWeatherImage(bean.getImageRes());
        }
        mWeatherView.setWeatherData(list);
        mWeatherView.hideProgress();
    }

    @Override
    public void onFailure(String msg, Exception e) {

    }
}
