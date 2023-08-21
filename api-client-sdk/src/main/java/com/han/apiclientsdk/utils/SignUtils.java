package com.han.apiclientsdk.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

public class SignUtils {

    /**
     * 生成签名，采用SHA256加密算法
     * @param body
     * @param secretKey
     * @return
     */
    public static String generateSign(String body, String secretKey) {
        //用户参数 + 密钥
        String content = body + "." + secretKey;
        //采用hutool的摘要加密算法
        Digester sha256 = new Digester(DigestAlgorithm.SHA256);
        return sha256.digestHex(content);
    }
}
