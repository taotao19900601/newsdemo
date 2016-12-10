package com.mt.newsdemo.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mt.newsdemo.R;

/**
 * Created by meitao on 2016/12/10.
 */

public class ImageLoaderUtil {

    public static void display(Context context, ImageView imageView, String url,int placeholder, int error) {
        if(imageView == null)
            throw new IllegalArgumentException("ImageView不能为空");
        Glide.with(context).load(url).placeholder(placeholder).error(error).crossFade().into(imageView);
    }

    public static void display(Context context, ImageView imageView, String url) {
        if(imageView == null)
            throw new IllegalArgumentException("ImageView不能为空");
        Glide.with(context).load(url).placeholder(R.mipmap.ic_image_loading).error(R.mipmap.ic_image_loadfail).crossFade().into(imageView);
    }

}
