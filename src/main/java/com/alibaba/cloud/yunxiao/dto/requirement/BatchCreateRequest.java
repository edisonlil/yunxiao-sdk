package com.alibaba.cloud.yunxiao.dto.requirement;

import lombok.Data;

import java.util.List;

/**
 * 批量创建工作项请求
 * 
 * @author yunxiao-sdk
 * @since 1.0.0
 */
@Data
public class BatchCreateRequest {

    /**
     * 工作项创建请求列表
     */
    private List<CreateRequirementRequest> items;

    /**
     * 批量操作选项
     */
    private BatchOptions options;

    /**
     * 批量操作选项
     */
    @Data
    public static class BatchOptions {
        /**
         * 是否在遇到错误时继续处理其他项目
         */
        private Boolean continueOnError = true;

        /**
         * 是否返回详细的结果信息
         */
        private Boolean returnDetailedResults = true;

        /**
         * 是否验证所有项目后再执行创建
         */
        private Boolean validateAll = false;
    }
}
