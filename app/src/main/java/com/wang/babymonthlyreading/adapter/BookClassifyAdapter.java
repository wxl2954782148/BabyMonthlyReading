package com.wang.babymonthlyreading.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

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
    /**
     * 当前被勾选的书籍分类列表
     */
    private final List<BookClassifyInfo> isCheckedBookClassifyInfo = new ArrayList<>();

    public BookClassifyAdapter(List<BookClassifyInfo> data) {
        if (bookClassifyInfoList == null)
            bookClassifyInfoList = new ArrayList<>();
        this.bookClassifyInfoList = data;
    }

    /**
     * 书籍分类按钮选中和取消选中的事件监听，用于刷新书籍列表
     * 参数bookClassifyInfo表示当前被勾选的书籍分类枚举
     */
    public interface BookClassifyCheckedListener {
        /**
         * 书籍分类被勾选时触发的回调事件，由MainActivity监听，并刷新书籍列表
         *
         * @param bookClassifyInfoList 被勾选的书籍分类列表
         */
        void isChecked(List<BookClassifyInfo> bookClassifyInfoList);

        /**
         * 书籍分类取消勾选时触发的回调事件，由MainActivity监听，并刷新书籍列表
         *
         * @param bookClassifyInfoList 剩余被勾选的书籍分类列表
         */
        void cancelChecked(List<BookClassifyInfo> bookClassifyInfoList);
    }

    private BookClassifyCheckedListener bookClassifyCheckedListener;

    public void setBookClassifyCheckedListener(BookClassifyCheckedListener bookClassifyCheckedListener) {
        this.bookClassifyCheckedListener = bookClassifyCheckedListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_book_classify, parent, false);
        ViewHolder viewHolder = new ViewHolder(inflate);

        viewHolder.bookClassifyCB.setOnCheckedChangeListener((checkBox, isChecked) -> {
            String desc = checkBox.getText().toString();
            BookClassifyInfo current = BookClassifyInfo.findByDesc(desc, bookClassifyInfoList);
            if (isChecked) {
                checkBox.setBackgroundResource(R.drawable.book_classify_select);
                checkBox.setTextColor(context.getColor(R.color.orange));

                isCheckedBookClassifyInfo.add(current);
                bookClassifyCheckedListener.isChecked(isCheckedBookClassifyInfo);
            } else {
                checkBox.setBackgroundColor(context.getColor(R.color.white));
                checkBox.setTextColor(context.getColor(R.color.dark_charcoal));

                isCheckedBookClassifyInfo.remove(current);
                bookClassifyCheckedListener.cancelChecked(isCheckedBookClassifyInfo);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BookClassifyInfo bookClassifyInfo = bookClassifyInfoList.get(position);
        holder.bookClassifyCB.setText(bookClassifyInfo.getDesc());
        holder.bookClassifyCB.setChecked(position == 0);
    }

    @Override
    public int getItemCount() {
        return bookClassifyInfoList.size();
    }

    /**
     * 刷新数据集
     * 点击年龄分类选项卡后，刷新书籍分类数据集；
     * 并且清空isCheckedBookClassifyInfo，
     * 因为默认勾选第一个，把第一个加入到isCheckedBookClassifyInfo
     * 并且触发bookClassifyCheckedListener事件来刷新图书列表
     *
     * @param data
     */
    @SuppressLint("NotifyDataSetChanged")
    public void updateData(List<BookClassifyInfo> data) {
        this.bookClassifyInfoList = data;
        notifyDataSetChanged();

        isCheckedBookClassifyInfo.clear();
        isCheckedBookClassifyInfo.add(data.get(0));
        bookClassifyCheckedListener.isChecked(isCheckedBookClassifyInfo);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final CheckBox bookClassifyCB;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookClassifyCB = itemView.findViewById(R.id.cb_item_book_classify);
        }
    }
}
