package com.codingending.uisystemdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.codingending.uisystemdemo.common.CommonWidgetActivity;
import com.codingending.uisystemdemo.list.ListViewActivity;
import com.codingending.uisystemdemo.md.MDWidgetActivity;

/**
 * demo入口界面
 * @author CodingEnding
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews(){
        findViewById(R.id.to_normal_widget).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CommonWidgetActivity.class));
            }
        });
        findViewById(R.id.to_md_widget).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MDWidgetActivity.class));
            }
        });
        findViewById(R.id.to_list_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ListViewActivity.class));
            }
        });
        findViewById(R.id.to_gird_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        findViewById(R.id.to_recycler_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
