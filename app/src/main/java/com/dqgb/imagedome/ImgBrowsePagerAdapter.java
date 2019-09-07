package com.dqgb.imagedome;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class ImgBrowsePagerAdapter extends PagerAdapter {

    List<ImgSimple> imgSimples;

    List<View> views;

    Activity mContext;

    private int width;
    private int heightPm;

    public ImgBrowsePagerAdapter(Activity context, List<ImgSimple> imgSimples) {

        this.mContext = context;
        this.imgSimples = imgSimples;
        this.views = new ArrayList<>();
        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        //获取屏幕宽度,高度
        width = dm.widthPixels;
        heightPm = dm.heightPixels;
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

        ((ViewPager) container).removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        LinearLayout view = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.layout_img_browse, null);
        ImageLayout layoutContent = (ImageLayout) view.findViewById(R.id.layoutContent);

        try {

            String imgUrl = imgSimples.get(position).url;
            float scale = imgSimples.get(position).scale;
            ArrayList<PointSimple> pointSimples = imgSimples.get(position).pointSimples;

            layoutContent.setPoints(pointSimples);

            int height = (int) (width * scale);
//            Log.e("111111111111", "width:" + width + "height:" + height);

            layoutContent.setImgBg(width, height, imgUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ((ViewPager) container).addView(view);

        return view;
    }
}