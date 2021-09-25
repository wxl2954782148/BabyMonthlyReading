package com.wang.babymonthlyreading.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wang.babymonthlyreading.R;

import java.util.Collections;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class UserCenterAdapter extends RecyclerView.Adapter<UserCenterAdapter.ViewHolder> {

    private Context context;
    private final List<UserCenterAdapterInfo> infos;

    public UserCenterAdapter(List<UserCenterAdapterInfo> infos) {
        if (infos == null) {
            this.infos = Collections.emptyList();
        } else {
            this.infos = infos;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_user_center, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserCenterAdapterInfo info = infos.get(position);
        TextView titleText = holder.titleText;
        titleText.setText(info.getTitle());
        //跳转到对应页面
        titleText.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClassName(context, info.getToActivity());
            context.startActivity(intent);
        });
        holder.iconImg.setImageDrawable(info.getIcon());
    }

    @Override
    public int getItemCount() {
        return infos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView titleText;
        private final ImageView iconImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.text_recycler_title);
            iconImg = itemView.findViewById(R.id.img_icon);
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class UserCenterAdapterInfo {
        private Drawable icon;
        private String Title;
        /**
         * 点击跳转到哪个Activity。class全限定名
         */
        private String toActivity;
    }
}
