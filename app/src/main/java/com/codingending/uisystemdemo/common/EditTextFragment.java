package com.codingending.uisystemdemo.common;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.codingending.uisystemdemo.R;

/**
 * 演示EditText
 * Created by CodingEnding on 2018/2/11.
 */

public class EditTextFragment extends Fragment{
    public static final String TAG="EditTextFragment";
    private EditText watchWriteEditText;//监听用户输入的EditText
    private EditText styleOmeOptionEditText;//自定义虚拟键盘右下角Enter键
    private EditText codeLimitInputEditText;//通过代码限制输入内容

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_edit_text,container,false);
        initViews(view);
        return view;
    }

    private void initViews(View view){
        watchWriteEditText=view.findViewById(R.id.edit_text_watch_write);
        styleOmeOptionEditText=view.findViewById(R.id.edit_text_style_ime_option);
        codeLimitInputEditText=view.findViewById(R.id.edit_text_code_limit_input);

        //监听用户的输入
        watchWriteEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.i(TAG,"beforeTextChanged："+s);//内容改变前
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i(TAG,"onTextChanged："+s);//内容改变时
            }
            @Override
            public void afterTextChanged(Editable s) {
                Log.i(TAG,"afterTextChanged："+s);//内容改变后
            }
        });

        //自定义虚拟键盘右下角的行为
        styleOmeOptionEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId== EditorInfo.IME_ACTION_SEARCH){
                    Toast.makeText(v.getContext(),"点击了搜索按钮",Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });

        //通过代码限制输入内容只能为数字
        InputFilter filter=new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                if(!TextUtils.isDigitsOnly(source)){
                    return "";//如果当前输入的内容包含数字之外的字符，就默认输入为空字符
                }
                return source;
            }
        };
        codeLimitInputEditText.setFilters(new InputFilter[]{filter});//可以设置多个InputFilter
    }
}
