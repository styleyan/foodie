package com.iswn.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.iswn.enums.CommentLevel;
import com.iswn.mapper.*;
import com.iswn.pojo.Items;
import com.iswn.pojo.ItemsImg;
import com.iswn.pojo.ItemsParam;
import com.iswn.pojo.ItemsSpec;
import com.iswn.service.ItemService;
import com.iswn.utils.PagedGridResult;
import com.iswn.vo.CommentLevelCountsVO;
import com.iswn.vo.ItemCommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public PagedGridResult queryPagedComments(String itemId, Integer level, Integer page, Integer pageSize) {
        Map<String, Object> map = new HashMap<>(8);
        map.put("itemId", itemId);
        map.put("level", level);

        // 使用 mybatis-pagehelper
        PageHelper.startPage(page, pageSize);
        List<ItemCommentVO> list = itemsCommentsMapper.queryItemComments(map);

        return setterPagedGrid(list, page);
    }

    /**
     * 统一分装设置翻页
     * @param list
     * @param page
     * @return
     */
    private PagedGridResult setterPagedGrid(List<?> list, Integer page) {
        PageInfo<?> pageList = new PageInfo<>(list);
        PagedGridResult grid = new PagedGridResult();
        grid.setPage(page);
        grid.setRows(list);
        grid.setTotal(pageList.getPages());
        grid.setRecords(pageList.getTotal());

        return grid;
    }
}
