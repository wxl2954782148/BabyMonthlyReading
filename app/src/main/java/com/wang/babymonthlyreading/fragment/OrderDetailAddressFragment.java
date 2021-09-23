package com.wang.babymonthlyreading.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wang.babymonthlyreading.R;
import com.wang.babymonthlyreading.data.TestData;
import com.wang.babymonthlyreading.entity.UserInfo;

public class OrderDetailAddressFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_address, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        UserInfo userInfo = getUserInfo();
        if (!userInfo.getShippingInfos().isEmpty()) {
            TextView userNameText = inflate.findViewById(R.id.text_user_name);
            userNameText.setText(userInfo.getUserName());
            TextView consigneePhoneText = inflate.findViewById(R.id.text_consignee_phone);
            consigneePhoneText.setText(userInfo.getShippingInfos().get(0).getConsigneePhone());
            TextView consigneeAddrText = inflate.findViewById(R.id.text_consignee_address);
            consigneeAddrText.setText(userInfo.getShippingInfos().get(0).getAddress());
        } else {
            //填写收货地址
            RelativeLayout relativeLayout = inflate.findViewById(R.id.relativeLayout);
            relativeLayout.setVisibility(View.INVISIBLE);
            inflate.findViewById(R.id.text_address_tips).setVisibility(View.VISIBLE);
        }
    }

    /**
     * 获取用户信息
     */
    private UserInfo getUserInfo() {
        return TestData.getUserInfo();
    }
}
