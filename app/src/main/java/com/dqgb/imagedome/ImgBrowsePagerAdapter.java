package com.dqgb.imagedome;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

public class ImgBrowsePagerAdapter extends PagerAdapter {

    List<ImgSimple> imgSimples;

    List<View> views;

    Activity mContext;
    private int type;

    private double width;
    private double heightPm;
    private float scalePm;
    private ArrayList<PointSimple> PointList = new ArrayList<>();

    public ImgBrowsePagerAdapter(Activity context, List<ImgSimple> imgSimples, int type) {

        this.mContext = context;
        this.imgSimples = imgSimples;
        this.type = type;
        this.views = new ArrayList<>();
        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        //获取屏幕宽度,高度
        width = dm.widthPixels;
        heightPm = dm.heightPixels;
        scalePm = (float) DivideUtils.divide(heightPm, width);

//        Log.e("111111111111", "width:" + width + "height:" + heightPm);
    }

    @Override
    public int getCount() { // 获得size
        return imgSimples.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LinearLayout view = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.layout_img_browse, null);
        ImageLayout layoutContent = (ImageLayout) view.findViewById(R.id.layoutContent);
        try {
            String imgUrl = imgSimples.get(position).url;
            float scale = scalePm;
            ArrayList<PointSimple> pointSimples = imgSimples.get(position).pointSimples;
            layoutContent.setPoints(pointSimples);
            double height = heightPm;
//            Log.e("111111111111", "width:" + width + "height:" + height);
            layoutContent.setImgBg(width, height, imgUrl,type);
            PointList = layoutContent.points;
        } catch (Exception e) {
            e.printStackTrace();
        }
        ((ViewPager) container).addView(view);
        return view;
    }

    public ArrayList<PointSimple> getPointList() {
        return PointList;
    }
}