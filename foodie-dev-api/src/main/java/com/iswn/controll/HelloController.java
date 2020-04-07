package com.iswn.controll;

import com.iswn.exception.http.RequestBadException;
import com.iswn.service.UsersService;
import com.iswn.utils.JsonResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/api/queryAllUser")
    public Object queryAllUser() {
        return usersService.queryAll();
    }

    @GetMapping("/api/userNameIsExist")
    public JsonResult userNameIsExist(@RequestParam("userName") String userName) {
        if (StringUtils.isBlank(userName)) {
            throw new RequestBadException("sdfsfd");
        }

        boolean result = usersService.userNameIsExist(userName);
        return result ? JsonResult.success() : JsonResult.failure(233, "用户名存在");
    }
}
