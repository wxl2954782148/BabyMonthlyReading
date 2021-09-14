package com.wang.babymonthlyreading.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wang.babymonthlyreading.R;
import com.wang.babymonthlyreading.adapter.SearchHintAdapter;
import com.wang.babymonthlyreading.data.TestData;

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
        cancelBtn.setOnClickListener(v -> {
            finish();
        });
        // -->搜索历史相关
        RecyclerView searchHistoryRecycle = findViewById(R.id.recycle_search_history);
        searchHistoryRecycle.setLayoutManager(new GridLayoutManager(this, 3));
        searchHistoryAdapter = new SearchHintAdapter(TestData.getSearchHistoryData());
        searchHistoryRecycle.setAdapter(searchHistoryAdapter);
        searchHistoryRecycle.addItemDecoration(new SearchHintAdapter.SpaceItemDecoration(20));
        //清空搜索历史
        ImageButton clearAllImgB = findViewById(R.id.imgb_clear_all);
        clearAllImgB.setOnClickListener(v -> {
            searchHistoryAdapter.removeAll();
        });

        // -->热门搜索相关
        RecyclerView hotSearchRecycle = findViewById(R.id.recycle_hot_search);
        hotSearchRecycle.setLayoutManager(new GridLayoutManager(this, 3));
        SearchHintAdapter hotSearchAdapter = new SearchHintAdapter(TestData.getHotSearchData());
        hotSearchRecycle.setAdapter(hotSearchAdapter);
        hotSearchRecycle.addItemDecoration(new SearchHintAdapter.SpaceItemDecoration(20));
    }


    /**
     * 搜索框的监听事件
     * 1. 添加到搜索历史
     * 2. 跳转到搜索结果页面
     */
    SearchView.OnQueryTextListener listener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            SearchResultActivity.startSearchResultActivity(SearchActivity.this, query);
            searchHistoryAdapter.addFirst(query);
            finish();
            return true;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };


    /**
     * 修改SearchView的样式
     * 1. EditText中的字体大小
     * 2. 取消下划线
     *
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

    /**
     * 从其它Activity启动SearchActivity
     *
     * @param context
     */
    public static void startSearchActivity(Context context) {
        Intent intent = new Intent(context, SearchActivity.class);
        context.startActivity(intent);
    }
}