package com.alguojian.customviewdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.alguojian.customviewdemo.R;
import com.alguojian.customviewdemo.viewgroup.TestViewGroup;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        TestViewGroup viewById = findViewById(R.id.testViewGroup);

        viewById.setOnItemClickListener(new TestViewGroup.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(Main3Activity.this,position+"---😄",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
