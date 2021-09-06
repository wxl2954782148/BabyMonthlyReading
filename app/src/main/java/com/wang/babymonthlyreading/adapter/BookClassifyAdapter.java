package com.wang.babymonthlyreading.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wang.babymonthlyreading.R;
import com.wang.babymonthlyreading.enums.BookClassifyInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 书籍分类信息适配器
 */
public class BookClassifyAdapter extends RecyclerView.Adapter<BookClassifyAdapter.ViewHolder> {
    private static final String TAG = "BookClassifyAdapter";
    private List<BookClassifyInfo> bookClassifyInfoList;
    private Context context;

    public BookClassifyAdapter(List<BookClassifyInfo> data) {
        if (bookClassifyInfoList == null)
            bookClassifyInfoList = new ArrayList<>();
        this.bookClassifyInfoList = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_book_classify, parent, false);
        ViewHolder viewHolder = new ViewHolder(inflate);
        viewHolder.bookClassifyCB.setOnCheckedChangeListener((checkBox , isChecked) -> {
            if (isChecked) {
                checkBox.setBackgroundResource(R.drawable.book_classify_select);
                checkBox.setTextColor(context.getColor(R.color.orange));
                //TODO 刷新图书列表
            }else {
                checkBox.setBackgroundColor(context.getColor(R.color.white));
                checkBox.setTextColor(context.getColor(R.color.dark_charcoal));
                //TODO 刷新图书列表
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: ");
        BookClassifyInfo bookClassifyInfo = bookClassifyInfoList.get(position);
        holder.bookClassifyCB.setText(bookClassifyInfo.getDesc());
        holder.bookClassifyCB.setChecked(false);
        if (position == 0){
            holder.bookClassifyCB.setChecked(true);
        }
    }

    @Override
    public int getItemCount() {
        return bookClassifyInfoList.size();
    }

    /**
     * 刷新数据集
     * @param data
     */
    @SuppressLint("NotifyDataSetChanged")
    public void updateData(List<BookClassifyInfo> data){
        this.bookClassifyInfoList = data;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final CheckBox bookClassifyCB;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookClassifyCB = itemView.findViewById(R.id.cb_item_book_classify);
        }
    }
}
