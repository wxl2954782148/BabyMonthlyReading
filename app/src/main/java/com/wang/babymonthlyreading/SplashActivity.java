package com.wang.babymonthlyreading;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Window;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.Objects;

/**
 * 闪屏页实现
 */
public class SplashActivity extends AppCompatActivity {
    public static final int START_MAIN = 0x3E9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView imageView = findViewById(R.id.img_splash);
        setSplashImg(imageView);
        handler.sendEmptyMessageDelayed(START_MAIN, 1000);

    }

    private void setSplashImg(ImageView imageView) {
        TypedArray typedArray = getResources().obtainTypedArray(R.array.splash_img);
        Drawable drawable = typedArray.getDrawable(0);
        Glide.with(this).load(drawable).into(imageView);
        typedArray.recycle();
    }

    Handler handler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == START_MAIN){
                MainActivity.StartMainActivity(SplashActivity.this);
                finish();
            }
        }
    };

}