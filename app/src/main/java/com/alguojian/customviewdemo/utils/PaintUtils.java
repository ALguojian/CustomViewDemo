package com.alguojian.customviewdemo.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


/**
 * 有关path的使用
 *
 * @author alguojian
 * @date 2018/7/14
 */
public class PaintUtils extends View {

    private Path mPath;
    private Paint mPaint;

    public PaintUtils(Context context) {
        super(context);
        init();
    }

    private void init() {
        Paint mPaint = new Paint();

        mPaint.reset();//重置paint

        mPaint.setColor(Color.BLUE);

        mPaint.setStyle(Paint.Style.FILL);//只填充，不描边
        mPaint.setStyle(Paint.Style.STROKE);//只描边，不填充
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);//描边和填充

        mPaint.setStrokeWidth(1.2f);//设置画笔的粗细

        mPaint.setStrokeWidth(10f);//设置画笔宽度为10px

        mPaint.getColor();//获得画笔颜色

        mPaint.setShader(new Shader());//设置着色器，绘制多彩图案

        mPaint.setARGB(100, 100, 100, 100);//设置画笔颜色

        mPaint.setAlpha(30);//设置透明度
        mPaint.getAlpha();//获得画笔透明度

        mPaint.setTextSize(10);//设置字体大小10px

        mPaint.setTextAlign(Paint.Align.LEFT);//设置左对齐
        mPaint.setTextAlign(Paint.Align.CENTER);//设置中间对齐
        mPaint.setTextAlign(Paint.Align.RIGHT);//设置右对齐

        mPaint.setUnderlineText(true);//设置文本下划线

        mPaint.setStrikeThruText(true);//设置删除线

        mPaint.setFakeBoldText(true);//设置文本粗体

        mPaint.setTextSkewX(-0.5f);//设置斜体

        mPaint.setShadowLayer(5, 5, 5, Color.YELLOW);//设置文字阴影

        mPaint.setAntiAlias(true);//设置抗锯齿

        mPaint.setStrokeCap(Paint.Cap.ROUND);//这只末端仙帽为圆形，即在绘制的图形两端添加半圆形
        mPaint.setStrokeCap(Paint.Cap.BUTT);//默认无线帽
        mPaint.setStrokeCap(Paint.Cap.SQUARE);//这只末端仙帽为方形，即在绘制的图形两端添加半圆形

        mPaint.setStrokeJoin(Paint.Join.BEVEL);//设置线段连接处样式为直线
        mPaint.setStrokeJoin(Paint.Join.MITER);//设置线段连接处样式为锐角
        mPaint.setStrokeJoin(Paint.Join.ROUND);//圆角

        mPaint.setStrokeMiter(30f);//设置画笔的倾斜度，实际并没什么卵用

        mPaint.setPathEffect(new CornerPathEffect(100));//设置连接点为半径100px的圆形接入


        mPaint.setTextSkewX(-0.3f);//这是字体倾斜度，默认右方向倾斜为-0.25

        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.ADD));//设置多图案叠加方式
// case  0: return Mode.CLEAR;
// case  1: return Mode.SRC;
// case  2: return Mode.DST;
// case  3: return Mode.SRC_OVER;
// case  4: return Mode.DST_OVER;
// case  5: return Mode.SRC_IN;
// case  6: return Mode.DST_IN;
// case  7: return Mode.SRC_OUT;
// case  8: return Mode.DST_OUT;
// case  9: return Mode.SRC_ATOP;
// case 10: return Mode.DST_ATOP;
// case 11: return Mode.XOR;
// case 16: return Mode.DARKEN;
// case 17: return Mode.LIGHTEN;
// case 13: return Mode.MULTIPLY;
// case 14: return Mode.SCREEN;
// case 12: return Mode.ADD;
// case 15: return Mode.OVERLAY;

        String s = "1231111111";

        float v = mPaint.measureText(s);//测量文本长度

        mPaint.measureText(s, 2, 5);//测量文本长度，开始以及结束的索引


        //通过外边距矩形获得内置文本宽高
        Rect rect = new Rect();
        mPaint.getTextBounds(s, 2, 6, rect);
        int width = rect.width();
        int height = rect.height();


        //获得精确字体宽度
        int arr = 0;
        int len = s.length();
        float[] floats = new float[len];
        mPaint.getTextWidths(s, floats);

        for (int i = 0; i < len; i++) {
            arr += (int) Math.ceil(floats[i]);
        }
        int widthText = arr;


    }

    public PaintUtils(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    public PaintUtils(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


}
