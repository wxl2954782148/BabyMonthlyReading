package com.wang.babymonthlyreading.entity;

import lombok.Data;

/**
 * 用户收货信息
 */
@Data
public class ShippingInfo {
    /**
     * 收货地址
     */
    private String address;
    /**
     * 收货人电话
     */
    private String consigneePhone;
}
