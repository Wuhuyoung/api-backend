package com.han.service;

import com.han.apiclientsdk.model.AvatarObj;

/**
 * 测试调用接口方法
 */
public interface TestInvokeService {
    String getUserName(String requestParams, String accessKey, String secretKey);

    String getSoulSoother(String accessKey, String secretKey);

    String getNickName(String accessKey, String secretKey);

    AvatarObj getAvatar(String accessKey, String secretKey);
}
