package com.wang.babymonthlyreading;

import org.junit.Test;

import static org.junit.Assert.*;

import android.icu.number.NumberFormatter;

import com.wang.babymonthlyreading.enums.AgeRangeEnum;
import com.wang.babymonthlyreading.enums.BookClassifyInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
//        List<BookClassifyInfo> bookClassifyInfoList = new ArrayList<>();
//        BookClassifyInfo.RANGE_ONE[] values = BookClassifyInfo.RANGE_ONE.values();
//        Collections.addAll(bookClassifyInfoList,values);
//        for (BookClassifyInfo bookClassifyInfo : bookClassifyInfoList) {
//            System.out.println(bookClassifyInfo.getDesc());
//        }
        List<BookClassifyInfo> bookClassifyInfo = AgeRangeEnum.RANGE_TWO.getBookClassifyInfo();
        for (BookClassifyInfo classifyInfo : bookClassifyInfo) {
            System.out.println(classifyInfo.getDesc());
        }
    }

    @Test
    public void test(){
        List<BookClassifyInfo> bookClassifyInfo = AgeRangeEnum.RANGE_TWO.getBookClassifyInfo();
        BookClassifyInfo desc = BookClassifyInfo.findByDesc("益智", bookClassifyInfo);
        System.out.println(desc);
    }
}