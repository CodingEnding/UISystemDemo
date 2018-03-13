package com.codingending.uisystemdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.HeaderViewListAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.codingending.uisystemdemo.R;
import com.codingending.uisystemdemo.bean.Book;

import java.util.List;

/**
 * 自定义ListView的Adapter
 * Created by CodingEnding on 2018/3/9.
 */

public class StyleListViewAdapter extends BaseAdapter{
    private Context context;
    private List<Book> dataList;//数据源
    private LayoutInflater inflater;//布局解析器

    public StyleListViewAdapter(Context context, List<Book> dataList) {
        this.context = context;
        this.dataList = dataList;
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
        if(convertView==null){
            convertView= inflater.inflate(R.layout.listview_custom_item,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.bookImageView=convertView.findViewById(R.id.book_image);
            viewHolder.bookNameView=convertView.findViewById(R.id.book_name);
            convertView.setTag(viewHolder);
        }else{//重复利用已有的View
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.bookImageView.setImageResource(book.getImageRes());
        viewHolder.bookNameView.setText(book.getName());
        return convertView;
    }

    static class ViewHolder{
        ImageView bookImageView;
        TextView bookNameView;
    }
}
