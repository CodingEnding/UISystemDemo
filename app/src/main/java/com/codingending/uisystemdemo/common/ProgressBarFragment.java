package com.codingending.uisystemdemo.common;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.codingending.uisystemdemo.R;

/**
 * 演示ProgressBar[进度条]
 * Created by CodingEnding on 2018/2/2.
 */

public class ProgressBarFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_progressbar,container,false);
        return view;
    }
}
