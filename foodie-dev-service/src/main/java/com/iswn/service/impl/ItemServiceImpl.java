package com.iswn.service.impl;

import com.github.pagehelper.PageHelper;
import com.iswn.enums.CommentLevel;
import com.iswn.mapper.*;
import com.iswn.pojo.Items;
import com.iswn.pojo.ItemsImg;
import com.iswn.pojo.ItemsParam;
import com.iswn.pojo.ItemsSpec;
import com.iswn.service.ItemService;
import com.iswn.utils.DesensitizationUtil;
import com.iswn.utils.PagedGridResult;
import com.iswn.vo.CommentLevelCountsVO;
import com.iswn.vo.ItemCommentVO;
import com.iswn.vo.SearchItemsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        PageHelper.startPage(page, pageSize);
        List<ItemCommentVO> list = itemsCommentsMapper.queryItemComments(map);

        /**
         * 进行脱敏
         */
        for (ItemCommentVO itemCommentVO : list) {
            itemCommentVO.setNickname(DesensitizationUtil.commonDisplay(itemCommentVO.getNickname()));
        }

        return PagedGridResult.setterPagedGrid(list, page);
    }

    @Override
    public PagedGridResult searchItems(String keywords, String sort, Integer page, Integer pageSize) {
        Map paramMap = new HashMap(8);
        paramMap.put("keywords", keywords);
        paramMap.put("sort", sort);

        PageHelper.startPage(page, pageSize);
        List<SearchItemsVO> list = itemsMapper.searchItems(paramMap);

        return PagedGridResult.setterPagedGrid(list, page);
    }


    @Override
    public PagedGridResult searchItemsByThirdCat(Integer catId, String sort, Integer page, Integer pageSize) {
        Map paramMap = new HashMap(8);
        paramMap.put("catId", catId);
        paramMap.put("sort", sort);

        PageHelper.startPage(page, pageSize);
        List<SearchItemsVO> list = itemsMapper.searchItemsByThirdCat(paramMap);

        return PagedGridResult.setterPagedGrid(list, page);
    }
}
