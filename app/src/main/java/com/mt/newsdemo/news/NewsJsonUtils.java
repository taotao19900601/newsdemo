package com.mt.newsdemo.news;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mt.newsdemo.beans.NewsBean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/11.
 */

public class NewsJsonUtils {

    public static List<NewsBean> readJsonNewsBeans(String res, String value){
        JsonParser parser = new JsonParser();
        JsonObject jsonObj=parser.parse(res).getAsJsonObject();
        JsonElement jsonElement = jsonObj.get(value);
        if(jsonElement==null)
            return null;
        JsonArray jsonArray = jsonElement.getAsJsonArray();

        return null;
    }

}
