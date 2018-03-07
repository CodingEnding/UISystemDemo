package com.codingending.uisystemdemo.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.codingending.uisystemdemo.R;
import com.codingending.uisystemdemo.bean.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于AutoCompleteTextView自定义Adapter
 * Created by CodingEnding on 2018/2/12.
 */
public class StyleFilterAdapter extends BaseAdapter implements Filterable{
    private Context context;
    private List<Book> dataList;//数据源
    private List<Book> originDataList;//保存原始数据
    private BookFilter bookFilter;//过滤器
    private final Object lock=new Object();//同步锁（为了在进行数据复制时保证线程安全）

    public StyleFilterAdapter(Context context, List<Book> dataList) {
        this.context = context;
        this.dataList = dataList;
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
        ViewHolder holder=null;
        if(convertView==null){
            LayoutInflater inflater=LayoutInflater.from(context);
            convertView=inflater.inflate(R.layout.auto_complete_view_item,parent,false);
            holder=new ViewHolder();
            holder.bookImage=convertView.findViewById(R.id.book_image);
            holder.bookNameView=convertView.findViewById(R.id.book_name);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }

        Book book=dataList.get(position);
        holder.bookImage.setImageResource(book.getImageRes());
        holder.bookNameView.setText(book.getName());

        return convertView;
    }

    static class ViewHolder{//View复用机制
        ImageView bookImage;
        TextView bookNameView;
    }

    @Override
    public Filter getFilter() {
        if(bookFilter==null){
            bookFilter=new BookFilter();
        }
        return bookFilter;
    }

    //用于筛选Book列表的过滤器
    class BookFilter extends Filter{
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {//指定过滤逻辑
            FilterResults results=new FilterResults();

            if(originDataList==null){//为原始数据列表赋值
                synchronized (lock){//添加同步锁
                    originDataList=new ArrayList<>(dataList);
                }
            }

            if(TextUtils.isEmpty(constraint)){//如果筛选条件为空直接返回原始数据列表的副本
                synchronized (lock){//添加同步锁
                    List<Book> tempList=new ArrayList<>(originDataList);
                    results.values=tempList;
                    results.count=tempList.size();
                }
            }else{
                List<Book> tempList=new ArrayList<>(originDataList.size());//保存已筛选的数据
                for(Book book:originDataList){
                    if(book.getName().contains(constraint)){//保存包含关键字的数据
                        tempList.add(book);
                    }
                }
                results.values=tempList;
                results.count=tempList.size();
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {//将过滤结果反馈给Adapter
            dataList= (List<Book>) results.values;
            if(results.count>0){
                notifyDataSetChanged();//刷新数据
            }else{
                notifyDataSetInvalidated();//重绘控件
            }
        }
    }
}
