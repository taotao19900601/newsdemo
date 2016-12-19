package com.mt.newsdemo.weather.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.mt.newsdemo.utils.ToolsUtil;
import com.mt.newsdemo.weather.model.WeatherModel;
import com.mt.newsdemo.weather.model.WeatherModelImpl;
import com.mt.newsdemo.weather.view.WeatherView;

/**
 * Created by meitao on 2016/12/17.
 */

public class WeatherPresenterImpl implements WeatherPresenter {
    private WeatherModel mWeatherModel;
    private WeatherView mWeatherView;
    private Context mContext;
    WeatherModelImpl.LoadWeatherLintener mListener;

    public WeatherPresenterImpl(WeatherView weatherView, Context ctx) {
        mWeatherModel = new WeatherModelImpl();
        mContext = ctx;
        mWeatherView = weatherView;
    }

    @Override
    public void loadWeatherData() {
        if (ToolsUtil.isNetworkAvailable(mContext)) {
            mWeatherModel.loadLocation(mContext, new WeatherModelImpl.LoadLocationLintener() {
                @Override
                public void onSuccess(String cityName) {
                    if(!TextUtils.isEmpty(cityName)){
                        mWeatherView.setCity(cityName);
                    }
                }

                @Override
                public void onFailure(String msg, Exception e) {

                }
            });
        }

    }
}
