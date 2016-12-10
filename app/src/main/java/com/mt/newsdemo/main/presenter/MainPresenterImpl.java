package com.mt.newsdemo.main.presenter;

import com.mt.newsdemo.R;
import com.mt.newsdemo.main.view.MainView;

/**
 * Created by meitao on 2016/12/10.
 */

public class MainPresenterImpl implements MainPresenter{
    private MainView mMainView;
    public MainPresenterImpl(MainView mainView){
        this.mMainView = mainView;
    }
    @Override
    public void switchNavigation(int id) {
        switch (id){
            case R.id.navigation_item_news:
                mMainView.switch2News();
                break;
            case R.id.navigation_item_images:
                mMainView.switch2Images();
                break;
            case R.id.navigation_item_weather:
                mMainView.switch2Weather();
                break;
            case R.id.navigation_item_about:
                mMainView.switch2About();
                break;

        }
    }
}
