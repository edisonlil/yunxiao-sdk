package com.alibaba.cloud.yunxiao.dto.project;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 项目信息DTO
 * 
 * @author yunxiao-sdk
 * @since 1.0.0
 */
@Data
public class ProjectInfo {

    /**
     * 项目ID
     */
    private String id;

    /**
     * 项目名称
     */
    private String name;

    /**
     * 项目描述
     */
    private String description;

    /**
     * 项目标识符
     */
    private String identifier;

    /**
     * 项目类型
     */
    private String type;

    /**
     * 项目状态
     */
    private String status;

    /**
     * 项目可见性
     */
    private String visibility;

    /**
     * 创建者ID
     */
    private String creatorId;

    /**
     * 创建者名称
     */
    private String creatorName;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 项目成员数量
     */
    private Integer memberCount;

    /**
     * 项目标签
     */
    private List<String> tags;

    /**
     * 项目配置
     */
    private ProjectConfig config;

    /**
     * 项目配置类
     */
    @Data
    public static class ProjectConfig {
        /**
         * 是否启用代码管理
         */
        private Boolean codeManagementEnabled;

        /**
         * 是否启用流水线
         */
        private Boolean pipelineEnabled;

        /**
         * 是否启用测试管理
         */
        private Boolean testManagementEnabled;

        /**
         * 是否启用制品管理
         */
        private Boolean artifactManagementEnabled;
    }
}
