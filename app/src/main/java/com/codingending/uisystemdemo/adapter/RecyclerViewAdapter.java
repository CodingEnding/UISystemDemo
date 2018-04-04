package com.codingending.uisystemdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codingending.uisystemdemo.R;

import java.util.List;

/**
 * RecyclerView的适配器
 * Created by CodingEnding on 2018/3/30.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private List<String> dataList;//数据源
    private LayoutInflater inflater;//布局解析器
    private ItemClickListener itemClickListener;//列表项点击监听器

    public RecyclerViewAdapter(List<String> dataList){
        this.dataList = dataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        if(inflater==null){//避免多次初始化
            inflater=LayoutInflater.from(parent.getContext());
        }
        View itemView=inflater.inflate(R.layout.recycler_view_item,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        final String itemContent=dataList.get(position);
        holder.textView.setText(itemContent);

        //为列表项设置点击监听
        if(itemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClick(itemContent);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    itemClickListener.onItemLongClick(itemContent);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    //为RecyclerView设置点击监听器
    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    //自定义ViewHolder
    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.text_view_recycler);
        }
    }

    //自定义的点击监听器接口
    public interface ItemClickListener{
        void onItemClick(String clickItem);//单击事件
        void onItemLongClick(String clickItem);//长按事件
    }
}
