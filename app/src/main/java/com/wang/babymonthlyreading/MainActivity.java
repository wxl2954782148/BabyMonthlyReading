package com.wang.babymonthlyreading;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.wang.babymonthlyreading.adapter.BannerPagerAdapter;
import com.wang.babymonthlyreading.adapter.BookClassifyAdapter;
import com.wang.babymonthlyreading.adapter.BookListAdapter;
import com.wang.babymonthlyreading.adapter.CustomBookAdapter;
import com.wang.babymonthlyreading.data.TestData;
import com.wang.babymonthlyreading.enums.AgeRangeEnum;
import com.wang.babymonthlyreading.enums.BookClassifyInfo;
import com.wang.babymonthlyreading.fragment.CustomBookInfoFragment;
import com.wang.babymonthlyreading.fragment.CustomBookTipsFragment;

import java.util.ArrayList;
import java.util.Collections;
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
    private BookClassifyAdapter bookClassifyAdapter;

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
        List<BannerPagerAdapter.BannerItemInfo> bannerData = TestData.getBannerData(this);
        bannerPagerAdapter = new BannerPagerAdapter(bannerData);
        bannerPager.setAdapter(bannerPagerAdapter);
        bannerPager.setCurrentItem(0);
        setAutoShuffling();

        // -->轮播图指示器相关
        pointLayout = findViewById(R.id.linear_banner_point);
        addPointView(pointLayout, this, bannerData.size());
        setPointWithBanner(bannerPager);
        View childAt = pointLayout.getChildAt(0);
        childAt.setBackgroundResource(R.drawable.banner_point_item_focus);

        //-->专属定制、定制书单相关
        switchCustomBookFragment();

        //--> 年龄范围选项卡
        TabLayout ageRangeTab = findViewById(R.id.tab_age_range);
        setAgeRangeTabTitle(ageRangeTab);
        setAgeRangeTabSelectListener(ageRangeTab);

        //--> 图书分类相关
        RecyclerView bookClassifyRecycle = findViewById(R.id.recycle_book_classify);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        bookClassifyRecycle.setLayoutManager(layoutManager);
        bookClassifyAdapter = new BookClassifyAdapter(getBookClassifyData());
        bookClassifyRecycle.setAdapter(bookClassifyAdapter);

        //--> 书籍列表相关
        RecyclerView bookListRecycler = findViewById(R.id.recycle_book_list);
        bookListRecycler.setLayoutManager(new GridLayoutManager(this, 2));
        BookListAdapter bookListAdapter = new BookListAdapter(TestData.getBookInfoList(this));
        bookListRecycler.setAdapter(bookListAdapter);
        bookListRecycler.addItemDecoration(new BookListAdapter.SpaceItemDecoration(20));
    }

    public List<BookClassifyInfo> getBookClassifyData() {
        List<BookClassifyInfo> list = new ArrayList<>();
        BookClassifyInfo.RANGE_ONE[] values = BookClassifyInfo.RANGE_ONE.values();
        Collections.addAll(list, values);
        return list;
    }

    /**
     * 设置年龄范围选项卡ageRangeTab(年龄范围选择器)的标题
     *
     * @param ageRangeTab
     */
    private void setAgeRangeTabTitle(TabLayout ageRangeTab) {
        AgeRangeEnum[] values = AgeRangeEnum.values();
        for (AgeRangeEnum ageRangeEnum : values) {
            ageRangeTab.addTab(
                    ageRangeTab.newTab()
                            .setId(ageRangeEnum.getAgeRangeId())
                            .setText(ageRangeEnum.getAgeRangeDesc()));
        }
    }

    /**
     * 设置年龄范围选项卡的选择监听器:
     * 被选中时，刷新书籍分类列表及书籍列表
     *
     * @param tabLayout
     */
    private void setAgeRangeTabSelectListener(TabLayout tabLayout) {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                List<BookClassifyInfo> list = new ArrayList<>();
                if (tab.getId() == AgeRangeEnum.RANGE_ONE.getAgeRangeId()) {
                    list = AgeRangeEnum.RANGE_ONE.getBookClassifyInfo();
                } else if (tab.getId() == AgeRangeEnum.RANGE_TWO.getAgeRangeId()) {
                    list = AgeRangeEnum.RANGE_TWO.getBookClassifyInfo();
                } else if (tab.getId() == AgeRangeEnum.RANGE_THREE.getAgeRangeId()) {
                    list = AgeRangeEnum.RANGE_THREE.getBookClassifyInfo();
                } else if (tab.getId() == AgeRangeEnum.RANGE_FOUR.getAgeRangeId()) {
                    list = AgeRangeEnum.RANGE_FOUR.getBookClassifyInfo();
                }
                //刷新书籍分类
                bookClassifyAdapter.updateData(list);
                //TODO 刷新书籍列表
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    /**
     * 切换定制专属书单的Fragment
     * 如果用户已经登录，并且存在已经定制的书籍，那么显示CustomBookInfoFragment
     * 如果用户未登录或者没有定制书籍，那么显示CustomBookTipsFragment
     */
    public void switchCustomBookFragment() {
        List<CustomBookAdapter.CustomBookInfo> customBookData = TestData.getCustomBookData(this);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (customBookData.isEmpty()) {
            transaction.add(R.id.fragment_container_custom_exclusive, new CustomBookTipsFragment());
        } else {
            transaction.add(R.id.fragment_container_custom_exclusive, new CustomBookInfoFragment(customBookData));
        }
        transaction.commit();
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
     * 添加轮播图指示器的指示点
     */
    private void addPointView(LinearLayout pointLayout, Context context, int count) {
        for (int i = 0; i < count; i++) {
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
                    Thread.sleep(4000);
                    handler.sendEmptyMessage(SHUFFLING_CODE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        bannerThread.start();
    }

    /**
     * handler修改轮播图
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