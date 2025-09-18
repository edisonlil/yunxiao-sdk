package com.alibaba.cloud.yunxiao.dto.requirement;

import lombok.Data;

import java.util.List;

/**
 * 批量工作项状态修改响应
 * 
 * @author yunxiao-sdk
 * @since 1.0.0
 */
@Data
public class BatchUpdateStatusResponse {

    /**
     * 成功更新的工作项ID列表
     */
    private List<String> successIds;

    /**
     * 失败的工作项ID列表
     */
    private List<String> failedIds;

    /**
     * 失败详情
     */
    private List<FailureDetail> failures;

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
         * 工作项ID
         */
        private String workItemId;

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
