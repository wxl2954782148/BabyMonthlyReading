package com.wang.babymonthlyreading.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wang.babymonthlyreading.R;
import com.wang.babymonthlyreading.adapter.ShoppingCartAdapter.ShoppingCartInfo;
import com.wang.babymonthlyreading.entity.BookInfo;
import com.wang.babymonthlyreading.enums.BookClassifyInfo;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class SubmitOrderAdapter extends RecyclerView.Adapter<SubmitOrderAdapter.ViewHolder> {
    private final List<ShoppingCartInfo> orderSubmitInfo;
    private Context context;

    public SubmitOrderAdapter(List<ShoppingCartInfo> infos) {
        if (infos == null) {
            infos = Collections.emptyList();
        }
        this.orderSubmitInfo = infos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_submit_order, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ShoppingCartInfo cartInfo = orderSubmitInfo.get(position);
        BookInfo bookInfo = cartInfo.getBookInfo();
        Glide.with(context).load(bookInfo.getBookImgId()).into(holder.bookImg);
        holder.bookTitleText.setText(bookInfo.getBookDesc());
        holder.bookPriceText.setText(priceFormat(bookInfo.getBookPrice()));
        holder.bookCountText.setText(String.format(Locale.CHINA, "×%d", cartInfo.getBookCount()));
    }

    public String priceFormat(float price) {
        return String.format(Locale.CHINA, context.getString(R.string.format_book_price), price);
    }
    @Override
    public int getItemCount() {
        return orderSubmitInfo.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView bookImg;
        private final TextView bookTitleText;
        private final TextView bookPriceText;
        private final TextView bookCountText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookImg = itemView.findViewById(R.id.img_order_book);
            bookTitleText = itemView.findViewById(R.id.text_order_book_title);
            bookPriceText = itemView.findViewById(R.id.text_order_book_price);
            bookCountText = itemView.findViewById(R.id.text_order_book_count);
        }
    }
}
