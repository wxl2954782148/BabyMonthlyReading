package com.wang.babymonthlyreading.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wang.babymonthlyreading.R;
import com.wang.babymonthlyreading.adapter.SearchResultAdapter;
import com.wang.babymonthlyreading.customview.ShoppingCartButton;
import com.wang.babymonthlyreading.data.TestData;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 展示书籍搜索页的搜索结果
 */
public class SearchResultActivity extends AppCompatActivity {

    private static final String QUERY_KEY = "QUERY_KEY";
    private MenuItem shoppingCart;
    /**
     * 购物车信息
     * key:商品id
     * value:商品数量
     */
    private final Map<Integer, Integer> cartMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        initView();
    }

    private void initView() {
        Toolbar toolbar = findViewById(R.id.tbar_search_result);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(v -> {
            finish();
        });

        RecyclerView recyclerView = findViewById(R.id.recycle_product_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        SearchResultAdapter searchResultAdapter = new SearchResultAdapter(TestData.getBookInfoList(this));
        searchResultAdapter.setCartChangeListener((bookId, count) -> {
            invalidateOptionsMenu();
            Integer value = cartMap.get(bookId);
            if (value != null) {
                value += count;
                cartMap.put(bookId, value);
            } else {
                cartMap.put(bookId, count);
            }
        });
        recyclerView.setAdapter(searchResultAdapter);
    }

    /**
     * 获取搜索页面提供的搜索信息，用于请求服务器相关商品列表
     *
     * @return
     */
    private String getQueryValue() {
        return getIntent().getStringExtra(QUERY_KEY);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        shoppingCart = menu.add(getString(R.string.shopping_cart));
        shoppingCart.setActionView(new ShoppingCartButton(this))
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(shoppingCart.getItemId());
        ShoppingCartButton cartButton = (ShoppingCartButton) item.getActionView();
        cartButton.setShoppingMsgText(getShoppingCartSum());
        return super.onPrepareOptionsMenu(menu);
    }

    private int getShoppingCartSum() {
        int sum = 0;
        for (Map.Entry<Integer, Integer> entry : cartMap.entrySet()) {
            sum += entry.getValue();
        }
        return sum;
    }

    public static void startSearchResultActivity(Context context, String query) {
        Intent intent = new Intent(context, SearchResultActivity.class);
        intent.putExtra(QUERY_KEY, query);
        context.startActivity(intent);
    }
}