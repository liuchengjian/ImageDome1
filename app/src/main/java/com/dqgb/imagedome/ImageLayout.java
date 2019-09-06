package com.dqgb.imagedome;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ImageLayout extends FrameLayout implements View.OnClickListener {

    ArrayList<PointSimple> points;

    FrameLayout layouPoints;

    ImageView imgBg;

    Context mContext;

    public ImageLayout(Context context) {
        this(context, null);
    }

    public ImageLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImageLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context, attrs);
    }


    private void initView(Context context, AttributeSet attrs) {

        mContext = context;

        View imgPointLayout = inflate(context, R.layout.layout_imgview_point, this);

        imgBg = (ImageView) imgPointLayout.findViewById(R.id.imgBg);
        layouPoints = (FrameLayout) imgPointLayout.findViewById(R.id.layouPoints);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @SuppressLint("ClickableViewAccessibility")
    public void setImgBg(int width, int height, String imgUrl) {

        ViewGroup.LayoutParams lp = imgBg.getLayoutParams();
        lp.width = width;
        lp.height = height;

        imgBg.setLayoutParams(lp);

        ViewGroup.LayoutParams lp1 = layouPoints.getLayoutParams();
        lp1.width = width;
        lp1.height = height;

        layouPoints.setLayoutParams(lp1);

        Glide.with(mContext).load(imgUrl).asBitmap().into(imgBg);


        layouPoints.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int currentY;
                int currentX;
                switch (event.getAction()) {
                    // ACTION_DOWN 按下
                    // ACTION_MOVE 在屏幕上移动
                    // ACTION_UP   离开屏幕
                    case MotionEvent.ACTION_DOWN:
                        currentX = (int) event.getX();
                        currentY = (int) event.getY();

                        //添加标记（控件）
                        addPoint(currentX, currentY);
                        //添加标记（删除）
                        //mRelativeLayout.removeView(mDrawView);
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }

                return true;
            }
        });


        addPoints(width, height);

    }

    public void setPoints(ArrayList<PointSimple> points) {

        this.points = points;
    }

    private void addPoints(int width, int height) {

        layouPoints.removeAllViews();

        for (int i = 0; i < points.size(); i++) {
            double width_scale = points.get(i).width_scale;
            double height_scale = points.get(i).height_scale;


            LinearLayout view = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.layout_img_point, this, false);
//            ImageView imageView = (ImageView) view.findViewById(R.id.imgPoint);
            Button bt = view.findViewById(R.id.BtPoint);
            bt.setTag(i);
            bt.setText(i + 1 + "");

//            AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();
//            animationDrawable.start();

            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();

            layoutParams.leftMargin = (int) (width * width_scale);
            layoutParams.topMargin = (int) (height * height_scale);

            bt.setOnClickListener(this);

            layouPoints.addView(view, layoutParams);
        }
    }

    private void addPoint(int width, int height) {

        double width_scale = width;
        double height_scale = height;


        LinearLayout view = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.layout_img_point, this, false);
//            ImageView imageView = (ImageView) view.findViewById(R.id.imgPoint);
        Button bt = view.findViewById(R.id.BtPoint);
//        points.add(new PointSimple());
        bt.setTag(points.size() + 1);
        bt.setText(points.size() + 1 + "");
//            AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();
//            animationDrawable.start();

        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();

        layoutParams.leftMargin = (int) (width - bt.getWidth() / 2);
        layoutParams.topMargin = (int) (height - bt.getHeight() / 2);

        bt.setOnClickListener(this);

        layouPoints.addView(view, layoutParams);
    }


    @Override
    public void onClick(View view) {
        int pos = (int) view.getTag();
        Toast.makeText(getContext(), "pos : " + pos, Toast.LENGTH_SHORT).show();
    }
}
