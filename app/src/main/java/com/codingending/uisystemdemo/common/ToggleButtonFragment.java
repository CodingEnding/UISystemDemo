package com.codingending.uisystemdemo.common;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.codingending.uisystemdemo.R;

/**
 * 演示ToggleButton[多状态按钮]
 * Created by CodingEnding on 2018/2/1.
 */

public class ToggleButtonFragment extends Fragment {
    private ToggleButton normalToggleBtn;
    private ToggleButton styleToggleBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_toggle_button,container,false);
        initViews(view);
        return  view;
    }

    private void initViews(View view){
        normalToggleBtn=view.findViewById(R.id.toggle_button_normal);
        styleToggleBtn=view.findViewById(R.id.toggle_button_style);
        CompoundButton.OnCheckedChangeListener listener=new ToggleButtonListener();

        normalToggleBtn.setOnCheckedChangeListener(listener);
        styleToggleBtn.setOnCheckedChangeListener(listener);
    }

    private class ToggleButtonListener implements CompoundButton.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(isChecked){
                Toast.makeText(getActivity(),"当前为开启状态",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getActivity(),"当前为关闭状态",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
