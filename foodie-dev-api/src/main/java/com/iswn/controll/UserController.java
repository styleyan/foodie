package com.iswn.controll;

import com.iswn.bo.UserBO;
import com.iswn.enums.ErrorCodeEnum;
import com.iswn.exception.http.LoginBadException;
import com.iswn.exception.http.RequestBadException;
import com.iswn.pojo.Users;
import com.iswn.service.UsersService;
import com.iswn.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UsersService usersService;

    @GetMapping("/api/user/userNameIsExist")
    public JsonResult userNameIsExist(@RequestParam("userName") String userName) {
        if (StringUtils.isBlank(userName)) {
            throw new RequestBadException("sdfsfd");
        }

        boolean result = usersService.userNameIsExist(userName);
        return result ? JsonResult.success() : JsonResult.failure(233, "用户名存在");
    }

    @PostMapping("/api/user/register")
    public JsonResult register(@RequestBody UserBO userBO) {
        String pwd = userBO.getPassword();
        String username = userBO.getUsername();
        String confirmPwd = userBO.getConfirmPassword();

        if (StringUtils.isAnyBlank(pwd, username, confirmPwd)) {
            throw new RequestBadException("用户名或密码不能为空");
        }

        if (pwd.length() < 6) {
            throw new RequestBadException("密码长度不能小于");
        }

        if (!pwd.equals(confirmPwd)) {
            throw new RequestBadException("2次密码不一致");
        }

        boolean isExist = usersService.userNameIsExist(username);
        if (isExist) {
            throw new RequestBadException("用户已存在");
        }

        usersService.createUser(userBO);

        // TODO: 生成用户 token，存入 redis 会话
        // TODO: 同步购物车数据

        return JsonResult.success();
    }

    /**
     * 登录
     * @return
     */
    @PostMapping("/api/user/login")
    public JsonResult login(@RequestBody UserBO userBO) throws Exception {
        if (StringUtils.isAnyBlank(userBO.getUsername(), userBO.getPassword())) {
            throw new LoginBadException("用户名和密码不能为空");
        }

        // 登录查询
        Users users = usersService.queryUserForLogin(userBO);
        String md5String = MD5Utils.getMD5Str(users.getUsername());
        RedisUtils.setValueTimeout(md5String, users, 60*60*24*360);

        Map map = new HashMap(2);
        map.put("user", users);
        map.put("token", md5String);

        return JsonResult.success(map);
    }

    /**
     * 获取用户信息
     * @return
     */
    @GetMapping("/api/user/info")
    public JsonResult userInfo(HttpServletRequest httpServletRequest) {
        Users users = (Users)httpServletRequest.getAttribute("user");

        if (users == null) {
            return JsonResult.failure(ErrorCodeEnum.BAD_NOT_LOGIN.getCode(), ErrorCodeEnum.BAD_NOT_LOGIN.getMessage());
        }
        return JsonResult.success(users);
    }

    /**
     * 用户退出
     * @return
     */
    @PostMapping("/api/user/logout")
    public JsonResult logout( HttpServletRequest request) {
        RedisUtils.delKey(request.getHeader("token"));
        return JsonResult.success();
    }
}
