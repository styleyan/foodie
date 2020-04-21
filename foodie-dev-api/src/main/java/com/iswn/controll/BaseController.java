package com.iswn.controll;

import org.springframework.stereotype.Controller;

import java.io.File;

@Controller
public class BaseController {
    public static final Integer COMMENT_PAGE_SIZE = 10;
    public static final Integer PAGE_SIZE = 20;

    /**
     * 购物车 cookie 名称
     */
    public static final String FOODIE_SHOPCART = "shopcart";

    /**
     * 文件存储路径
     */
    public static final String IMAGE_USER_FACE_LOCATION = File.separator + "workspaces" +
            File.separator + "images" +
            File.separator + "foodie" +
            File.separator + "faces";

}
