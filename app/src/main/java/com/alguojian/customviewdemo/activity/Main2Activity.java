package com.alguojian.customviewdemo.activity;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.LinearInterpolator;
import android.widget.Button;

import com.alguojian.customviewdemo.R;

public class Main2Activity extends AppCompatActivity {

    private Button mButton;
    private Button mButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mButton = findViewById(R.id.button2);
        mButton3 = findViewById(R.id.button3);

        intValue();

        initObject();


    }

    /**
     * 属性动画，整型插值器
     */
    private void intValue() {

        //设置动画属性的初始值以及结束值，ofInt创建动画实例,讲传入的多个Int型参数进行平滑过渡，内置整型估值器，直接使用默认
        final ValueAnimator valueAnimator = ValueAnimator.ofInt(100, 300, 800);
        valueAnimator.setDuration(3000);//播放时间
        valueAnimator.setStartDelay(100);//延迟播放时间
        valueAnimator.setInterpolator(new Ccc());//设置插值器
        valueAnimator.setRepeatCount(-1);//设置播放次数,-1就是无限循环
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);//设置重复播放模式ValueAnimator.RESTART(默认):正序重放，ValueAnimator.REVERSE:倒序回放

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (Integer) valueAnimator.getAnimatedValue();

                mButton.setText("---" + animatedValue);

                mButton.setTranslationX(animatedValue);
//                mButton.getLayoutParams().width = animatedValue;
//                mButton.getLayoutParams().height = animatedValue;
//                mButton.setRotation(animatedValue);
//                mButton.setAlpha(animatedValue);

                mButton.requestLayout();//刷新view的位置
            }
        });
        valueAnimator.start();
    }

    /**
     * 对象估值器，需要自定义
     */
    private void initObject() {


        ObjectAnimator alpha = ObjectAnimator.ofObject(new Test(mButton3), "text", new Bbb(), new LinearInterpolator(), "asdasd", "qweqweqweqw", "zxczxczxczc");
        alpha.setDuration(3000);
        alpha.setRepeatCount(-1);
        alpha.start();

        ObjectAnimator rotation = ObjectAnimator.ofFloat(mButton3, "rotation", 1f, 360f, 100f);
        rotation.setDuration(3000);
        rotation.setRepeatCount(-1);
        rotation.start();

        ObjectAnimator translationX = ObjectAnimator.ofFloat(mButton3, "translationY", 1f, 360f, 100f);
        translationX.setDuration(3000);
        translationX.setRepeatCount(-1);
        translationX.start();

        ObjectAnimator scaleY = ObjectAnimator.ofFloat(mButton3, "scaleY", 1f, 4f, 1f);
        scaleY.setDuration(3000);
        scaleY.setRepeatCount(-1);
        scaleY.start();


    }

    public void setAaa(String s) {
        mButton3.setText(s);
    }

    private class Test {

        private Button mView;

        public Test(Button view) {
            mView = view;
        }

        public String getText() {

            return mView.getText().toString();
        }

        public void setText(String s) {

            mView.setText(s);
        }

    }

    private class Bbb implements TypeEvaluator {

        @Override
        public Object evaluate(float fraction, Object startValue, Object endValue) {

            return startValue;
        }
    }


    private class Ccc implements TimeInterpolator {

        /**
         * @param input 范围变化1-0，
         * @return
         */
        @Override
        public float getInterpolation(float input) {
            float aa;
            if (input <= 0.5) {
                aa = (float) ((Math.sin(Math.PI * input)) / 2);
            } else {
                aa = (float) (2 - Math.sin(Math.PI * input) / 2);
            }
            return aa;
        }
    }


}
