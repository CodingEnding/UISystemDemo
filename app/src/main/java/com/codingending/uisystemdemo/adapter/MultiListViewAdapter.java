package com.codingending.uisystemdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codingending.uisystemdemo.R;
import com.codingending.uisystemdemo.bean.BaseMultiBean;
import com.codingending.uisystemdemo.bean.ItemBean;
import com.codingending.uisystemdemo.bean.TitleBean;

import java.util.List;

/**
 * 实现ListView多状态布局
 * Created by CodingEnding on 2018/3/13.
 */
public class MultiListViewAdapter extends BaseAdapter{
    private Context context;
    private List<BaseMultiBean> dataList;//数据源（含有不同类型的数据）
    private LayoutInflater inflater;//布局解析器

    public MultiListViewAdapter(Context context, List<BaseMultiBean> dataList) {
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
    public int getViewTypeCount() {//返回类型种类数
        return 2;
    }

    @Override
    public int getItemViewType(int position) {//返回当前项的类型
        BaseMultiBean bean=dataList.get(position);
        return bean.getType();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TitleViewHolder titleViewHolder;
        ItemViewHolder itemViewHolder;
        switch(getItemViewType(position)){//根据Item的类型不同，执行相应的操作
            case BaseMultiBean.TYPE_TITLE:
                TitleBean titleBean= (TitleBean) dataList.get(position);
                if(convertView==null){
                    convertView=inflater.inflate(R.layout.listview_multi_title,parent,false);
                    titleViewHolder=new TitleViewHolder();
                    titleViewHolder.titleView=convertView.findViewById(R.id.item_title);
                    convertView.setTag(titleViewHolder);
                }else{
                    titleViewHolder= (TitleViewHolder) convertView.getTag();
                }
                titleViewHolder.titleView.setText(titleBean.getTitle());
                break;
            case BaseMultiBean.TYPE_ITEM:
                ItemBean itemBean= (ItemBean) dataList.get(position);
                if(convertView==null){
                    convertView=inflater.inflate(R.layout.listview_multi_item,parent,false);
                    itemViewHolder=new ItemViewHolder();
                    itemViewHolder.itemImageView=convertView.findViewById(R.id.item_image);
                    itemViewHolder.itemContentView=convertView.findViewById(R.id.item_content);
                    convertView.setTag(itemBean);
                }else{
                    itemViewHolder= (ItemViewHolder) convertView.getTag();
                }
                itemViewHolder.itemImageView.setImageResource(itemBean.getImageRes());
                itemViewHolder.itemContentView.setText(itemBean.getContent());
                break;
            default:break;
        }
        return convertView;
    }

    static class TitleViewHolder{//针对标题项的复用
        TextView titleView;
    }

    static class ItemViewHolder{//针对内容项的复用
        ImageView itemImageView;
        TextView itemContentView;
    }
}
