package com.alibaba.cloud.yunxiao.dto.requirement;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 需求查询请求DTO
 * 
 * @author yunxiao-sdk
 * @since 1.0.0
 */
@Data
public class RequirementQueryRequest {

    /**
     * 项目ID
     */
    private String projectId;

    /**
     * 需求标题（模糊查询）
     */
    private String title;

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
     * 创建者ID
     */
    private String creatorId;

    /**
     * 指派人ID
     */
    private String assigneeId;

    /**
     * 需求标签
     */
    private List<String> tags;

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
     * 需求来源
     */
    private String source;

    /**
     * 父需求ID
     */
    private String parentId;

    /**
     * 是否只查询子需求
     */
    private Boolean onlyChildren;

    /**
     * 是否只查询父需求
     */
    private Boolean onlyParents;

    /**
     * 创建时间开始
     */
    private LocalDateTime createTimeStart;

    /**
     * 创建时间结束
     */
    private LocalDateTime createTimeEnd;

    /**
     * 计划开始时间开始
     */
    private LocalDateTime plannedStartTimeStart;

    /**
     * 计划开始时间结束
     */
    private LocalDateTime plannedStartTimeEnd;

    /**
     * 计划结束时间开始
     */
    private LocalDateTime plannedEndTimeStart;

    /**
     * 计划结束时间结束
     */
    private LocalDateTime plannedEndTimeEnd;

    /**
     * 页码，从1开始
     */
    private Integer pageNumber = 1;

    /**
     * 每页大小，最大100
     */
    private Integer pageSize = 20;

    /**
     * 排序字段
     */
    private String orderBy = "createTime";

    /**
     * 排序方向：ASC、DESC
     */
    private String orderDirection = "DESC";

    /**
     * 是否包含已删除的需求
     */
    private Boolean includeDeleted = false;

    /**
     * 是否包含子需求
     */
    private Boolean includeChildren = false;

    /**
     * 是否包含关联需求
     */
    private Boolean includeRelated = false;
}
