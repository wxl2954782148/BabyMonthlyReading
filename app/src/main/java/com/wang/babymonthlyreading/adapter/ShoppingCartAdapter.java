package com.wang.babymonthlyreading.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 购物车列表适配器
 */
public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder> {
    private static final String TAG = "ShoppingCartAdapter";

    private final List<ShoppingCartInfo> shoppingCartInfos;
    private Context context;
    //勾选的商品
    private final List<ShoppingCartInfo> checkedInfos = new ArrayList<>();

    /**
     * 购物车商品列表勾选按钮监听事件
     * 按钮被勾选、取消勾选时，回调至ShoppingCartActivity
     */
    public interface OnCartCheckedListener {
        void onCartChecked(List<ShoppingCartInfo> checkedInfos);
    }

    private OnCartCheckedListener cartCheckedListener;

    public void setCartCheckedListener(OnCartCheckedListener cartCheckedListener) {
        this.cartCheckedListener = cartCheckedListener;
    }

    public ShoppingCartAdapter(List<ShoppingCartInfo> infos) {
        if (infos == null) {
            this.shoppingCartInfos = new ArrayList<>();
        } else {
            this.shoppingCartInfos = infos;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_shopping_cart, parent, false);
        ViewHolder holder = new ViewHolder(inflate);

        holder.addImgb.setOnClickListener(v -> {
            int position = holder.getAdapterPosition();
            ShoppingCartInfo cartInfo = shoppingCartInfos.get(position);
            int bookCount = cartInfo.getBookCount();
            cartInfo.setBookCount(++bookCount);
            holder.countText.setText(String.valueOf(bookCount));
            cartCheckedListener.onCartChecked(checkedInfos);
        });
        holder.removeImgb.setOnClickListener(v -> {
            int position = holder.getAdapterPosition();
            ShoppingCartInfo cartInfo = shoppingCartInfos.get(position);
            int bookCount = cartInfo.getBookCount();
            bookCount--;
            if (bookCount == 0) {
                shoppingCartInfos.remove(position);
                checkedInfos.remove(cartInfo);
                notifyItemRemoved(position);
            } else {
                cartInfo.setBookCount(bookCount);
                holder.countText.setText(String.valueOf(bookCount));
            }
            cartCheckedListener.onCartChecked(checkedInfos);
        });
        holder.isShoppingCb.setOnCheckedChangeListener((v, isChecked) -> {
            int position = holder.getAdapterPosition();
            ShoppingCartInfo cartInfo = shoppingCartInfos.get(position);
            if (isChecked) {
                checkedInfos.add(cartInfo);
            } else {
                checkedInfos.remove(cartInfo);
            }
            cartCheckedListener.onCartChecked(checkedInfos);
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ShoppingCartInfo cartInfo = shoppingCartInfos.get(position);
        BookInfo bookInfo = cartInfo.getBookInfo();
        Glide.with(context)
                .load(bookInfo.getBookImgId())
                .into(holder.bookImg);
        addTag(holder.boolTagsLinear, bookInfo.getBookClassifyInfos());
        holder.bookDescText.setText(bookInfo.getBookDesc());
        holder.bookPriceText.setText(priceFormat(bookInfo.getBookPrice()));
        holder.countText.setText(String.valueOf(cartInfo.getBookCount()));
    }

    public String priceFormat(float price) {
        return String.format(Locale.CHINA, context.getString(R.string.format_book_price), price);
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


    @Override
    public int getItemCount() {
        return shoppingCartInfos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView bookImg;
        private final TextView bookDescText;
        private final LinearLayout boolTagsLinear;
        private final TextView bookPriceText;
        private final ImageButton removeImgb;
        private final ImageButton addImgb;
        private final TextView countText;
        private final CheckBox isShoppingCb;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookImg = itemView.findViewById(R.id.img_order_book);
            bookDescText = itemView.findViewById(R.id.text_order_book_title);
            boolTagsLinear = itemView.findViewById(R.id.linear_order_book_tag);
            bookPriceText = itemView.findViewById(R.id.text_order_book_price);
            removeImgb = itemView.findViewById(R.id.imbg_search_result_remove);
            addImgb = itemView.findViewById(R.id.imbg_search_result_add);
            countText = itemView.findViewById(R.id.text_search_result_count);
            isShoppingCb = itemView.findViewById(R.id.cb_is_shopping);
        }
    }


    @AllArgsConstructor
    @Getter
    @Setter
    @EqualsAndHashCode
    @ToString
    public static class ShoppingCartInfo implements Serializable {
        private BookInfo bookInfo;
        /**
         * 购物车中书籍的数量
         */
        private int bookCount;
    }
}
