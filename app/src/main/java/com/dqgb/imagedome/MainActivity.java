package com.dqgb.imagedome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    private DrawView mDrawView;
    private RelativeLayout mView;
    private int mCols = 10,mRows = 10;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bt = findViewById(R.id.btBage);
        mView = findViewById(R.id.mView);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ImageBrowseActivity.class));
            }
        });
        mView.setOnTouchListener(new touch());
    }

    public void btnClick(View view) {
        startActivity(new Intent(this, ImageBrowseActivity.class));
    }

    class touch implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {

            int x = (int) event.getX() - (mView.getWidth() - mCols) / 2; // getX是获取相对当前控件的坐标
            int y = (int) event.getY() - (mView.getHeight() - mRows) / 2; // getRawX是获取相对屏幕左上角的坐标
            switch (event.getAction()) {
                // ACTION_DOWN 按下
                // ACTION_MOVE 在屏幕上移动
                // ACTION_UP   离开屏幕


                case MotionEvent.ACTION_DOWN:
                    mDrawView = new DrawView(MainActivity.this);
                    mDrawView.currentY = (int) event.getY();
                    mDrawView.currentX = (int) event.getX();
                    mDrawView.flag = 1;
                    //添加标记（控件）
                    mView.addView(mDrawView);
                    //添加标记（删除）
                    //mRelativeLayout.removeView(mDrawView);
                case MotionEvent.ACTION_MOVE:


                    break;
                case MotionEvent.ACTION_UP:

                    break;
            }

            return true;
        }
    }

}
