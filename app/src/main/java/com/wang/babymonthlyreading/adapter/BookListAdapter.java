package com.wang.babymonthlyreading.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wang.babymonthlyreading.R;
import com.wang.babymonthlyreading.entity.BookInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * 首页书籍列表适配器
 */
public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.ViewHolder> {
    List<BookInfo> bookInfoList;
    private Context context;

    public BookListAdapter(List<BookInfo> data) {
        if (data == null) {
            bookInfoList = new ArrayList<>();
        }
        this.bookInfoList = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_book_list_info, parent, false);

        ViewHolder viewHolder = new ViewHolder(inflate);
        initView(viewHolder);
        return viewHolder;
    }

    /**
     * bookList内部控件事件
     *
     * @param holder
     */
    private void initView(ViewHolder holder) {
        holder.addCarImgb.setOnClickListener(v -> {
            holder.cartCountText.setVisibility(View.VISIBLE);
            int count = getCartCount(holder.cartCountText);
            if (count == 0) {
                holder.removeCartImgb.setVisibility(View.VISIBLE);
            }
            holder.cartCountText.setText(String.valueOf(++count));
            //TODO 添加到ToolBar购物车
        });
        holder.removeCartImgb.setOnClickListener(v->{
            int count = getCartCount(holder.cartCountText);
            if (count == 1){
                holder.removeCartImgb.setVisibility(View.INVISIBLE);
            }
            holder.cartCountText.setText(String.valueOf(--count));
            //TODO 添加到ToolBar购物车
        });
        holder.bookImg.setOnClickListener(bookInfoListener);
        holder.bookDescText.setOnClickListener(bookInfoListener);
    }

    /**
     * TODO 书籍图片、描述的点击事件，跳转到详情页
     */
    View.OnClickListener bookInfoListener = v -> {

    };
    /**
     * 获取item中的购物车数量
     * @param cartCountText
     * @return
     */
    private int getCartCount(TextView cartCountText) {
        String text = (String) cartCountText.getText();
        return Integer.parseInt(text);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BookInfo bookInfo = bookInfoList.get(position);
        Glide.with(context).load(bookInfo.getBookImg()).into(holder.bookImg);
        holder.bookDescText.setText(bookInfo.getBookDesc());
        holder.bookPriceText.setText(priceFormat(bookInfo.getBookPrice()));
    }

    @Override
    public int getItemCount() {
        return bookInfoList.size();
    }

    /**
     * 格式化书籍价格
     * 128f -->  ￥ 128.00
     *
     * @param price
     * @return
     */
    public String priceFormat(float price) {
        return String.format(Locale.CHINA,
                context.getString(R.string.format_book_price),
                price);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView bookImg;
        private final TextView bookDescText;
        private final LinearLayout bookTagLinear;
        private final TextView bookPriceText;
        private final ImageButton removeCartImgb;
        private final ImageButton addCarImgb;
        private final TextView cartCountText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookImg = itemView.findViewById(R.id.img_book);
            bookDescText = itemView.findViewById(R.id.text_book_desc);
            bookTagLinear = itemView.findViewById(R.id.linear_book_tag);
            bookPriceText = itemView.findViewById(R.id.text_book_price);
            removeCartImgb = itemView.findViewById(R.id.imgb_remove_shopping_cart);
            addCarImgb = itemView.findViewById(R.id.imgb_add_shopping_cart);
            cartCountText = itemView.findViewById(R.id.text_book_shopping_count);

        }
    }

    public static class SpaceItemDecoration extends RecyclerView.ItemDecoration {
        private final int space;

        public SpaceItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            int position = parent.getChildLayoutPosition(view);
            if (position % 2 == 0) {
                outRect.right = space / 2;
            } else {
                outRect.left = space / 2;
            }
            outRect.bottom = 15;
        }
    }
}
