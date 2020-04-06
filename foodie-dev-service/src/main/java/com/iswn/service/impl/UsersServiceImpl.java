package com.iswn.service.impl;

import com.iswn.mapper.UsersMapper;
import com.iswn.pojo.Users;
import com.iswn.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public List<Users> queryAll() {
        return usersMapper.queryAll();
    }
}
