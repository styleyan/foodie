package com.iswn.service.impl;

import com.iswn.mapper.ItemsSpecMapper;
import com.iswn.service.ItemsSpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemsSpecServiceImpl implements ItemsSpecService {

    @Autowired
    private ItemsSpecMapper itemsSpecMapper;

    /**
     * 最主要逻辑，减库存
     * TODO: 这里逻辑最重要了, 涉及到高并发下会出现超卖问题
     * 1. synchronized 不推荐使用，集群下无用，性能低下
     * 2. 锁数据库: 不推荐，导致数据库性能低下
     * 3. 分布式锁 zookeeper redis
     */
    @Override
    public void decreaseItemSpecStock(String specId, int buyCounts) {
        int result = itemsSpecMapper.decreaseItemSpecStock(specId, buyCounts);

        if (result != 1) {
            throw new RuntimeException("订单创建失败，原因: 库存不足!");
        }
    }
}
