package com.iswn.mapper;

import com.iswn.pojo.ItemsSpec;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemsSpecMapper {
    List<ItemsSpec> querySpecById(String itemId);
}