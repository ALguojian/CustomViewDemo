package com.alguojian.customviewdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.alguojian.customviewdemo.R;
import com.alguojian.customviewdemo.view.PullScrollViewThree;
import com.alguojian.customviewdemo.view.PullScrollViewTwo;

public class Main4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        PullScrollViewThree viewById = findViewById(R.id.pullScrollView);

        ImageView viewById1 = findViewById(R.id.image);

        viewById.setHeader(viewById1);
    }
}
