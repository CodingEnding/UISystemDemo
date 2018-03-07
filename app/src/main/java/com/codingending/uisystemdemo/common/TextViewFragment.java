package com.codingending.uisystemdemo.common;

import android.app.Fragment;
import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.MaskFilterSpan;
import android.text.style.QuoteSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.codingending.uisystemdemo.R;

/**
 * 演示TextView
 * Created by CodingEnding on 2018/2/10.
 */

public class TextViewFragment extends Fragment {
    private TextView htmlStrTextView;//测试HTML格式的TextView
    private TextView spannableTextView;//测试Spannable的TextView

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_text_view,container,false);
        initViews(view);
        return view;
    }

    private void initViews(View view){
        //演示TextView支持HTML
        htmlStrTextView=view.findViewById(R.id.text_view_html_str);
        String htmlStr="这是一段测试文字。用于测试TextView对HTML的支持。如下：<a href='http://blog.csdn.net/codingending'>我的博客</a>";
        Spanned htmlSpanned=Html.fromHtml(htmlStr);
        htmlStrTextView.setText(htmlSpanned);
        htmlStrTextView.setMovementMethod(new LinkMovementMethod());//使超链接生效

        //演示TextView应用Spannable
        spannableTextView=view.findViewById(R.id.text_view_spannable_str);
        Spannable testSpannable=new SpannableString("这是一段测试文字。用于测试TextView对Spannable的支持。包括背景色、前景色、下划线区域、链接、点击事件等");
        testSpannable.setSpan(new BackgroundColorSpan(Color.BLUE),
                37,40,Spanned.SPAN_INCLUSIVE_EXCLUSIVE);//设置背景色
        testSpannable.setSpan(new ForegroundColorSpan(Color.RED),
                41,44,Spanned.SPAN_INCLUSIVE_EXCLUSIVE);//设置前景色
        testSpannable.setSpan(new UnderlineSpan(),
                45,50,Spanned.SPAN_INCLUSIVE_EXCLUSIVE);//设置下划线
        testSpannable.setSpan(new URLSpan("http://blog.csdn.net/codingending"),
                51,53,Spanned.SPAN_INCLUSIVE_EXCLUSIVE);//设置超链接
        testSpannable.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(widget.getContext(),"文本内点击",Toast.LENGTH_SHORT).show();
            }
        },54,58,Spanned.SPAN_INCLUSIVE_EXCLUSIVE);//设置点击事件
        spannableTextView.setText(testSpannable);
        spannableTextView.setMovementMethod(new LinkMovementMethod());//支持超链接和点击事件
    }
}
