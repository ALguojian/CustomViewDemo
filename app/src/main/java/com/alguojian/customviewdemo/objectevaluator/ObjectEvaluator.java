package com.alguojian.customviewdemo.objectevaluator;

import android.animation.TypeEvaluator;

import com.alguojian.customviewdemo.bean.Point;

/**
 * ${Descript}
 *
 * @author alguojian
 * @date 2018/7/17
 */
public class ObjectEvaluator implements TypeEvaluator{

    /**
     *
     * @param fraction 动画完成度
     * @param startValue 动画初始值
     * @param endValue 动画结束值
     * @return
     */
    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {

        Point point = (Point) startValue;
        Point point2 = (Point) endValue;

        float v = point.getX() + fraction * (point2.getX() - point.getX());
        float y = point.getY() + fraction * (point2.getY() - point.getY());

        Point point1 = new Point(v, y);

        return point1;
    }
}
