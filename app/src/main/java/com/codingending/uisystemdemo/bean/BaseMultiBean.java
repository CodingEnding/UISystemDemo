package com.codingending.uisystemdemo.bean;

/**
 * 多状态布局的Item基类
 * 抽象类
 * Created by CodingEnding on 2018/3/13.
 */
public abstract class BaseMultiBean {
    public static final int TYPE_TITLE=0;//标题项
    public static final int TYPE_ITEM=1;//内容项
    protected int type;//类型

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
