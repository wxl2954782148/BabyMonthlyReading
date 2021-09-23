package com.wang.babymonthlyreading.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wang.babymonthlyreading.R;
import com.wang.babymonthlyreading.adapter.ShoppingCartAdapter.ShoppingCartInfo;
import com.wang.babymonthlyreading.adapter.SubmitOrderAdapter;

import java.io.Serializable;
import java.util.List;

/**
 * 提交订单页面，由购物车页面打开
 */
public class SubmitOrderActivity extends AppCompatActivity {
    private static final String TAG = "SubmitOrderActivity";
    private static final String ORDER_LIST = "ORDER_LIST";
    //从购物车页面传递来的订单列表
    private List<ShoppingCartInfo> orderSubmitInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_order);
        orderSubmitInfo = (List<ShoppingCartInfo>) getIntent().getSerializableExtra(ORDER_LIST);
        initView();
    }

    private void initView() {
        RecyclerView submitOderRecycler = findViewById(R.id.recycle_submit_order);
        submitOderRecycler.setLayoutManager(new LinearLayoutManager(this));
        submitOderRecycler.setAdapter(new SubmitOrderAdapter(orderSubmitInfo));
        TextView countText = findViewById(R.id.text_check_count);
        countText.setText(String.valueOf(getBookCount()));

        Button submitBtn = findViewById(R.id.btn_submit);
        submitBtn.setOnClickListener(v -> {
            //TODO 跳转到订单详情页
        });
    }

    /**
     * 获取订单列表商品数量
     *
     * @return
     */
    @NonNull
    private Integer getBookCount() {
        return orderSubmitInfo.stream()
                .map(ShoppingCartInfo::getBookCount)
                .reduce(Integer::sum)
                .orElse(0);
    }

    /**
     * 打开下单页面，需要传入购物车页面中用户购物的商品列表
     *
     * @param context
     * @param infos
     */
    public static void startSubmitOrderActivity(Context context, List<ShoppingCartInfo> infos) {
        if (infos == null || infos.isEmpty()) {
            Toast.makeText(context, "选择领取的书籍", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(context, SubmitOrderActivity.class);
        intent.putExtra(ORDER_LIST, (Serializable) infos);
        context.startActivity(intent);
    }

}