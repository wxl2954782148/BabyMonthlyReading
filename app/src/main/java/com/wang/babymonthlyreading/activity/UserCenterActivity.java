package com.wang.babymonthlyreading.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wang.babymonthlyreading.R;
import com.wang.babymonthlyreading.adapter.UserCenterAdapter;
import com.wang.babymonthlyreading.adapter.UserCenterAdapter.UserCenterAdapterInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserCenterActivity extends AppCompatActivity {
    private static final String TAG = "UserCenterActivity";
    private TextView userNameText;

    public static void startUserCenterActivity(Context context) {
        Intent intent = new Intent(context, UserCenterActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_center);
        initView();
    }

    /**
     * 启动{@link PersonalDataActivity}的启动器
     */
    ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                Intent data = result.getData();
                if (data != null && result.getResultCode() == RESULT_OK) {
                    PersonalDataActivity.UserInfo userInfo =
                            data.getParcelableExtra(PersonalDataActivity.PERSONAL_DATA_ACTIVITY_RESULT);
                    Log.d(TAG, "userInfo_result" + userInfo);
                }
            });

    private void initView() {
        Toolbar toolbar = findViewById(R.id.tbar_user_center);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        userNameText = findViewById(R.id.text_user_name);
        userNameText.setOnClickListener(v -> {
            launcher.launch(new Intent(UserCenterActivity.this, PersonalDataActivity.class));
        });

        RecyclerView userCenterRecycler = findViewById(R.id.recycle_user_center);
        userCenterRecycler.setLayoutManager(new LinearLayoutManager(this));
        UserCenterAdapter adapter = new UserCenterAdapter(getAdapterInfo());
        userCenterRecycler.setAdapter(adapter);
    }

    private List<UserCenterAdapterInfo> getAdapterInfo() {
        List<UserCenterAdapterInfo> list = new ArrayList<>();

        Resources resources = getResources();
        String[] titles = resources.getStringArray(R.array.user_center_title);
        TypedArray icons = resources.obtainTypedArray(R.array.user_center_icon);
        String[] toClasses = resources.getStringArray(R.array.user_center_to_class);

        for (int i = 0; i < icons.length(); i++) {
            Drawable drawable = icons.getDrawable(i);
            UserCenterAdapterInfo info = new UserCenterAdapterInfo(drawable, titles[i],
                    toClasses[i]);
            list.add(info);
        }
        icons.recycle();
        return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem index = menu.add(1, 910975, 1, "首页");
        index.setIcon(R.drawable.ic_index);
        index.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == 910975) {
            MainActivity.StartMainActivity(this);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}