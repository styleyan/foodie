package com.iswn.service.impl;

import com.iswn.mapper.CategoryMapper;
import com.iswn.pojo.Category;
import com.iswn.service.CategoryService;
import com.iswn.vo.CategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分类
 */
@Service
public class CategorySereviceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<Category> queryAllRootLevelCat() {
        List<Category> result = categoryMapper.rootLevelCat(1);

        if (result == null) {
            result = new ArrayList<>();
        }
        return result;
    }

    @Override
    public List<CategoryVO> getSubCatList(Integer rootCatId) {
        List<CategoryVO> result = categoryMapper.getSubCatList(rootCatId);

        if (result == null) {
            result = new ArrayList<>();
        }
        return result;
    }

    @Override
    public List getSixNewItemsLazy(Integer rootCatId) {
        Map<String, Object> map = new HashMap<>(8);

        map.put("rootCatId", rootCatId);
        return categoryMapper.getSixNewItemsLazy(map);
    }
}
