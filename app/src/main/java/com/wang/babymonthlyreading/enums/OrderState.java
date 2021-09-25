package com.wang.babymonthlyreading.enums;

public enum OrderState {
    UNPAID("待支付"), WAITDELIVER("待发货"), SHIPPED("已发货"), TO_EVALUATE("待评价"), COMPLETED("已完成");

    private final String desc;

    OrderState(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
