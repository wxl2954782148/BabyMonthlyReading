package com.wang.babymonthlyreading.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wang.babymonthlyreading.R;
import com.wang.babymonthlyreading.entity.BookInfo;
import com.wang.babymonthlyreading.enums.BookClassifyInfo;
import com.wang.babymonthlyreading.listener.OnShoppingCartChangeListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder> {
    private final List<BookInfo> bookInfoList;
    private Context context;
    private OnShoppingCartChangeListener cartChangeListener;

    public void setCartChangeListener(OnShoppingCartChangeListener cartChangeListener) {
        this.cartChangeListener = cartChangeListener;
    }

    public SearchResultAdapter(List<BookInfo> bookInfoList) {
        if (bookInfoList == null) {
            bookInfoList = new ArrayList<>();
        }
        this.bookInfoList = bookInfoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_search_result, parent, false);
        return new ViewHolder(inflate);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BookInfo bookInfo = bookInfoList.get(position);
        Glide.with(context).load(bookInfo.getBookImgId()).into(holder.bookImg);
        holder.bookDescText.setText(bookInfo.getBookDesc());
        holder.bookPriceText.setText(formatPrice(bookInfo.getBookPrice()));
        addTag(holder.bookTagsLinear, bookInfo.getBookClassifyInfos());
        onViewClick(holder, bookInfo);

    }

    private void onViewClick(ViewHolder holder, BookInfo bookInfo) {
        holder.addGoodsImgb.setOnClickListener(v -> {
            int count = getCartCount(holder.goodsCountText);
            if (count == 0) {
                holder.goodsCountText.setVisibility(View.VISIBLE);
                holder.removeGoodsImgb.setVisibility(View.VISIBLE);
            }
            holder.goodsCountText.setText(String.valueOf(++count));
            cartChangeListener.onShoppingCartChange(bookInfo.getBookId(), 1);
        });
        holder.removeGoodsImgb.setOnClickListener(v -> {
            int count = getCartCount(holder.goodsCountText);
            if (count == 1) {
                holder.removeGoodsImgb.setVisibility(View.INVISIBLE);
                holder.goodsCountText.setVisibility(View.INVISIBLE);
            }
            holder.goodsCountText.setText(String.valueOf(--count));
            cartChangeListener.onShoppingCartChange(bookInfo.getBookId(), -1);
        });
    }

    private int getCartCount(TextView countText) {
        String text = countText.getText().toString();
        return Integer.parseInt(text);
    }

    private void addTag(LinearLayout layout, List<BookClassifyInfo> bookClassifyInfos) {
        for (int i = 0; i < bookClassifyInfos.size(); i++) {
            TextView child = new TextView(context);
            child.setText(bookClassifyInfos.get(i).getDesc());
            child.setTextColor(ContextCompat.getColor(context, R.color.coffee));
            child.setTextSize(14);
            child.setBackgroundResource(R.drawable.book_tag_bg);
            child.setPadding(8, 0, 8, 0);
            LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
            params.rightMargin = 10;
            child.setLayoutParams(params);
            layout.addView(child);
        }
    }

    private String formatPrice(float price) {
        return String.format(Locale.CHINA,
                context.getString(R.string.format_book_price),
                price);
    }

    @Override
    public int getItemCount() {
        return bookInfoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView bookImg;
        private final TextView bookDescText;
        private final LinearLayout bookTagsLinear;
        private final TextView bookPriceText;
        private final ImageButton removeGoodsImgb;
        private final ImageButton addGoodsImgb;
        private final TextView goodsCountText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookImg = itemView.findViewById(R.id.img_search_result_book);
            bookDescText = itemView.findViewById(R.id.text_search_result_book_desc);
            bookTagsLinear = itemView.findViewById(R.id.linear_search_result_tag);
            bookPriceText = itemView.findViewById(R.id.text_search_result_book_price);
            removeGoodsImgb = itemView.findViewById(R.id.imbg_search_result_remove);
            addGoodsImgb = itemView.findViewById(R.id.imbg_search_result_add);
            goodsCountText = itemView.findViewById(R.id.text_search_result_count);
        }
    }
}
