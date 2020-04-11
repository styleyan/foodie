package com.iswn.service.impl;

import com.iswn.bo.UserBO;
import com.iswn.enums.SexEnum;
import com.iswn.exception.http.LoginBadException;
import com.iswn.mapper.UsersMapper;
import com.iswn.pojo.Users;
import com.iswn.service.UsersService;
import com.iswn.utils.MD5Utils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersMapper usersMapper;

    /**
     * 默认用户头像地址
     */
    private static final String USER_FACE = "https://www.isyxf.com/_nuxt/img/054b8a8.png";

    @Override
    public boolean userNameIsExist(String userName) {
        int count = usersMapper.userNameIsExist(userName);
        boolean result = count == 0 ? false : true;
        return result;
    }

    @Override
    public Users createUser(UserBO userBO) {
        Users user = new Users();
        user.setUsername(userBO.getUsername());

        // TODO: 通过 n3r 生成分布式id
        String userId = Sid.nextShort();
        user.setId(userId);
        try {
            user.setPassword(MD5Utils.getMD5Str(userBO.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 默认用户昵称为用户名
        user.setNickname(userBO.getUsername());
        // 默认头像
        user.setFace(USER_FACE);
        // 用户生日 TODO: 没有日期转换工具类，暂时去掉 user.setBirthday();
        user.setBirthday(new Date());
        user.setSex(SexEnum.SECRET.getType());

        user.setCreatedTime(new Date());
        user.setUpdatedTime(new Date());

        usersMapper.insertUser(user);
        return null;
    }

    @Override
    public Users queryUserForLogin(UserBO userBO) throws Exception {
        String md5Str = MD5Utils.getMD5Str(userBO.getPassword());
        userBO.setPassword(md5Str);

        // 查询
        Users users = usersMapper.queryUserByLogin(userBO);
        if (users == null) {
            throw new LoginBadException("用户名或密码不正确");
        }
        return users;
    }
}
