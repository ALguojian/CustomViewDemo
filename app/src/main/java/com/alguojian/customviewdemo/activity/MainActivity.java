package com.alguojian.customviewdemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private View button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {

        button = findViewById(R.id.button);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.translateanim);
        button.startAnimation(animation);

        TranslateAnimation translateAnimation = new TranslateAnimation(0, 500, 0, 500);
        translateAnimation.setDuration(2000);
        button.startAnimation(translateAnimation);


        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(2000);
        button.startAnimation(scaleAnimation);


        Animation rotateAnimation = new RotateAnimation(0, 270, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(2000);
        rotateAnimation.setFillAfter(true);
        button.startAnimation(rotateAnimation);


        AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
        alphaAnimation.setDuration(2000);
        button.startAnimation(alphaAnimation);


        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.ic_launcher_background);
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();
        animationDrawable.start();
        animationDrawable.stop();


        AnimationDrawable animationDrawable1 = new AnimationDrawable();
        for (int i = 0; i < 10; i++) {
            int drawable = getResources().getIdentifier("aaa" + i, "drawable", getPackageName());
            Drawable drawable1 = getResources().getDrawable(drawable);
            animationDrawable1.addFrame(drawable1,100);
        }
        animationDrawable1.setOneShot(true);
        ImageView imageView1 = new ImageView(this);
        imageView.setImageResource(R.drawable.ic_launcher_background);
        //开启动画之前先停止动画，不然第一次动画之后会停留在最后一帧，这样动画就只会触发一次
        animationDrawable1.stop();
        animationDrawable1.start();


        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0.0f, 120f);
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
        });






    }
}
