package com.alguojian.customviewdemo.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.alguojian.customviewdemo.bean.Point;
import com.alguojian.customviewdemo.objectevaluator.ObjectEvaluator;

/**
 * ${Descript}
 *
 * @author alguojian
 * @date 2018/7/17
 */
public class ObjectEvaluatorView extends View {

    private Paint mPaint;
    private Point mPoint;

    private static final int ROUND = 81;

    public ObjectEvaluatorView(Context context) {
        this(context, null);
    }

    public ObjectEvaluatorView(Context context, @Nullable AttributeSet attrs) {

        this(context, attrs, 0);
    }

    public ObjectEvaluatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.YELLOW);
        mPaint.setTextSize(120);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mPoint == null) {
            mPoint = new Point(ROUND,ROUND);

            canvas.drawCircle(ROUND,ROUND,ROUND,mPaint);

            Point point = new Point(600, 800);
            Point point3 = new Point(80, 1200);
            Point point4 = new Point(80, 80);

            ValueAnimator valueAnimator = ValueAnimator.ofObject(new ObjectEvaluator(), mPoint, point,point3,point4);
            valueAnimator.setDuration(3000);
            valueAnimator.setRepeatCount(-1);

            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mPoint = (Point) animation.getAnimatedValue();

                    invalidate();
                }
            });

            valueAnimator.start();

        }else {
            canvas.drawText("😄",mPoint.getX(),mPoint.getY(),mPaint);
        }
    }
}
