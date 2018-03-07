package com.codingending.uisystemdemo.common;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.codingending.uisystemdemo.R;
import com.codingending.uisystemdemo.adapter.StyleSpinnerAdapter;
import com.codingending.uisystemdemo.bean.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 演示Spinner[下拉选择框]
 * Created by CodingEnding on 2018/2/10.
 */

public class SpinnerFragment extends Fragment {
    public static final String TAG="SpinnerFragment";
    private Spinner normalSpinner;
    private Spinner dialogSpinner;
    private Spinner styleSpinner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_spinner,container,false);
        initViews(view);
        return view;
    }

    private void initViews(View view){
        normalSpinner=view.findViewById(R.id.spinner_normal);
        final String[] normalItems={"选项一","选项二","选项三"};
        ArrayAdapter<String> normalAdapter=new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item,normalItems);
        //为下拉列表项设置布局
        normalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        normalSpinner.setAdapter(normalAdapter);
        normalSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG,"当前选中项："+normalItems[position]);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.i(TAG,"当前未选中任何项");
            }
        });

        dialogSpinner=view.findViewById(R.id.spinner_dialog);
        ArrayAdapter<String> dialogAdapter=new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item,normalItems);
        dialogAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dialogSpinner.setAdapter(dialogAdapter);
        dialogSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG,"当前选中项："+normalItems[position]);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.i(TAG,"当前未选中任何项");
            }
        });

        styleSpinner=view.findViewById(R.id.spinner_style);
        final List<Book> bookList=new ArrayList<>();
        bookList.add(new Book("《小王子》",R.mipmap.ic_launcher));
        bookList.add(new Book("《资本论》",R.mipmap.ic_launcher));
        bookList.add(new Book("《三体》",R.mipmap.ic_launcher));
        StyleSpinnerAdapter styleAdapter=new StyleSpinnerAdapter(getActivity(),bookList);
        styleSpinner.setAdapter(styleAdapter);
        styleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG,"当前选中项："+bookList.get(position).getName());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.i(TAG,"当前未选中任何项");
            }
        });
    }



}
