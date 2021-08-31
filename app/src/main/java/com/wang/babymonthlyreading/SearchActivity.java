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
import java.util.List;

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
        //搜索框相关
        bookSearch = findViewById(R.id.search_book);
        updateSearchStyle(bookSearch);
        bookSearch.setOnQueryTextListener(listener);
        //取消按钮相关
        Button cancelBtn = findViewById(R.id.btn_cancel);
        cancelBtn.setOnClickListener(v->{
            finish();
        });
        //搜索历史相关
        RecyclerView searchHistoryRecycle = findViewById(R.id.recycle_search_history);
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        searchHistoryRecycle.setLayoutManager(manager);
        searchHistoryAdapter = new SearchHintAdapter(getSearchHistoryData());
        searchHistoryRecycle.setAdapter(searchHistoryAdapter);
        searchHistoryRecycle.addItemDecoration(new SearchHintAdapter.SpaceItemDecoration(15));
        ImageButton clearAllImgB = findViewById(R.id.imgb_clear_all);
        clearAllImgB.setOnClickListener(v->{
            searchHistoryAdapter.removeAll();
        });
    }

    /**
     * 搜索框的监听事件
     */
    SearchView.OnQueryTextListener listener= new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            searchHistoryAdapter.addItem(query);
            bookSearch.clearFocus();
            return true;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };

    private List<String> getSearchHistoryData() {
        List<String> searchHistory = new ArrayList<>();
        searchHistory.add("少儿启蒙");
        searchHistory.add("画图");
        return searchHistory;
    }

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

    public static void startSearchActivity(Context context) {
        Intent intent = new Intent(context, SearchActivity.class);
        context.startActivity(intent);
    }
}