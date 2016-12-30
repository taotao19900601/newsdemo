package com.mt.newsdemo.news.model;

/**
 * Created by meitao on 2016/12/12.
 */

public interface NewsModel {
    void loadNews(String url, int type, NewsModelImpl.OnLoadNewsListListener listener);

    void loadNewsDetail(String docid, NewsModelImpl.OnLoadNewsDetailListener listener);
}
