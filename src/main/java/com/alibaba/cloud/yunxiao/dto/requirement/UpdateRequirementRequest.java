package com.alibaba.cloud.yunxiao.dto.requirement;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * 更新需求请求DTO
 * 
 * @author yunxiao-sdk
 * @since 1.0.0
 */
@Data
public class UpdateRequirementRequest {

    /**
     * 需求ID
     */
    private String id;

    /**
     * 需求标题
     */
    private String title;

    /**
     * 需求描述
     */
    private String description;

    /**
     * 需求类型
     */
    private String type;

    /**
     * 需求状态
     */
    private String status;

    /**
     * 优先级
     */
    private String priority;

    /**
     * 指派人ID
     */
    private String assigneeId;

    /**
     * 计划开始时间
     */
    private LocalDateTime plannedStartTime;

    /**
     * 计划结束时间
     */
    private LocalDateTime plannedEndTime;

    /**
     * 实际开始时间
     */
    private LocalDateTime actualStartTime;

    /**
     * 实际结束时间
     */
    private LocalDateTime actualEndTime;

    /**
     * 需求标签
     */
    private List<String> tags;

    /**
     * 自定义字段
     */
    private Map<String, Object> customFields;

    /**
     * 父需求ID
     */
    private String parentId;

    /**
     * 关联的需求ID列表
     */
    private List<String> relatedRequirementIds;

    /**
     * 需求来源
     */
    private String source;

    /**
     * 业务价值
     */
    private String businessValue;

    /**
     * 验收标准
     */
    private String acceptanceCriteria;

    /**
     * 需求分类
     */
    private String category;

    /**
     * 需求模块
     */
    private String module;

    /**
     * 需求版本
     */
    private String version;

    /**
     * 需求复杂度
     */
    private String complexity;

    /**
     * 预估工作量（人天）
     */
    private Double estimatedEffort;

    /**
     * 实际工作量（人天）
     */
    private Double actualEffort;

    /**
     * 需求依赖
     */
    private List<RequirementDependency> dependencies;

    /**
     * 更新原因
     */
    private String updateReason;

    /**
     * 需求依赖信息
     */
    @Data
    public static class RequirementDependency {
        /**
         * 依赖的需求ID
         */
        private String requirementId;

        /**
         * 依赖类型：BLOCKED_BY（被阻塞）、BLOCKS（阻塞）、RELATED（关联）
         */
        private String dependencyType;

        /**
         * 依赖描述
         */
        private String description;
    }
}
