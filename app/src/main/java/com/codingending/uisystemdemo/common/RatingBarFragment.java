package com.codingending.uisystemdemo.common;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.codingending.uisystemdemo.R;

/**
 * 演示RatingBar[星级评分控件]
 * Created by CodingEnding on 2018/2/2.
 */

public class RatingBarFragment extends Fragment {
    public static final String TAG="RatingBarFragment";
    private RatingBar normalRatingBar;
    private RatingBar indicatorRatingBar;
    private RatingBar styleRatingBar;//自定义样式的RatingBar
    private TextView ratingView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_ratingbar,container,false);
        initViews(view);
        return view;
    }

    private void initViews(View view){
        normalRatingBar=view.findViewById(R.id.rating_bar_normal);
        indicatorRatingBar=view.findViewById(R.id.rating_bar_indicator);
        styleRatingBar=view.findViewById(R.id.rating_bar_style);
        ratingView=view.findViewById(R.id.text_view_rating);

        RatingBarListener listener=new RatingBarListener();

        normalRatingBar.setOnRatingBarChangeListener(listener);
        styleRatingBar.setOnRatingBarChangeListener(listener);
    }

    private class RatingBarListener implements RatingBar.OnRatingBarChangeListener{
        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            //当评分改变时该方法会触发
            Log.i(TAG,"当前评分："+rating);
            ratingView.setText("当前评分："+rating);
        }
    }
}
