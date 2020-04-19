package com.iswn.mapper;

import com.iswn.pojo.ItemsImg;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemsImgMapper {
    /**
     * 根据商品id获取图片列表
     * @param itemId
     * @return
     */
    List<ItemsImg> queryItemsById(String itemId);

    /**
     * 根据商品id 获得商品图片主图url
     */
    ItemsImg queryItemMainImgById(String itemId);
}