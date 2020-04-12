package com.iswn.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 二级分类查询内容
 */
@Setter
@Getter
@ToString
public class CategoryVO {
    private Integer id;
    private String name;
    private Integer type;
    private Integer fatherId;

    /**
     * 三级分类 vo list
     */
    private List<SubCategoryVO> subCatList;
}
