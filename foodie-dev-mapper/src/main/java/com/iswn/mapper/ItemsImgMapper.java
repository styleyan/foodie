package com.iswn.mapper;

import com.iswn.pojo.ItemsImg;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemsImgMapper {
    List<ItemsImg> queryItemsById(String itemId);
}