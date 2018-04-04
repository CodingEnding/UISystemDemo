package com.codingending.uisystemdemo.list;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.codingending.uisystemdemo.R;
import com.codingending.uisystemdemo.adapter.RecyclerViewAdapter;
import com.codingending.uisystemdemo.adapter.StyleRecyclerViewAdapter;
import com.codingending.uisystemdemo.base.BaseWidgetActivity;
import com.codingending.uisystemdemo.bean.BaseMultiBean;
import com.codingending.uisystemdemo.bean.ItemBean;
import com.codingending.uisystemdemo.bean.TitleBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 演示RecyclerView
 * Created by CodingEnding on 2018/3/30.
 */
public class RecyclerViewActivity extends BaseWidgetActivity {
    public static final String TAG="RecyclerViewActivity";
    public static final int TYPE_LIST=1;//ListView形式
    public static final int TYPE_GRID=2;//GridView形式
    public static final int TYPE_STAGGER=3;//瀑布流形式

    private RecyclerView recyclerView;
    private RecyclerView styleRecyclerView;//具有多布局的RecyclerView
    private Button listBtn;//切换为线性的RecyclerView
    private Button gridBtn;//切换为网格型的RecyclerView
    private Button staggerBtn;//切换为瀑布流型的RecyclerView
    private Button normalBtn;//切换到普通布局的RecyclerView
    private Button viewTypeBtn;//切换为多布局的RecyclerView
    private Button addItemBtn;//添加Item
    private Button removeItemBtn;//删除Item
    private Button scrollBtn;//跳转到指定位置
    private Button partUpdateBtn;//局部更新

    private View headerView;//列表头
    private View footerView;//列表尾

    private List<String> dataList;//普通RecyclerView的数据源
    private RecyclerViewAdapter recyclerViewAdapter;//普通RecyclerView的适配器
    private List<BaseMultiBean> multiDataList;//多布局RecyclerView的数据源
    private StyleRecyclerViewAdapter styleRecyclerViewAdapter;//多布局的RecyclerView适配器

