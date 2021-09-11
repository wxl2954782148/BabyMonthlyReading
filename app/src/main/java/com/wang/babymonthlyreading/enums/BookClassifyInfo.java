package com.wang.babymonthlyreading.enums;

import com.wang.babymonthlyreading.adapter.BookListAdapter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 书籍分类枚举
 */
public interface BookClassifyInfo {

    int getId();

    String getDesc();

    /**
     * 根据枚举的desc字段来查找枚举
     * @param desc
     * @param classifyInfoList
     * @return
     */
    static BookClassifyInfo findByDesc(String desc, List<BookClassifyInfo> classifyInfoList){
        return classifyInfoList.stream()
                .filter(bookClassifyInfo -> bookClassifyInfo.getDesc().equals(desc))
                .collect(Collectors.toList())
                .get(0);
    }

    /**
     * 0-3岁图书分类
     */
    enum RANGE_ONE implements BookClassifyInfo {

        PICTURE_BOOK(1, "绘本"),
        TOY(2, "玩具"),
        CHILDREN_SONG(3, "儿歌");

        private final int id;
        private final String desc;

        RANGE_ONE(int id, String desc) {
            this.id = id;
            this.desc = desc;
        }

        @Override
        public int getId() {
            return this.id;
        }

        @Override
        public String getDesc() {
            return this.desc;
        }
    }

    /**
     * 4-7岁图书分类
     */
    enum RANGE_TWO implements BookClassifyInfo {
        ENLIGHTEN(1, "启蒙"),
        PUZZLE(2, "益智"),
        CARTOON(3, "动漫"),
        CHILDREN_LITERATURE(4, "儿童文学"),
        ;
        private final int id;
        private final String desc;

        RANGE_TWO(int id, String desc) {
            this.id = id;
            this.desc = desc;
        }

        @Override
        public int getId() {
            return this.id;
        }

        @Override
        public String getDesc() {
            return this.desc;
        }
    }

    /**
     * 8-11岁图书分类
     */
    enum RANGE_THREE implements BookClassifyInfo {
        LITERATURE(1, "文学"),
        MATH(2, "数学"),
        ENGLISH(3, "英语"),
        ENCYCLOPAEDIA(4, "百科全书"),
        ;

        private final int id;
        private final String desc;

        RANGE_THREE(int id, String desc) {
            this.id = id;
            this.desc = desc;
        }

        @Override
        public int getId() {
            return this.id;
        }

        @Override
        public String getDesc() {
            return this.desc;
        }
    }

    /**
     * 12-14岁图书分类
     */
    enum RANGE_FOUR implements BookClassifyInfo {
        PROSE(1, "散文"),
        POETRY(2, "诗歌"),
        LITERATURE(3, "文学"),
        HISTORY(4, "历史"),
        ;

        private final int id;
        private final String desc;

        RANGE_FOUR(int id, String desc) {
            this.id = id;
            this.desc = desc;
        }

        @Override
        public int getId() {
            return this.id;
        }

        @Override
        public String getDesc() {
            return this.desc;
        }
    }
}
