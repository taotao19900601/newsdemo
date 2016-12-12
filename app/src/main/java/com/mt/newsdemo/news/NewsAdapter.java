package com.mt.newsdemo.news;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/12/11.
 */

public class NewsAdapter extends RecyclerView.Adapter{
    private OnItemClickListener mOnItemClickListener;
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public void setOnClickListener(OnItemClickListener onClickListener){
        this.mOnItemClickListener = onClickListener;
    }
    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }
}
