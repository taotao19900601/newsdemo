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
import com.mt.newsdemo.utils.ToolsUtil;

import java.util.List;

/**
 * Created by meitao on 2016/12/15.
 */

public class ImageAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<ImageBean> mList;
    private int mMaxWidth;
    private int mMaxHeight;

    public ImageAdapter(Context context) {
        this.mContext = context;
        // 获取屏幕的宽度
        mMaxWidth = ToolsUtil.getWidthInPx(mContext) - 20;
        // 获取屏幕的高度 减去状态栏的高度 - 96dp
        mMaxHeight = ToolsUtil.getHeightInPx(mContext) - ToolsUtil.getStatusHeight(mContext) - ToolsUtil.dip2px(mContext, 96);

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
                    //**********   重新计算图片的宽和高   **********////
                    float scale = (float) imgItem.getWidth() / (float) mMaxWidth;
                    int height = (int) (imgItem.getHeight() / scale);
                    if (height > mMaxHeight) {
                        height = mMaxHeight;
                    }
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mMaxWidth, height);
                    ((ImageViewHolder) holder).mImageView.setLayoutParams(params);


                    ImageLoaderUtil.display(mContext, ((ImageViewHolder) holder).mImageView, imgItem.getThumburl());

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
