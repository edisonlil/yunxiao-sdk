package com.alibaba.cloud.yunxiao.dto;

import lombok.Data;

/**
 * 云效API基础响应类
 * 
 * @author yunxiao-sdk
 * @since 1.0.0
 */
@Data
public class BaseResponse<T> {

    /**
     * 请求ID
     */
    private String requestId;

    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 错误码
     */
    private String errorCode;

    /**
     * 错误信息
     */
    private String errorMessage;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 判断是否成功
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * 判断是否失败
     */
    public boolean isFailed() {
        return !success;
    }
}
