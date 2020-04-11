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
    Users createUser(UserBO userBO);

    /**
     * 检索用户名和密码是否匹配，用于登录
     */
    Users queryUserForLogin(UserBO userBO) throws Exception;
}
