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
     * 书籍图片列表，用于详情页展示
     */
    private List<Integer> bookImgIds;
    /**
     * 书籍描述/书籍标题
     */
    private String bookDesc;
    /**
     * 书籍详细信息描述
     */
    private String bookDetail;
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


    public BookInfo(int bookId, int bookImgId, String bookDesc, String bookDetail, Float bookPrice,
                    List<AgeRangeEnum> bookAgeRanges, List<BookClassifyInfo> bookClassifyInfos) {
        this.bookId = bookId;
        this.bookImgId = bookImgId;
        this.bookDesc = bookDesc;
        this.bookDetail = bookDetail;
        this.bookPrice = bookPrice;
        this.bookAgeRanges = bookAgeRanges;
        this.bookClassifyInfos = bookClassifyInfos;
    }

    public BookInfo(int bookId, int bookImgId, List<Integer> bookImgIds, String bookDesc, String bookDetail
            , Float bookPrice, List<AgeRangeEnum> bookAgeRanges, List<BookClassifyInfo> bookClassifyInfos) {
        this.bookId = bookId;
        this.bookImgId = bookImgId;
        this.bookImgIds = bookImgIds;
        this.bookDesc = bookDesc;
        this.bookDetail = bookDetail;
        this.bookPrice = bookPrice;
        this.bookAgeRanges = bookAgeRanges;
        this.bookClassifyInfos = bookClassifyInfos;
    }
}
