package com.codingending.uisystemdemo.bean;

/**
 * 多状态布局的内容项
 * Created by CodingEnding on 2018/3/13.
 */
public class ItemBean extends BaseMultiBean{
    private int imageRes;//图片资源
    private String content;//内容

    public ItemBean(int imageRes, String content) {
        this.imageRes = imageRes;
        this.content = content;
        this.type=TYPE_ITEM;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
