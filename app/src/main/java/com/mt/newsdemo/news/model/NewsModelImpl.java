package com.mt.newsdemo.news.model;

import com.mt.newsdemo.beans.NewsBean;
import com.mt.newsdemo.news.NewsJsonUtils;
import com.mt.newsdemo.utils.LogUtil;
import com.mt.newsdemo.utils.OkHttpUtil;

import java.util.List;

/**
 * Created by meitao on 2016/12/12.
 */

public class NewsModelImpl implements NewsModel{
    private static final String TAG = "NewsModelImpl";

    @Override
    public void loadNews(String url, int type, final OnLoadNewsListListener listener) {
        OkHttpUtil.ResultCallBack callBack = new OkHttpUtil.ResultCallBack() {
            @Override
            public void onSuccess(Object response) {
//                NewsJsonUtils
//                listener.onSuccess();
                LogUtil.e(TAG,"onSuccess");
            }

            @Override
            public void onFailure(Exception e) {

            }
        };
        OkHttpUtil.get(url, callBack);
    }

    @Override
    public void loadNewsDetail(String docid, OnLoadNewsListListener listener) {

    }

    public interface OnLoadNewsListListener {
        void onSuccess(List<NewsBean> list);
        void onFailure(String msg, Exception e);
    }
}
