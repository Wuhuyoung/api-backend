package com.han.project.controller;


import com.han.apiclientsdk.model.User;
import com.han.apiclientsdk.utils.SignUtils;
import com.han.project.utils.NameUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

/**
 * 模拟api接口
 */
@RestController
@RequestMapping("/name")
public class NameController {

    @GetMapping
    public String getNameByGet(String name) {
        return "GET 你的名字是" + name;
    }

    @PostMapping
    public String getNameByPost(@RequestParam String name) {
        return "POST 你的名字是" + name;
    }

    @PostMapping("/user")
    public String getUserName(@RequestBody User user, HttpServletRequest request) {
        return "POST 用户名字是 " + user.getName();
    }

    /**
     * 随机获取用户昵称
     * @return
     */
    @GetMapping("/nick")
    public String getNickName() {
        return NameUtil.getName();
    }
}
