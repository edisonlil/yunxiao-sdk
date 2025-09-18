package com.alibaba.cloud.yunxiao.dto.requirement;

import lombok.Data;

import java.util.List;

/**
 * 需求列表响应DTO
 * 
 * @author yunxiao-sdk
 * @since 1.0.0
 */
@Data
public class RequirementListResponse {

    /**
     * 需求列表
     */
    private List<RequirementInfo> requirements;

    /**
     * 总数量
     */
    private Integer totalCount;

    /**
     * 当前页码
     */
    private Integer pageNumber;

    /**
     * 每页大小
     */
    private Integer pageSize;

    /**
     * 是否有下一页
     */
    private Boolean hasNext;

    /**
     * 统计信息
     */
    private RequirementStatistics statistics;

    /**
     * 需求统计信息
     */
    @Data
    public static class RequirementStatistics {
        /**
         * 总需求数
         */
        private Integer totalCount;

        /**
         * 待处理需求数
         */
        private Integer pendingCount;

        /**
         * 进行中需求数
         */
        private Integer inProgressCount;

        /**
         * 已完成需求数
         */
        private Integer completedCount;

        /**
         * 已关闭需求数
         */
        private Integer closedCount;

        /**
         * 高优先级需求数
         */
        private Integer highPriorityCount;

        /**
         * 中优先级需求数
         */
        private Integer mediumPriorityCount;

        /**
         * 低优先级需求数
         */
        private Integer lowPriorityCount;
    }
}