    private LinearLayoutManager linearLayoutManager;//线性布局管理器
    private GridLayoutManager gridLayoutManager;//网格型布局管理器
    private StaggeredGridLayoutManager staggeredGridLayoutManager;//瀑布流式的布局管理器
    private RecyclerView.LayoutManager currentLayoutManager;//当前的布局管理器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        initViews();
        initLayoutManager();//初始化LayoutManager
        initRecyclerView();//初始化RecyclerView
    }

    //初始化LayoutManager
    private void initLayoutManager(){
        linearLayoutManager=new LinearLayoutManager(RecyclerViewActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        gridLayoutManager=new GridLayoutManager(RecyclerViewActivity.this,3);
        staggeredGridLayoutManager=new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL);
        currentLayoutManager=linearLayoutManager;
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView(){
        //初始化普通的RecyclerView
//        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);//设置为纵向排列
        recyclerView.setLayoutManager(linearLayoutManager);//设置布局管理器
        dataList=createDataList();//数据源
        recyclerViewAdapter=new RecyclerViewAdapter(dataList);
        recyclerView.setAdapter(recyclerViewAdapter);//设置适配器
        recyclerView.setItemAnimator(new DefaultItemAnimator());//设置默认的动画效果
        recyclerViewAdapter.setItemClickListener(new RecyclerViewAdapter.ItemClickListener() {
            @Override
            public void onItemClick(String clickItem) {
                Toast.makeText(RecyclerViewActivity.this,"点击："+clickItem,
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onItemLongClick(String clickItem) {
                Toast.makeText(RecyclerViewActivity.this,"长按："+clickItem,
                        Toast.LENGTH_SHORT).show();
            }
        });
        //为普通布局的RecyclerView设置滑动监听器
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                //滑动状态发生改变
                //newState的可能值：[SCROLL_STATE_IDLE|SCROLL_STATE_DRAGGING|SCROLL_STATE_SETTLING]
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //滑动过程中将反复触发
                //dx : 水平滚动距离
                //dy : 垂直滚动距离
                Log.i(TAG,"dx:"+dx+" dy:"+dy);//如果RecyclerView是垂直布局（只能上下滑动），则dx始终为0。
            }
        });


        //初始化列表头和列表尾
        headerView=LayoutInflater.from(this).inflate(R.layout.recycler_view_header,null);
        footerView=LayoutInflater.from(this).inflate(R.layout.recycler_view_footer,null);

        //初始化多布局的RecyclerView
        multiDataList=new ArrayList<>();
        multiDataList.add(new TitleBean("第一个区域"));
        multiDataList.add(new ItemBean(R.mipmap.ic_launcher,"《小王子》"));
        multiDataList.add(new ItemBean(R.mipmap.ic_launcher,"《狮子王》"));
        multiDataList.add(new TitleBean("第二个区域"));
        multiDataList.add(new ItemBean(R.mipmap.ic_launcher,"《资本论》"));
        multiDataList.add(new ItemBean(R.mipmap.ic_launcher,"《三体》"));
        multiDataList.add(new ItemBean(R.mipmap.ic_launcher,"《孤独的进化者》"));
        styleRecyclerViewAdapter=new StyleRecyclerViewAdapter(multiDataList);

        //设置列表头和列表尾
        styleRecyclerViewAdapter.setHeaderView(headerView);
        styleRecyclerViewAdapter.setFooterView(footerView);

        //设置布局管理器和适配器
        LinearLayoutManager styleLayoutManager=new LinearLayoutManager(this);
        styleRecyclerView.setLayoutManager(styleLayoutManager);
        styleRecyclerView.setAdapter(styleRecyclerViewAdapter);
    }

    //生成随机数据
    private List<String> createDataList(){
        List<String> list=new ArrayList<>();
        String[] rootArray={"Java","Android","Swift","Python","Ruby"};
        for(int i=0;i<60;i++){
            list.add(rootArray[i%rootArray.length]+i);
        }
        return list;
    }

    @Override
    protected void initViews() {
        recyclerView=findViewById(R.id.recycler_view);
        styleRecyclerView=findViewById(R.id.recycler_view_style);
        listBtn=findViewById(R.id.btn_list);
        gridBtn=findViewById(R.id.btn_grid);
        staggerBtn=findViewById(R.id.btn_stagger);
        normalBtn=findViewById(R.id.btn_normal);
        viewTypeBtn=findViewById(R.id.btn_view_type);
        addItemBtn=findViewById(R.id.btn_item_add);
        removeItemBtn=findViewById(R.id.btn_item_remove);
        scrollBtn=findViewById(R.id.btn_scroll);
        partUpdateBtn=findViewById(R.id.btn_part_update);

        listBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//线性的RecyclerView（类似ListView）
                if(!checkIsNormalLayout()){
                    showToast("请先切换到默认布局");
                    return;
                }
                currentLayoutManager=linearLayoutManager;
                recyclerView.setLayoutManager(linearLayoutManager);
            }
        });
        gridBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//网格型的RecyclerView（类似GridView）
                if(!checkIsNormalLayout()){
                    showToast("请先切换到默认布局");
                    return;
                }
                currentLayoutManager=gridLayoutManager;
                recyclerView.setLayoutManager(gridLayoutManager);
            }
        });
        staggerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//瀑布流型的RecyclerView
                if(!checkIsNormalLayout()){
                    showToast("请先切换到默认布局");
                    return;
                }
                currentLayoutManager=staggeredGridLayoutManager;
                recyclerView.setLayoutManager(staggeredGridLayoutManager);
            }
        });
        addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkIsNormalLayout()){
                    showToast("请先切换到默认布局");
                    return;
                }
                dataList.add(1,"新的一项"+dataList.size());//在指定位置插入新数据
                recyclerViewAdapter.notifyItemInserted(1);//局部刷新
            }
        });
        removeItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkIsNormalLayout()){
                    showToast("请先切换到默认布局");
                    return;
                }
                dataList.remove(2);//移除第三项
                recyclerViewAdapter.notifyItemRemoved(2);
            }
        });
        normalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNormalRecyclerView();//显示普通的RecyclerView
            }
        });
        viewTypeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showStyleRecyclerView(); //显示多布局的RecyclerView
            }
        });
        partUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//局部更新
                if(checkIsNormalLayout()){
                    showToast("请先切换到多布局");
                    return;
                }
                ItemBean itemBean= (ItemBean) multiDataList.get(2);
                itemBean.setContent("《通过局部更新获得的内容》");
                multiDataList.set(2,itemBean);
                //这里的payload用于标识要更新的列表项类型
                styleRecyclerViewAdapter.notifyItemChanged(3,"TYPE_CONTENT");
            }
        });
        scrollBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkIsNormalLayout()){
                    showToast("请先切换到默认布局");
                    return;
                }
                if(getRecyclerViewType()==TYPE_LIST){
                    linearLayoutManager.scrollToPosition(18);//跳转到指定位置
//                linearLayoutManager.scrollToPositionWithOffset(18,50);//带偏移量跳转到指定位置
//                linearLayoutManager.smoothScrollToPosition(recyclerView,null,18);//平滑移动
                }else if(getRecyclerViewType()==TYPE_GRID){
                    gridLayoutManager.scrollToPosition(18);
//                    gridLayoutManager.scrollToPositionWithOffset(18,50);
//                    gridLayoutManager.smoothScrollToPosition(recyclerView,null,18);
                }else{
                    staggeredGridLayoutManager.scrollToPosition(18);
//                    staggeredGridLayoutManager.scrollToPositionWithOffset(18,50);
//                    staggeredGridLayoutManager.smoothScrollToPosition(recyclerView,null,18);
                }
            }
        });
    }

    //显示多布局的RecyclerView
    private void showStyleRecyclerView(){
        styleRecyclerView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    //显示普通的RecyclerView
    private void showNormalRecyclerView(){
        styleRecyclerView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    //获取当前LayoutManager的形式
    private int getRecyclerViewType(){
        if(currentLayoutManager instanceof StaggeredGridLayoutManager){
            return TYPE_STAGGER;
        }
        if(currentLayoutManager instanceof GridLayoutManager){
            return TYPE_GRID;
        }
        return TYPE_LIST;
    }

    //检查当前RecyclerView是否为普通布局（因为一些演示功能只为普通布局的RecyclerView进行了实现）
    private boolean checkIsNormalLayout(){
        if(recyclerView.getVisibility()==View.VISIBLE){
            return true;
        }
        return false;
    }

}
