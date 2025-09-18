package com.alibaba.cloud.yunxiao.client;

import com.alibaba.cloud.yunxiao.dto.BaseResponse;
import com.alibaba.cloud.yunxiao.dto.project.*;

/**
 * 项目客户端接口
 * 
 * @author yunxiao-sdk
 * @since 1.0.0
 */
public interface ProjectClient {

    /**
     * 创建项目
     */
    BaseResponse<ProjectInfo> createProject(CreateProjectRequest request);

    /**
     * 获取项目信息
     */
    BaseResponse<ProjectInfo> getProject(String projectId);

    /**
     * 更新项目信息
     */
    BaseResponse<ProjectInfo> updateProject(UpdateProjectRequest request);

    /**
     * 删除项目
     */
    BaseResponse<Void> deleteProject(String projectId);

    /**
     * 查询项目列表
     */
    BaseResponse<ProjectListResponse> listProjects(ProjectQueryRequest request);
}
