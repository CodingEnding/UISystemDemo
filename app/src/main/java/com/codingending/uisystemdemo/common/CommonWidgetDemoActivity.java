package com.codingending.uisystemdemo.common;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.codingending.uisystemdemo.R;
import com.codingending.uisystemdemo.base.BaseWidgetDemoActivity;

/**
 * 演示常用控件的执行效果
 * 通过Fragment进行切换
 * @author CodingEnding
 */

public class CommonWidgetDemoActivity extends BaseWidgetDemoActivity {
//    public static final String KEY_WIDGET_TYPE="widget_type";
    public static final int TYPE_CHECKBOX=1;//标识-展示复选框
    public static final int TYPE_RADIO_BUTTON=2;//标识-展示单选按钮
    public static final int TYPE_TOGGLE_BUTTON=3;//标识-展示ToggleButton
    public static final int TYPE_SWITCH=4;//标识-展示Switch
    public static final int TYPE_PROGRESSBAR=5;//标识-展示ProgressBar
    public static final int TYPE_SEEK_BAR=6;//标识-展示SeekBar
    public static final int TYPE_RATING_BAR=7;//标识-展示RatingBar
    public static final int TYPE_SPINNER=8;//标识-展示Spinner
    public static final int TYPE_IMAGE_BUTTON=9;//标识-展示ImageButton
    public static final int TYPE_TEXT_VIEW =10;//标识-展示TextView
    public static final int TYPE_EDIT_TEXT =11;//标识-展示EditText
    public static final int TYPE_AUTO_COMPLETE_TEXT_VIEW =12;//标识-展示AutoCompleteTextView

    /**
     * 根据标识加载相应的Fragment
     * @param type 需要展示的控件标识
     */
    public static void actionStart(Context context, int type){
        Intent intent=new Intent(context,CommonWidgetDemoActivity.class);
        intent.putExtra(KEY_WIDGET_TYPE,type);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_widget_demo);

        loadFragment();//加载Fragment
    }

    /**
     * 从Intent中解析出type，并根据type加载Fragment
     */
    @Override
    protected void loadFragment(){
        int type=getIntent().getIntExtra(KEY_WIDGET_TYPE,TYPE_CHECKBOX);
        Fragment fragment=null;
        switch (type){
            case TYPE_CHECKBOX:
                fragment=new CheckBoxFragment();
                break;
            case TYPE_RADIO_BUTTON:
                fragment=new RadioButtonFragment();
                break;
            case TYPE_TOGGLE_BUTTON:
                fragment=new ToggleButtonFragment();
                break;
            case TYPE_SWITCH:
                fragment=new SwitchFragment();
                break;
            case TYPE_PROGRESSBAR:
                fragment=new ProgressBarFragment();
                break;
            case TYPE_SEEK_BAR:
                fragment=new SeekBarFragment();
                break;
            case TYPE_RATING_BAR:
                fragment=new RatingBarFragment();
                break;
            case TYPE_SPINNER:
                fragment=new SpinnerFragment();
                break;
            case TYPE_IMAGE_BUTTON:
                fragment=new ImageButtonFragment();
                break;
            case TYPE_TEXT_VIEW:
                fragment=new TextViewFragment();
                break;
            case TYPE_EDIT_TEXT:
                fragment=new EditTextFragment();
                break;
            case TYPE_AUTO_COMPLETE_TEXT_VIEW:
                fragment=new AutoCompleteTextViewFragment();
                break;
            default:
                break;
        }
        getFragmentManager().beginTransaction()
                .add(R.id.container,fragment)
                .commit();
    }
}
