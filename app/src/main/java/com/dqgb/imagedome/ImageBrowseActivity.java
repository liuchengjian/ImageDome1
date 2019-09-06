package com.dqgb.imagedome;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;


import java.util.ArrayList;
import java.util.List;

public class ImageBrowseActivity extends AppCompatActivity {

    private ViewPager viewPagerImgs;

    private List<ImgSimple> imgSimples;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_image_browse);


        viewPagerImgs = (ViewPager) this.findViewById(R.id.viewPagerImgs);
        viewPagerImgs.setOffscreenPageLimit(2);

        initData();

        PagerAdapter adapter = new ImgBrowsePagerAdapter(this, imgSimples);
        viewPagerImgs.setAdapter(adapter);

    }

    private void initData() {

        imgSimples = new ArrayList<>();

        ImgSimple imgSimple1 = new ImgSimple();
        imgSimple1.url = "http://hiphotos.qianqian.com/ting/pic/item/b3119313b07eca80b93485cf932397dda14483e1.jpg";
        imgSimple1.scale = 1.6f;

        ArrayList<PointSimple> pointSimples = new ArrayList<>();
        PointSimple pointSimple1 = new PointSimple();
        pointSimple1.width_scale = 0.36f;
        pointSimple1.height_scale = 0.75f;

        PointSimple pointSimple2 = new PointSimple();
        pointSimple2.width_scale = 0.64f;
        pointSimple2.height_scale = 0.5f;


        PointSimple pointSimple3 = new PointSimple();
        pointSimple3.width_scale = 0.276f;
        pointSimple3.height_scale = 0.764f;

        PointSimple pointSimple4 = new PointSimple();
        pointSimple4.width_scale = 0.638f;
        pointSimple4.height_scale = 0.74f;

        PointSimple pointSimple5 = new PointSimple();
        pointSimple5.width_scale = 0.796f;
        pointSimple5.height_scale = 0.526f;

        PointSimple pointSimple6 = new PointSimple();
        pointSimple6.width_scale = 0.486f;
        pointSimple6.height_scale = 0.364f;

        pointSimples.add(pointSimple1);
        pointSimples.add(pointSimple2);
        pointSimples.add(pointSimple3);
        pointSimples.add(pointSimple4);
        pointSimples.add(pointSimple5);
        pointSimples.add(pointSimple6);

        imgSimple1.pointSimples = pointSimples;


        ImgSimple imgSimple2 = new ImgSimple();
        imgSimple2.url = "http://hiphotos.qianqian.com/ting/pic/item/728da9773912b31b106b9e4e8518367adab4e156.jpg";
        imgSimple2.scale = 1.6f;

        ArrayList<PointSimple> pointSimples2 = new ArrayList<>();
        PointSimple pointSimple7 = new PointSimple();
        pointSimple7.width_scale = 0.36f;
        pointSimple7.height_scale = 0.75f;

        PointSimple pointSimple8 = new PointSimple();
        pointSimple8.width_scale = 0.64f;
        pointSimple8.height_scale = 0.5f;


        PointSimple pointSimple9 = new PointSimple();
        pointSimple9.width_scale = 0.276f;
        pointSimple9.height_scale = 0.764f;


        pointSimples2.add(pointSimple7);
        pointSimples2.add(pointSimple8);
        pointSimples2.add(pointSimple9);

        imgSimple2.pointSimples = pointSimples2;


        ImgSimple imgSimple3 = new ImgSimple();
        imgSimple3.url = "http://hiphotos.qianqian.com/ting/pic/item/eac4b74543a9822659d378408982b9014a90eb43.jpg";
        imgSimple3.scale = 0.75f;

        ArrayList<PointSimple> pointSimples3 = new ArrayList<>();
        PointSimple pointSimple11 = new PointSimple();
        pointSimple11.width_scale = 0.1f;
        pointSimple11.height_scale = 0.3f;

        PointSimple pointSimple12 = new PointSimple();
        pointSimple12.width_scale = 0.3f;
        pointSimple12.height_scale = 0.5f;


        PointSimple pointSimple13 = new PointSimple();
        pointSimple13.width_scale = 0.5f;
        pointSimple13.height_scale = 0.8f;


        pointSimples3.add(pointSimple11);
        pointSimples3.add(pointSimple12);
        pointSimples3.add(pointSimple13);

        imgSimple3.pointSimples = pointSimples3;

        imgSimples.add(imgSimple1);
        imgSimples.add(imgSimple2);
        imgSimples.add(imgSimple3);
    }
}
