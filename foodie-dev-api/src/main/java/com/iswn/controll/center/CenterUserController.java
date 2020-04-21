package com.iswn.controll.center;

import com.iswn.exception.http.RequestBadException;
import com.iswn.utils.JsonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * 用户中心
 */
@RestController
@RequestMapping("/api/center")
public class CenterUserController {

    /**
     * 上传图片
     * @return
     */
    @PostMapping("update")
    public JsonResult update(MultipartFile file, @RequestParam String userId) {
        // 定义头像保存的地址
        String uploadPathPrefix = File.separator + userId;
        // 在路径上为每一个用户增加一个userId, 用于区分不同用户上传

        // 开始文件上传
        if (file != null) {

        } else {
            throw new RequestBadException("文件不能问空");
        }
        return JsonResult.success();
    }
}
