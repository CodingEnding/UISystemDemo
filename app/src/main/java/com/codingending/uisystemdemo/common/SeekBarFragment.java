package com.codingending.uisystemdemo.common;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.codingending.uisystemdemo.R;

/**
 * 演示SeekBar[滑动条]
 * Created by CodingEnding on 2018/2/2.
 */

public class SeekBarFragment extends Fragment {
    public static final String TAG="SeekBarFragment";
    private SeekBar defaultSeekBar;
    private SeekBar normalStyleSeekBar;
    private SeekBar holoStyleSeekBar;
    private SeekBar attrStyleSeekBar;
    private SeekBar customStyleSeekBar;
    private TextView progressView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_seekbar,container,false);
        initViews(view);
        return view;
    }

    private void initViews(View view){
        defaultSeekBar=view.findViewById(R.id.seek_bar_default);
        normalStyleSeekBar=view.findViewById(R.id.seek_bar_style_normal);
        holoStyleSeekBar=view.findViewById(R.id.seek_bar_style_holo);
        attrStyleSeekBar=view.findViewById(R.id.seek_bar_style_attr);
        customStyleSeekBar=view.findViewById(R.id.seek_bar_style_custom);
        progressView=view.findViewById(R.id.text_view_progress);

        SeekBar.OnSeekBarChangeListener listener=new SeekBarListener();
        defaultSeekBar.setOnSeekBarChangeListener(listener);
        normalStyleSeekBar.setOnSeekBarChangeListener(listener);
        holoStyleSeekBar.setOnSeekBarChangeListener(listener);
        attrStyleSeekBar.setOnSeekBarChangeListener(listener);
        customStyleSeekBar.setOnSeekBarChangeListener(listener);
    }

    private class SeekBarListener implements SeekBar.OnSeekBarChangeListener{
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            progressView.setText("当前进度："+progress);
        }
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            Toast.makeText(getActivity(),"开始滑动",Toast.LENGTH_SHORT).show();
        }
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            Toast.makeText(getActivity(),"停止滑动",Toast.LENGTH_SHORT).show();
        }
    }
}
