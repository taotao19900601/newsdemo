package com.mt.newsdemo.news.view;

import com.mt.newsdemo.beans.NewsBean;

import java.util.List;

/**
 * Created by meitao on 2016/12/12.
 */

public interface NewsView {
    void showProgress();

    void addNews(List<NewsBean> newsList);

    void hideProgress();

    void showLoadFailMsg();

}
