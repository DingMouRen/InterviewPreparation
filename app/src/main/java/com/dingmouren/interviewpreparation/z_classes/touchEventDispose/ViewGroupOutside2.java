package com.dingmouren.interviewpreparation.z_classes.touchEventDispose;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by dingmouren
 * email: naildingmouren@gmail.com
 * github: https://github.com/DingMouRen
 * 内部拦截法
 */

public class ViewGroupOutside2 extends ViewGroup {
    private static final String TAG = "ViewGroupOutside2";

    private int mChildrenSize;
    private int mChildWidth;
    private int mChildIndex;
    /*分别记录上次滑动的坐标*/
    private int mLastX = 0;
    private int mLastY = 0;
    /*分别记录上次滑动的坐标（onInterceptTouchEvent）*/
    private int mLastXIntercept = 0;
    private int mLastYIntercept = 0;

    private Scroller mScroller;
    private VelocityTracker mVelocityTracker;


    public ViewGroupOutside2(Context context) {
        this(context,null);
    }

    public ViewGroupOutside2(Context context, AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public ViewGroupOutside2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mScroller = new Scroller(getContext());
        mVelocityTracker = VelocityTracker.obtain();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /*测量所有子view*/
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        /*测量自己*/
        measureSelf(widthMeasureSpec,heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        mChildrenSize = childCount;
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            mChildWidth = childWidth;
            child.layout(childWidth * i,0,childWidth * (i + 1),childHeight);
        }
    }


    /**
     * 内部拦截法
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
       int x = (int) ev.getX();
       int y = (int) ev.getY();
      if (ev.getAction() == MotionEvent.ACTION_DOWN){
          mLastX = x;
          mLastY = y;
          if (!mScroller.isFinished()){/*横向滑动还没结束时，拦截事件*/
              mScroller.abortAnimation();
              return true;
          }
          return false;
      }else {
          return true;
      }
    }

    /**
     * 横向滑动得处理
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mVelocityTracker.addMovement(event);
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (!mScroller.isFinished()){
                    mScroller.abortAnimation();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int deltax = x - mLastX;
                int deltay = y - mLastY;
                scrollBy(-deltax,0);
                break;
            case MotionEvent.ACTION_UP:
                int scrollX = getScrollX();
                int scrollToChildIndex = scrollX / mChildWidth;
                mVelocityTracker.computeCurrentVelocity(1000);
                float xVelocity = mVelocityTracker.getXVelocity();
                if (Math.abs(xVelocity) >= 50){
                    mChildIndex = xVelocity > 0 ? mChildIndex - 1 : mChildIndex + 1;
                }else {
                    mChildIndex = (scrollX + mChildWidth / 2) / mChildWidth;
                }
                mChildIndex = Math.max(0,Math.min(mChildIndex,mChildrenSize - 1));
                int dx = mChildIndex * mChildWidth - scrollX;
                smoothScrollBy(dx,0);
                mVelocityTracker.clear();
                break;
        }
        mLastX = x;
        mLastY = y;
        return super.onTouchEvent(event);
    }


    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()){
            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            postInvalidate();
        }
    }


    /**
     * 滑动
     * @param dx
     * @param dy
     */
    private void smoothScrollBy(int dx,int dy){
        mScroller.startScroll(getScrollX(),0,dx,0,500);
        invalidate();
    }

    private void measureSelf(int widthMeasureSpec,int heightMeausreSpec){
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeausreSpec);
        int heightSize = MeasureSpec.getSize(heightMeausreSpec);
        int width = 0;
        int height = 0;
        switch (widthMode){
            case MeasureSpec.EXACTLY:
                width = widthSize;
                break;
            case MeasureSpec.AT_MOST:
                width = widthSize;
                break;
            case MeasureSpec.UNSPECIFIED:
                break;
        }
        switch (heightMode){
            case MeasureSpec.EXACTLY:
                height = heightSize;
                break;
            case MeasureSpec.AT_MOST:
                 height = heightSize;
                 break;
            case MeasureSpec.UNSPECIFIED:
                break;
        }
        setMeasuredDimension(width,height);
    }

}
