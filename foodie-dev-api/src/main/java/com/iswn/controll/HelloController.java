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

    @PostMapping("/api/userCreate")
    public JsonResult userCreate(@RequestBody UserBO userBO) {
        usersService.createUser(userBO);

        return JsonResult.success();
    }
}
