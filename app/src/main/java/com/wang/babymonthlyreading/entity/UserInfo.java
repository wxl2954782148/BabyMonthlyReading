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
    private String nickname;
    private Gender gender;
    private int age;
    private List<ShippingInfo> shippingInfos;


    public enum Gender {
        MAN(1), WOMAN(2), UNKNOWN(0);

        Gender(int id) {
        }
    }
}
