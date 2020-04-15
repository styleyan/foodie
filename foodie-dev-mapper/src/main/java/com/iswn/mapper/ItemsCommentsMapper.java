package com.iswn.mapper;

import org.springframework.stereotype.Repository;

@Repository
public interface ItemsCommentsMapper {
    Integer queryCommentNum(String itemId, Integer level);
}