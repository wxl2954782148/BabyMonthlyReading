package com.wang.babymonthlyreading.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wang.babymonthlyreading.R;
import com.wang.babymonthlyreading.adapter.SubscribeBookAdapter;
import com.wang.babymonthlyreading.data.TestData;

import java.util.Objects;

/**
 * 订阅书单Activity
 * MainActivity点击 CustomBookTipsFragment中的"定制书单按钮跳转制该页面进行书单订阅"
 */
public class CustomBookActivity extends AppCompatActivity {

    private RecyclerView subscribeBookRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_book);
        initView();
    }

    private void initView() {
        Toolbar customBookTb = findViewById(R.id.tbar_custom_book);
        setSupportActionBar(customBookTb);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        subscribeBookRecycler = findViewById(R.id.recycle_subscribe_book);
        SubscribeBookAdapter adapter =
                new SubscribeBookAdapter(TestData.getSubscribeBookList(this));
        subscribeBookRecycler.setLayoutManager(new LinearLayoutManager(this));
        subscribeBookRecycler.setAdapter(adapter);
        subscribeBookRecycler.addItemDecoration(new SubscribeBookAdapter.SpaceItemDecoration(20));
    }

    public static void startCustomBookActivity(Context context) {
        Intent intent = new Intent(context, CustomBookActivity.class);
        context.startActivity(intent);
    }
}