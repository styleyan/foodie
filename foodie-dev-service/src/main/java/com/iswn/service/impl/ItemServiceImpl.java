package com.iswn.service.impl;

import com.iswn.enums.CommentLevel;
import com.iswn.mapper.*;
import com.iswn.pojo.Items;
import com.iswn.pojo.ItemsImg;
import com.iswn.pojo.ItemsParam;
import com.iswn.pojo.ItemsSpec;
import com.iswn.service.ItemService;
import com.iswn.vo.CommentLevelCountsVO;
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

    @Autowired
    private ItemsCommentsMapper itemsCommentsMapper;

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

    @Override
    public CommentLevelCountsVO queryCommentCounts(String itemId) {
        Integer goodCounts = itemsCommentsMapper.queryCommentNum(itemId, CommentLevel.GOOD.getType());
        Integer normals = itemsCommentsMapper.queryCommentNum(itemId, CommentLevel.NORMAL.getType());
        Integer bad = itemsCommentsMapper.queryCommentNum(itemId, CommentLevel.NORMAL.getType());

        CommentLevelCountsVO commentLevelCountsVO = new CommentLevelCountsVO();

        commentLevelCountsVO.setBadCounts(bad);
        commentLevelCountsVO.setGoodCounts(goodCounts);
        commentLevelCountsVO.setNormalCounts(normals);
        commentLevelCountsVO.setTotalCounts(goodCounts + normals + bad);

        return commentLevelCountsVO;
    }
}
