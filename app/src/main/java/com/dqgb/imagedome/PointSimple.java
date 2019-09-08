package com.dqgb.imagedome;

import java.io.Serializable;

/**
 * Created by lining on 2016/7/14.
 */
public class PointSimple implements Serializable    {

    // 标记点相对于横向的宽度的比例
    public double width_scale;
    // 标记点相对于横向的高度的比例
    public double height_scale;

    public double getWidth_scale() {
        return width_scale;
    }

    public void setWidth_scale(double width_scale) {
        this.width_scale = width_scale;
    }

    public double getHeight_scale() {
        return height_scale;
    }

    public void setHeight_scale(double height_scale) {
        this.height_scale = height_scale;
    }
}
