package com.wang.babymonthlyreading.entity;

import java.util.List;

import lombok.Data;

/**
 * 用户信息
 */
@Data
public class UserInfo {
    private int userId;
    private String userName;
    private List<ShippingInfo> shippingInfos;
}
