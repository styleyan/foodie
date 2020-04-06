package com.iswn.service;

import com.iswn.pojo.Users;

import java.util.List;

public interface UsersService {
    /**
     * 判断用户名是否存在
     */
    List<Users> queryAll();
}
