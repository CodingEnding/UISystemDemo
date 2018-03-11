package com.codingending.uisystemdemo.list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.codingending.uisystemdemo.R;
import com.codingending.uisystemdemo.adapter.StyleGridViewAdapter;
import com.codingending.uisystemdemo.base.BaseWidgetActivity;
import com.codingending.uisystemdemo.bean.Book;

import java.util.ArrayList;
import java.util.List;


/**
 * 演示GridView
 * Created by CodingEnding on 2018/3/11.
 */
public class GridViewActivity extends BaseWidgetActivity {
    private GridView normalGridView;
    private GridView customGridView;
    private Button normalBtn;
    private Button customBtn;
    private Button selectionBtn;
    private Button smoothScrollBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        initViews();
        initGridView();
    }

    /**
     * 初始化GridView
     */
    private void initGridView(){
        //长按监听器
        AdapterView.OnItemLongClickListener longClickListener=new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(GridViewActivity.this,"发生长按事件",Toast.LENGTH_SHORT).show();
                return true;
            }
        };

        //初始化普通布局的GridView
        String[] normalArray=getDataArray();//new String[]{"coding","ending","Java","Github","coder","Android"};
        ArrayAdapter<String> normalAdapter=new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,normalArray);
        normalGridView.setAdapter(normalAdapter);
        //监听单击事件
        normalGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemMsg= (String) parent.getAdapter().getItem(position);//获取选中对象
                Toast.makeText(GridViewActivity.this,itemMsg,Toast.LENGTH_SHORT).show();
            }
        });
        //监听长按事件
        normalGridView.setOnItemLongClickListener(longClickListener);

        //初始化自定义布局的GridView
        List<Book> dataList=new ArrayList<>();
        dataList.add(new Book("《小王子》",R.mipmap.ic_launcher));
        dataList.add(new Book("《资本论》",R.mipmap.ic_launcher));
        dataList.add(new Book("《三体》",R.mipmap.ic_launcher));
        dataList.add(new Book("《必然》",R.mipmap.ic_launcher));
        dataList.add(new Book("《创客》",R.mipmap.ic_launcher));
        dataList.add(new Book("《狮子王》",R.mipmap.ic_launcher));
        StyleGridViewAdapter styleAdapter=new StyleGridViewAdapter(this,dataList);
        customGridView.setAdapter(styleAdapter);
        customGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book clickBook= (Book) parent.getAdapter().getItem(position);//获取选中对象
                Toast.makeText(GridViewActivity.this,clickBook.getName(),Toast.LENGTH_SHORT).show();
            }
        });
        customGridView.setOnItemLongClickListener(longClickListener);
    }

    /**
     * 生成数据源[数组形式]
     */
    private String[] getDataArray(){
        String[] rootArray=new String[]{"coding","ending","Java","Github","coder","Android"};
        String[] array=new String[60];
        for(int i=0;i<array.length;i++){
            array[i]=rootArray[i%rootArray.length]+i;
        }
        return array;
    }

    @Override
    protected void initViews() {
        normalGridView=findViewById(R.id.grid_view_normal);
        normalBtn=findViewById(R.id.btn_grid_view_normal);
        customGridView=findViewById(R.id.grid_view_custom);
        customBtn=findViewById(R.id.btn_grid_view_custom);
        selectionBtn=findViewById(R.id.btn_grid_view_selection);
        smoothScrollBtn=findViewById(R.id.btn_grid_view_smooth_scroll);

        normalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                normalGridView.setVisibility(View.VISIBLE);
                customGridView.setVisibility(View.GONE);
            }
        });
        customBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                normalGridView.setVisibility(View.GONE);
                customGridView.setVisibility(View.VISIBLE);
            }
        });
        selectionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(normalGridView.getVisibility()==View.VISIBLE){
                    normalGridView.setSelection(35);//跳转到指定位置
                }else{
                    Toast.makeText(GridViewActivity.this,"请先切换到默认布局",Toast.LENGTH_SHORT).show();
                }
            }
        });
        smoothScrollBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(normalGridView.getVisibility()==View.VISIBLE){
                    normalGridView.smoothScrollToPosition(35);//平滑滚动到指定位置
                    normalGridView.smoothScrollByOffset(3);//平滑滚动n行高度的距离
                }else{
                    Toast.makeText(GridViewActivity.this,"请先切换到默认布局",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
