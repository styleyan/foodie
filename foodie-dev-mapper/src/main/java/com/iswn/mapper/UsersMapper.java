package com.iswn.mapper;

import com.iswn.pojo.Users;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersMapper {
    /**
     * 查询所有用户
     * @return
     */
    public List<Users> queryAll();
}