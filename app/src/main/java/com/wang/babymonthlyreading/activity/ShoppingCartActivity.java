package com.wang.babymonthlyreading.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wang.babymonthlyreading.R;
import com.wang.babymonthlyreading.adapter.ShoppingCartAdapter;
import com.wang.babymonthlyreading.adapter.ShoppingCartAdapter.ShoppingCartInfo;
import com.wang.babymonthlyreading.data.TestData;

import java.util.List;
import java.util.Objects;

/**
 * 购物车信息页面
 */
public class ShoppingCartActivity extends AppCompatActivity {

    private TextView checkCountText;
    //购物车列表信息
    private List<ShoppingCartInfo> infos;
    private Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        initView();
    }

    private void initView() {
        Toolbar toolbar = findViewById(R.id.tbar_shopping_cart);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(v -> {
            finish();
        });

        //TODO 如果用户已开通VIP则隐藏该提示
        LinearLayout openVipTipsLinear = findViewById(R.id.linear_open_vip_tips);
        Button openVipTipsBtn = findViewById(R.id.btn_open_vip_tips);
        openVipTipsBtn.setOnClickListener(v -> {
            //TODO 跳转到开通VIP界面
        });
        TextView openVipText = findViewById(R.id.text_open_vip_text);
        String tipsContent = "每月可领取<span style=\"color:#FF870A\">" + 2 + "</span>本定制图书";
        openVipText.setText(Html.fromHtml(tipsContent, 0));

        RecyclerView shoppingCartRecycler = findViewById(R.id.recycle_shopping_cart);
        shoppingCartRecycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        ShoppingCartAdapter shoppingCartAdapter = new ShoppingCartAdapter(TestData.getShoppingInfos(this));
        shoppingCartAdapter.setCartCheckedListener(checkedInfos -> {
            int checkedCount = checkedInfos.stream()
                    .map(ShoppingCartInfo::getBookCount)
                    .reduce(Integer::sum)
                    .orElse(0);
            checkCountText.setText(String.valueOf(checkedCount));
            infos = checkedInfos;
        });
        shoppingCartRecycler.setAdapter(shoppingCartAdapter);

        checkCountText = findViewById(R.id.text_check_count);

        // 跳转提交订单页面
        submitBtn = findViewById(R.id.btn_submit);
        submitBtn.setOnClickListener(v -> {
            SubmitOrderActivity.startSubmitOrderActivity(this, infos);
        });
    }

    public static void startShoppingCartActivity(Context context) {
        Intent intent = new Intent(context, ShoppingCartActivity.class);
        context.startActivity(intent);
    }
}