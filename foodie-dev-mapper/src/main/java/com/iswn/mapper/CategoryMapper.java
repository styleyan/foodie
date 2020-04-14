package com.iswn.mapper;

import com.iswn.pojo.Category;
import com.iswn.vo.CategoryVO;
import com.iswn.vo.NewItemsVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CategoryMapper{
    List<Category> rootLevelCat(Integer type);

    List<CategoryVO> getSubCatList(Integer rootCatId);

    List<NewItemsVO> getSixNewItemsLazy(@Param("paramsMap")Map<String, Object> map);
}