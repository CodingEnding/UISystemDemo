package com.codingending.uisystemdemo.md;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.codingending.uisystemdemo.R;
import com.codingending.uisystemdemo.base.BaseWidgetActivity;

/**
 * 选择MaterialDesign系列控件进行演示
 * @author CodingEnding
 */

public class MDWidgetActivity extends BaseWidgetActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mdwidget);

        initViews();
    }


    @Override
    protected void initViews() {
        findViewById(R.id.btn_card_view_demo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
