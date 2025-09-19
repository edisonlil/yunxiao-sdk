package com.alibaba.cloud.yunxiao.dto.requirement;

import lombok.Data;

import java.util.List;

/**
 * 批量创建工作项响应
 * 
 * @author yunxiao-sdk
 * @since 1.0.0
 */
@Data
public class BatchCreateResponse {

    /**
     * 成功创建的工作项列表
     */
    private List<RequirementInfo> successItems;

    /**
     * 失败的工作项列表
     */
    private List<FailureDetail> failedItems;

    /**
     * 总数量
     */
    private Integer totalCount;

    /**
     * 成功数量
     */
    private Integer successCount;

    /**
     * 失败数量
     */
    private Integer failureCount;

    /**
     * 失败详情
     */
    @Data
    public static class FailureDetail {
        /**
         * 原始请求索引
         */
        private Integer index;

        /**
         * 原始请求数据
         */
        private CreateRequirementRequest originalRequest;

        /**
         * 错误代码
         */
        private String errorCode;

        /**
         * 错误信息
         */
        private String errorMessage;
    }
}
