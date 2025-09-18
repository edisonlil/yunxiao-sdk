package com.alibaba.cloud.yunxiao.dto.project;

import lombok.Data;

import java.util.List;

/**
 * 项目列表响应DTO
 * 
 * @author yunxiao-sdk
 * @since 1.0.0
 */
@Data
public class ProjectListResponse {

    /**
     * 项目列表
     */
    private List<ProjectInfo> projects;

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
}
