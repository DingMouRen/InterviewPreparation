package com.dingmouren.interviewpreparation.z_classes.touchEventDispose;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by dingmouren
 * email: naildingmouren@gmail.com
 * github: https://github.com/DingMouRen
 */

public class MyView extends android.support.v7.widget.AppCompatTextView {
    private static final String TAG = "MyView";
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e(TAG,"dispatchTouchEvent");
//        getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG,"onTouchEvent");
        return true;
    }
}
