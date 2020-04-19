package com.iswn.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户创建淡定的BO对象
 */
@Setter
@Getter
@ToString
public class SubmitOrderBO {
    private String userId;
    private String itemSpecIds;
    private String addressId;
    private Integer payMethod;
    private String leftMsg;
}
