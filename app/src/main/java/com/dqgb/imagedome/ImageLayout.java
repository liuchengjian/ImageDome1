package com.dqgb.imagedome;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.SystemClock;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.DragEvent;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class ImageLayout extends DragLayout implements View.OnClickListener {

    ArrayList<PointSimple> points;

    DragLayout layouPoints;

    ImageView imgBg;

    Context mContext;
    double widthPm;
    double heightPm;
    private int index= 0;

    public ImageLayout(Context context) {
        this(context, null);
    }

    public ImageLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }


    private void initView(Context context, AttributeSet attrs) {

        mContext = context;

        View imgPointLayout = inflate(context, R.layout.layout_imgview_point, this);

        imgBg = (ImageView) imgPointLayout.findViewById(R.id.imgBg);
        layouPoints = (DragLayout) imgPointLayout.findViewById(R.id.layouPoints);
        //响应拖拉布局
        layouPoints.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                //v 永远是设置该监听的view，这里即fl_blue
                String simpleName = v.getClass().getSimpleName();
                Log.e("111111111111", "view name:" + simpleName);
                //获取事件
                int action = event.getAction();
                switch (action) {
                    case DragEvent.ACTION_DRAG_STARTED:
//                        Log.i(BLUE, "开始拖拽");
//                        findViewById(index).setVisibility(View.INVISIBLE);
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
//                        Log.i(BLUE, "结束拖拽");
//                        findViewById(index).setVisibility(View.VISIBLE);
                        break;
                    case DragEvent.ACTION_DRAG_ENTERED:
//                        Log.i(BLUE, "拖拽的view进入监听的view时");
                        break;

                    case DragEvent.ACTION_DRAG_EXITED:
//                        Log.i(BLUE, "拖拽的view离开监听的view时");
                        break;

                    case DragEvent.ACTION_DRAG_LOCATION:
                        float x = event.getX();
                        float y = event.getY();
                        long l = SystemClock.currentThreadTimeMillis();
//                        Log.i(BLUE, "拖拽的view在BLUE中的位置:x =" + x + ",y=" + y);
                        break;

                    case DragEvent.ACTION_DROP:
//                        Log.i(BLUE, "释放拖拽的view");
                        TextView localState = (TextView) event.getLocalState();
                        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        localState.setWidth(120);
                        localState.setHeight(120);
                        layoutParams.topMargin = (int) event.getY() - localState.getWidth() / 2;
                        layoutParams.leftMargin = (int) event.getX() - localState.getHeight() / 2;
//
                        double a = widthPm / event.getX();
                        double b = heightPm / event.getY();
                        double width_scale = 1 / a;
                        double height_scale = 1 / b;
                        Log.e("2222222", "width_scale" + width_scale + "height_scale" + height_scale);
                        points.get(index - 1).width_scale = width_scale;
                        points.get(index - 1).height_scale = height_scale;
                        ((ViewGroup) localState.getParent()).removeView(localState);
                        layouPoints.addView(localState, layoutParams);
                        break;

                }
                //是否响应拖拽事件，true响应，返回false只能接受到ACTION_DRAG_STARTED事件，后续事件不会收到
                return true;
            }
        });
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
    public void setImgBg(final int width, final int height, String imgUrl) {
        widthPm = (double) width;
        heightPm = (double) height;
        ViewGroup.LayoutParams lp = imgBg.getLayoutParams();
        lp.width = width;//图片的宽度
        lp.height = height;//图片的高度

        imgBg.setLayoutParams(lp);

        ViewGroup.LayoutParams lp1 = layouPoints.getLayoutParams();
        lp1.width = width;//容器的宽度
        lp1.height = height;//容器的高度
        layouPoints.setLayoutParams(lp1);
        Glide.with(mContext).load(imgUrl).asBitmap().into(imgBg);

        layouPoints.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int currentY;//
                int currentX;
                switch (event.getAction()) {
                    // ACTION_DOWN 按下
                    // ACTION_MOVE 在屏幕上移动
                    // ACTION_UP   离开屏幕
                    case MotionEvent.ACTION_DOWN:
                        currentX = (int) event.getX();
                        currentY = (int) event.getY();
                        double a = width / currentX;
                        double b = height / currentY;
                        double width_scale = 1 / a;
                        double height_scale = 1 / b;
//                        Log.e("111111111111", "currentX" + currentX + "width" + width + "height_scale" + width_scale);

                        //添加标记（控件）
                        addPoint(currentX, currentY, width_scale, height_scale);
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
            final TextView bt = view.findViewById(R.id.BtPoint);
            bt.setTag(i);
            bt.setId(i);
            bt.setText(i + 1 + "");
            bt.setWidth(120);
            bt.setHeight(120);
//            AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();
//            animationDrawable.start();
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            layoutParams.leftMargin = (int) (width * width_scale);
            layoutParams.topMargin = (int) (height * height_scale);
            bt.setOnClickListener(this);
            //长按进行拖拉
            bt.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    index = (int) v.getTag();
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                    v.startDrag(null, shadowBuilder, bt, 0);
                    //震动反馈
                    v.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS, HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING);
                    return true;
                }

            });
            layouPoints.addView(view, layoutParams);
        }
    }

    private void addPoint(int currentX, int currentY, double width_scale, double height_scale) {

        LinearLayout view = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.layout_img_point, this, false);
//            ImageView imageView = (ImageView) view.findViewById(R.id.imgPoint);
        final TextView bt = view.findViewById(R.id.BtPoint);
//        points.add(new PointSimple());
        PointSimple newPoint = new PointSimple();
        newPoint.width_scale = width_scale;
        newPoint.height_scale = height_scale;
        points.add(newPoint);
        bt.setWidth(120);
        bt.setHeight(120);
        bt.setTag(points.size());
        bt.setText(points.size() + "");
//            AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();
//            animationDrawable.start();

        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
//        Log.e("111111111111", "getWidth" + bt.getWidth() + "getHeight" + bt.getHeight());

        layoutParams.leftMargin = (int) (currentX - 60);
        layoutParams.topMargin = (int) (currentY - 60);

        bt.setOnClickListener(this);
        //长按进行拖拉
        bt.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                index = (int) v.getTag();
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                v.startDrag(null, shadowBuilder, bt, 0);
                //震动反馈
                v.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS, HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING);
                return true;
            }

        });
        layouPoints.addView(view, layoutParams);
    }


    @Override
    public void onClick(View view) {
        int pos = (int) view.getTag();
//        PointSimple clixkPoint = points.get(pos - 1);
//        Toast.makeText(getContext(), "width_scale:" + clixkPoint.width_scale + "\n" + "height_scale:" + clixkPoint.height_scale, Toast.LENGTH_SHORT).show();
    }


}

