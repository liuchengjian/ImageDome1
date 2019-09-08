package com.dqgb.imagedome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;

import org.simple.eventbus.Subscriber;

public class MainActivity extends AppCompatActivity {
    private DrawView mDrawView;
    private VDHLinearLayout mView;
    private int mCols = 10, mRows = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bt = findViewById(R.id.btBage);
        Button bt1 = findViewById(R.id.dragView);
        mView = findViewById(R.id.mView);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ImageBrowseActivity.class));
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
            }
        });
//        bt.setOnTouchListener(new OnDragTouchListener());
        findViewById(R.id.edgeDragView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PhotoActivity.class));

            }
        });

    }

    @Subscriber
    public void updateUser(PointType type) {
        if (type.type == 1) {
            Toast.makeText(this, "你好", Toast.LENGTH_LONG).show();
        }
    }


}
