package com.wang.babymonthlyreading.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class BookDetailImgAdapter extends PagerAdapter {
    private final List<Integer> imgList;
    private Context context;

    public BookDetailImgAdapter(List<Integer> imgList) {
        if (imgList == null) {
            this.imgList = new ArrayList<>();
        } else {
            this.imgList = imgList;
        }
    }

    @Override
    public int getCount() {
        return imgList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        context = container.getContext();
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(imgList.get(position));
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
