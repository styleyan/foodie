package com.iswn.service.impl;

import com.iswn.mapper.ItemsImgMapper;
import com.iswn.mapper.ItemsMapper;
import com.iswn.mapper.ItemsParamMapper;
import com.iswn.mapper.ItemsSpecMapper;
import com.iswn.pojo.Items;
import com.iswn.pojo.ItemsImg;
import com.iswn.pojo.ItemsParam;
import com.iswn.pojo.ItemsSpec;
import com.iswn.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemsMapper itemsMapper;

    @Autowired
    private ItemsImgMapper itemsImgMapper;

    @Autowired
    private ItemsSpecMapper itemsSpecMapper;

    @Autowired
    private ItemsParamMapper itemsParamMapper;

    @Override
    public Items queryItemById(String itemId) {
        return itemsMapper.getItemById(itemId);
    }

    @Override
    public List<ItemsImg> queryItemImgList(String itemId) {
        return itemsImgMapper.queryItemsById(itemId);
    }

    @Override
    public List<ItemsSpec> queryItemSpecList(String itemId) {
        return itemsSpecMapper.querySpecById(itemId);
    }

    @Override
    public ItemsParam queryItemParam(String itemId) {
        return itemsParamMapper.queryById(itemId);
    }
}
