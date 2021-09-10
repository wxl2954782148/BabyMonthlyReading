package com.wang.babymonthlyreading.enums;

import static com.wang.babymonthlyreading.enums.BookClassifyInfo.RANGE_ONE;
import static com.wang.babymonthlyreading.enums.BookClassifyInfo.RANGE_THREE;
import static com.wang.babymonthlyreading.enums.BookClassifyInfo.RANGE_TWO;

import java.util.Arrays;
import java.util.List;

/**
 * 年龄范围枚举
 */
public enum AgeRangeEnum {
    RANGE_ONE("0-3岁", 1) {
        @Override
        public List<BookClassifyInfo> getBookClassifyInfo() {
            RANGE_ONE[] values = BookClassifyInfo.RANGE_ONE.values();
            return Arrays.asList(values);
        }
    }, RANGE_TWO("4-7岁", 2) {
        @Override
        public List<BookClassifyInfo> getBookClassifyInfo() {
            RANGE_TWO[] values = BookClassifyInfo.RANGE_TWO.values();
            return Arrays.asList(values);
        }
    }, RANGE_THREE("8-11岁", 3) {
        @Override
        public List<BookClassifyInfo> getBookClassifyInfo() {
            RANGE_THREE[] values = BookClassifyInfo.RANGE_THREE.values();
            return Arrays.asList(values);
        }
    }, RANGE_FOUR("12-14岁", 4) {
        @Override
        public List<BookClassifyInfo> getBookClassifyInfo() {
            BookClassifyInfo.RANGE_FOUR[] values = BookClassifyInfo.RANGE_FOUR.values();
            return Arrays.asList(values);
        }
    };

    private final String ageRangeDesc;
    private final int ageRangeId;

    AgeRangeEnum(String s, int id) {
        this.ageRangeDesc = s;
        this.ageRangeId = id;
    }

    public String getAgeRangeDesc() {
        return ageRangeDesc;
    }

    public int getAgeRangeId() {
        return ageRangeId;
    }

    /**
     * 返回每个年龄段对应的书籍分类信息
     *
     * @return
     */
    public abstract List<BookClassifyInfo> getBookClassifyInfo();
}
