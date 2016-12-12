package com.mt.newsdemo.news.model;

import com.mt.newsdemo.beans.NewsBean;
import com.mt.newsdemo.commons.Urls;
import com.mt.newsdemo.news.NewsJsonUtils;
import com.mt.newsdemo.news.widget.NewsFragment;
import com.mt.newsdemo.utils.LogUtil;
import com.mt.newsdemo.utils.OkHttpUtil;

import java.util.List;

/**
 * Created by meitao on 2016/12/12.
 */

public class NewsModelImpl implements NewsModel{
    private static final String TAG = "NewsModelImpl";

    @Override
    public void loadNews(String url, final int type, final OnLoadNewsListListener listener) {
        OkHttpUtil.ResultCallBack<String> callBack = new OkHttpUtil.ResultCallBack<String>() {
            @Override
            public void onSuccess(String response) {
                List<NewsBean> dataList = NewsJsonUtils.readJsonNewsBeans(response, getID(type));
                listener.onSuccess(dataList);
                LogUtil.e(TAG,"onSuccesse");
            }

            @Override
            public void onFailure(Exception e) {
                listener.onFailure("请求失败",e);
            }
        };
        OkHttpUtil.get(url, callBack);
    }
    // 加载详情页
    @Override
    public void loadNewsDetail(String docid, OnLoadNewsListListener listener) {

    }

    public interface OnLoadNewsListListener {
        void onSuccess(List<NewsBean> list);
        void onFailure(String msg, Exception e);
    }

    private static String getID(int type){
        String id;
        switch(type){
            case NewsFragment.NEWS_TYPE_TOP:
                id = Urls.TOP_ID;
                break;
            case NewsFragment.NEWS_TYPE_NBA:
                id = Urls.NBA_ID;
                break;
            case NewsFragment.NEWS_TYPE_CARS:
                id =Urls.CAR_ID;
                break;
            case NewsFragment.NEWS_TYPE_JOKES:
                id =Urls.JOKE_ID;
                break;
            default:
                id = Urls.TOP_ID;
                break;
        }
        return id;
    }

}
