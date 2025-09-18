package com.alibaba.cloud.yunxiao.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 云效SDK配置属性
 * 
 * @author yunxiao-sdk
 * @since 1.0.0
 */
@Data
@ConfigurationProperties(prefix = "yunxiao")
public class YunxiaoProperties {

    /**
     * 是否启用云效SDK
     */
    private boolean enabled = true;

    /**
     * 云效API网关地址
     */
    private String gatewayUrl = "https://devops.cn-hangzhou.aliyuncs.com";

    /**
     * 访问密钥ID
     */
    private String accessKeyId;

    /**
     * 访问密钥Secret
     */
    private String accessKeySecret;

    /**
     * 请求超时时间（毫秒）
     */
    private int timeout = 30000;

    /**
     * 连接超时时间（毫秒）
     */
    private int connectTimeout = 10000;

    /**
     * 是否启用SSL验证
     */
    private boolean sslEnabled = true;

    /**
     * 重试次数
     */
    private int retryCount = 3;

    /**
     * 重试间隔（毫秒）
     */
    private int retryInterval = 1000;
}
