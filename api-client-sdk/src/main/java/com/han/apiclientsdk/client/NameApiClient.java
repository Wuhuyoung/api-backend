package com.han.apiclientsdk.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.han.apiclientsdk.model.User;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static com.han.apiclientsdk.utils.SignUtils.generateSign;


/**
 * 调用第三方接口的客户端
 */
public class NameApiClient {

    private String accessKey;
    private String secretKey;

    private static final String GATEWAY_HOST = "http://127.0.0.1:8090";

    public NameApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public String getNameByGet(String name) {
        // 可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        // 采用Hutool第三方库调用Http请求
        String result= HttpUtil.get(GATEWAY_HOST + "/api/name", paramMap);
        System.out.println(result);
        return result;
    }

    public String getNameByPost(String name) {
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String result= HttpUtil.post(GATEWAY_HOST + "/api/name", paramMap);
        System.out.println(result);
        return result;
    }


    /**
     * 在请求头中添加的校验参数
     * @return
     */
    private Map<String, String> getHeaderMap(String body) {
        HashMap<String, String> map = new HashMap<>();
        map.put("accessKey", accessKey);
        // 密钥一定不能直接传递 !!!
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

    public String getUserName(User user) {
        String json = JSONUtil.toJsonStr(user);
        HttpResponse response = HttpRequest.post(GATEWAY_HOST + "/api/name/user")
                .body(json)
                .addHeaders(getHeaderMap(json))
                .execute();
        System.out.println(response.getStatus());
        return response.body();
    }

    public String getNickName() {
        HttpResponse response = HttpRequest.get(GATEWAY_HOST + "/api/name/nick")
                .addHeaders(getHeaderMap(""))
                .execute();
        System.out.println(response.getStatus());
        return response.body();
    }
}
