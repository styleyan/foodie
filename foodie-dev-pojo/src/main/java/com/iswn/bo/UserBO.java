package com.iswn.bo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserBO {
    private String username;
    private String password;
    private String confirmPassword;
}
