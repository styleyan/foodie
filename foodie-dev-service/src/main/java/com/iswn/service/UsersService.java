package com.iswn.service;

import com.iswn.bo.UserBO;
import com.iswn.pojo.Users;


public interface UsersService {
    /**
     * 查询用户名是否存在
     */
    boolean userNameIsExist(String userName);

    /**
     * 创建用户
     */
    public Users createUser(UserBO userBO);
}
