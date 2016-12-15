package com.mt.newsdemo.images.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mt.newsdemo.R;
import com.mt.newsdemo.beans.ImageBean;
import com.mt.newsdemo.images.ImageAdapter;
import com.mt.newsdemo.images.presenter.ImagePresenter;
import com.mt.newsdemo.images.presenter.ImagePresenterImpl;
import com.mt.newsdemo.images.view.ImageView;

import java.util.List;

/**
 * Created by meitao on 2016/12/15.
 */

public class ImageFragment extends Fragment implements ImageView, SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "ImageFragment";
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private List<ImageBean> mList;
    private ImageAdapter mImageAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private ImagePresenter mImagePresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mImagePresenter = new ImagePresenterImpl(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_image, container, false);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_widget);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mImageAdapter = new ImageAdapter(getContext());
        mRecyclerView.setAdapter(mImageAdapter);
        onRefresh();
        return view;
    }

    @Override
    public void addImages(List<ImageBean> list) {
        if (list != null) {
            // 更新数据
            this.mList = list;
        }
        mImageAdapter.setDatas(mList);
    }

    @Override
    public void showProgress() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showLoadFailMsg() {

    }

    @Override
    public void onRefresh() {
        if (mList != null) {
            mList.clear();
        }
        // 请求网络获取最新数据
        mImagePresenter.loadImageList();
    }
}
