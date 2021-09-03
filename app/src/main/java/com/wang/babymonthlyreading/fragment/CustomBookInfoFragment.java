package com.wang.babymonthlyreading.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wang.babymonthlyreading.R;
import com.wang.babymonthlyreading.adapter.CustomBookAdapter;
import com.wang.babymonthlyreading.adapter.CustomBookAdapter.CustomBookInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 主页专属定制部分，用户指定书单后，显示已经定制的书单信息
 */
public class CustomBookInfoFragment extends Fragment {

    private Context context;
    private List<CustomBookInfo> data;

    public CustomBookInfoFragment(List<CustomBookInfo> data) {
        if (data == null || data.isEmpty())
            throw new NullPointerException("data must be not null or empty");
        this.data = data;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getContext();
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.fragment_custom_book_info, container, false);

        CustomBookAdapter adapter = new CustomBookAdapter(data);
        recyclerView.setAdapter(adapter);

        //设置RecyclerView为横向布局，并且在数据集<=3的时候禁止滑动
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                context, LinearLayoutManager.HORIZONTAL, false){
            @Override
            public boolean canScrollHorizontally() {
                if (data.size() <= 3){
                    return false;
                }
                return super.canScrollHorizontally();
            }
        };
        recyclerView.setLayoutManager(layoutManager);

        return recyclerView;
    }
}
