package com.dqgb.imagedome;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class DragTouchListener implements View.OnTouchListener {


    /**
     * drag directions
     */
    public static final int DIRECTION_UP = 0;
    public static final int DIRECTION_DOWN = 1;
    public static final int DIRECTION_LEFT = 2;
    public static final int DIRECTION_RIGHT = 3;


    private int mDragDirection = -1;
    private int mDragDistance = -1;
    private ViewGroup.MarginLayoutParams mParams;
    private ViewGroup.MarginLayoutParams mOriginParams;


    private int viewOriginMargin = -1000;

    private float mStartY = 0;
    private float mStartX = 0;
    private boolean isTouched = false;

    public DragTouchListener(int dragDirection, int dragDistance) {
        mDragDirection = dragDirection;
        mDragDistance = dragDistance;
    }

    protected void onClick(View view) {

    }

    protected void onDragComplete(View view) {

    }

    protected void onDragRebound(View view) {

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {


        if (viewOriginMargin == -1000) {
            mParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            if (mDragDirection == DIRECTION_UP) {
                viewOriginMargin = mParams.bottomMargin;
            } else if (mDragDirection == DIRECTION_DOWN) {
                viewOriginMargin = mParams.topMargin;
            } else if (mDragDirection == DIRECTION_LEFT) {
                viewOriginMargin = mParams.rightMargin;
            } else if (mDragDirection == DIRECTION_RIGHT) {
                viewOriginMargin = mParams.leftMargin;
            }
        }


        int action = motionEvent.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                isTouched = true;
                mStartY = motionEvent.getY();
                mStartX = motionEvent.getX();
                return true;
            case MotionEvent.ACTION_MOVE:
                float y = motionEvent.getY();
                float x = motionEvent.getX();

                if (mDragDirection == DIRECTION_UP) {
                    if (y < mStartY) {
                        mParams.bottomMargin = viewOriginMargin + (int) (mStartY - y);
                    }
                } else if (mDragDirection == DIRECTION_DOWN) {
                    if (y > mStartY) {
                        mParams.topMargin = viewOriginMargin + (int) (y - mStartY);
                    }
                } else if (mDragDirection == DIRECTION_LEFT) {
                    if (x < mStartX) {
                        mParams.rightMargin = viewOriginMargin + (int) (mStartX - x);
                    }
                } else if (mDragDirection == DIRECTION_RIGHT) {
                    if (x > mStartX) {
                        mParams.leftMargin = viewOriginMargin + (int) (x - mStartX);
                    }
                }

                view.setLayoutParams(mParams);
                break;
            case MotionEvent.ACTION_UP:
                float nowY = motionEvent.getY();
                float nowX = motionEvent.getX();

                int deltaX = (int) nowX - (int) mStartX;
                int deltaY = (int) nowY - (int) mStartY;

                if (isTouched && Math.abs(deltaX) < 5 && Math.abs(deltaY) < 5) {
                    onClick(view);
                    break;
                }

                if (mDragDirection == DIRECTION_UP) {
                    if (isTouched && mStartY - nowY > mDragDistance) {

// Log.i("test-drag", "direction up , startY = " + mStartY + ", nowY = " + nowY +
// ", startY - nowY = " + (mStartY - nowY) + ", dragDistance : " + mDragDistance);
                        onDragComplete(view);

                    } else if (mStartY - nowY > 0 && mStartY - nowY < mDragDistance) {
                        mParams.bottomMargin = viewOriginMargin;
                        view.setLayoutParams(mParams);
                        onDragRebound(view);
                    }
                } else if (mDragDirection == DIRECTION_DOWN) {

                    if (isTouched && nowY - mStartY > mDragDistance) {
                        onDragComplete(view);
                    } else if (nowY - mStartY > 0 && nowY - mStartY < mDragDistance) {
                        mParams.topMargin = viewOriginMargin;
                        view.setLayoutParams(mParams);
                        onDragRebound(view);
                    }
                } else if (mDragDirection == DIRECTION_LEFT) {
                    if (isTouched && mStartX - nowX > mDragDistance) {
                        onDragComplete(view);
                    } else if (mStartX - nowX > 0 && mStartX - nowX < mDragDistance) {
                        mParams.rightMargin = viewOriginMargin;
                        view.setLayoutParams(mParams);
                        onDragRebound(view);
                    }
                } else if (mDragDirection == DIRECTION_RIGHT) {
                    if (isTouched && nowX - mStartX > mDragDistance) {
                        onDragComplete(view);
                    } else if (nowX - mStartX > 0 && nowX - mStartX < mDragDistance) {
                        mParams.leftMargin = viewOriginMargin;
                        view.setLayoutParams(mParams);
                        onDragRebound(view);
                    }
                }

                isTouched = false;

                break;
        }
        return false;
    }
}
