package com.iswn.mapper;


import com.iswn.pojo.ItemsParam;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsParamMapper {
    ItemsParam queryById(String itemId);
}