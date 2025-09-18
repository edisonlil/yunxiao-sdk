package com.alibaba.cloud.yunxiao.dto.requirement;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * 需求信息DTO
 * 
 * @author yunxiao-sdk
 * @since 1.0.0
 */
@Data
public class RequirementInfo {

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
     * 项目ID
     */
    private String projectId;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 创建者ID
     */
    private String creatorId;

    /**
     * 创建者名称
     */
    private String creatorName;

    /**
     * 指派人ID
     */
    private String assigneeId;

    /**
     * 指派人名称
     */
    private String assigneeName;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

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
     * 需求附件
     */
    private List<Attachment> attachments;

    /**
     * 自定义字段
     */
    private Map<String, Object> customFields;

    /**
     * 需求编号
     */
    private String requirementNumber;

    /**
     * 父需求ID
     */
    private String parentId;

    /**
     * 子需求列表
     */
    private List<RequirementInfo> children;

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
     * 附件信息
     */
    @Data
    public static class Attachment {
        /**
         * 附件ID
         */
        private String id;

        /**
         * 附件名称
         */
        private String name;

        /**
         * 附件URL
         */
        private String url;

        /**
         * 附件大小
         */
        private Long size;

        /**
         * 附件类型
         */
        private String type;

        /**
         * 上传时间
         */
        private LocalDateTime uploadTime;
    }
}
