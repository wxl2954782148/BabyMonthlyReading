package com.wang.babymonthlyreading.data;

import android.content.Context;
import android.content.res.TypedArray;

import com.wang.babymonthlyreading.R;
import com.wang.babymonthlyreading.adapter.BannerPagerAdapter;
import com.wang.babymonthlyreading.adapter.CustomBookAdapter;
import com.wang.babymonthlyreading.adapter.SubscribeBookAdapter;
import com.wang.babymonthlyreading.entity.BookInfo;
import com.wang.babymonthlyreading.enums.AgeRangeEnum;
import com.wang.babymonthlyreading.enums.BookClassifyInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
        TypedArray drawableAyy =
                context.getResources().obtainTypedArray(R.array.banner_drawable_list);
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
//        bookInfoList.add(new CustomBookAdapter.CustomBookInfo(
//                1001, ContextCompat.getDrawable(context, R.drawable.book1), 199, "比利时心灵成长"
//        ));
//        bookInfoList.add(new CustomBookAdapter.CustomBookInfo(
//                1002, ContextCompat.getDrawable(context, R.drawable.book2), 249, "儿童摄影欣赏可"
//        ));
//        bookInfoList.add(new CustomBookAdapter.CustomBookInfo(
//                1003, ContextCompat.getDrawable(context, R.drawable.book3), 142, "写给孩子的人文历史"
//        ));
//        bookInfoList.add(new CustomBookAdapter.CustomBookInfo(
//                1004, ContextCompat.getDrawable(context, R.drawable.book1), 199, "比利时心灵成长"
//        ));
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
     * 图书信息所有列表
     *
     * @return
     */
    public static List<BookInfo> getBookInfoList(Context context) {
        String bookDetail = context.getString(R.string.book_detail);

        List<BookInfo> bookInfoList = new ArrayList<>();

        List<AgeRangeEnum> ageRangeEnums1 = new ArrayList<>();
        List<BookClassifyInfo> bookClassifyInfoList1 = new ArrayList<>();
        ageRangeEnums1.add(AgeRangeEnum.RANGE_ONE);
        ageRangeEnums1.add(AgeRangeEnum.RANGE_TWO);
        bookClassifyInfoList1.add(BookClassifyInfo.RANGE_ONE.CHILDREN_SONG);
        bookClassifyInfoList1.add(BookClassifyInfo.RANGE_TWO.CHILDREN_LITERATURE);
        BookInfo bookInfo1 = new BookInfo(1001, R.drawable.book1,
                "蓝风筝童书：鲑鱼的134…", bookDetail, 128.00f, ageRangeEnums1, bookClassifyInfoList1);
        bookInfoList.add(bookInfo1);

        List<AgeRangeEnum> ageRangeEnums2 = new ArrayList<>();
        List<BookClassifyInfo> bookClassifyInfoList2 = new ArrayList<>();
        ageRangeEnums2.add(AgeRangeEnum.RANGE_THREE);
        bookClassifyInfoList2.add(BookClassifyInfo.RANGE_THREE.ENCYCLOPAEDIA);
        BookInfo bookInfo2 = new BookInfo(1002, R.drawable.book2,
                "蓝风筝童书：鲑鱼的134…", bookDetail, 89.00f, ageRangeEnums2, bookClassifyInfoList2);
        bookInfoList.add(bookInfo2);


        List<AgeRangeEnum> ageRangeEnums3 = new ArrayList<>();
        List<BookClassifyInfo> bookClassifyInfoList3 = new ArrayList<>();
        ageRangeEnums3.add(AgeRangeEnum.RANGE_FOUR);
        ageRangeEnums3.add(AgeRangeEnum.RANGE_THREE);
        bookClassifyInfoList3.add(BookClassifyInfo.RANGE_THREE.LITERATURE);
        bookClassifyInfoList3.add(BookClassifyInfo.RANGE_FOUR.HISTORY);
        BookInfo bookInfo3 = new BookInfo(1003, R.drawable.book3,
                "蓝风筝童书：鲑鱼的134…", bookDetail, 109.00f, ageRangeEnums3, bookClassifyInfoList3);
        bookInfoList.add(bookInfo3);

        List<AgeRangeEnum> ageRangeEnums4 = new ArrayList<>();
        List<BookClassifyInfo> bookClassifyInfoList4 = new ArrayList<>();
        ageRangeEnums4.add(AgeRangeEnum.RANGE_ONE);
        ageRangeEnums4.add(AgeRangeEnum.RANGE_TWO);
        bookClassifyInfoList4.add(BookClassifyInfo.RANGE_ONE.PICTURE_BOOK);
        bookClassifyInfoList4.add(BookClassifyInfo.RANGE_TWO.ENLIGHTEN);
        BookInfo bookInfo4 = new BookInfo(1004, R.drawable.book4,
                "蓝风筝童书：鲑鱼的134…", bookDetail, 99.00f, ageRangeEnums4, bookClassifyInfoList4);
        bookInfoList.add(bookInfo4);

        List<AgeRangeEnum> ageRangeEnums5 = new ArrayList<>();
        List<BookClassifyInfo> bookClassifyInfoList5 = new ArrayList<>();
        ageRangeEnums5.add(AgeRangeEnum.RANGE_ONE);
        ageRangeEnums5.add(AgeRangeEnum.RANGE_THREE);
        bookClassifyInfoList5.add(BookClassifyInfo.RANGE_ONE.PICTURE_BOOK);
        bookClassifyInfoList5.add(BookClassifyInfo.RANGE_THREE.ENGLISH);
        BookInfo bookInfo5 = new BookInfo(1005, R.drawable.book5,
                "蓝风筝童书：鲑鱼的134…", bookDetail, 123.00f, ageRangeEnums5, bookClassifyInfoList5);
        bookInfoList.add(bookInfo5);

        List<AgeRangeEnum> ageRangeEnums6 = new ArrayList<>();
        List<BookClassifyInfo> bookClassifyInfoList6 = new ArrayList<>();
        ageRangeEnums6.add(AgeRangeEnum.RANGE_ONE);
        ageRangeEnums6.add(AgeRangeEnum.RANGE_THREE);
        bookClassifyInfoList6.add(BookClassifyInfo.RANGE_ONE.PICTURE_BOOK);
        bookClassifyInfoList6.add(BookClassifyInfo.RANGE_ONE.TOY);
        bookClassifyInfoList6.add(BookClassifyInfo.RANGE_THREE.ENGLISH);
        BookInfo bookInfo6 = new BookInfo(1006, R.drawable.book6,
                "蓝风筝童书：鲑鱼的134…", bookDetail, 93.00f, ageRangeEnums6, bookClassifyInfoList6);
        bookInfoList.add(bookInfo6);

        List<AgeRangeEnum> ageRangeEnums7 = new ArrayList<>();
        List<BookClassifyInfo> bookClassifyInfoList7 = new ArrayList<>();
        ageRangeEnums7.add(AgeRangeEnum.RANGE_ONE);
        ageRangeEnums7.add(AgeRangeEnum.RANGE_TWO);
        bookClassifyInfoList7.add(BookClassifyInfo.RANGE_ONE.PICTURE_BOOK);
        bookClassifyInfoList7.add(BookClassifyInfo.RANGE_ONE.TOY);
        bookClassifyInfoList7.add(BookClassifyInfo.RANGE_TWO.CARTOON);
        BookInfo bookInfo7 = new BookInfo(1007, R.drawable.book7,
                "蓝风筝童书：鲑鱼的134…", bookDetail, 95.00f, ageRangeEnums7, bookClassifyInfoList7);
        bookInfoList.add(bookInfo7);

        List<AgeRangeEnum> ageRangeEnums8 = new ArrayList<>();
        List<BookClassifyInfo> bookClassifyInfoList8 = new ArrayList<>();
        ageRangeEnums8.add(AgeRangeEnum.RANGE_ONE);
        ageRangeEnums8.add(AgeRangeEnum.RANGE_TWO);
        bookClassifyInfoList8.add(BookClassifyInfo.RANGE_ONE.PICTURE_BOOK);
        bookClassifyInfoList8.add(BookClassifyInfo.RANGE_ONE.TOY);
        bookClassifyInfoList8.add(BookClassifyInfo.RANGE_TWO.CARTOON);
        BookInfo bookInfo8 = new BookInfo(1008, R.drawable.book8,
                "蓝风筝童书：鲑鱼的134…", bookDetail, 65.00f, ageRangeEnums8, bookClassifyInfoList8);
        bookInfoList.add(bookInfo8);

        List<AgeRangeEnum> ageRangeEnums9 = new ArrayList<>();
        List<BookClassifyInfo> bookClassifyInfoList9 = new ArrayList<>();
        ageRangeEnums9.add(AgeRangeEnum.RANGE_ONE);
        bookClassifyInfoList9.add(BookClassifyInfo.RANGE_ONE.PICTURE_BOOK);
        bookClassifyInfoList9.add(BookClassifyInfo.RANGE_ONE.TOY);
        BookInfo bookInfo9 = new BookInfo(1009, R.drawable.book9,
                "蓝风筝童书：鲑鱼的134…", bookDetail, 165.00f, ageRangeEnums9, bookClassifyInfoList9);
        bookInfoList.add(bookInfo9);

        List<AgeRangeEnum> ageRangeEnums10 = new ArrayList<>();
        List<BookClassifyInfo> bookClassifyInfoList10 = new ArrayList<>();
        ageRangeEnums10.add(AgeRangeEnum.RANGE_ONE);
        ageRangeEnums10.add(AgeRangeEnum.RANGE_FOUR);
        bookClassifyInfoList10.add(BookClassifyInfo.RANGE_ONE.PICTURE_BOOK);
        bookClassifyInfoList10.add(BookClassifyInfo.RANGE_ONE.TOY);
        bookClassifyInfoList10.add(BookClassifyInfo.RANGE_FOUR.POETRY);
        BookInfo bookInfo10 = new BookInfo(1010, R.drawable.book10,
                "蓝风筝童书：鲑鱼的134…", bookDetail, 55.00f, ageRangeEnums10, bookClassifyInfoList10);
        bookInfoList.add(bookInfo10);

        List<AgeRangeEnum> ageRangeEnums11 = new ArrayList<>();
        List<BookClassifyInfo> bookClassifyInfoList11 = new ArrayList<>();
        ageRangeEnums11.add(AgeRangeEnum.RANGE_ONE);
        bookClassifyInfoList11.add(BookClassifyInfo.RANGE_ONE.PICTURE_BOOK);
        bookClassifyInfoList11.add(BookClassifyInfo.RANGE_ONE.TOY);
        bookClassifyInfoList11.add(BookClassifyInfo.RANGE_ONE.CHILDREN_SONG);
        BookInfo bookInfo11 = new BookInfo(1011, R.drawable.book11,
                "蓝风筝童书：鲑鱼的134…", bookDetail, 132.00f, ageRangeEnums11, bookClassifyInfoList11);
        bookInfoList.add(bookInfo11);

        List<AgeRangeEnum> ageRangeEnums12 = new ArrayList<>();
        List<BookClassifyInfo> bookClassifyInfoList12 = new ArrayList<>();
        ageRangeEnums12.add(AgeRangeEnum.RANGE_ONE);
        bookClassifyInfoList12.add(BookClassifyInfo.RANGE_ONE.PICTURE_BOOK);
        bookClassifyInfoList12.add(BookClassifyInfo.RANGE_ONE.TOY);
        bookClassifyInfoList12.add(BookClassifyInfo.RANGE_ONE.CHILDREN_SONG);
        BookInfo bookInfo12 = new BookInfo(1012, R.drawable.book12,
                "蓝风筝童书：鲑鱼的134…", bookDetail, 182.00f, ageRangeEnums12, bookClassifyInfoList12);
        bookInfoList.add(bookInfo12);
        return bookInfoList;
    }

    /**
     * 根据书籍Id查询书籍信息
     *
     * @return
     */
    public static BookInfo selectBookById(Context context, int bookId) {
        String bookDetail = context.getString(R.string.book_detail);
        List<AgeRangeEnum> ageRangeEnums1 = new ArrayList<>();
        List<BookClassifyInfo> bookClassifyInfoList1 = new ArrayList<>();
        ageRangeEnums1.add(AgeRangeEnum.RANGE_ONE);
        ageRangeEnums1.add(AgeRangeEnum.RANGE_TWO);
        bookClassifyInfoList1.add(BookClassifyInfo.RANGE_ONE.CHILDREN_SONG);
        bookClassifyInfoList1.add(BookClassifyInfo.RANGE_TWO.CHILDREN_LITERATURE);
        List<Integer> imgs = new ArrayList<>();
        imgs.add(R.drawable.book1);
        imgs.add(R.drawable.book10);
        imgs.add(R.drawable.book11);
        imgs.add(R.drawable.book12);
        BookInfo bookInfo1 = new BookInfo(1001, R.drawable.book1, imgs,
                "蓝风筝童书：鲑鱼的134…", bookDetail, 128.00f, ageRangeEnums1, bookClassifyInfoList1);
        return bookInfo1;
    }

    /**
     * 根据指定条件来过滤查询图书列表
     *
     * @param context
     * @param predicate
     * @return
     */
    public static List<BookInfo> getBookInfoWithPredicate(Context context,
                                                          Predicate<BookInfo> predicate) {
        return getBookInfoList(context).stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    /**
     * 获取订阅书籍列表
     *
     * @return
     */
    public static List<SubscribeBookAdapter.SubscribeBookInfo> getSubscribeBookList(Context context) {
        List<SubscribeBookAdapter.SubscribeBookInfo> list = new ArrayList<>();
        ArrayList<Integer> imgs = new ArrayList<>();
        imgs.add(R.drawable.book1);
        imgs.add(R.drawable.book2);
        imgs.add(R.drawable.book3);
        list.add(new SubscribeBookAdapter.SubscribeBookInfo(2001, "一岁认知书单(共30本)", false, "2–3" +
                "岁是一个比较特别的年龄段,\n这个时期的孩子语言、\n思维和行动等能力都迅速发展，开始有自我意识，从故事内容的选择上可以从之前的三段式重复增加有简单情节变化的绘本。",
                imgs));

        ArrayList<Integer> imgs2 = new ArrayList<>();
        imgs2.add(R.drawable.book4);
        imgs2.add(R.drawable.book5);
        imgs2.add(R.drawable.book6);
        list.add(new SubscribeBookAdapter.SubscribeBookInfo(2002, "两岁认知书单(共10本)", false, "2–3" +
                "岁是一个比较特别的年龄段,\n这个时期的孩子语言、\n思维和行动等能力都迅速发展，开始有自我意识，从故事内容的选择上可以从之前的三段式重复增加有简单情节变化的绘本。",
                imgs2));

        ArrayList<Integer> imgs3 = new ArrayList<>();
        imgs3.add(R.drawable.book7);
        imgs3.add(R.drawable.book8);
        imgs3.add(R.drawable.book9);
        list.add(new SubscribeBookAdapter.SubscribeBookInfo(2003, "两岁认知书单(共10本)", false, "2–3" +
                "岁是一个比较特别的年龄段,\n这个时期的孩子语言、\n思维和行动等能力都迅速发展，开始有自我意识，从故事内容的选择上可以从之前的三段式重复增加有简单情节变化的绘本。",
                imgs3));
        return list;
    }
}
