package com.mt.newsdemo.weather.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mt.newsdemo.R;
import com.mt.newsdemo.beans.WeatherBean;
import com.mt.newsdemo.utils.LogUtil;
import com.mt.newsdemo.weather.WeatherJsonUtils;
import com.mt.newsdemo.weather.presenter.WeatherPresenter;
import com.mt.newsdemo.weather.presenter.WeatherPresenterImpl;
import com.mt.newsdemo.weather.view.WeatherView;

import java.util.List;

/**
 * Created by meitao on 2016/12/17.
 */

public class WeatherFragment extends Fragment implements WeatherView {
    private WeatherPresenter mWeatherPresenter;
    private TextView mTodayTV;
    private ImageView mTodayWeatherImage;
    private TextView mTodayTemperatureTV;
    private TextView mTodayWindTV;
    private TextView mTodayWeatherTV;
    private TextView mCityTV;
    private ProgressBar mProgressBar;
    private LinearLayout mWeatherLayout;
    private LinearLayout mWeatherContentLayout;
    private FrameLayout mRootLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWeatherPresenter = new WeatherPresenterImpl(this, getContext());

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_weather, null, false);
        mTodayTV = (TextView) view.findViewById(R.id.today);
        mTodayWeatherImage = (ImageView) view.findViewById(R.id.weatherImage);
        mTodayTemperatureTV = (TextView) view.findViewById(R.id.weather_Temp);
        mTodayWindTV = (TextView) view.findViewById(R.id.wind);
        mTodayWeatherTV = (TextView) view.findViewById(R.id.weather);
        mCityTV = (TextView) view.findViewById(R.id.city);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progress);
        mWeatherLayout = (LinearLayout) view.findViewById(R.id.weather_layout);
        mWeatherContentLayout = (LinearLayout) view.findViewById(R.id.wearther_content);
        mRootLayout = (FrameLayout) view.findViewById(R.id.root_layout);
        mWeatherPresenter.loadWeatherData();

        return view;
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showWeatherLayout() {

    }

    @Override
    public void setCity(String city) {
        if (mCityTV != null)
            mCityTV.setText(city);
    }

    @Override
    public void setToday(String data) {
        if (mTodayTV != null)
            mTodayTV.setText(data);
    }

    @Override
    public void setTemperature(String temperature) {
        if (mTodayTemperatureTV != null)
            mTodayTemperatureTV.setText(temperature);
    }

    @Override
    public void setWind(String wind) {
        if (mTodayWindTV != null)
            mTodayWindTV.setText(wind);
    }

    @Override
    public void setWeather(String weather) {
        if (mTodayWeatherTV != null)
            mTodayWeatherTV.setText(weather);
    }

    @Override
    public void setWeatherImage(int res) {
        if (mTodayWeatherImage != null)
            mTodayWeatherImage.setImageResource(res);

    }

    @Override
    public void setWeatherData(List<WeatherBean> lists) {
        if (lists != null) {
            for (WeatherBean bean : lists) {
                View weatherView = LayoutInflater.from(getContext()).inflate(R.layout.item_weather, null);
                TextView dataTV = (TextView) weatherView.findViewById(R.id.date);
                dataTV.setText(bean.getDate());
                ImageView weatherIV = (ImageView) weatherView.findViewById(R.id.weatherImage);
                weatherIV.setImageResource(bean.getImageRes());
                TextView tempTV = (TextView) weatherView.findViewById(R.id.weatherTemp);
                tempTV.setText(bean.getTemperature());
                TextView windTV = (TextView) weatherView.findViewById(R.id.wind);
                windTV.setText(bean.getWind());
                TextView weatherTV = (TextView) weatherView.findViewById(R.id.weather);
                weatherTV.setText(bean.getWeather());
                mWeatherContentLayout.addView(weatherView);
            }
        }
    }

    @Override
    public void showErrorToast(String msg) {

    }
}
