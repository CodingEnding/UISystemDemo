package com.codingending.uisystemdemo.md;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.codingending.uisystemdemo.R;
import com.codingending.uisystemdemo.base.BaseWidgetDemoActivity;
import com.codingending.uisystemdemo.common.AutoCompleteTextViewFragment;
import com.codingending.uisystemdemo.common.CheckBoxFragment;
import com.codingending.uisystemdemo.common.EditTextFragment;
import com.codingending.uisystemdemo.common.ImageButtonFragment;
import com.codingending.uisystemdemo.common.ProgressBarFragment;
import com.codingending.uisystemdemo.common.RadioButtonFragment;
import com.codingending.uisystemdemo.common.RatingBarFragment;
import com.codingending.uisystemdemo.common.SeekBarFragment;
import com.codingending.uisystemdemo.common.SpinnerFragment;
import com.codingending.uisystemdemo.common.SwitchFragment;
import com.codingending.uisystemdemo.common.TextViewFragment;
import com.codingending.uisystemdemo.common.ToggleButtonFragment;

/**
 * 演示MaterialDesign系列的控件
 * 通过Fragment进行切换
 * @author CodingEnding
 */

public class MDWidgetDemoActivity extends BaseWidgetDemoActivity {
    public static final int TYPE_CARD_VIEW=1;//标识-展示CardView

    /**
     * 根据标识加载相应的Fragment
     * @param type 需要展示的控件标识
     */
    public static void actionStart(Context context, int type){
        Intent intent=new Intent(context,MDWidgetDemoActivity.class);
        intent.putExtra(KEY_WIDGET_TYPE,type);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mdwidget_demo);

        loadFragment();//加载Fragment
    }


    @Override
    protected void loadFragment() {
        int type=getIntent().getIntExtra(KEY_WIDGET_TYPE,TYPE_CARD_VIEW);
        Fragment fragment=null;
        switch (type){
            case TYPE_CARD_VIEW:

                break;
            default:
                break;
        }
        getFragmentManager().beginTransaction()
                .add(R.id.container,fragment)
                .commit();
    }
}
