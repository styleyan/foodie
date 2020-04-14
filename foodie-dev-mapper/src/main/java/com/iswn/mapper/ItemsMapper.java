package com.iswn.mapper;


import com.iswn.pojo.Items;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsMapper {
    Items getItemById(String itemId);
}