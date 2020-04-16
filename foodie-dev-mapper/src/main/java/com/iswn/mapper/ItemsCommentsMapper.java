package com.iswn.mapper;

import com.iswn.vo.ItemCommentVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ItemsCommentsMapper {
    /**
     * 查询评价数量
     * @param itemId
     * @param level
     * @return
     */
    Integer queryCommentNum(String itemId, Integer level);

    /**
     * 查询评价信息
     */
     List<ItemCommentVO> queryItemComments(@Param("paramsMap") Map<String, Object> map);
}