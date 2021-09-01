package com.wang.babymonthlyreading;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.wang.babymonthlyreading.adapter.SearchHintAdapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SearchActivity extends AppCompatActivity {

    private SearchHintAdapter searchHistoryAdapter;
    private SearchView bookSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
    }

    private void initView() {
        // -->搜索框相关
        bookSearch = findViewById(R.id.search_book);
        updateSearchStyle(bookSearch);
        bookSearch.setOnQueryTextListener(listener);
        // -->取消按钮相关
        Button cancelBtn = findViewById(R.id.btn_cancel);
        cancelBtn.setOnClickListener(v->{
            finish();
        });
        // -->搜索历史相关
        RecyclerView searchHistoryRecycle = findViewById(R.id.recycle_search_history);
        searchHistoryRecycle.setLayoutManager(new GridLayoutManager(this, 3));
        searchHistoryAdapter = new SearchHintAdapter(getSearchHistoryData());
        searchHistoryRecycle.setAdapter(searchHistoryAdapter);
        searchHistoryRecycle.addItemDecoration(new SearchHintAdapter.SpaceItemDecoration(20));
        //清空搜索历史
        ImageButton clearAllImgB = findViewById(R.id.imgb_clear_all);
        clearAllImgB.setOnClickListener(v->{
            searchHistoryAdapter.removeAll();
        });

        // -->热门搜索相关
        RecyclerView hotSearchRecycle = findViewById(R.id.recycle_hot_search);
        hotSearchRecycle.setLayoutManager(new GridLayoutManager(this, 3));
        hotSearchRecycle.setAdapter(new SearchHintAdapter(getHotSearchData()));
        hotSearchRecycle.addItemDecoration(new SearchHintAdapter.SpaceItemDecoration(20));
    }



    /**
     * 搜索框的监听事件
     * 1. 添加到搜索历史
     * 2. 跳转到指定页面 TODO
     */
    SearchView.OnQueryTextListener listener= new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            searchHistoryAdapter.addItem(query);
            return true;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };

    /**
     * 获取搜索历史数据
     * @return
     */
    private List<String> getSearchHistoryData() {
        List<String> searchHistory = new ArrayList<>();
        searchHistory.add("少儿启蒙");
        searchHistory.add("画图");
        return searchHistory;
    }

    /**
     * 获取热门搜索数据
     * @return
     */
    private List<String> getHotSearchData() {
        List<String> hotSearch = new ArrayList<>();
        hotSearch.add("逻辑思维");
        hotSearch.add("启蒙");
        hotSearch.add("算术");
        hotSearch.add("认知");
        hotSearch.add("心理学");
        return hotSearch;
    }

    /**
     * 修改SearchView的样式
     * 1. EditText中的字体大小
     * 2. 取消下划线
     * @param searchView
     */
    private void updateSearchStyle(SearchView searchView) {
        if (searchView == null)
            return;
        Resources resources = searchView.getContext().getResources();
        int identifier = resources.getIdentifier("android:id/search_src_text", null, null);
        TextView textView = searchView.findViewById(identifier);
        textView.setTextSize(18);

        int plateId = resources.getIdentifier("android:id/search_plate", null, null);
        LinearLayout layout = searchView.findViewById(plateId);
        layout.setBackground(null);
    }

    public static void startSearchActivity(Context context) {
        Intent intent = new Intent(context, SearchActivity.class);
        context.startActivity(intent);
    }
}