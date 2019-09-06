package com.dqgb.imagedome;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author by lchj,
 * Email 627107345 @qq.com, Date on 2019/9/5.
 */
public class DrawView extends View {
    //定义并创建画笔
    Paint p = new Paint();
    public int flag;
    public float currentX, currentY;

    public DrawView(Context context) {
        super(context);
    }

    public DrawView(Context context, AttributeSet set) {
        super(context, set);
    }


    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (flag == 1) {
            p.setColor(Color.RED);
        } else if (flag == 2) {
            p.setColor(Color.GREEN);
        } else if (flag == 3) {
            p.setColor(Color.BLUE);
        } else if (flag == 4) {
            p.setColor(Color.YELLOW);
        }
        canvas.drawCircle(currentX, currentY, 6, p);
    }
}
