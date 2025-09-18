package com.alibaba.cloud.yunxiao.dto.project;

import lombok.Data;

import java.util.List;

/**
 * 更新项目请求DTO
 * 
 * @author yunxiao-sdk
 * @since 1.0.0
 */
@Data
public class UpdateProjectRequest {

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
     * 项目状态
     */
    private String status;

    /**
     * 项目可见性
     */
    private String visibility;

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
