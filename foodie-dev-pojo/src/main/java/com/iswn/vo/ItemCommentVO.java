package com.iswn.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 用于展示商品评价的VO
 */
@Getter
@Setter
@ToString
public class ItemCommentVO {
    private Integer commentLevel;
    private String  content;
    private String sepcName;
    private Date createdTime;
    private String userFace;
    private String nickname;
}
