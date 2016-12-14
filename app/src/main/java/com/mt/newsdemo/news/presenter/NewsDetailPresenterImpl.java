package com.mt.newsdemo.news.presenter;

import android.content.Context;

import com.mt.newsdemo.beans.NewsBean;
import com.mt.newsdemo.beans.NewsDetailBean;
import com.mt.newsdemo.news.model.NewsModel;
import com.mt.newsdemo.news.model.NewsModelImpl;
import com.mt.newsdemo.news.view.NewsDetailView;
import com.mt.newsdemo.news.view.NewsView;
import com.mt.newsdemo.news.widget.NewsDetailActivity;

import java.util.List;

/**
 * Created by meitao on 2016/12/14.
 */

public class NewsDetailPresenterImpl implements NewsDetailPresenter, NewsModelImpl.OnLoadNewsDetailListener {
    private Context mContext;
    private NewsDetailView mNewsDetailView;
    private NewsModel mNewsModel;


    public NewsDetailPresenterImpl(Context context, NewsDetailView detailView) {
        this.mContext = context;
        this.mNewsDetailView = detailView;
        mNewsModel = new NewsModelImpl();
    }

    @Override
    public void loadNewsDetail(String docid) {
        mNewsDetailView.showProgress();
        mNewsModel.loadNewsDetail(docid, this);
    }


    @Override
    public void onSuccess(NewsDetailBean newsDetailBean) {
        if (newsDetailBean != null) {
            mNewsDetailView.showNewsDetialContent(newsDetailBean.getBody());
        }

    }

    @Override
    public void onFailure(String msg, Exception e) {

    }
}
