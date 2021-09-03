package com.wang.babymonthlyreading.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wang.babymonthlyreading.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 主页专属定制部分，用户指定书单后，显示已经定制的书单信息适配器
 */
public class CustomBookAdapter extends RecyclerView.Adapter<CustomBookAdapter.ViewHolder> {
    private List<CustomBookInfo> customBookInfoList;
    private Context context;

    public CustomBookAdapter(List<CustomBookInfo> customBookInfoList) {
        if (customBookInfoList == null) {
            this.customBookInfoList = new ArrayList<>();
        }
        this.customBookInfoList = customBookInfoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_custom_book_info, parent, false);
        setItemWidth(context, inflate);
        inflate.setOnClickListener(this.onItemClickListener);
        return new ViewHolder(inflate);
    }

    /**
     * 修改每个item的宽度为屏幕的 1/3
     * @param context
     * @param item
     */
    private void setItemWidth(Context context, View item) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        //减去recyclerView的外边距
        int container = dm.widthPixels - 20;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                container / 3, ViewGroup.LayoutParams.WRAP_CONTENT);
        item.setLayoutParams(params);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CustomBookInfo customBookInfo = customBookInfoList.get(position);
        Glide.with(context)
                .load(customBookInfo.getBookImg())
                .into(holder.customBookImg);
        holder.customBookPrice.setText(formatPrice(customBookInfo.getBookPrice()));
        holder.customBookDesc.setText(customBookInfo.getBookDescription());
    }

    @Override
    public int getItemCount() {
        return customBookInfoList.size();
    }

    /**
     * 刷新RecycleView的数据列表
     *
     * @param newCustomBookInfoList 新的数据列表
     */
    public void updateData(List<CustomBookInfo> newCustomBookInfoList) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffCallBack(customBookInfoList, newCustomBookInfoList));
        diffResult.dispatchUpdatesTo(this);
        customBookInfoList = newCustomBookInfoList;
    }

    /**
     * 添加一个Item，并刷新数据集
     *
     * @param bookInfo
     */
    public void addCustomBook(CustomBookInfo bookInfo) {
        customBookInfoList.add(bookInfo);
        notifyItemInserted(customBookInfoList.size() - 1);
    }

    /**
     * 格式化书本价格显示方式 如: ￥ 128.00
     *
     * @param price
     * @return
     */
    public String formatPrice(float price) {
        return String.format(Locale.CHINA,
                context.getString(R.string.format_book_price),
                price);
    }

    /**
     * TODO item的点击事件，点击跳转到详情页
     */
    View.OnClickListener onItemClickListener = v -> {

    };

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView customBookImg;
        private final TextView customBookDesc;
        private final TextView customBookPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            customBookImg = itemView.findViewById(R.id.img_custom_book);
            customBookDesc = itemView.findViewById(R.id.text_custom_book_desc);
            customBookPrice = itemView.findViewById(R.id.text_custom_book_price);
        }
    }


    public static class DiffCallBack extends DiffUtil.Callback {
        List<CustomBookInfo> oldCustomBooks;
        List<CustomBookInfo> newCustomBooks;

        public DiffCallBack(List<CustomBookInfo> oldCustomBooks, List<CustomBookInfo> newCustomBooks) {
            if (oldCustomBooks == null) {
                oldCustomBooks = new ArrayList<>();
            }
            if (newCustomBooks == null) {
                newCustomBooks = new ArrayList<>();
            }
            this.oldCustomBooks = oldCustomBooks;
            this.newCustomBooks = newCustomBooks;
        }

        @Override
        public int getOldListSize() {
            return oldCustomBooks.size();
        }

        @Override
        public int getNewListSize() {
            return newCustomBooks.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            CustomBookInfo oldInfo = oldCustomBooks.get(oldItemPosition);
            CustomBookInfo newInfo = newCustomBooks.get(newItemPosition);
            return oldInfo.getBookId() == newInfo.getBookId();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            CustomBookInfo oldInfo = oldCustomBooks.get(oldItemPosition);
            CustomBookInfo newInfo = newCustomBooks.get(newItemPosition);
            return oldInfo.equals(newInfo);
        }
    }


    @AllArgsConstructor
    @Getter
    @Setter
    @EqualsAndHashCode
    public static class CustomBookInfo {
        private int bookId;
        private Drawable bookImg;
        private float bookPrice;
        private String bookDescription;
    }
}
