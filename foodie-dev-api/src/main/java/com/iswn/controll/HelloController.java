package com.iswn.controll;

import com.iswn.bo.UserBO;
import com.iswn.exception.http.RequestBadException;
import com.iswn.service.UsersService;
import com.iswn.utils.JsonResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/api/userNameIsExist")
    public JsonResult userNameIsExist(@RequestParam("userName") String userName) {
        if (StringUtils.isBlank(userName)) {
            throw new RequestBadException("sdfsfd");
        }

        boolean result = usersService.userNameIsExist(userName);
        return result ? JsonResult.success() : JsonResult.failure(233, "用户名存在");
    }

    @PostMapping("/api/regist")
    public JsonResult regist(@RequestBody UserBO userBO) {
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
            return JsonResult.failure(3000, "用户已存在");
        }

        usersService.createUser(userBO);

        return JsonResult.success();
    }
}
