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

/**
 * 自定义Spinner适配器
 * Created by CodingEnding on 2018/2/10.
 */
public class StyleSpinnerAdapter extends BaseAdapter {
    private Context context;
    private List<Book> itemList;

    public StyleSpinnerAdapter(Context context, List<Book> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }
    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if(convertView==null){
            LayoutInflater inflater=LayoutInflater.from(context);
            convertView=inflater.inflate(R.layout.spinner_item,parent,false);
            holder=new ViewHolder();
            holder.bookImage=convertView.findViewById(R.id.book_image);
            holder.bookNameView=convertView.findViewById(R.id.book_name);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }

        Book book=itemList.get(position);
        holder.bookImage.setImageResource(book.getImageRes());
        holder.bookNameView.setText(book.getName());

        return convertView;
    }

    static class ViewHolder{//复用机制
        ImageView bookImage;
        TextView bookNameView;
    }
}