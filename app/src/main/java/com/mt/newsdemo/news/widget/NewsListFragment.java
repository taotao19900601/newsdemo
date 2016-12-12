package com.mt.newsdemo.news.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mt.newsdemo.R;
import com.mt.newsdemo.beans.NewsBean;
import com.mt.newsdemo.news.NewsAdapter;
import com.mt.newsdemo.news.presenter.NewsPresenter;
import com.mt.newsdemo.news.presenter.NewsPresenterImpl;
import com.mt.newsdemo.news.view.NewsView;

import java.util.List;

/**
 * Created by meitao on 2016/12/12.
 */

public class NewsListFragment extends Fragment implements NewsView, SwipeRefreshLayout.OnRefreshListener, NewsAdapter.OnItemClickListener {
    public static final String TAG = "NewsListFragment";

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private NewsAdapter mNewsAdapter;
    private List<NewsBean> mData;
    private NewsPresenter mNewsPresenter;

    private int mType = NewsFragment.NEWS_TYPE_TOP;
    private int pageIndex = 0;


    public static NewsListFragment newInstance(int mType) {
        Bundle args = new Bundle();
        args.putInt("type", mType);
        NewsListFragment listFragment = new NewsListFragment();
        listFragment.setArguments(args);
        return listFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * 作为展示器 连接view与model
         */
        mNewsPresenter = new NewsPresenterImpl(this);
        mType = getArguments().getInt("type");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newslist, null);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_widget);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.primary,
                R.color.primary_dark, R.color.primary_light,
                R.color.accent);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mNewsAdapter = new NewsAdapter();
        // 创建点击回调方法
        mNewsAdapter.setOnClickListener(this);
        mRecyclerView.setAdapter(mNewsAdapter);
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }
        });
        // 刷新
        onRefresh();
        return view;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void addNews(List<NewsBean> newsList) {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showLoadFailMsg() {

    }

    @Override
    public void onRefresh() {
        pageIndex = 0;
        if(mData != null)
            mData.clear();
        mNewsPresenter.loadNews(mType,pageIndex);
    }

    @Override
    public void onItemClick(View view, int position) {


    }
}
