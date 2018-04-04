package com.codingending.uisystemdemo.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codingending.uisystemdemo.R;
import com.codingending.uisystemdemo.bean.BaseMultiBean;
import com.codingending.uisystemdemo.bean.ItemBean;
import com.codingending.uisystemdemo.bean.TitleBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 具有列表头/列表尾/多布局
 * Created by CodingEnding on 2018/3/31.
 */
public class StyleRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    public static final int TYPE_TITLE=0;//标题形式的列表项
    public static final int TYPE_CONTENT=1;//内容形式的列表项
    public static final int TYPE_HEADER=2;//列表头
    public static final int TYPE_FOOTER=3;//列表尾

    private View headerView;//头部View
    private View footerView;//尾部View
    private int headerCount;//头部View数量（0或1）
    private List<BaseMultiBean> dataList;//数据源

    private LayoutInflater inflater;//布局解析器

    public StyleRecyclerViewAdapter(List<BaseMultiBean> dataList) {
        this.dataList = dataList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(inflater==null){//只初始化一次
            inflater=LayoutInflater.from(parent.getContext());
        }
        switch (viewType){//根据布局类型创建合适的ViewHolder
            case TYPE_HEADER:
                return new HeaderFooterViewHolder(headerView);
            case TYPE_FOOTER:
                return new HeaderFooterViewHolder(footerView);
            case TYPE_TITLE:
                View titleView=inflater.inflate(R.layout.recycler_view_multi_title,parent,false);
                return new TitleViewHolder(titleView);
            case TYPE_CONTENT:
                View contentView=inflater.inflate(R.layout.recycler_view_multi_item,parent,false);
                return new ContentViewHolder(contentView);
            default:break;
        }
        return null;
    }

    //在这个方法中实现Item的局部更新（比如只更新ViewHolder中的一个View）
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List<Object> payloads) {
        if(payloads.isEmpty()){//如果payloads为空，就调用默认实现
            super.onBindViewHolder(holder,position,payloads);
        }
        else{//在payloads不为空的时候实现ViewHolder中的部分更新
            if("TYPE_CONTENT".equals(payloads.get(0))){
                ContentViewHolder contentViewHolder= (ContentViewHolder) holder;
                ItemBean itemBean= (ItemBean) getItem(position);
                contentViewHolder.itemContentView.setText(itemBean.getContent());
            }
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType=getItemViewType(position);
        if(viewType==TYPE_TITLE){//为标题形式的列表项绑定数据
            TitleBean titleBean= (TitleBean) getItem(position);
            TitleViewHolder titleViewHolder= (TitleViewHolder) holder;
            titleViewHolder.titleView.setText(titleBean.getTitle());
        }
        if(viewType==TYPE_CONTENT){//为内容形式的列表项绑定数据
            ItemBean itemBean= (ItemBean) getItem(position);
            ContentViewHolder contentViewHolder= (ContentViewHolder) holder;
            contentViewHolder.itemImageView.setImageResource(itemBean.getImageRes());
            contentViewHolder.itemContentView.setText(itemBean.getContent());
        }
    }

    @Override
    public int getItemCount() {//计算列表项的真正数量
        int count=dataList.size();
        if(headerView!=null){
            count++;
        }
        if(footerView!=null){
            count++;
        }
        return count;//返回列表头、列表尾和列表项的总数量
    }

    @Override
    public int getItemViewType(int position) {//返回列表项的类型
        if(headerView!=null&&position==0){
            return TYPE_HEADER;
        }
        if(footerView!=null&&position==headerCount+dataList.size()){
            return TYPE_FOOTER;
        }
        BaseMultiBean baseMultiBean=dataList.get(position-headerCount);
        return baseMultiBean.getType();
    }

    //设置列表头
    public void setHeaderView(View headerView){
        this.headerView=headerView;
        headerCount=1;
    }

    //移除列表头
    public void removeHeaderView(){
        headerView=null;
        headerCount=0;
    }

    //设置列表尾
    public void setFooterView(View footerView){
        this.footerView=footerView;
    }

    //移除列表尾
    public void removeFooterView(){
        footerView=null;
    }

    //获取数据源中的真实数据（避免HeaderView的影响）
    private BaseMultiBean getItem(int position){
        return dataList.get(position-headerCount);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager=recyclerView.getLayoutManager();

        //针对网格型的布局管理器进行额外处理，避免头/尾布局显示异常
        if(layoutManager instanceof GridLayoutManager){
            final GridLayoutManager gridLayoutManager= (GridLayoutManager) layoutManager;
            final GridLayoutManager.SpanSizeLookup spanSizeLookup=gridLayoutManager
                    .getSpanSizeLookup();//保存旧的布局方式
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int viewType=getItemViewType(position);
                    if(viewType==TYPE_HEADER||viewType==TYPE_FOOTER){
                        return gridLayoutManager.getSpanCount();//返回当前网格的列数（即让列表头/尾占据一行）
                    }
                    return spanSizeLookup.getSpanSize(position);
                }
            });
        }
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        int viewType=holder.getItemViewType();
        if(viewType==TYPE_HEADER||viewType==TYPE_FOOTER){
            ViewGroup.LayoutParams layoutParams=holder.itemView.getLayoutParams();

            //针对瀑布流式的布局管理器进行额外处理，避免头/尾布局显示异常
            if(layoutParams instanceof StaggeredGridLayoutManager.LayoutParams){
                StaggeredGridLayoutManager.LayoutParams staggerLayoutParams=
                        (StaggeredGridLayoutManager.LayoutParams) layoutParams;
                staggerLayoutParams.setFullSpan(true);//列表头/尾占据一行
            }
        }
    }

    //内容Item的ViewHolder
    static class ContentViewHolder extends RecyclerView.ViewHolder{
        private TextView itemContentView;
        private ImageView itemImageView;
        public ContentViewHolder(View itemView) {
            super(itemView);
            itemContentView=itemView.findViewById(R.id.item_content);
            itemImageView=itemView.findViewById(R.id.item_image);
        }
    }

    //标题Item的ViewHolder
    static class TitleViewHolder extends RecyclerView.ViewHolder{
        private TextView titleView;
        public TitleViewHolder(View itemView) {
            super(itemView);
            titleView=itemView.findViewById(R.id.item_title);
        }
    }

    //头部和尾部布局的ViewHolder
    static class HeaderFooterViewHolder extends RecyclerView.ViewHolder{
        public HeaderFooterViewHolder(View itemView) {
            super(itemView);
        }
    }

}
