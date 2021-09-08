package com.wang.babymonthlyreading.listener;

/**
 * 购物车书籍信息改变监听器
 */
public interface OnShoppingCartChangeListener {
    /**
     * @param bookId 书籍ID
     * @param count  ID对应的书籍数量
     */
    void onShoppingCartChange(int bookId, int count);
}
