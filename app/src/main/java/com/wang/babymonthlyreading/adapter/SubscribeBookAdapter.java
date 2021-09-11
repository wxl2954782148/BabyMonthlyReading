package com.wang.babymonthlyreading.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wang.babymonthlyreading.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class SubscribeBookAdapter extends RecyclerView.Adapter<SubscribeBookAdapter.ViewHolder> {
    private List<SubscribeBookInfo> subscribeBookInfoList;
    /**
     * 用户的订阅信息，用于保存用户所订阅的书籍，返回MainActivity后展示这些信息
     */
    private final Set<SubscribeBookInfo> subscribeBookInfoResult = new HashSet<>();

    public List<SubscribeBookInfo> getSubscribeBookInfoResult() {
        return new ArrayList<>(subscribeBookInfoResult);
    }

    private Context context;

    public SubscribeBookAdapter(List<SubscribeBookInfo> list) {
        if (list == null) {
            subscribeBookInfoList = new ArrayList<>();
        }
        subscribeBookInfoList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_subscribe_book, parent,
                false);
        return new ViewHolder(inflate);
    }

    /**
     * 设置勾选按钮的勾选、被勾选样式
     * 包括初始化时、被点击时
     *
     * @param cb
     * @param isChecked
     * @param position  按钮在RecyclerView中的位置
     */
    private void setCheckedStyle(Button cb, boolean isChecked, int position) {
        cb.setBackgroundResource(R.drawable.subscribe_checkbox_state);
        SubscribeBookInfo subscribeBookInfo = subscribeBookInfoList.get(position);
        if (isChecked) {
            cb.setText(R.string.has_subscribe);
            cb.setTextColor(ContextCompat.getColor(context, R.color.gray));
            subscribeBookInfoResult.add(subscribeBookInfo);
        } else {
            cb.setText(R.string.subscribe);
            cb.setTextColor(ContextCompat.getColor(context, R.color.orange));
            subscribeBookInfoResult.remove(subscribeBookInfo);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SubscribeBookInfo subscribeBookInfo = subscribeBookInfoList.get(position);
        holder.subTitleText.setText(subscribeBookInfo.getTitle());

        holder.subscribeCb.setChecked(subscribeBookInfo.isSubscribe());
        setCheckedStyle(holder.subscribeCb, holder.subscribeCb.isChecked(), position);
        holder.subscribeCb.setOnCheckedChangeListener((buttonView, isChecked) -> {
            setCheckedStyle(buttonView, isChecked, position);
        });

        holder.subDescText.setText(subscribeBookInfo.getDesc());

        imgSetting(holder, subscribeBookInfo);
    }

    private void imgSetting(@NonNull ViewHolder holder, SubscribeBookInfo subscribeBookInfo) {
        Glide.with(context).load(subscribeBookInfo.imgs.get(0)).into(holder.imageView1);
        Glide.with(context).load(subscribeBookInfo.imgs.get(1)).into(holder.imageView2);
        Glide.with(context).load(subscribeBookInfo.imgs.get(2)).into(holder.imageView3);
    }


    @Override
    public int getItemCount() {
        return subscribeBookInfoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView subTitleText;
        private final CheckBox subscribeCb;
        private final TextView subDescText;
        private final ImageView imageView1;
        private final ImageView imageView2;
        private final ImageView imageView3;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            subTitleText = itemView.findViewById(R.id.text_subscribe_title);
            subscribeCb = itemView.findViewById(R.id.cb_subscribe);
            subDescText = itemView.findViewById(R.id.text_subscribe_desc);
            imageView1 = itemView.findViewById(R.id.img_subscribe_img1);
            imageView2 = itemView.findViewById(R.id.img_subscribe_img2);
            imageView3 = itemView.findViewById(R.id.img_subscribe_img3);
        }
    }

    public static class SpaceItemDecoration extends RecyclerView.ItemDecoration {
        private final int marginBottom;

        public SpaceItemDecoration(int marginBottom) {
            this.marginBottom = marginBottom;
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent,
                                   @NonNull RecyclerView.State state) {
            outRect.bottom = marginBottom;
        }

    }

    @AllArgsConstructor
    @Getter
    @Setter
    @EqualsAndHashCode
    @ToString
    public static class SubscribeBookInfo {
        private int id;
        private String title;
        private boolean subscribe;
        private String desc;
        private List<Integer> imgs;
    }
}
