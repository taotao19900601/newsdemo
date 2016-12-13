package com.mt.newsdemo.news;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mt.newsdemo.beans.NewsBean;
import com.mt.newsdemo.utils.JsonUtil;
import com.mt.newsdemo.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/11.
 */

public class NewsJsonUtils {

    public static List<NewsBean> readJsonNewsBeans(String res, String value) {
        List<NewsBean> beans = new ArrayList<>();
        JsonParser parser = new JsonParser();
        JsonObject jsonObj = parser.parse(res).getAsJsonObject();
        JsonElement jsonElement = jsonObj.get(value);
        if (jsonElement == null)
            return null;
        JsonArray jsonArray = jsonElement.getAsJsonArray();
        for (int i = 1; i < jsonArray.size(); i++) {
            JsonObject jo = jsonArray.get(i).getAsJsonObject();
            if (jo.has("skipType") && "special".equals(jo.get("skipType").getAsString())) {
                continue;
            }
            if (jo.has("TAGS") && !jo.has("TAG")) {
                continue;
            }

            if (!jo.has("imgextra")) {
                NewsBean news = JsonUtil.deserialize(jo, NewsBean.class);
                beans.add(news);
            }
        }

        return beans;
    }

}
