package com.iswn.controll;

import com.iswn.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/api/queryAllUser")
    public Object queryAllUser() {
        return usersService.queryAll();
    }
}
