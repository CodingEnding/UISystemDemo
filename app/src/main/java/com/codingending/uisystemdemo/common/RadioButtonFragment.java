package com.codingending.uisystemdemo.common;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.codingending.uisystemdemo.R;

/**
 * 演示RadioButton
 * Created by CodingEnding on 2018/1/31.
 */

public class RadioButtonFragment extends Fragment {
    private RadioGroup normalRadioGroup;
    private RadioGroup styleRadioGroup;
    private RadioButton appleRadioBtn;
    private RadioButton bananaRadioBtn;
    private RadioButton styleAppleRadioBtn;
    private RadioButton styleBananaRadioBtn;

    private Button getCheckedRadioBtn;
    private Button clearCheckedRadioBtn;
    private Button checkSpecialRadioBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_radio_button,container,false);
        initViews(view);
        return view;
    }

    /**
     * 弹出指定Toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
    }

    /**
     * 初始化控件
     * @param view
     */
    private void initViews(View view){
        normalRadioGroup=view.findViewById(R.id.radio_group_normal);
        styleRadioGroup=view.findViewById(R.id.radio_group_style);
        appleRadioBtn=view.findViewById(R.id.radio_button_apple);
        bananaRadioBtn=view.findViewById(R.id.radio_button_banana);
        styleAppleRadioBtn=view.findViewById(R.id.radio_button_style_apple);
        styleBananaRadioBtn=view.findViewById(R.id.radio_button_style_banana);

        getCheckedRadioBtn=view.findViewById(R.id.btn_get_checked_radio);
        clearCheckedRadioBtn=view.findViewById(R.id.btn_clear_checked_radio);
        checkSpecialRadioBtn=view.findViewById(R.id.btn_check_special_radio);

        normalRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){//checkedId是触发这一方法的RadioButton的Id属性
                    case R.id.radio_button_apple:
                        if(appleRadioBtn.isChecked()){
                            toastMessage(appleRadioBtn.getText().toString());
                        }
                        break;
                    case R.id.radio_button_banana:
                        if(bananaRadioBtn.isChecked()){
                            toastMessage(bananaRadioBtn.getText().toString());
                        }
                        break;
                        default:break;
                }
            }
        });
        getCheckedRadioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedRadioId=normalRadioGroup.getCheckedRadioButtonId();
                switch (checkedRadioId){
                    case R.id.radio_button_apple:
                        toastMessage(appleRadioBtn.getText().toString());
                        break;
                    case R.id.radio_button_banana:
                        toastMessage(bananaRadioBtn.getText().toString());
                        break;
                    default:break;
                }
            }
        });
        clearCheckedRadioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//清除选中项（会触发OnCheckedChangeListener#onCheckedChanged方法）
                normalRadioGroup.clearCheck();
            }
        });
        checkSpecialRadioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//选中指定项
                normalRadioGroup.check(R.id.radio_button_banana);//选中香蕉选项
            }
        });
        styleRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radio_button_style_apple:
                        if(styleAppleRadioBtn.isChecked()){
                            toastMessage(styleAppleRadioBtn.getText().toString());
                        }
                        break;
                    case R.id.radio_button_style_banana:
                        if(styleBananaRadioBtn.isChecked()){
                            toastMessage(styleBananaRadioBtn.getText().toString());
                        }
                        break;
                    default:break;
                }
            }
        });
    }
}
