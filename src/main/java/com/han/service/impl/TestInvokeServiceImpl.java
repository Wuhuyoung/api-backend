package com.han.service.impl;

import com.google.gson.Gson;
import com.han.apiclientsdk.client.AvatarClient;
import com.han.apiclientsdk.client.NameApiClient;
import com.han.apiclientsdk.client.SoulSootherClient;
import com.han.apiclientsdk.model.AvatarObj;
import com.han.service.TestInvokeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TestInvokeServiceImpl implements TestInvokeService {
    @Override
    public String getUserName(String requestParams, String accessKey, String secretKey) {
        NameApiClient tempClient = new NameApiClient(accessKey, secretKey);
        //将请求参数转为User对象
        Gson gson = new Gson();
        com.han.apiclientsdk.model.User user = gson.fromJson(requestParams, com.han.apiclientsdk.model.User.class);
        String response = tempClient.getUserName(user);
        return response;
    }

    @Override
    public String getSoulSoother(String accessKey, String secretKey) {
        SoulSootherClient soulSootherClient = new SoulSootherClient(accessKey, secretKey);
        return soulSootherClient.getRandSoulSoother();
    }

    @Override
    public String getNickName(String accessKey, String secretKey) {
        NameApiClient tempClient = new NameApiClient(accessKey, secretKey);
        return tempClient.getNickName();
    }

    @Override
    public AvatarObj getAvatar(String accessKey, String secretKey) {
        AvatarClient avatarClient = new AvatarClient(accessKey, secretKey);
        return avatarClient.getAvatar();
    }
}
