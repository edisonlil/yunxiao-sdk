package com.alibaba.cloud.yunxiao.dto.project;

import lombok.Data;

import java.util.List;

/**
 * 创建项目请求DTO
 * 
 * @author yunxiao-sdk
 * @since 1.0.0
 */
@Data
public class CreateProjectRequest {

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
    private String type = "DevOps";

    /**
     * 项目可见性
     */
    private String visibility = "Private";

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
        private Boolean codeManagementEnabled = true;

        /**
         * 是否启用流水线
         */
        private Boolean pipelineEnabled = true;

        /**
         * 是否启用测试管理
         */
        private Boolean testManagementEnabled = true;

        /**
         * 是否启用制品管理
         */
        private Boolean artifactManagementEnabled = true;
    }
}
