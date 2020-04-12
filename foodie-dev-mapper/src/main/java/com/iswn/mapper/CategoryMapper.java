package com.iswn.mapper;

import com.iswn.pojo.Category;
import com.iswn.vo.CategoryVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryMapper{
    List<Category> rootLevelCat(Integer type);

    List<CategoryVO> getSubCatList(Integer rootCatId);
}