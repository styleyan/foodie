package com.iswn.controll;

import com.iswn.service.UsersService;
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
    public Object userNameIsExist(@RequestParam("userName") String userName) {
        return usersService.userNameIsExist(userName);
    }
}
