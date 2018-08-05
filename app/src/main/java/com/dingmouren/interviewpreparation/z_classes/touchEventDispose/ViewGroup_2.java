/*
package com.dingmouren.interviewpreparation.z_classes.touchEventDispose;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;

*/
/**
 * Created by dingmouren
 * email: naildingmouren@gmail.com
 * github: https://github.com/DingMouRen
 *//*


public class ViewGroup_2 extends FrameLayout {
    private static final String TAG = "View_2";

    private int mLastX,mLastY;

    public ViewGroup_2(Context context) {
        super(context);
    }

    public ViewGroup_2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewGroup_2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    */
/**
     * 内部拦截法
     * @param ev
     * @return
     *//*

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e(TAG,"dispatchTouchEvent");
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                if (父容器需要此类点击事件){
                    getParent().requestDisallowInterceptTouchEvent(false);*/
/*设置父元素能继续拦截所需的事件*//*

                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        mLastX = x;
        mLastY = y;
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e(TAG,"onInterceptTouchEvent");
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG,"onTouchEvent");
        return super.onTouchEvent(event);
    }
}
*/
