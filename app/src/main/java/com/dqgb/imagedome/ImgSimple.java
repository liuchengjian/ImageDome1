package com.dqgb.imagedome;

import java.io.Serializable;
import java.util.ArrayList;

public class ImgSimple implements Serializable {

    public String url;//图片地址
    public float scale;//像素比//192
    public ArrayList<PointSimple> pointSimples;//图片里的标记点
    public ImgSimple() {
    }
    public ImgSimple(String url) {
        this.url = url;
    }
    public ImgSimple(String url, float scale) {
        this.url = url;
        this.scale = scale;
    }
    public ImgSimple(String url, float scale, ArrayList<PointSimple> pointSimples) {
        this.url = url;
        this.scale = scale;
        this.pointSimples = pointSimples;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public ArrayList<PointSimple> getPointSimples() {
        return pointSimples;
    }

    public void setPointSimples(ArrayList<PointSimple> pointSimples) {
        this.pointSimples = pointSimples;
    }
}
