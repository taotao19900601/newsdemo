package com.mt.newsdemo.images.view;

import com.mt.newsdemo.beans.ImageBean;

import java.util.List;

/**
 * Created by meitao on 2016/12/15.
 */

public interface ImageView {
    void addImages(List<ImageBean> list);
    void showProgress();
    void hideProgress();
    void showLoadFailMsg();
}
