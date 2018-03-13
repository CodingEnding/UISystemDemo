package com.codingending.uisystemdemo.bean;

/**
 * 多状态布局的标题项
 * Created by CodingEnding on 2018/3/13.
 */
public class TitleBean extends BaseMultiBean{
    private String title;

    public TitleBean(String title) {
        this.title = title;
        this.type=TYPE_TITLE;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
