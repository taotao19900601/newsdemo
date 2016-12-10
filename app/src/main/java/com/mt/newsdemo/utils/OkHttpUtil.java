package com.mt.newsdemo.utils;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.internal.$Gson$Types;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.Proxy;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by meitao on 2016/12/9.
 */

public class OkHttpUtil {
    public static final String TAG = "OkHttpUtil";
    private static OkHttpUtil mInstance;
    private OkHttpClient mOkHttpClient;
    private Handler mDelivery;

    /**
     * 私有构造方法
     */
    private OkHttpUtil() {
        mOkHttpClient = new OkHttpClient();
        mOkHttpClient.setConnectTimeout(10, TimeUnit.SECONDS);
        mOkHttpClient.setWriteTimeout(10, TimeUnit.SECONDS);
        mOkHttpClient.setReadTimeout(30, TimeUnit.SECONDS);
        mOkHttpClient.setCookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ORIGINAL_SERVER));
        mDelivery = new Handler(Looper.myLooper());

    }
    // 获取单例
    private synchronized static OkHttpUtil getmInstance() {
        if (mInstance == null) {
            mInstance = new OkHttpUtil();
        }
        return mInstance;
    }

    private void getRequest(String url, ResultCallBack callBack) {
        Request request = new Request.Builder().url(url).build();
        deliveryResult(callBack, request);
    }

    private void postRequest(String url, ResultCallBack callBack, List<Param> paramList) {
        Request request = buildPostRequest(url, paramList);
        deliveryResult(callBack, request);
    }
    // 请求结果分发
    private void deliveryResult(final ResultCallBack callback, Request request) {
        if (mOkHttpClient != null) {
            mOkHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    sendFailCallBack(callback, e);
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    try {
                        if(response == null)
                            return;
                        String str = response.body().toString();
                        if (callback.mType == String.class) {
                            sendSuccessCallBack(callback, str);
                        } else {
                            Object object = JsonUtil.deserialize(str, callback.mType);
                            sendSuccessCallBack(callback, object);
                        }
                    } catch (Exception e) {
                        LogUtil.e(TAG, "json解析错误");
                        sendFailCallBack(callback, e);
                    }

                }
            });
        }
    }
    // 返回请求成功的回掉方法（回掉方法在UI线程中运行）
    private void sendSuccessCallBack(final ResultCallBack callback, final Object obj) {
        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onSuccess(obj);
                }
            }
        });
    }
    // 请求失败的回掉方法
    private void sendFailCallBack(final ResultCallBack callBack, final Exception exception) {
        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    callBack.onFailure(exception);
                }
            }
        });
    }
    // 创建post请求方式的request对象
    private Request buildPostRequest(String url, List<Param> paramList) {
        FormEncodingBuilder encodingBuilder = new FormEncodingBuilder();
        if (paramList != null && paramList.size() > 0) {
            for (Param param : paramList) {
                if (param != null) {
                    encodingBuilder.add(param.getKey(), param.getValue());
                }

            }


        }
        RequestBody body = encodingBuilder.build();
        Request postRequest = new Request.Builder().url(url).post(body).build();
        return postRequest;

    }

    /**
     * get 请求
     * @param url
     * @param callBack
     */
    public static void get(String url, ResultCallBack callBack) {
        getmInstance().getRequest(url, callBack);
    }

    /**
     *  post 请求
     * @param url
     * @param callBack
     * @param paramList
     */
    public static void post(String url, ResultCallBack callBack, List<Param> paramList) {
        getmInstance().postRequest(url, callBack, paramList);
    }

    /**
     * 网络请求回掉
     *
     * @param <T>
     */
    public static abstract class ResultCallBack<T> {

        Type mType;

        public ResultCallBack() {
            mType = getSuperclassTypeParameter(getClass());
        }

        static Type getSuperclassTypeParameter(Class<?> subclass) {
            Type superclass = subclass.getGenericSuperclass();
            if (superclass instanceof Class) {
                throw new RuntimeException("Missing type parameter.");
            }
            ParameterizedType parameterized = (ParameterizedType) superclass;
            return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
        }

        /**
         * 请求成功回调
         *
         * @param response
         */
        public abstract void onSuccess(T response);

        /**
         * 请求失败回调
         *
         * @param e
         */
        public abstract void onFailure(Exception e);
    }

    // Post请求参数类
    public static class Param {
        private String key;
        private String value;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }


        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }


    }
}
