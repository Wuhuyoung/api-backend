package com.han;

import com.han.apiclientsdk.utils.SignUtils;
import com.han.apicommon.model.entity.InterfaceInfo;
import com.han.apicommon.model.entity.User;
import com.han.apicommon.service.InnerInterfaceInfoService;
import com.han.apicommon.service.InnerUserInterfaceInfoService;
import com.han.apicommon.service.InnerUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 自定义全局过滤器
 */
@Slf4j
@Component
public class CustomGlobalFilter implements GlobalFilter, Ordered {

    // 请求ip白名单
    private static final List<String> IP_WHITE_LIST = Arrays.asList("127.0.0.1");

    @DubboReference
    private InnerUserService innerUserService;

    @DubboReference
    private InnerInterfaceInfoService innerInterfaceInfoService;

    @DubboReference
    private InnerUserInterfaceInfoService innerUserInterfaceInfoService;

    private static String INTERFACE_HOST = "http://localhost:8123";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1.请求日志
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        String path = INTERFACE_HOST + request.getPath().toString();
        String method = request.getMethodValue();
        log.info("请求唯一标识：" + request.getId());
        log.info("请求方法：" + method);
        log.info("请求路径：" + path);
        log.info("请求uri：" + request.getURI());
        log.info("请求参数：" + request.getQueryParams());
        String sourceHostName = request.getLocalAddress().getHostString();
        log.info("请求来源地址：" + request.getLocalAddress());
        log.info("请求来源主机：" + sourceHostName);

        // 2.黑白名单
//        if (!IP_WHITE_LIST.contains(sourceHostName)) {
//            return handleNoAuth(response);
//        }

        // 3.用户鉴权（判断 ak sk 是否合法）
        HttpHeaders headers = request.getHeaders();
        String accessKey = headers.getFirst("accessKey");
        String body = headers.getFirst("body");
        //解决接口传入中文乱码问题
        if (StringUtils.isNotBlank(body)) {
            body = new String(body.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        }
        String nonce = headers.getFirst("nonce");
        String timestamp = headers.getFirst("timestamp");
        String sign = headers.getFirst("sign");
        if (accessKey == null || nonce == null || timestamp == null || sign == null) {
            return handleNoAuth(response);
        }

        // 从数据库中查询 accessKey 是否已分配给用户
        User invokeUser = null;
        try {
            invokeUser = innerUserService.getInvokeUser(accessKey);
        } catch (Exception e) {
            log.error("getInvokeUser error", e);
            return handleNoAuth(response);
        }
        if (invokeUser == null) {
            return handleNoAuth(response);
        }
        // todo 实际应该将随机数存储在数据库中或redis的set中
        if (Long.parseLong(nonce) > 10000) {
            return handleNoAuth(response);
        }

        // 校验 timestamp和当前时间不能超过5分钟
        LocalDateTime clientTimeStamp = LocalDateTime.parse(timestamp);
        if (clientTimeStamp.plusMinutes(5).isBefore(LocalDateTime.now())) {
            return handleNoAuth(response);
        }

        // invokeUser是从数据库中查询出来的，得到 secretKey，对比签名是否一致
        String secretKey = invokeUser.getSecretKey();
        String serverSign = SignUtils.generateSign(body, secretKey);
        if (!serverSign.equals(sign)) {
            return handleNoAuth(response);
        }
        // 4.请求的接口是否存在
        // 需要从数据库中查询模拟接口是否存在，以及请求方法是否匹配（远程调用backend中的方法去查）
        InterfaceInfo interfaceInfo = null;
        try {
            interfaceInfo = innerInterfaceInfoService.getInterfaceInfo(path, method);
        } catch (Exception e) {
            log.error("getInterfaceInfo error", e);
            return handleNoAuth(response);
        }
        if (interfaceInfo == null) {
            return handleNoAuth(response);
        }

        // 5.判断用户剩余调用次数是否大于0
        Long userId = invokeUser.getId();
        Long interfaceInfoId = interfaceInfo.getId();
        boolean count = innerUserInterfaceInfoService.hasLeftInvokeCount(userId, interfaceInfoId);
        if (!count) {
            response.getHeaders().add("refuse", "没有调用次数");
            return handleNoAuth(response);
        }
        // 6.转发请求，调用模拟接口
        Mono<Void> mono = chain.filter(exchange);

        return handleResponse(exchange, chain, userId, interfaceInfoId);
        // return filter;
    }

    /**
     * 处理响应
     * @return
     */
    public Mono<Void> handleResponse(ServerWebExchange exchange, GatewayFilterChain chain,
                                     long userId, long interfaceInfoId) {
        try {
            ServerHttpResponse originalResponse = exchange.getResponse();
            DataBufferFactory bufferFactory = originalResponse.bufferFactory();
            // 拿到响应码
            HttpStatus statusCode = originalResponse.getStatusCode();

            if(statusCode == HttpStatus.OK){
                // 装饰，增强原有 response 能力
                ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(originalResponse) {
                    // 等调用完响应的接口才会执行
                    @Override
                    public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                        log.info("body instanceof Flux: {}", (body instanceof Flux));
                        if (body instanceof Flux) {
                            Flux<? extends DataBuffer> fluxBody = Flux.from(body);
                            // 拼接字符串
                            return super.writeWith(fluxBody.map(dataBuffer -> {
                                // 7. 调用成功，则 调用次数 + 1 invokeCount
                                try {
                                    innerUserInterfaceInfoService.invokeCount(userId, interfaceInfoId);
                                } catch (Exception e) {
                                    log.error("invokeCount error");
                                }
                                byte[] content = new byte[dataBuffer.readableByteCount()];
                                dataBuffer.read(content);
                                DataBufferUtils.release(dataBuffer);//释放掉内存
                                // 构建日志
                                StringBuilder sb2 = new StringBuilder(200);
                                List<Object> rspArgs = new ArrayList<>();
                                rspArgs.add(originalResponse.getStatusCode());
                                //rspArgs.add(requestUrl);
                                String data = new String(content, StandardCharsets.UTF_8);//data
                                sb2.append(data);
                                // 8.响应日志
                                log.info("调用请求响应:" + getStatusCode());
                                log.info("返回结果：" + data);
                                return bufferFactory.wrap(content);
                            }));
                        } else {
                            // 8.调用失败，返回一个规范的错误码
                            log.error("<--- {} 响应code异常", getStatusCode());
                        }
                        return super.writeWith(body); //调用接口之前执行，继续处理下一个filter
                    }
                };
                // 设置 response 对象为装饰过的
                return chain.filter(exchange.mutate().response(decoratedResponse).build());
            }
            return chain.filter(exchange);//调用失败时，降级处理返回数据
        }catch (Exception e){
            log.error("gateway log exception.\n" + e);
            return chain.filter(exchange);
        }
    }

    private Mono<Void> handleNoAuth(ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.FORBIDDEN);
        return response.setComplete();
    }

    private Mono<Void> handleInvokeError(ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        return response.setComplete();
    }


    @Override
    public int getOrder() {
        return -1;
    }
}