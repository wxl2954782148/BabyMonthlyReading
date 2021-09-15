package com.wang.babymonthlyreading.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.wang.babymonthlyreading.R;
import com.wang.babymonthlyreading.adapter.BookDetailImgAdapter;
import com.wang.babymonthlyreading.data.TestData;
import com.wang.babymonthlyreading.entity.BookInfo;
import com.wang.babymonthlyreading.enums.BookClassifyInfo;

import java.util.List;
import java.util.Locale;

/**
 * 书籍详情信息页
 */
public class BookDetailActivity extends AppCompatActivity {

    private static final String BOOK_ID = "BOOK_ID";
    private ViewPager pager;
    private ImageButton backImgb;
    private ImageButton shareImgb;
    private TextView imgCountText;
    /**
     * 本页面显示的书籍的Id
     */
    private int bookId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        bookId = getIntent().getIntExtra(BOOK_ID, 0);
        initView();
    }

    private void initView() {
        pager = findViewById(R.id.pager_detail);
        pager.addOnPageChangeListener(pageChangeListener);
        pager.setAdapter(new BookDetailImgAdapter(getBookImgs()));

        backImgb = findViewById(R.id.imgb_book_detail_back);
        backImgb.setOnClickListener(v -> finish());
        shareImgb = findViewById(R.id.imgb_book_detail_share);
        shareImgb.setOnClickListener(v -> {
            //TODO 分享书籍信息
        });
        imgCountText = findViewById(R.id.text_book_detail_count);

        TextView detailTitleText = findViewById(R.id.text_book_detail_title);
        detailTitleText.setText(getBookInfo().getBookDesc());

        LinearLayout detailTagLinear = findViewById(R.id.linear_book_detail_tag);
        setTags(detailTagLinear);

        TextView priceText = findViewById(R.id.text_book_detail_price);
        priceText.setText(formatPrice(getBookInfo().getBookPrice()));

        TextView detailInfoText = findViewById(R.id.text_goods_detail_info);
        detailInfoText.setText(getBookInfo().getBookDetail());

        Button cartBtn = findViewById(R.id.btn_book_detail_cart);
        cartBtn.setOnClickListener(v -> {
            //TODO 打开购物车
        });

        Button addCartBtn = findViewById(R.id.btn_boo_detail_add_cart);
        addCartBtn.setOnClickListener(v -> {
            //TODO 添加到购物车
        });
    }

    private String formatPrice(Float bookPrice) {
        return String.format(Locale.CHINA, getString(R.string.format_book_price), bookPrice);
    }

    private void setTags(LinearLayout detailTagLinear) {
        for (BookClassifyInfo tag : getBookInfo().getBookClassifyInfos()) {
            TextView tagView = new TextView(this);
            tagView.setText(tag.getDesc());
            tagView.setTextColor(getColor(R.color.coffee));
            tagView.setTextSize(14);
            tagView.setBackgroundResource(R.drawable.book_tag_bg);
            tagView.setPadding(8, 0, 8, 0);
            LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
            params.rightMargin = 10;
            tagView.setLayoutParams(params);
            detailTagLinear.addView(tagView);
        }
    }

    private BookInfo getBookInfo() {
        return TestData.selectBookById(this, bookId);
    }

    private List<Integer> getBookImgs() {
        return getBookInfo().getBookImgIds();
    }

    /**
     * 图片滑动时，修改计数器的显示
     */
    ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            String imgCounter = position + 1 + "/" + getBookImgs().size();
            imgCountText.setText(imgCounter);

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    public static void startBookDetailActivity(Context context, int bookId) {
        Intent intent = new Intent(context, BookDetailActivity.class);
        intent.putExtra(BOOK_ID, bookId);
        context.startActivity(intent);
    }
}