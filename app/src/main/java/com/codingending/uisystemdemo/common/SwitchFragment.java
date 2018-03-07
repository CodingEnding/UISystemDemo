package com.codingending.uisystemdemo.common;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.codingending.uisystemdemo.R;

/**
 * 演示Switch[开关按钮]
 * Created by CodingEnding on 2018/2/1.
 */

public class SwitchFragment extends Fragment {
    private Switch normalSwitch;
    private Switch styleSwitch;
    private SwitchCompat compatSwitch;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_switch,container,false);
        initViews(view);
        return view;
    }

    private void initViews(View view){
        normalSwitch=view.findViewById(R.id.switch_normal);
        styleSwitch=view.findViewById(R.id.switch_style);
        compatSwitch=view.findViewById(R.id.switch_compat);

        CompoundButton.OnCheckedChangeListener listener=new SwitchListener();

        normalSwitch.setOnCheckedChangeListener(listener);
        styleSwitch.setOnCheckedChangeListener(listener);
        compatSwitch.setOnCheckedChangeListener(listener);
    }

    private class SwitchListener implements CompoundButton.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(isChecked){
                Toast.makeText(getActivity(),"开关已开启",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getActivity(),"开关已关闭",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
