package com.mt.newsdemo.news.model;

import android.util.Log;

import com.mt.newsdemo.beans.NewsBean;
import com.mt.newsdemo.beans.NewsDetailBean;
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
                LogUtil.e(TAG,"onFailure"+e);
            }
        };
        OkHttpUtil.get(url, callBack);
    }
    // 加载详情页
    @Override
    public void loadNewsDetail(final String docid, final OnLoadNewsDetailListener listener) {
        OkHttpUtil.ResultCallBack<String> callBack = new OkHttpUtil.ResultCallBack<String>() {
            @Override
            public void onSuccess(String response) {
                NewsDetailBean newsDetailBean = NewsJsonUtils.readJsonNewsDetailBeans(response, docid);
                listener.onSuccess(newsDetailBean);
            }

            @Override
            public void onFailure(Exception e) {

            }
        };
        OkHttpUtil.get(getDetailUrl(docid),callBack);
    }

    private String getDetailUrl(String docId) {
        StringBuffer sb = new StringBuffer(Urls.NEW_DETAIL);
        sb.append(docId).append(Urls.END_DETAIL_URL);
        return sb.toString();
    }
    public interface OnLoadNewsListListener {
        void onSuccess(List<NewsBean> list);
        void onFailure(String msg, Exception e);
    }

    public interface OnLoadNewsDetailListener {
        void onSuccess(NewsDetailBean newsDetailBean);
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
