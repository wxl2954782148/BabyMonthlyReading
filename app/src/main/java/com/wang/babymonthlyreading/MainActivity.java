package com.wang.babymonthlyreading;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.wang.babymonthlyreading.adapter.BannerPagerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        // --> 顶部选项菜单相关
        Toolbar mainBar = findViewById(R.id.tbar_main);
        setSupportActionBar(mainBar);
        //取消原来的ActionBar的标题
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        // -->轮播图相关
        ViewPager bannerPager = findViewById(R.id.vp_banner);
        bannerPager.setAdapter(new BannerPagerAdapter(getBannerData()));

    }



    /**
     * 菜单项的被选中事件 TODO
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.title_meun,menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.linear_main_search:
                SearchActivity.startSearchActivity(this);
            break;
            default:
        }
    }

    public static void StartMainActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    /**
     * 获取轮播图相关信息
     * @return
     */
    private List<BannerPagerAdapter.BannerItemInfo> getBannerData() {
        List<BannerPagerAdapter.BannerItemInfo> bannerItemInfoList = new ArrayList<>();
        int[] itemIdArr = getResources().getIntArray(R.array.banner_item_id);
        TypedArray typedArray = getResources().obtainTypedArray(R.array.banner_drawable_list);
        if (itemIdArr.length != typedArray.length())
            throw new ArrayIndexOutOfBoundsException("Array resource \"banner_item_id\" " +
                    "and \"banner_drawable_list\" of length is not equal");

        for (int i = 0; i < typedArray.length(); i++) {
            BannerPagerAdapter.BannerItemInfo itemInfo =
                    new BannerPagerAdapter.BannerItemInfo(itemIdArr[i], typedArray.getDrawable(i));
            bannerItemInfoList.add(itemInfo);
        }
        typedArray.recycle();
        return bannerItemInfoList;
    }

}