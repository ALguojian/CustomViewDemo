package com.alguojian.customviewdemo.view;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;

import com.alguojian.customviewdemo.Constant;
import com.socks.library.KLog;

/**
 * ${Descript}
 *
 * @author alguojian
 * @date 2018/7/19
 */
public class PullScrollViewTwo extends NestedScrollView {

    /**头部图片view*/
    private View mHeaderView;

    /**
     * 阻力系数
     */
    private   float SCROLL_RATIO = 0.5f;



    /**
     * 头部图片初始化位置
     */
    private Rect mHeaderInitRect = new Rect();

    /**
     * 底部view
     */
    private View rootView;

    /**
     * 初始化点击位置
     */
    private Point mPoint = new Point();

    private boolean mIsMoving;


    /**
     * 头部view的实时顶部位置
     */
    private int mHeaderCurTop;
    private int mHeaderCurBottom;


    private int mContentTop;
    private int mContentBottom;


    /**
     * 底部控件初始化位置
     */
    private Rect mContentInitRect = new Rect();

    public PullScrollViewTwo(@NonNull Context context) {
        super(context);
    }


    public PullScrollViewTwo(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PullScrollViewTwo(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        if (getChildCount() > 0)
            rootView = getChildAt(0);
        super.onFinishInflate();
    }

    /**
     * 设置头部view
     *
     * @param view view的对象
     */
    public void setHeaderView(@NonNull View view) {
        this.mHeaderView = view;
        invalidate();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:

                //设置初始化位置
                mPoint.set((int) ev.getX(), (int) ev.getY());

                mHeaderInitRect.set(mHeaderView.getLeft(), mHeaderView.getTop(), mHeaderView.getRight(), mHeaderView.getBottom());

                mContentInitRect.set(rootView.getLeft(), rootView.getTop(), rootView.getRight(), rootView.getBottom());
                break;
            default:
                break;
        }

        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        float scrollY = ev.getY();

        switch (ev.getAction()) {

            case MotionEvent.ACTION_MOVE:

                int delta = (int) scrollY - mPoint.y;

                delta = delta > mHeaderView.getHeight() ? mHeaderView.getHeight() : delta;

                //判断手指是向下移动的，
                if (delta > 0 && delta >= getScrollY()) {

                    //计算头部view应该移动的距离
                    float headerMoveHeight = delta * 0.5f*0.3f;

                    KLog.d(Constant.TTAG, "高度是" + headerMoveHeight);

                    mHeaderCurTop = (int) (mHeaderInitRect.top + headerMoveHeight);
                    mHeaderCurBottom = (int) (mHeaderInitRect.bottom + headerMoveHeight);

                    float contentMoveHeight =  delta * 0.5f*0.3f;

                    mContentTop = (int) (mContentInitRect.top + contentMoveHeight);
                    mContentBottom = (int) (mContentInitRect.bottom + contentMoveHeight);

                    if (mContentTop <= mHeaderCurBottom) {

                        mHeaderView.layout(mHeaderInitRect.left, mHeaderCurTop, mHeaderInitRect.right, mHeaderCurBottom);
                        rootView.layout(mContentInitRect.left, mContentTop, mContentInitRect.right, mContentBottom);
                        mIsMoving = true;
                    }
                }
                break;

            case MotionEvent.ACTION_UP:

                if (mIsMoving) {

                    mHeaderView.layout(mHeaderInitRect.left, mHeaderInitRect.top, mHeaderInitRect.right, mHeaderInitRect.bottom);
                    TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, mHeaderCurTop - mHeaderInitRect.top, 0);
                    translateAnimation.setDuration(200);
                    mHeaderView.startAnimation(translateAnimation);

                    rootView.layout(mContentInitRect.left, mContentInitRect.top, mContentInitRect.right, mContentInitRect.bottom);
                    TranslateAnimation translateAnimation1 = new TranslateAnimation(0, 0, mContentTop - mContentInitRect.top, 0);
                    translateAnimation1.setDuration(200);
                    mHeaderView.startAnimation(translateAnimation1);

                    mIsMoving = false;
                }

                break;

            default:
                break;
        }
        return super.onTouchEvent(ev);
    }
}
