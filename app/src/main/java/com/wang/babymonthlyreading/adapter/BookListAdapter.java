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
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wang.babymonthlyreading.R;
import com.wang.babymonthlyreading.activity.BookDetailActivity;
import com.wang.babymonthlyreading.activity.MainActivity;
import com.wang.babymonthlyreading.entity.BookInfo;
import com.wang.babymonthlyreading.enums.BookClassifyInfo;
import com.wang.babymonthlyreading.listener.OnShoppingCartChangeListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * 首页书籍列表适配器
 */
public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.ViewHolder> {
    List<BookInfo> bookInfoList;
    private Context context;
    private OnShoppingCartChangeListener onShoppingCartChangeListener;

    public void setOnShoppingCartChangeListener(OnShoppingCartChangeListener onShoppingCartChangeListener) {
        this.onShoppingCartChangeListener = onShoppingCartChangeListener;
    }

    public BookListAdapter(List<BookInfo> data) {
        if (data == null) {
            bookInfoList = new ArrayList<>();
        }
        this.bookInfoList = data;
    }

    public BookListAdapter() {
        bookInfoList = new ArrayList<>();
    }

    public void setBookInfoList(@NonNull List<BookInfo> bookInfoList) {
        this.bookInfoList = bookInfoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_book_list_info, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BookInfo bookInfo = bookInfoList.get(position);
        Glide.with(context).load(bookInfo.getBookImgId()).into(holder.bookImg);
        holder.bookDescText.setText(bookInfo.getBookDesc());
        holder.bookPriceText.setText(priceFormat(bookInfo.getBookPrice()));
//        displayBookClassifyInfos(holder.bookTagLinear, bookInfo.getBookClassifyInfos());
        viewOnClick(holder, position);
    }

    /**
     * 显示书籍分类信息
     * 由于没有真实数据，模拟数据有太多分类、暂停使用
     *
     * @param linearLayout
     * @param list
     */
    private void displayBookClassifyInfos(LinearLayout linearLayout, List<BookClassifyInfo> list) {
        for (BookClassifyInfo bookClassifyInfo : list) {
            TextView textView = (TextView) LayoutInflater.from(context).inflate(R.layout.item_book_list_info_classify,null,false);
            textView.setText(bookClassifyInfo.getDesc());
            linearLayout.addView(textView);
        }
    }

    /**
     * bookList内部控件的点击事件监听事件
     * 1. addCarImgb
     * <p>点击该按钮时，显示cartCountText及removeCartImgb，并且设置cartCountText的text</p>
     * <p>触发事件监听onShoppingCartChangeListener：把商品信息添加到购物车{@link MainActivity#shoppingCartMap}</p>
     * <p>更改menu_item_shopping_cart 购物车菜单的图标，显示购物车的数量</p>
     * 2. removeCartImgb 与addCarImgb类似
     * 3. 点击图片、标题进入详情页
     * @param holder
     */
    private void viewOnClick(ViewHolder holder, int position) {
        BookInfo bookInfo = bookInfoList.get(position);

        holder.addCarImgb.setOnClickListener(v -> {
            int count = getCartCount(holder.cartCountText);
            if (count == 0) {
                holder.cartCountText.setVisibility(View.VISIBLE);
                holder.removeCartImgb.setVisibility(View.VISIBLE);
            }
            holder.cartCountText.setText(String.valueOf(++count));

            onShoppingCartChangeListener.onShoppingCartChange(bookInfo.getBookId(), 1);
        });

        holder.removeCartImgb.setOnClickListener(v -> {
            int count = getCartCount(holder.cartCountText);
            if (count == 1) {
                holder.cartCountText.setVisibility(View.INVISIBLE);
                holder.removeCartImgb.setVisibility(View.INVISIBLE);
            }
            holder.cartCountText.setText(String.valueOf(--count));
            onShoppingCartChangeListener.onShoppingCartChange(bookInfo.getBookId(), -1);
        });
        //跳转到详情页
        holder.bookImg.setOnClickListener(v -> {
            BookDetailActivity.startBookDetailActivity(context, bookInfoList.get(position).getBookId());
        });
    }


    /**
     * 获取item中的购物车数量
     *
     * @param cartCountText
     * @return
     */
    private int getCartCount(TextView cartCountText) {
        String text = (String) cartCountText.getText();
        return Integer.parseInt(text);
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
            bookTagLinear = itemView.findViewById(R.id.linear_book_detail_tag);
            bookPriceText = itemView.findViewById(R.id.text_book_price);
            removeCartImgb = itemView.findViewById(R.id.imgb_remove_shopping_cart);
            addCarImgb = itemView.findViewById(R.id.imgb_add_shopping_cart);
            cartCountText = itemView.findViewById(R.id.text_book_shopping_count);

        }
    }

    /**
     * 刷新数据集
     *
     * @param newBookInfoList
     */
    public void updateData(List<BookInfo> newBookInfoList) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffCallBack(bookInfoList, newBookInfoList));
        diffResult.dispatchUpdatesTo(this);
        bookInfoList = newBookInfoList;
    }

    static class DiffCallBack extends DiffUtil.Callback {
        private final List<BookInfo> oldBookInfoList;
        private final List<BookInfo> newBookInfoList;

        public DiffCallBack(List<BookInfo> oldBookInfoList, List<BookInfo> newBookInfoList) {
            if (oldBookInfoList == null) {
                oldBookInfoList = new ArrayList<>();
            }
            if (newBookInfoList == null) {
                newBookInfoList = new ArrayList<>();
            }
            this.oldBookInfoList = oldBookInfoList;
            this.newBookInfoList = newBookInfoList;
        }

        @Override
        public int getOldListSize() {
            return oldBookInfoList.size();
        }

        @Override
        public int getNewListSize() {
            return newBookInfoList.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            BookInfo oldBookInfo = oldBookInfoList.get(oldItemPosition);
            BookInfo newBookInfo = newBookInfoList.get(newItemPosition);
            return oldBookInfo.getBookId() == newBookInfo.getBookId();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            BookInfo oldBookInfo = oldBookInfoList.get(oldItemPosition);
            BookInfo newBookInfo = newBookInfoList.get(newItemPosition);
            return oldBookInfo.equals(newBookInfo);
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
