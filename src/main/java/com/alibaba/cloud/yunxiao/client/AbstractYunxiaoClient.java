package com.alibaba.cloud.yunxiao.client;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.Method;
import com.alibaba.cloud.yunxiao.config.YunxiaoProperties;
import com.alibaba.cloud.yunxiao.dto.BaseResponse;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 云效API客户端抽象基类
 * 
 * @author yunxiao-sdk
 * @since 1.0.0
 */
@Slf4j
public abstract class AbstractYunxiaoClient {

    protected final YunxiaoProperties properties;
    protected static final String API_VERSION = "2021-06-25";
    protected static final String SIGNATURE_METHOD = "HMAC-SHA1";
    protected static final String SIGNATURE_VERSION = "1.0";

    public AbstractYunxiaoClient(YunxiaoProperties properties) {
        this.properties = properties;
    }

    /**
     * 构建API URL
     */
    protected String buildApiUrl(String path) {
        return properties.getGatewayUrl() + "/oapi/v1/projex/organizations/" + properties.getOrganizationId() + path;
    }

    /**
     * 发送HTTP请求
     */
    protected HttpResponse sendRequest(String method, String url, String requestBody) {
        Map<String, String> headers = buildHeaders(method, url, requestBody);
        
        HttpRequest request = HttpRequest.of(url)
                .method(Method.valueOf(method))
                .headerMap(headers, true)
                .timeout(properties.getTimeout())
                .setConnectionTimeout(properties.getConnectTimeout());
        
        if (StrUtil.isNotBlank(requestBody)) {
            request.body(requestBody);
        }
        
        log.debug("发送请求: {} {}", method, url);
        if (StrUtil.isNotBlank(requestBody)) {
            log.debug("请求体: {}", requestBody);
        }
        
        return request.execute();
    }

    /**
     * 构建请求头
     */
    private Map<String, String> buildHeaders(String method, String url, String requestBody) {
        Map<String, String> headers = new HashMap<>();
        
        // 基础头信息
        headers.put("Content-Type", "application/json; charset=UTF-8");
        headers.put("Accept", "application/json");
        headers.put("User-Agent", "yunxiao-sdk/1.0.0");
        
        // 云效个人访问令牌认证 (根据官方文档)
        if (StrUtil.isNotBlank(properties.getToken())) {
            headers.put("x-yunxiao-token", properties.getToken());
        } else {
            // 如果没有token，使用AccessKey认证作为备选方案
            buildAccessKeyHeaders(headers, method, url, requestBody);
        }
        
        return headers;
    }

    /**
     * 构建AccessKey认证头信息 (备选方案)
     */
    private void buildAccessKeyHeaders(Map<String, String> headers, String method, String url, String requestBody) {
        // 时间戳
        String timestamp = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
                .withZone(ZoneOffset.UTC)
                .format(Instant.now());
        headers.put("X-Acs-Timestamp", timestamp);
        
        // 请求ID
        String requestId = UUID.randomUUID().toString();
        headers.put("X-Acs-Request-Id", requestId);
        
        // API版本
        headers.put("X-Acs-Version", API_VERSION);
        
        // 签名方法
        headers.put("X-Acs-Signature-Method", SIGNATURE_METHOD);
        headers.put("X-Acs-Signature-Version", SIGNATURE_VERSION);
        
        // AccessKey
        headers.put("X-Acs-Access-Key-Id", properties.getAccessKeyId());
        
        // 签名
        String signature = generateSignature(method, url, requestBody, timestamp);
        headers.put("X-Acs-Signature", signature);
    }

    /**
     * 生成签名
     */
    private String generateSignature(String method, String url, String requestBody, String timestamp) {
        try {
            // 构建签名字符串
            StringBuilder stringToSign = new StringBuilder();
            stringToSign.append(method).append("\n");
            stringToSign.append(url).append("\n");
            stringToSign.append(timestamp).append("\n");
            if (StrUtil.isNotBlank(requestBody)) {
                stringToSign.append(requestBody);
            }
            
            // 使用HMAC-SHA1签名
            String signature = hmacSha1(stringToSign.toString(), properties.getAccessKeySecret());
            return signature;
        } catch (Exception e) {
            log.error("生成签名失败", e);
            throw new RuntimeException("生成签名失败", e);
        }
    }

    /**
     * HMAC-SHA1签名
     */
    private String hmacSha1(String data, String key) throws Exception {
        javax.crypto.Mac mac = javax.crypto.Mac.getInstance("HmacSHA1");
        javax.crypto.spec.SecretKeySpec secretKeySpec = new javax.crypto.spec.SecretKeySpec(
                key.getBytes(StandardCharsets.UTF_8), "HmacSHA1");
        mac.init(secretKeySpec);
        byte[] result = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return java.util.Base64.getEncoder().encodeToString(result);
    }

    /**
     * 解析响应
     */
    protected <T> BaseResponse<T> parseResponse(HttpResponse response, Class<T> clazz) {
        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.setRequestId(response.header("X-Acs-Request-Id"));
        
        if (response.getStatus() >= 200 && response.getStatus() < 300) {
            baseResponse.setSuccess(true);
            if (clazz != Void.class && StrUtil.isNotBlank(response.body())) {
                try {
                    T data = JSON.parseObject(response.body(), clazz);
                    baseResponse.setData(data);
                } catch (Exception e) {
                    log.error("解析响应数据失败", e);
                    baseResponse.setSuccess(false);
                    baseResponse.setErrorCode("PARSE_ERROR");
                    baseResponse.setErrorMessage("解析响应数据失败: " + e.getMessage());
                }
            }
        } else {
            baseResponse.setSuccess(false);
            baseResponse.setErrorCode(String.valueOf(response.getStatus()));
            baseResponse.setErrorMessage("HTTP请求失败: " + response.getStatus());
            
            if (StrUtil.isNotBlank(response.body())) {
                try {
                    Map<String, Object> errorInfo = JSON.parseObject(response.body(), Map.class);
                    if (errorInfo.containsKey("errorCode")) {
                        baseResponse.setErrorCode((String) errorInfo.get("errorCode"));
                    }
                    if (errorInfo.containsKey("errorMessage")) {
                        baseResponse.setErrorMessage((String) errorInfo.get("errorMessage"));
                    }
                } catch (Exception e) {
                    log.warn("解析错误响应失败", e);
                }
            }
        }
        
        return baseResponse;
    }

    /**
     * 构建错误响应
     */
    protected <T> BaseResponse<T> buildErrorResponse(String errorMessage) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setSuccess(false);
        response.setErrorCode("CLIENT_ERROR");
        response.setErrorMessage(errorMessage);
        return response;
    }
}
