package com.alibaba.cloud.yunxiao.config;

import com.alibaba.cloud.yunxiao.client.*;
import com.alibaba.cloud.yunxiao.client.impl.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 云效SDK自动配置类
 * 
 * @author yunxiao-sdk
 * @since 1.0.0
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(YunxiaoProperties.class)
@ConditionalOnProperty(prefix = "yunxiao", name = "enabled", havingValue = "true", matchIfMissing = true)
public class YunxiaoAutoConfiguration {

    private final YunxiaoProperties yunxiaoProperties;

    public YunxiaoAutoConfiguration(YunxiaoProperties yunxiaoProperties) {
        this.yunxiaoProperties = yunxiaoProperties;
    }

    @Bean
    public ProjectClient projectClient() {
        log.info("Initializing ProjectClient with gateway: {}", yunxiaoProperties.getGatewayUrl());
        return new YunxiaoProjectClient(yunxiaoProperties);
    }

    @Bean
    public RequirementClient requirementClient() {
        log.info("Initializing RequirementClient with gateway: {}", yunxiaoProperties.getGatewayUrl());
        return new YunxiaoRequirementClient(yunxiaoProperties);
    }

    @Bean
    public TaskClient taskClient() {
        log.info("Initializing TaskClient with gateway: {}", yunxiaoProperties.getGatewayUrl());
        return new YunxiaoTaskClient(yunxiaoProperties);
    }

    @Bean
    public BugClient bugClient() {
        log.info("Initializing BugClient with gateway: {}", yunxiaoProperties.getGatewayUrl());
        return new YunxiaoBugClient(yunxiaoProperties);
    }
}
