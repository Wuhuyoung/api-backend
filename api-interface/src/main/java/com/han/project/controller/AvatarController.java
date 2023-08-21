package com.han.project.controller;

import com.han.apiclientsdk.model.AvatarObj;
import com.han.project.utils.AvatarUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 模拟api接口
 */
@RestController
@RequestMapping("/avatar")
public class AvatarController {

    // 随机函数
    public static ThreadLocalRandom getRandom(){
        return ThreadLocalRandom.current();
    }

    /**
     * 返回随机头像的url
     * @return
     */
    @GetMapping
    public AvatarObj getAvatar() {
        String urlPrefix = "https://avatar-1314662469.cos.ap-shanghai.myqcloud.com/avatar/";
        int num = getRandom().nextInt(21);
        urlPrefix += AvatarUtils.urlSuffix[num] + ".jpeg";
        AvatarObj avatarObj = new AvatarObj();
        avatarObj.setImgUrl(urlPrefix);
        avatarObj.setHeight("400");
        avatarObj.setWidth("400");
        return avatarObj;
    }
}
