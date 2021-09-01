package com.wang.babymonthlyreading.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.wang.babymonthlyreading.R;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class BannerPagerAdapter extends PagerAdapter {
    List<BannerItemInfo> infoList;
    private Context context;

    public BannerPagerAdapter(List<BannerItemInfo> infoList) {
        if (infoList == null) {
            this.infoList = new ArrayList<>();
        }
        this.infoList = infoList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        context = container.getContext();
        ImageView imageView = createImageView(context, getRelPosition(position));
        container.addView(imageView);
        return imageView;
    }


    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    /**
     * 创建ImageView，并把infoList中指定位置的Drawable设置给ImageView
     *
     * @param context
     * @param position
     * @return
     */
    private ImageView createImageView(Context context, int position) {
        BannerItemInfo itemInfo = infoList.get(position);
        ImageView imageView = new ImageView(context);
        ViewPager.LayoutParams params = new ViewPager.LayoutParams();
        params.width = ViewPager.LayoutParams.MATCH_PARENT;
        params.height = ViewPager.LayoutParams.MATCH_PARENT;
        imageView.setLayoutParams(params);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(context).load(itemInfo.getImg()).into(imageView);
        return imageView;
    }

    /**
     * 获取当前item在轮播图中的显示位置
     *
     * @param position recyclerView中item的位置。
     *                 由于无限轮播 {@link #getCount()}  position从1 到 Integer.MAX_VALUE
     * @return relPosition 轮播图的显示位置。即当前显示的信息是infoList中的哪个位置
     */
    public int getRelPosition(int position) {
        return position % infoList.size();
    }

    /**
     * 轮播图相关信息
     * ItemId: 点击跳转后查询数据的id
     * img: 轮播图显示的图片
     */
    @AllArgsConstructor
    @Getter
    @Setter
    public static class BannerItemInfo {
        private int ItemId;
        private Drawable img;
    }
}
