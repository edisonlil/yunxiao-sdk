package com.alibaba.cloud.yunxiao.dto.requirement;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * 创建需求请求DTO
 * 
 * @author yunxiao-sdk
 * @since 1.0.0
 */
@Data
public class CreateRequirementRequest {

    /**
     * 工作项标题 (对应官方API的subject)
     */
    private String subject;

    /**
     * 工作项描述 (对应官方API的description)
     */
    private String description;

    /**
     * 工作项类型ID (对应官方API的workitemTypeId) - 必填
     */
    private String workitemTypeId;

    /**
     * 空间ID，项目ID (对应官方API的spaceId) - 必填
     */
    private String spaceId;

    /**
     * 指派人userId (对应官方API的assignedTo) - 必填
     */
    private String assignedTo;

    /**
     * 父工作项ID (对应官方API的parentId)
     */
    private String parentId;

    /**
     * 参与人userId列表 (对应官方API的participants)
     */
    private List<String> participants;

    /**
     * 抄送人userId列表 (对应官方API的trackers)
     */
    private List<String> trackers;

    /**
     * 验证人userId (对应官方API的verifier)
     */
    private String verifier;

    /**
     * 关联的标签ID列表 (对应官方API的labels)
     */
    private List<String> labels;

    /**
     * 关联的迭代ID (对应官方API的sprint)
     */
    private String sprint;

    /**
     * 关联的版本ID列表 (对应官方API的versions)
     */
    private List<String> versions;

    /**
     * 自定义字段值 (对应官方API的customFieldValues)
     * 格式为：{"fieldId":"value"}，多值value用逗号隔开
     */
    private Map<String, String> customFieldValues;

    // 以下为扩展字段，用于更丰富的工作项信息
    /**
     * 计划开始时间
     */
    private LocalDateTime plannedStartTime;

    /**
     * 计划结束时间
     */
    private LocalDateTime plannedEndTime;

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
}
