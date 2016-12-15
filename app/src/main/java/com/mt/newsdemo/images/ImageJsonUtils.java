package com.mt.newsdemo.images;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mt.newsdemo.beans.ImageBean;
import com.mt.newsdemo.utils.JsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by meitao on 2016/12/15.
 */

public class ImageJsonUtils {

    /**
     * 通过json字符串解析为List<ImageBean> 实体对象
     * @param res
     * @return List<ImageBean>
     */
    public static List<ImageBean> readJsonImageBeans(String res) {
        List<ImageBean> parserDatas = new ArrayList<>();
        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse(res);
        JsonArray jsonArrayElements = jsonElement.getAsJsonArray();
        for (int i = 0; i < jsonArrayElements.size(); i++) {
            JsonElement element = jsonArrayElements.get(i);
            JsonObject jsonObject = element.getAsJsonObject();
            ImageBean bean = JsonUtil.deserialize(jsonObject, ImageBean.class);
            parserDatas.add(bean);
        }


        return parserDatas;
    }

}
