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
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
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

    private LinearLayout pointLayout;
    private BannerPagerAdapter bannerPagerAdapter;
    /**
     * bannerPager中的item的显示位置，即当前展示的item对应的point的位置
     * 用来设置轮播图指示器的切换状态
     */
    private int bannerRelCurrentItem = 0;

    private ViewPager bannerPager;

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
        bannerPager = findViewById(R.id.vp_banner);
        bannerPagerAdapter = new BannerPagerAdapter(getBannerData());
        bannerPager.setAdapter(bannerPagerAdapter);
        bannerPager.setCurrentItem(0);
        setAutoShuffling();

        // -->轮播图指示器相关
        pointLayout = findViewById(R.id.linear_banner_point);
        addPointView(pointLayout, this);
        setPointWithBanner(bannerPager);
        View childAt = pointLayout.getChildAt(0);
        childAt.setBackgroundResource(R.drawable.banner_point_item_focus);

    }


    /**
     * 菜单项的被选中事件 TODO
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.title_meun, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    public void onClick(View view) {
        switch (view.getId()) {
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
     *
     * @return
     */
    private List<BannerPagerAdapter.BannerItemInfo> getBannerData() {
        List<BannerPagerAdapter.BannerItemInfo> bannerItemInfoList = new ArrayList<>();
        int[] itemIdArr = getResources().getIntArray(R.array.banner_item_id);
        TypedArray drawableAyy = getResources().obtainTypedArray(R.array.banner_drawable_list);
        if (itemIdArr.length != drawableAyy.length())
            throw new ArrayIndexOutOfBoundsException("Array resource \"banner_item_id\" " +
                    "and \"banner_drawable_list\" of length is not equal");

        for (int i = 0; i < drawableAyy.length(); i++) {
            BannerPagerAdapter.BannerItemInfo itemInfo =
                    new BannerPagerAdapter.BannerItemInfo(itemIdArr[i], drawableAyy.getDrawable(i));
            bannerItemInfoList.add(itemInfo);
        }
        drawableAyy.recycle();
        return bannerItemInfoList;
    }

    /**
     * 添加轮播图指示器的指示点
     */
    private void addPointView(LinearLayout pointLayout, Context context) {
        for (int i = 0; i < getBannerData().size(); i++) {
            View view = new View(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(30, 30);
            params.leftMargin = 10;
            view.setLayoutParams(params);
            view.setBackgroundResource(R.drawable.banner_point_item_bg);
            pointLayout.addView(view);
        }
    }

    /**
     * 轮播图滑动时，切换指示器的item显示状态
     *
     * @param bannerPager
     */
    private void setPointWithBanner(ViewPager bannerPager) {
        bannerPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //把即将到来的指示点设置为焦点
                int relNextPosition = bannerPagerAdapter.getRelPosition(position);
                View nextPoint = pointLayout.getChildAt(relNextPosition);
                nextPoint.setBackgroundResource(R.drawable.banner_point_item_focus);

                //把当前位置的指示点设置为背景
                View curPoint = pointLayout.getChildAt(bannerRelCurrentItem);
                curPoint.setBackgroundResource(R.drawable.banner_point_item_bg);

                bannerRelCurrentItem = relNextPosition;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private final int SHUFFLING_CODE = 0x7D1;

    /**
     * 设置@code{bannerPager}轮播图的自动轮播线程
     */
    private void setAutoShuffling() {
        Thread bannerThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(2000);
                    handler.sendEmptyMessage(SHUFFLING_CODE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        bannerThread.start();
    }

    /**
     *
     */
    Handler handler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == SHUFFLING_CODE) {
                int currentItem = bannerPager.getCurrentItem() + 1;
                bannerPager.setCurrentItem(currentItem);
            }
        }
    };
}