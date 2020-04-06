package com.iswn.mapper;

import com.iswn.pojo.Users;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersMapper {
    /**
     * 查询所有用户
     * @return
     */
    List<Users> queryAll();

    /**
     * 查询用户名是否存在
     */
    Integer userNameIsExist(@Param("userName") String userName);
}