package com.codingending.uisystemdemo.base;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * 选择演示控件的基础Activity
 * Created by CodingEnding on 2018/2/12.
 */

public abstract class BaseWidgetActivity extends AppCompatActivity{

    /**
     * 初始化控件
     */
    protected abstract void initViews();

    /**
     * 展示提示信息
     * @param msg 提示信息
     */
    protected void showToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
