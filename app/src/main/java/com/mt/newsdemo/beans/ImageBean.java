package com.mt.newsdemo.beans;

import java.io.Serializable;

/**
 * Created by meitao on 2016/12/15.
 */

public class ImageBean implements Serializable{
    private static final long serialVersionUID = 1L;
    private String title;
    private String thumburl;
    private String sourceurl;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getSourceurl() {
        return sourceurl;
    }

    public void setSourceurl(String sourceurl) {
        this.sourceurl = sourceurl;
    }

    public String getThumburl() {
        return thumburl;
    }

    public void setThumburl(String thumburl) {
        this.thumburl = thumburl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private int width;
    private int height;
}
