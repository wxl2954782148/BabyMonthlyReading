package com.wang.babymonthlyreading.activity;

import android.os.Bundle;
import android.widget.ExpandableListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.wang.babymonthlyreading.R;
import com.wang.babymonthlyreading.adapter.OrderCenterExpandableListAdapter;
import com.wang.babymonthlyreading.adapter.OrderCenterExpandableListAdapter.ChildEntity;
import com.wang.babymonthlyreading.adapter.OrderCenterExpandableListAdapter.GroupEntity;
import com.wang.babymonthlyreading.enums.BookClassifyInfo;
import com.wang.babymonthlyreading.enums.OrderState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 选书订单页面
 * 由个人中心-选书订单跳转
 */
public class OrderCenterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_center);
        initView();
    }

    private void initView() {
        Toolbar toolbar = findViewById(R.id.tbar_order_center);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(v -> finish());

        ExpandableListView expandableListView = findViewById(R.id.expand_order);
        OrderCenterExpandableListAdapter adapter = new OrderCenterExpandableListAdapter(getGroupList(),
                getChildList(), this);
        expandableListView.setAdapter(adapter);
        expandableListView.expandGroup(0);
    }

    private List<List<ChildEntity>> getChildList() {
        List<List<ChildEntity>> list = new ArrayList<>();

        ChildEntity childEntity = new ChildEntity(getDrawable(R.drawable.book1),
                "宝宝睡前好故事必读100篇", 12.80f, 1, Arrays.asList(BookClassifyInfo.RANGE_ONE.CHILDREN_SONG,
                BookClassifyInfo.RANGE_TWO.CARTOON));
        ChildEntity childEntity2 = new ChildEntity(getDrawable(R.drawable.book2),
                "蒲公英·英语拼读王（全12册+9CD＋6D VD光盘）", 10.00f, 1, Arrays.asList(BookClassifyInfo.RANGE_FOUR.POETRY,
                BookClassifyInfo.RANGE_THREE.MATH));

        List<ChildEntity> childs1 = new ArrayList<>();
        childs1.add(childEntity);
        childs1.add(childEntity2);
        List<ChildEntity> childs2 = new ArrayList<>();
        childs2.add(childEntity);
        childs2.add(childEntity2);
        List<ChildEntity> childs3 = new ArrayList<>();
        childs3.add(childEntity);
        childs3.add(childEntity2);
        List<ChildEntity> childs4 = new ArrayList<>();
        childs4.add(childEntity);
        childs4.add(childEntity2);
        List<ChildEntity> childs5 = new ArrayList<>();
        childs5.add(childEntity);
        childs5.add(childEntity2);
        List<ChildEntity> childs6 = new ArrayList<>();
        childs6.add(childEntity);
        childs6.add(childEntity2);

        Collections.addAll(list, childs1, childs2, childs3, childs4, childs5, childs6);
        return list;
    }

    private List<GroupEntity> getGroupList() {
        List<GroupEntity> groupList = new ArrayList<>();
        groupList.add(new GroupEntity("第6期书单", OrderState.WAITDELIVER));
        groupList.add(new GroupEntity("第5期书单", OrderState.SHIPPED));
        groupList.add(new GroupEntity("第4期书单", OrderState.TO_EVALUATE));
        groupList.add(new GroupEntity("第3期书单", OrderState.TO_EVALUATE));
        groupList.add(new GroupEntity("第2期书单", OrderState.COMPLETED));
        groupList.add(new GroupEntity("第1期书单", OrderState.COMPLETED));
        return groupList;
    }

}