package com.codingending.uisystemdemo.list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.codingending.uisystemdemo.MainActivity;
import com.codingending.uisystemdemo.R;
import com.codingending.uisystemdemo.adapter.StyleListViewAdapter;
import com.codingending.uisystemdemo.base.BaseWidgetActivity;
import com.codingending.uisystemdemo.bean.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 演示ListView
 * Created by CodingEnding on 2018/3/9.
 */

public class ListViewActivity extends BaseWidgetActivity {
    public static final String TAG="ListViewActivity";
    private ListView normalListView;
    private ListView customListView;//自定义布局的ListView
    private ListView headerFooterListView;//带列表头/尾的ListView
    private Button normalBtn;
    private Button customBtn;
    private Button headerFooterBtn;
    private Button addItemBtn;
    private Button scrollBtn;
    private Button smoothScrollBtn;

    private List<Book> dataList;//List形式的数据源
    private StyleListViewAdapter styleAdapter;//自定义的适配器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        initViews();
        initListView();
    }

    /**
     * 初始化ListView
     */
    private void initListView(){
        //Item点击监听器
        AdapterView.OnItemClickListener itemClickListener=new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG,"当前位置:"+position);
                String msg= (String) parent.getAdapter().getItem(position);
                Toast.makeText(ListViewActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
        };
        //Item长按监听器
        AdapterView.OnItemLongClickListener itemLongClickListener=new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListViewActivity.this,"发生长按事件",Toast.LENGTH_SHORT).show();
                return true;
            }
        };

        //初始化普通布局的ListView
        String[] dataArray=getDataArray();//1.建立数据源
        ArrayAdapter<String> normalAdapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,dataArray);//2.建立适配器
        normalListView.setAdapter(normalAdapter);//3.设置适配器
        normalListView.setOnItemClickListener(itemClickListener);
        normalListView.setOnItemLongClickListener(itemLongClickListener);
        View view=findViewById(R.id.empty_view);//获取EmptyView
        normalListView.setEmptyView(view);//为ListView设置空布局
        normalListView.setOnScrollListener(new AbsListView.OnScrollListener() {//监听ListView的滑动状态
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                //滑动状态发生变化时触发
                //scrollState的可能值：[SCROLL_STATE_IDLE|SCROLL_STATE_TOUCH_SCROLL|SCROLL_STATE_FLING]
            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                //滑动完成时触发
                //firstVisibleItem：第一个可见项的索引值
                //visibleItemCount：可见项的个数
                //totalItemCount：列表项的总数
            }
        });

        //为ListView添加列表头/尾
        String[] headerFooterArray=new String[]{"coding","ending","CodingEnding","Github","coder","Android"};
        ArrayAdapter<String> headerFooterAdapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,headerFooterArray);
        LayoutInflater inflater=LayoutInflater.from(this);
        View headerView=inflater.inflate(R.layout.listview_header,headerFooterListView,false);//实例化头布局
        View footerView=inflater.inflate(R.layout.listview_footer,headerFooterListView,false);//实例化尾布局
        headerFooterListView.addHeaderView(headerView,"HeaderView",true);//设置列表头可选中
        headerFooterListView.addFooterView(footerView,"FooterView",false);//设置列表尾不可选中
        headerFooterListView.setAdapter(headerFooterAdapter);
        headerFooterListView.setOnItemClickListener(itemClickListener);
        headerFooterListView.setOnItemLongClickListener(itemLongClickListener);

        //初始化自定义布局的ListView
        dataList=new ArrayList<>();
        dataList.add(new Book("《小王子》",R.mipmap.ic_launcher));
        dataList.add(new Book("《资本论》",R.mipmap.ic_launcher));
        dataList.add(new Book("《三体》",R.mipmap.ic_launcher));
        styleAdapter=new StyleListViewAdapter(this,dataList);
        customListView.setAdapter(styleAdapter);
        customListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG,"当前位置:"+position);
                Book book= (Book) parent.getAdapter().getItem(position);
                Toast.makeText(ListViewActivity.this,book.getName(),
                        Toast.LENGTH_SHORT).show();
            }
        });
        customListView.setOnItemLongClickListener(itemLongClickListener);
    }

    /**
     * 生成数据源[数组形式]
     */
    private String[] getDataArray(){
        String[] rootArray=new String[]{"coding","ending","CodingEnding","Github","coder","Android"};
        String[] array=new String[30];
        for(int i=0;i<array.length;i++){
            array[i]=rootArray[i%rootArray.length]+i;
        }
        return array;
    }

    @Override
    protected void initViews() {
        normalListView=findViewById(R.id.list_view_normal);
        customListView=findViewById(R.id.list_view_custom);
        headerFooterListView=findViewById(R.id.list_view_footer_header);
        normalBtn=findViewById(R.id.btn_list_view_normal);
        customBtn=findViewById(R.id.btn_list_view_custom);
        headerFooterBtn=findViewById(R.id.btn_list_view_header_footer);
        addItemBtn=findViewById(R.id.btn_list_view_add_item);
        scrollBtn=findViewById(R.id.btn_list_view_scroll);
        smoothScrollBtn=findViewById(R.id.btn_list_view_smooth_scroll);

        normalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                normalListView.setVisibility(View.VISIBLE);
                customListView.setVisibility(View.GONE);
                headerFooterListView.setVisibility(View.GONE);
            }
        });
        customBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customListView.setVisibility(View.VISIBLE);
                normalListView.setVisibility(View.GONE);
                headerFooterListView.setVisibility(View.GONE);
            }
        });
        headerFooterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                headerFooterListView.setVisibility(View.VISIBLE);
                customListView.setVisibility(View.GONE);
                normalListView.setVisibility(View.GONE);
            }
        });
        addItemBtn.setOnClickListener(new View.OnClickListener() {//动态增加列表项
            @Override
            public void onClick(View v) {
                if(customListView.getVisibility()==View.VISIBLE){//仅在自定义布局的ListView可见时执行动态增加
                    dataList.add(new Book("《新的书籍》",R.mipmap.ic_launcher));//为数据源新增数据
                    styleAdapter.notifyDataSetChanged();//通知ListView数据已更新
                }else{
                    Toast.makeText(ListViewActivity.this,"请先切换到自定义布局的ListView",Toast.LENGTH_SHORT).show();
                }
            }
        });
        scrollBtn.setOnClickListener(new View.OnClickListener() {//滚动到指定项
            @Override
            public void onClick(View v) {
                if(normalListView.getVisibility()==View.VISIBLE){
//                    normalListView.clearFocus();//在调用无效的时候可以先调用这个方法
//                    normalListView.setSelectionAfterHeaderView();//让HeaderView成为列表当前的第一个可见项
                    normalListView.setSelection(6);//跳转到指定位置
                }else{
                    Toast.makeText(ListViewActivity.this,"请先切换到默认布局的ListView",Toast.LENGTH_SHORT).show();
                }
            }
        });
        smoothScrollBtn.setOnClickListener(new View.OnClickListener() {//平滑滚动到指定位置
            @Override
            public void onClick(View v) {
                if(normalListView.getVisibility()==View.VISIBLE){
                    normalListView.smoothScrollToPosition(0);//平滑滚动到指定位置
//                    normalListView.smoothScrollByOffset(5);////平滑滚动n个列表项的距离
                }else{
                    Toast.makeText(ListViewActivity.this,"请先切换到默认布局的ListView",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
