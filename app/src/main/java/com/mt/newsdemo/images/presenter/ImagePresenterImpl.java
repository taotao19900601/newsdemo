package com.mt.newsdemo.images.presenter;

import android.content.Context;
import android.widget.Toast;

import com.mt.newsdemo.beans.ImageBean;
import com.mt.newsdemo.images.model.ImageModel;
import com.mt.newsdemo.images.model.ImageModelImpl;
import com.mt.newsdemo.images.view.ImageView;

import java.util.List;

/**
 * Created by meitao on 2016/12/15.
 */

public class ImagePresenterImpl implements ImagePresenter, ImageModelImpl.OnLoadImageList {
    private ImageModel mImageModel;
    private ImageView mImageView;
    private Context mContext;

    public ImagePresenterImpl(ImageView imageView,Context context) {
        this.mImageView = imageView;
        this.mContext = context;
        mImageModel = new ImageModelImpl();

    }

    @Override
    public void loadImageList() {
        mImageView.showProgress();
        mImageModel.loadImageList(this);

    }

    @Override
    public void onSuccess(List<ImageBean> list) {
        mImageView.hideProgress();
        mImageView.addImages(list);
    }

    @Override
    public void onFailure(String msg, Exception e) {
        mImageView.hideProgress();
        Toast.makeText(mContext,msg,Toast.LENGTH_SHORT).show();
    }
}
