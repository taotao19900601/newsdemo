package com.mt.newsdemo.news.presenter;

import com.mt.newsdemo.beans.NewsBean;
import com.mt.newsdemo.commons.Urls;
import com.mt.newsdemo.news.model.NewsModel;
import com.mt.newsdemo.news.model.NewsModelImpl;
import com.mt.newsdemo.news.view.NewsView;
import com.mt.newsdemo.news.widget.NewsFragment;
import com.mt.newsdemo.utils.LogUtil;

import java.util.List;

/**
 * Created by meitao on 2016/12/12.
 */

public class NewsPresenterImpl implements NewsPresenter, NewsModelImpl.OnLoadNewsListListener {
    private static final String TAG = "NewsPresenterImpl";
    private NewsView mNewsView;
    private NewsModel mNewsModel;

    public NewsPresenterImpl(NewsView newsView){
        this.mNewsView = newsView;
        mNewsModel = new NewsModelImpl();
    }

    @Override
    public void loadNews(int type, int pageIndex) {
        String url = getUrl(type, pageIndex);
        LogUtil.e(TAG,url);
        if(pageIndex == 0)
            mNewsView.showProgress();
        mNewsModel.loadNews(url, type, this);
    }

    private String getUrl(int type, int pageIndex){
        StringBuffer sb = new StringBuffer();
        switch(type){
            case NewsFragment.NEWS_TYPE_TOP:
                sb.append(Urls.TOP_URL).append(Urls.TOP_ID);
                break;
            case NewsFragment.NEWS_TYPE_NBA:
                sb.append(Urls.COMMON_URL).append(Urls.NBA_ID);
                break;
            case NewsFragment.NEWS_TYPE_CARS:
                sb.append(Urls.COMMON_URL).append(Urls.CAR_ID);
                break;
            case NewsFragment.NEWS_TYPE_JOKES:
                sb.append(Urls.COMMON_URL).append(Urls.JOKE_ID);
                break;

        }
        sb.append("/").append(pageIndex).append(Urls.END_URL);
        return sb.toString();
    }

    @Override
    public void onSuccess(List<NewsBean> list) {
        if(list !=null){
            mNewsView.hideProgress();
            mNewsView.addNews(list);
        }
    }

    @Override
    public void onFailure(String msg, Exception e) {
        mNewsView.hideProgress();
        mNewsView.showLoadFailMsg();
    }
}
