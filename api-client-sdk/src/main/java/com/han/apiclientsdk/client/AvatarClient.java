package com.han.apiclientsdk.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.han.apiclientsdk.model.AvatarObj;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static com.han.apiclientsdk.utils.SignUtils.generateSign;

/**
 * 调用第三方接口的客户端
 */
public class AvatarClient {
    private String accessKey;
    private String secretKey;

    private static final String GATEWAY_HOST = "http://127.0.0.1:8090";

    public AvatarClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    /**
     * 在请求头中添加的校验参数
     * @return
     */
    private Map<String, String> getHeaderMap(String body) {
        HashMap<String, String> map = new HashMap<>();
        map.put("accessKey", accessKey);
        // 密钥一定不能直接传递
        // map.put("secretKey", secretKey);
        //用户请求参数
        map.put("body", body);
        //随机数(防重放)
        map.put("nonce", RandomUtil.randomNumbers(4));
        //时间戳(防重放)
        map.put("timestamp", LocalDateTime.now().toString());
        //签名(用户参数+密钥 => 签名生成算法 => 签名(不可解密))
        map.put("sign", generateSign(body, secretKey));
        return map;
    }

    public AvatarObj getAvatar() {
        HttpResponse response = HttpRequest.get(GATEWAY_HOST + "/api/avatar")
                .addHeaders(getHeaderMap(""))
                .execute();
        System.out.println("getAvatar:" + response.getStatus());
        String body = response.body();
        AvatarObj avatarObj = JSONUtil.toBean(body, AvatarObj.class);
        if (avatarObj != null) {
            avatarObj.setCode(200);
        }
        return avatarObj;
    }
}
