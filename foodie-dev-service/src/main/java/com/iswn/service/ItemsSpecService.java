package com.iswn.service;

public interface ItemsSpecService {
    /**
     * 减少库存
     */
    void decreaseItemSpecStock(String specId, int buyCounts);
}
