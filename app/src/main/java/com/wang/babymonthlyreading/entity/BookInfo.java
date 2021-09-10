package com.wang.babymonthlyreading.entity;

import android.graphics.drawable.Drawable;

import com.wang.babymonthlyreading.enums.AgeRangeEnum;
import com.wang.babymonthlyreading.enums.BookClassifyInfo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 书籍信息相关
 */
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class BookInfo {
    /**
     * 书籍ID
     */
    private int bookId;
    /**
     * 书籍图片
     */
    private int bookImgId;
    /**
     * 书籍描述
     */
    private String bookDesc;
    /**
     * 书籍价格
     */
    private Float bookPrice;
    /**
     * 书籍年龄范围
     */
    private List<AgeRangeEnum> bookAgeRanges;
    /**
     * 书籍分类
     */
    private List<BookClassifyInfo> bookClassifyInfos;
}
