package com.wang.babymonthlyreading.data;

import android.content.Context;
import android.content.res.TypedArray;

import androidx.core.content.ContextCompat;

import com.wang.babymonthlyreading.R;
import com.wang.babymonthlyreading.adapter.BannerPagerAdapter;
import com.wang.babymonthlyreading.adapter.CustomBookAdapter;
import com.wang.babymonthlyreading.entity.BookInfo;
import com.wang.babymonthlyreading.enums.AgeRangeEnum;
import com.wang.babymonthlyreading.enums.BookClassifyInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试数据类
 */
public class TestData {

    /**
     * 模拟轮播图信息
     *
     * @return
     */
    public static List<BannerPagerAdapter.BannerItemInfo> getBannerData(Context context) {
        List<BannerPagerAdapter.BannerItemInfo> bannerItemInfoList = new ArrayList<>();
        int[] itemIdArr = context.getResources().getIntArray(R.array.banner_item_id);
        TypedArray drawableAyy = context.getResources().obtainTypedArray(R.array.banner_drawable_list);
        if (itemIdArr.length != drawableAyy.length())
            throw new ArrayIndexOutOfBoundsException("Array resource \"banner_item_id\" " +
                    "and \"banner_drawable_list\" of length is not equal");

        for (int i = 0; i < drawableAyy.length(); i++) {
            BannerPagerAdapter.BannerItemInfo itemInfo =
                    new BannerPagerAdapter.BannerItemInfo(itemIdArr[i], drawableAyy.getDrawable(i));
            bannerItemInfoList.add(itemInfo);
        }
        drawableAyy.recycle();
        return bannerItemInfoList;
    }


    /**
     * 模拟用户的定制书单信息
     *
     * @param context
     * @return
     */
    public static List<CustomBookAdapter.CustomBookInfo> getCustomBookData(Context context) {
        List<CustomBookAdapter.CustomBookInfo> bookInfoList = new ArrayList<>();
        bookInfoList.add(new CustomBookAdapter.CustomBookInfo(
                1001, ContextCompat.getDrawable(context, R.drawable.book1), 199, "比利时心灵成长"
        ));
        bookInfoList.add(new CustomBookAdapter.CustomBookInfo(
                1002, ContextCompat.getDrawable(context, R.drawable.book2), 249, "儿童摄影欣赏可"
        ));
        bookInfoList.add(new CustomBookAdapter.CustomBookInfo(
                1003, ContextCompat.getDrawable(context, R.drawable.book3), 142, "写给孩子的人文历史"
        ));
        bookInfoList.add(new CustomBookAdapter.CustomBookInfo(
                1004, ContextCompat.getDrawable(context, R.drawable.book1), 199, "比利时心灵成长"
        ));
        return bookInfoList;
    }

    /**
     * 获取搜索历史数据
     *
     * @return
     */
    public static List<String> getSearchHistoryData() {
        List<String> searchHistory = new ArrayList<>();
        searchHistory.add("少儿启蒙");
        searchHistory.add("画图");
        return searchHistory;
    }

    /**
     * 获取热门搜索数据
     *
     * @return
     */
    public static List<String> getHotSearchData() {
        List<String> hotSearch = new ArrayList<>();
        hotSearch.add("逻辑思维");
        hotSearch.add("启蒙");
        hotSearch.add("算术");
        hotSearch.add("认知");
        hotSearch.add("心理学");
        return hotSearch;
    }

    /**
     * 获取图书信息列表
     *
     * @return
     */
    public static List<BookInfo> getBookInfoList(Context context) {
        List<BookInfo> bookInfoList = new ArrayList<>();

        List<BookClassifyInfo> bookClassifyInfoList = new ArrayList<>();
        bookClassifyInfoList.add(BookClassifyInfo.RANGE_ONE.PICTURE_BOOK);
        bookClassifyInfoList.add(BookClassifyInfo.RANGE_ONE.CHILDREN_SONG);

        List<AgeRangeEnum> ageRangeEnums = new ArrayList<>();
        ageRangeEnums.add(AgeRangeEnum.RANGE_ONE);

        BookInfo bookInfo1 = new BookInfo(1001,
                ContextCompat.getDrawable(context, R.drawable.book1),
                "蓝风筝童书：鲑鱼的134…", 128f,
                ageRangeEnums, bookClassifyInfoList);

        bookInfoList.add(bookInfo1);
        bookInfoList.add(bookInfo1);
        bookInfoList.add(bookInfo1);
        bookInfoList.add(bookInfo1);
        bookInfoList.add(bookInfo1);
        bookInfoList.add(bookInfo1);
        bookInfoList.add(bookInfo1);
        bookInfoList.add(bookInfo1);
        bookInfoList.add(bookInfo1);
        bookInfoList.add(bookInfo1);
        bookInfoList.add(bookInfo1);
        bookInfoList.add(bookInfo1);
        return bookInfoList;
    }
}
