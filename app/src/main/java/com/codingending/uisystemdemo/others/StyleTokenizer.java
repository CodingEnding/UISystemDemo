package com.codingending.uisystemdemo.others;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.widget.MultiAutoCompleteTextView;

/**
 * 自定义MultiAutoCompleteTextView的分隔符
 * 分隔符通过构造方法指定
 * Created by CodingEnding on 2018/2/12.
 */

public class StyleTokenizer implements MultiAutoCompleteTextView.Tokenizer{
    private char myTokenizer;//分隔符

    public StyleTokenizer(char myTokenizer) {
        this.myTokenizer = myTokenizer;
    }

    public int findTokenStart(CharSequence text, int cursor) {
        int i = cursor;

        while (i > 0 && text.charAt(i - 1) != myTokenizer) {
            i--;
        }
        while (i < cursor && text.charAt(i) == ' ') {
            i++;
        }

        return i;
    }

    public int findTokenEnd(CharSequence text, int cursor) {
        int i = cursor;
        int len = text.length();

        while (i < len) {
            if (text.charAt(i) == myTokenizer) {
                return i;
            } else {
                i++;
            }
        }

        return len;
    }

    public CharSequence terminateToken(CharSequence text) {
        int i = text.length();

        while (i > 0 && text.charAt(i - 1) == ' ') {
            i--;
        }

        if (i > 0 && text.charAt(i - 1) == myTokenizer) {
            return text;
        } else {
            if (text instanceof Spanned) {
                SpannableString sp = new SpannableString(text + String.valueOf(myTokenizer));
                TextUtils.copySpansFrom((Spanned) text, 0, text.length(),
                        Object.class, sp, 0);
                return sp;
            } else {
                return text + String.valueOf(myTokenizer);
            }
        }
    }
}
