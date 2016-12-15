package com.mt.newsdemo.news.widget;

import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.mt.newsdemo.R;
import com.mt.newsdemo.beans.NewsBean;
import com.mt.newsdemo.news.presenter.NewsDetailPresenter;
import com.mt.newsdemo.news.presenter.NewsDetailPresenterImpl;
import com.mt.newsdemo.news.view.NewsDetailView;
import com.mt.newsdemo.utils.ImageLoaderUtil;
import com.mt.newsdemo.utils.LogUtil;
import com.mt.newsdemo.utils.ToolsUtil;

import org.sufficientlysecure.htmltextview.HtmlTextView;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by meitao on 2016/12/14.
 */

public class NewsDetailActivity extends SwipeBackActivity implements NewsDetailView {

    private NewsBean mNewsBean;
    private HtmlTextView mHtmlTextView;
    private NewsDetailPresenter mNewsDetailPresenter;
    private ProgressBar mProgressBar;
    private SwipeBackLayout mSwipeBackLayout;
    private Toolbar mToolbar;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        mHtmlTextView = (HtmlTextView) findViewById(R.id.htNewsContent);
        mProgressBar = (ProgressBar) findViewById(R.id.progress);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeSize(ToolsUtil.getWidthInPx(this));
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        mNewsBean = (NewsBean) getIntent().getSerializableExtra("news");

        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        if (mNewsBean != null) {
            mCollapsingToolbarLayout.setTitle(mNewsBean.getTitle());
            ImageLoaderUtil.display(this, (ImageView) findViewById(R.id.ivImage), mNewsBean.getImgsrc());
            mNewsDetailPresenter = new NewsDetailPresenterImpl(getApplication(), this);
            mNewsDetailPresenter.loadNewsDetail(mNewsBean.getDocid());
        }

    }

    @Override
    public void showNewsDetialContent(String newsDetailContent) {
        if (newsDetailContent != null) {
            hideProgress();
            mHtmlTextView.setHtmlFromString(newsDetailContent, new HtmlTextView.LocalImageGetter());
        }

    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }


}
