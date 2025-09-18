package com.alibaba.cloud.yunxiao.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 云效配置属性测试类
 * 
 * @author yunxiao-sdk
 * @since 1.0.0
 */
class YunxiaoPropertiesTest {

    @Test
    void testDefaultValues() {
        YunxiaoProperties properties = new YunxiaoProperties();
        
        // 验证默认值
        assertTrue(properties.isEnabled());
        assertEquals("https://devops.cn-hangzhou.aliyuncs.com", properties.getGatewayUrl());
        assertEquals(30000, properties.getTimeout());
        assertEquals(10000, properties.getConnectTimeout());
        assertTrue(properties.isSslEnabled());
        assertEquals(3, properties.getRetryCount());
        assertEquals(1000, properties.getRetryInterval());
    }

    @Test
    void testSettersAndGetters() {
        YunxiaoProperties properties = new YunxiaoProperties();
        
        // 测试设置和获取
        properties.setEnabled(false);
        properties.setGatewayUrl("https://test.example.com");
        properties.setAccessKeyId("test-key-id");
        properties.setAccessKeySecret("test-key-secret");
        properties.setTimeout(60000);
        properties.setConnectTimeout(20000);
        properties.setSslEnabled(false);
        properties.setRetryCount(5);
        properties.setRetryInterval(2000);
        
        // 验证设置的值
        assertFalse(properties.isEnabled());
        assertEquals("https://test.example.com", properties.getGatewayUrl());
        assertEquals("test-key-id", properties.getAccessKeyId());
        assertEquals("test-key-secret", properties.getAccessKeySecret());
        assertEquals(60000, properties.getTimeout());
        assertEquals(20000, properties.getConnectTimeout());
        assertFalse(properties.isSslEnabled());
        assertEquals(5, properties.getRetryCount());
        assertEquals(2000, properties.getRetryInterval());
    }
}
