package com.iswn.mapper;


import com.iswn.pojo.Items;
import com.iswn.vo.SearchItemsVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ItemsMapper {
    Items getItemById(String itemId);

    /**
     * 根据商品名搜索商品
     * @param hashMap
     * @return
     */
    List<SearchItemsVO> searchItems(@Param("paramsMap") Map<String, Object> hashMap);
}