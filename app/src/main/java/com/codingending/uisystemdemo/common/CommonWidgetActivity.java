package com.codingending.uisystemdemo.common;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.codingending.uisystemdemo.R;
import com.codingending.uisystemdemo.base.BaseWidgetActivity;

/**
 * 选择常用的控件进行演示
 * @author CodingEnding
 */

public class CommonWidgetActivity extends BaseWidgetActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_widget);

        initViews();
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initViews(){
        findViewById(R.id.btn_checkbox_demo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonWidgetDemoActivity.actionStart(CommonWidgetActivity.this,
                        CommonWidgetDemoActivity.TYPE_CHECKBOX);
            }
        });
        findViewById(R.id.btn_radio_button_demo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonWidgetDemoActivity.actionStart(CommonWidgetActivity.this,
                        CommonWidgetDemoActivity.TYPE_RADIO_BUTTON);
            }
        });
        findViewById(R.id.btn_toggle_button_demo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonWidgetDemoActivity.actionStart(CommonWidgetActivity.this,
                        CommonWidgetDemoActivity.TYPE_TOGGLE_BUTTON);
            }
        });
        findViewById(R.id.btn_switch_demo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonWidgetDemoActivity.actionStart(CommonWidgetActivity.this,
                        CommonWidgetDemoActivity.TYPE_SWITCH);
            }
        });
        findViewById(R.id.btn_progress_demo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonWidgetDemoActivity.actionStart(CommonWidgetActivity.this,
                        CommonWidgetDemoActivity.TYPE_PROGRESSBAR);
            }
        });
        findViewById(R.id.btn_seek_bar_demo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonWidgetDemoActivity.actionStart(CommonWidgetActivity.this,
                        CommonWidgetDemoActivity.TYPE_SEEK_BAR);
            }
        });
        findViewById(R.id.btn_rating_bar_demo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonWidgetDemoActivity.actionStart(CommonWidgetActivity.this,
                        CommonWidgetDemoActivity.TYPE_RATING_BAR);
            }
        });
        findViewById(R.id.btn_spinner_demo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonWidgetDemoActivity.actionStart(CommonWidgetActivity.this,
                        CommonWidgetDemoActivity.TYPE_SPINNER);
            }
        });
        findViewById(R.id.btn_image_button_demo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonWidgetDemoActivity.actionStart(CommonWidgetActivity.this,
                        CommonWidgetDemoActivity.TYPE_IMAGE_BUTTON);
            }
        });
        findViewById(R.id.btn_text_view_demo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonWidgetDemoActivity.actionStart(CommonWidgetActivity.this,
                        CommonWidgetDemoActivity.TYPE_TEXT_VIEW);
            }
        });
        findViewById(R.id.btn_edit_text_demo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonWidgetDemoActivity.actionStart(CommonWidgetActivity.this,
                        CommonWidgetDemoActivity.TYPE_EDIT_TEXT);
            }
        });
        findViewById(R.id.btn_auto_complete_text_view_demo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonWidgetDemoActivity.actionStart(CommonWidgetActivity.this,
                        CommonWidgetDemoActivity.TYPE_AUTO_COMPLETE_TEXT_VIEW);
            }
        });
    }
}
