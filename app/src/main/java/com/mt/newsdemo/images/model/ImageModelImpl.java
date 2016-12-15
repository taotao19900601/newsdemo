package com.mt.newsdemo.images.model;

import android.text.TextUtils;

import com.mt.newsdemo.beans.ImageBean;
import com.mt.newsdemo.commons.Urls;
import com.mt.newsdemo.images.ImageJsonUtils;
import com.mt.newsdemo.utils.OkHttpUtil;

import java.util.List;

/**
 * Created by meitao on 2016/12/15.
 */

public class ImageModelImpl implements ImageModel {

    @Override
    public void loadImageList(final ImageModelImpl.OnLoadImageList listener) {
        String imgUrl = Urls.IMAGES_URL;
        OkHttpUtil.ResultCallBack<String> callBack = new OkHttpUtil.ResultCallBack<String>() {
            @Override
            public void onSuccess(String response) {
                if(!TextUtils.isEmpty(response)){
                    List<ImageBean> resultList = ImageJsonUtils.readJsonImageBeans(response);
                    listener.onSuccess(resultList);
                }
            }

            @Override
            public void onFailure(Exception e) {
                listener.onFailure("",e);
            }
        };
        OkHttpUtil.get(imgUrl,callBack);
    }

    public interface OnLoadImageList {
        void onSuccess(List<ImageBean> list);

        void onFailure(String msg, Exception e);
    }
}
