package com.codingending.uisystemdemo.base;

import android.support.v7.app.AppCompatActivity;

/**
 * 选择演示控件的基础Activity
 * Created by CodingEnding on 2018/2/12.
 */

public abstract class BaseWidgetActivity extends AppCompatActivity{

    /**
     * 初始化控件
     */
    protected abstract void initViews();
}
