package com.mt.newsdemo.news;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mt.newsdemo.R;
import com.mt.newsdemo.beans.NewsBean;
import com.mt.newsdemo.utils.ImageLoaderUtil;

import java.net.URI;
import java.util.List;

/**
 * Created by Administrator on 2016/12/11.
 */

public class NewsAdapter extends RecyclerView.Adapter {
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;

    private List<NewsBean> mData;
    private boolean mShowFooter = true;
    private Context mContext;

    private OnItemClickListener mOnItemClickListener;

    public NewsAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<NewsBean> data) {
        this.mData = data;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {

        if (!mShowFooter) {
            return TYPE_ITEM;
        }
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    // 创建 view
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_news, parent, false);
            ItemViewHolder viewHolder = new ItemViewHolder(view);
            return viewHolder;

        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.footer, parent, false);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            return new FooterViewHolder(view);
        }

    }

    // 给view 绑定数据
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            if (mData != null && mData.size() > 0) {
                NewsBean bean = mData.get(position);
                ((ItemViewHolder) holder).mTitle.setText(bean.getTitle());
                ((ItemViewHolder) holder).mDesc.setText(bean.getDigest());
                Uri uri = Uri.parse(bean.getImgsrc());
                ((ItemViewHolder) holder).mNewImg.setImageURI(uri);
                ImageLoaderUtil.display(mContext, ((ItemViewHolder) holder).mNewImg, bean.getImgsrc());
            }
        }
    }

    @Override
    public int getItemCount() {
        int begin = mShowFooter ? 1 : 0;
        if (mData == null) {
            return begin;
        }
        return mData.size() + begin;
    }

    public NewsBean getItem(int position) {
        return mData == null ? null : mData.get(position);
    }

    public void isShowFooter(boolean isShowFooter) {
        this.mShowFooter = isShowFooter;
    }

    public boolean isShowFooter() {
        return this.mShowFooter;
    }

    public void setOnClickListener(OnItemClickListener onClickListener) {
        this.mOnItemClickListener = onClickListener;
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {
        public FooterViewHolder(View v) {
            super(v);
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mTitle;
        public TextView mDesc;
        public ImageView mNewImg;

        public ItemViewHolder(View v) {
            super(v);
            mTitle = (TextView) v.findViewById(R.id.tvTitle);
            mDesc = (TextView) v.findViewById(R.id.tvDesc);
            mNewImg = (ImageView) v.findViewById(R.id.ivNews);
            v.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            if (mOnItemClickListener != null)
                mOnItemClickListener.onItemClick(view, this.getPosition());
        }
    }
}
