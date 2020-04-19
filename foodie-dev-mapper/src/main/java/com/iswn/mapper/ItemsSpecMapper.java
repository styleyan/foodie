package com.iswn.mapper;

import com.iswn.pojo.ItemsSpec;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemsSpecMapper {
    List<ItemsSpec> querySpecById(String itemId);

    /**
     * 根据id查询商品价格
     * @return
     */
    ItemsSpec queryItemSpecById(@Param("specId") String specId);
}