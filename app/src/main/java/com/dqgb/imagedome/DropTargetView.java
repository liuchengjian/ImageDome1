package com.dqgb.imagedome;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.View;

public class DropTargetView extends AppCompatButton implements View.OnDragListener {
    private boolean mDropped;

    public DropTargetView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        init();
    }

    public DropTargetView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        init();
    }

    public DropTargetView(Context context, AttributeSet attrs, int defaultStyle) {
        super(context, attrs, defaultStyle);
        // TODO Auto-generated constructor stub
        init();
    }

    @SuppressLint("NewApi")
    private void init(){
        setOnDragListener(this);
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        // TODO Auto-generated method stub
        PropertyValuesHolder pvhx, pvhy;
        switch(event.getAction()){
            case DragEvent.ACTION_DRAG_STARTED:
                pvhx = PropertyValuesHolder.ofFloat("scaleX", 0.5f);
                pvhy = PropertyValuesHolder.ofFloat("scaleY", 0.5f);
                ObjectAnimator.ofPropertyValuesHolder(this, pvhx, pvhy).start();
//                setImageDrawable(null);
                mDropped = false;
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                if(!mDropped){
                    pvhx = PropertyValuesHolder.ofFloat("scaleX", 1f);
                    pvhy = PropertyValuesHolder.ofFloat("scaleY", 1f);
                    ObjectAnimator.ofPropertyValuesHolder(this, pvhx, pvhy).start();
                    mDropped = false;
                }
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                pvhx = PropertyValuesHolder.ofFloat("scaleX", 0.75f);
                pvhy = PropertyValuesHolder.ofFloat("scaleY", 0.75f);
                ObjectAnimator.ofPropertyValuesHolder(this, pvhx, pvhy).start();
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                pvhx = PropertyValuesHolder.ofFloat("scaleX", 0.5f);
                pvhy = PropertyValuesHolder.ofFloat("scaleY", 0.5f);
                ObjectAnimator.ofPropertyValuesHolder(this, pvhx, pvhy).start();
                break;
            case DragEvent.ACTION_DROP:
                Keyframe frame0 = Keyframe.ofFloat(0f, 0.75f);
                Keyframe frame1 = Keyframe.ofFloat(0.5f, 0f);
                Keyframe frame2 = Keyframe.ofFloat(1f, 0.75f);
                pvhx = PropertyValuesHolder.ofKeyframe("scaleX", frame0, frame1, frame2);
                pvhy = PropertyValuesHolder.ofKeyframe("scaleY", frame0, frame1, frame2);
                ObjectAnimator.ofPropertyValuesHolder(this, pvhx, pvhy).start();
//                setImageDrawable((Drawable)event.getLocalState());
                mDropped = true;
                break;
            default:
                return false;
        }
        return true;
    }
}
