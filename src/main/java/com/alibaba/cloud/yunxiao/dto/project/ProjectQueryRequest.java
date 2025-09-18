package com.alibaba.cloud.yunxiao.dto.project;

import lombok.Data;

/**
 * 项目查询请求DTO
 * 
 * @author yunxiao-sdk
 * @since 1.0.0
 */
@Data
public class ProjectQueryRequest {

    /**
     * 项目名称（模糊查询）
     */
    private String name;

    /**
     * 项目状态
     */
    private String status;

    /**
     * 项目类型
     */
    private String type;

    /**
     * 项目可见性
     */
    private String visibility;

    /**
     * 创建者ID
     */
    private String creatorId;

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
}
