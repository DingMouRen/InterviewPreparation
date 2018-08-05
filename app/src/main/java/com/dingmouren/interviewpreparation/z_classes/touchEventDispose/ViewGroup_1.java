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


public class ViewGroup_1 extends FrameLayout {
    private static final String TAG = "View_1";

    private int mLastXIntercept,mLastYIntercept;

    public ViewGroup_1(Context context) {
        super(context);
    }

    public ViewGroup_1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewGroup_1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e(TAG,"dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    */
/**
     * 外部拦截法
     * @param ev
     * @return
     *//*

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e(TAG,"onInterceptTouchEvent");
        boolean intercepted = false;
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                intercepted = false;
                break;
            case MotionEvent.ACTION_MOVE:
                if (父容器需要当前点击事件){
                    intercepted = true;
                }else {
                    intercepted = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                intercepted = false;
                break;
            default:
                break;;
        }

        mLastXIntercept = x;
        mLastYIntercept = y;
        return intercepted;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG,"onTouchEvent");
        return super.onTouchEvent(event);
    }
}
*/
