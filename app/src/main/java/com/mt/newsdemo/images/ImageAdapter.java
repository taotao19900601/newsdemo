package com.mt.newsdemo.images;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mt.newsdemo.R;
import com.mt.newsdemo.beans.ImageBean;
import com.mt.newsdemo.utils.ImageLoaderUtil;

import java.util.List;

/**
 * Created by meitao on 2016/12/15.
 */

public class ImageAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<ImageBean> mList;

    public ImageAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.item_image, parent, false);
        ImageViewHolder imgViewHolder = new ImageViewHolder(contentView);
        return imgViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ImageViewHolder) {
            if (mList != null) {
                ImageBean imgItem = mList.get(position);
                if (imgItem != null) {
                    ((ImageViewHolder) holder).mTextView.setText(imgItem.getTitle());
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(imgItem.getWidth(),imgItem.getHeight());
                    ((ImageViewHolder) holder).mImageView.setLayoutParams(params);
                    ImageLoaderUtil.display(mContext,((ImageViewHolder) holder).mImageView,imgItem.getThumburl());

                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public void setDatas(List<ImageBean> list) {
        this.mList = list;
        this.notifyDataSetChanged();
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mTextView;

        public ImageViewHolder(View view) {
            super(view);
            if (view != null) {
                mImageView = (ImageView) view.findViewById(R.id.img_iv);
                mTextView = (TextView) view.findViewById(R.id.img_tv);
            }
        }
    }
}
