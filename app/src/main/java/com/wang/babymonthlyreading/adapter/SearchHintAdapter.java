package com.wang.babymonthlyreading.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wang.babymonthlyreading.R;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * 搜索页面搜索历史、热门搜索RecyclerView适配器
 */
public class SearchHintAdapter extends RecyclerView.Adapter<SearchHintAdapter.ViewHolder> {
    List<String> infoList;
    private Context context;

    public SearchHintAdapter(List<String> infoList) {
        this.infoList = infoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_search_hint, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String info = infoList.get(position);
        holder.hintText.setText(info);
    }

    /**
     * 向RecyclerView中添加一条数据
     * 如果数据已存在，则不添加
     *
     * @param info
     */
    public void addItem(String info) {
        if (infoList.contains(info)) {
            return;
        }
        infoList.add(info);
        notifyItemChanged(infoList.size() - 1);
    }

    /**
     * 向RecyclerView中指定位置插入一条数据
     * 如果数据已存在，则不添加
     * @param info
     * @param index
     */
    public void insertItem(String info, int index){
        if (infoList.contains(info)) {
            return;
        }
        infoList.add(index, info);
        notifyItemInserted(index);
    }
    @SuppressLint("NotifyDataSetChanged")
    public void removeAll() {
        infoList.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return infoList == null ? 0 : infoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView hintText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hintText = itemView.findViewById(R.id.text_item_search_hint);
        }
    }

    /**
     * 设置每个item的间距
     */
    public static class SpaceItemDecoration extends RecyclerView.ItemDecoration {
        private final int space;

        public SpaceItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            int position = parent.getChildLayoutPosition(view);
            if (position % 3 == 0) {
                outRect.right = space * 2 / 3;
            } else if (position % 3 == 1) {
                outRect.left = space / 2;
                outRect.right = space / 2;
            } else {
                outRect.left = space * 2 / 3;
            }
            if (position >= 3) {
                outRect.top = 20;
            }
        }
    }
}
