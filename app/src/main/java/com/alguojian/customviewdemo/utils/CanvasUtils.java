package com.alguojian.customviewdemo.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.alguojian.customviewdemo.R;

import java.io.IOException;
import java.io.InputStream;

/**
 * ${Descript}
 *
 * @author alguojian
 * @date 2018/7/16
 */
public class CanvasUtils extends View {
    private Context mContext;

    public CanvasUtils(Context context) {
        super(context);
    }

    public CanvasUtils(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {

        Bitmap bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);
        Canvas canvas1 = new Canvas();
        Canvas canvas2 = new Canvas(bitmap);


        //使用于绘制高质量图形，刷新频率高，但是系统花销比较大
        SurfaceView surfaceView = new SurfaceView(context);
        SurfaceHolder holder = surfaceView.getHolder();
        Canvas canvas = holder.lockCanvas();
        holder.unlockCanvasAndPost(canvas);

    }

    public CanvasUtils(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressLint("WrongConstant")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();

        //绘制颜色
        canvas.drawColor(Color.BLUE);
        canvas.drawColor(Color.BLUE, PorterDuff.Mode.SRC_IN);


        //绘制点
        canvas.drawPoint(300, 300, paint);
        //绘制多个点
        canvas.drawPoints(new float[]{299, 399, 123, 234, 123, 567}, paint);

        //绘制直线
        canvas.drawLine(100, 200, 400, 500, paint);
        //绘制多条直线
        canvas.drawLines(new float[]{100, 200, 400, 500, 400, 200, 800, 20}, paint);

        //绘制矩形
        Rect rect = new Rect(100, 100, 500, 500);
        canvas.drawRect(rect, paint);

        //与rect的区别,Rect = int , RectF = float
        RectF rectF = new RectF(100, 100, 600, 800);
        canvas.drawRect(rectF, paint);

        //绘制圆角矩形，50代表着圆角对应的椭圆半径
        canvas.drawRoundRect(rectF, 50, 50, paint);

        //绘制椭圆，椭圆半径就是矩形的宽高一半
        canvas.drawOval(rectF, paint);

        //绘制圆形
        canvas.drawCircle(500, 500, 300, paint);

        //绘制圆弧，开始角度，扫过得角度，是否使用中心点，使用的话绘制的就是弧线以及中心点连接形成的图案，不适用就是弧线起点以及终点连接的图案
        canvas.drawArc(rectF, 15, 180, true, paint);

        //绘制文字,起始点默认为文本的左下角

        String string = "请问饿哦草我啊是多空我看";
        canvas.drawText(string, 200, 300, paint);

        //指定绘制文本字体开始以及结束索引
        canvas.drawText(string, 2, 6, 300, 400, paint);

        //指定开始索引，以及绘制的长度
        char[] chars = string.toCharArray();
        canvas.drawText(chars, 1, 5, 300, 300, paint);

        //指定每一个字的坐标点，切记是每一个字，文本过长可能造成卡顿，不用包含表情
        canvas.drawPosText(string, new float[]{10, 20, 100, 200, 200, 200}, paint);

        //在path路径上绘制文字，与路径起始点的水平偏移距离，与路径中心的垂直偏移距离
        canvas.drawTextOnPath(string, new Path(), 50, 0, paint);

        //绘制位图，图片左上角为绘制原点

        //获得图片资源从drawable等
        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_launcher_foreground);

        //从assets获得图片
        try {
            InputStream open = mContext.getAssets().open("aaa.png");

            Bitmap bitmap1 = BitmapFactory.decodeStream(open);
            open.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //从内存卡获得图片
        Bitmap bitmap1 = BitmapFactory.decodeFile("/sdcard/aaa.png");

        //matrix用于绘制是对图片进行一下改变
        canvas.drawBitmap(bitmap, new Matrix(), paint);

        //指定绘制原点
        canvas.drawBitmap(bitmap, 300, 400, paint);


        //指定绘制区域
        Rect rect1 = new Rect(0, 0, bitmap.getWidth() / 2, bitmap.getHeight());
        //指定图片要显示在那块区域
        Rect rect2 = new Rect(100, 200, 400, 500);
        canvas.drawBitmap(bitmap, rect1, rect2, paint);

        //向有一栋300px，向下移动400px
        canvas.translate(300,400);

        //设置缩放，以及缩放中心点，默认是原点
        canvas.scale(1.2f,0.8f,100,0);

        //已点（30，50）旋转90度，即与x轴角度增大90度，切记：屏幕坐标系与数学坐标系成镜像显示
        canvas.rotate(90,30,50);

        //向x轴正方向倾斜45度
        canvas.skew(1f,0);


        //画布裁剪，其他区域不在编辑
        canvas.clipPath(new Path());

        //指定裁剪叠加裁剪时的效果,有关Region.OP全部参数看下面
        canvas.clipPath(new Path(), Region.Op.XOR);

        //裁剪矩形
        canvas.clipRect(rect);

        //保存全部状态
        canvas.save();

        //获得保存次数
        canvas.getSaveCount();

        //回退上一次
        canvas.restore();

        //指定回退的版本
        canvas.restoreToCount(2);

    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
