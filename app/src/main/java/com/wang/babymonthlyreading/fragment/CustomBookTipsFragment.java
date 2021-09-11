package com.wang.babymonthlyreading.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wang.babymonthlyreading.R;
import com.wang.babymonthlyreading.activity.CustomBookActivity;

/**
 * 主页专属定制部分，未获取到用户账号定制书单信息时，显示该Fragment来提供定制书单选项
 */
public class CustomBookTipsFragment extends Fragment {

    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_custom_book_tips, container, false);
        context = getContext();
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        Button customBooksBtn = inflate.findViewById(R.id.btn_custom_books);
        //TODO 跳转到定制书单界面
        customBooksBtn.setOnClickListener(v -> {
            CustomBookActivity.startCustomBookActivity(context);
        });
    }
}
