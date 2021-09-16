package com.wang.babymonthlyreading.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.wang.babymonthlyreading.R;
import com.wang.babymonthlyreading.activity.ShoppingCartActivity;

public class ShoppingCartButton extends ConstraintLayout {

    private ImageButton shoppingCarImgb;
    private TextView shoppingMsgText;

    public ShoppingCartButton(@NonNull Context context) {
        this(context, null, 0);
    }

    public ShoppingCartButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShoppingCartButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private static final String TAG = "ShoppingCartButton";

    private void initView(@NonNull Context context) {
        LayoutInflater.from(context).inflate(R.layout.custom_shopping_car_button, this, true);
        shoppingCarImgb = findViewById(R.id.cus_imgb_shopping_car);
        shoppingMsgText = findViewById(R.id.cus_text_shopping_msg);
        shoppingCarImgb.setOnClickListener(v -> {
            ShoppingCartActivity.startShoppingCartActivity(getContext());
        });
    }

    /**
     * 设置购物车数量显示器中显示的数量
     *
     * @param count
     */
    public void setShoppingMsgText(int count) {
        String value = String.valueOf(count);
        if (count > 0) {
            shoppingMsgText.setVisibility(VISIBLE);
        } else {
            shoppingMsgText.setVisibility(INVISIBLE);
        }
        shoppingMsgText.setText(value);
    }
}
