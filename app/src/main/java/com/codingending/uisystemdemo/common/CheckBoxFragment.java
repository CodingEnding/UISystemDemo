package com.codingending.uisystemdemo.common;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.codingending.uisystemdemo.R;

/**
 * 演示CheckBox
 * Created by CodingEnding on 2018/1/31.
 */

public class CheckBoxFragment extends Fragment {
    private CheckBox normalCheckBox;
    private CheckBox styleCheckBox;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_checkbox,container,false);
        initViews(view);
        return view;
    }

    /**
     * 初始化控件
     */
    private void initViews(View view){
        normalCheckBox=view.findViewById(R.id.checkbox_normal);
        styleCheckBox=view.findViewById(R.id.checkbox_style);

        CompoundButton.OnCheckedChangeListener listener=new CheckBoxListener();

        normalCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(getActivity(),"选中",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(),"取消选中",Toast.LENGTH_SHORT).show();
                }
            }
        });
        styleCheckBox.setOnCheckedChangeListener(listener);
    }

    private class CheckBoxListener implements CompoundButton.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(isChecked){
                Toast.makeText(getActivity(),"选中："+buttonView.getText().toString(),
                        Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getActivity(),"取消选中",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
