package com.wang.babymonthlyreading.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.wang.babymonthlyreading.R;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class PersonalDataActivity extends AppCompatActivity {

    public static final String PERSONAL_DATA_ACTIVITY_RESULT = "PersonalDataActivityResult";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_data);
        initView();
    }

    private void initView() {
        Toolbar toolbar = findViewById(R.id.tbar_person_data);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        EditText phoneEdit = findViewById(R.id.edit_phone);
        EditText realNameEdit = findViewById(R.id.edit_real_name);
        RadioGroup sexRg = findViewById(R.id.rg_sex);

        NumberPicker picker = findViewById(R.id.number_pick_age);
        picker.setMinValue(0);
        picker.setMaxValue(100);
        picker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        toolbar.setNavigationOnClickListener(v -> {
            String phone = phoneEdit.getText().toString();
            String realName = realNameEdit.getText().toString();
            int sex = sexRg.getCheckedRadioButtonId() == R.id.rb_man ? 0 : 1;
            int age = picker.getValue();
            //封装结果并返回
            UserInfo userInfo = new UserInfo(phone, realName, sex, age);
            Intent data = new Intent();
            data.putExtra(PERSONAL_DATA_ACTIVITY_RESULT, userInfo);
            setResult(RESULT_OK, data);
            finish();
        });
    }

    @AllArgsConstructor
    @Setter
    @Getter
    @EqualsAndHashCode
    @ToString
    public static class UserInfo implements Parcelable {
        private final String phone;
        private final String realName;
        /**
         * 0：男
         * 1：女
         */
        private final int sex;
        private final int age;

        protected UserInfo(Parcel in) {
            phone = in.readString();
            realName = in.readString();
            sex = in.readInt();
            age = in.readInt();
        }

        public static final Creator<UserInfo> CREATOR = new Creator<UserInfo>() {
            @Override
            public UserInfo createFromParcel(Parcel in) {
                return new UserInfo(in);
            }

            @Override
            public UserInfo[] newArray(int size) {
                return new UserInfo[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(phone);
            dest.writeString(realName);
            dest.writeInt(sex);
            dest.writeInt(age);
        }
    }

}