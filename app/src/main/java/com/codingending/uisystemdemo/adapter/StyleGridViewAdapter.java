package com.codingending.uisystemdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codingending.uisystemdemo.R;
import com.codingending.uisystemdemo.bean.Book;

import java.util.List;

import javax.crypto.spec.PSource;

/**
 * 自定义GridView的适配器
 * Created by CodingEnding on 2018/3/11.
 */

public class StyleGridViewAdapter extends BaseAdapter{
    private Context context;
    private List<Book> dataList;//数据源
    private LayoutInflater inflater;//布局解析器

    public StyleGridViewAdapter(Context context,List<Book> dataList) {
        this.dataList = dataList;
        this.context = context;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Book book=dataList.get(position);
        ViewHolder viewHolder;
        if(convertView==null){//只有在无法复用View时再重新实例化列表项布局
            convertView=inflater.inflate(R.layout.gridview_custom_item,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.bookImageView=convertView.findViewById(R.id.book_image);
            viewHolder.bookNameView=convertView.findViewById(R.id.book_name);
            convertView.setTag(viewHolder);//存储ViewHolder
        }else{//复用已有的View
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.bookImageView.setImageResource(book.getImageRes());
        viewHolder.bookNameView.setText(book.getName());
        return convertView;
    }

    /**
     * 用于在GridView中复用View
     */
    static class ViewHolder{
        ImageView bookImageView;
        TextView bookNameView;
    }
}
