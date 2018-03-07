package com.codingending.uisystemdemo.common;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

import com.codingending.uisystemdemo.R;
import com.codingending.uisystemdemo.adapter.StyleFilterAdapter;
import com.codingending.uisystemdemo.bean.Book;
import com.codingending.uisystemdemo.others.StyleTokenizer;

import java.util.ArrayList;
import java.util.List;

/**
 * 演示AutoCompleteTextView
 * Created by CodingEnding on 2018/2/11.
 */

public class AutoCompleteTextViewFragment extends Fragment {
    private AutoCompleteTextView normalAutoCompleteView;
    private AutoCompleteTextView styleAutoCompleteView;
    private MultiAutoCompleteTextView multiAutoCompleteView;
    private MultiAutoCompleteTextView styleMultiAutoCompleteView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_auto_complete_text_view,container,false);
        initViews(view);
        return view;
    }

    private void initViews(View view){
        normalAutoCompleteView=view.findViewById(R.id.auto_complete_text_view_normal);
        styleAutoCompleteView=view.findViewById(R.id.auto_complete_text_view_style);
        multiAutoCompleteView =view.findViewById(R.id.multi_auto_complete_text_view);
        styleMultiAutoCompleteView=view.findViewById(R.id.multi_auto_complete_text_view_style);

        //为AutoCompleteTextView设置Adapter
        String[] dataResArray={"coding","ending","codingending","coding and ending"};
        ArrayAdapter<String> normalAdapter=new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,dataResArray);
        normalAutoCompleteView.setAdapter(normalAdapter);

        //为AutoCompleteTextView设置自定义的Adapter
        final List<Book> bookList=new ArrayList<>();
        bookList.add(new Book("《小王子》",R.mipmap.ic_launcher));
        bookList.add(new Book("《狮子王》",R.mipmap.ic_launcher));
        bookList.add(new Book("《王小波全集》",R.mipmap.ic_launcher));
        final StyleFilterAdapter styleAdapter=new StyleFilterAdapter(getActivity(),bookList);
        styleAutoCompleteView.setAdapter(styleAdapter);
        styleAutoCompleteView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){//保证在没有输入内容的情况下（但是有焦点）也能获得自动补全的提示
                    styleAutoCompleteView.showDropDown();//展示下拉列表（会根据内容自动筛选）
                }
            }
        });

        //为MultiAutoCompleteTextView设置Adapter
        ArrayAdapter<String> multiDataAdapter=new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,dataResArray);
        multiAutoCompleteView.setAdapter(multiDataAdapter);
        //设置分隔符
        multiAutoCompleteView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        //为MultiAutoCompleteTextView设置自定义的分隔符
        ArrayAdapter<String> styleMultiDataAdapter=new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,dataResArray);
        styleMultiAutoCompleteView.setAdapter(styleMultiDataAdapter);
        //设置自定义分隔符（#和空格）
        styleMultiAutoCompleteView.setTokenizer(new StyleTokenizer('#'));
    }

}
