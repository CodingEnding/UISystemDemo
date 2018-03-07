package com.codingending.uisystemdemo.bean;

/**
 * 自定义实体类
 * Created by CodingEnding on 2018/2/10.
 */

public class Book {
    private String name;
    private int imageRes;//图片资源

    public Book(String name, int imageRes) {
        this.name = name;
        this.imageRes = imageRes;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }
}
