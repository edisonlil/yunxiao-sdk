package com.alibaba.cloud.yunxiao.client.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpResponse;
import com.alibaba.cloud.yunxiao.client.AbstractYunxiaoClient;
import com.alibaba.cloud.yunxiao.client.ProjectClient;
import com.alibaba.cloud.yunxiao.config.YunxiaoProperties;
import com.alibaba.cloud.yunxiao.dto.BaseResponse;
import com.alibaba.cloud.yunxiao.dto.project.*;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * 云效项目API客户端实现
 * 
 * @author yunxiao-sdk
 * @since 1.0.0
 */
@Slf4j
public class YunxiaoProjectClient extends AbstractYunxiaoClient implements ProjectClient {

    public YunxiaoProjectClient(YunxiaoProperties properties) {
        super(properties);
    }

    @Override
    public BaseResponse<ProjectInfo> createProject(CreateProjectRequest request) {
        try {
            String url = buildApiUrl("/projects");
            String requestBody = JSON.toJSONString(request);
            
            HttpResponse response = sendRequest("POST", url, requestBody);
            return parseResponse(response, ProjectInfo.class);
        } catch (Exception e) {
            log.error("创建项目失败", e);
            return buildErrorResponse(e.getMessage());
        }
    }

    @Override
    public BaseResponse<ProjectInfo> getProject(String projectId) {
        try {
            String url = buildApiUrl("/projects/" + projectId);
            
            HttpResponse response = sendRequest("GET", url, null);
            return parseResponse(response, ProjectInfo.class);
        } catch (Exception e) {
            log.error("获取项目信息失败，项目ID: {}", projectId, e);
            return buildErrorResponse(e.getMessage());
        }
    }

    @Override
    public BaseResponse<ProjectInfo> updateProject(UpdateProjectRequest request) {
        try {
            String url = buildApiUrl("/projects/" + request.getId());
            String requestBody = JSON.toJSONString(request);
            
            HttpResponse response = sendRequest("PUT", url, requestBody);
            return parseResponse(response, ProjectInfo.class);
        } catch (Exception e) {
            log.error("更新项目信息失败，项目ID: {}", request.getId(), e);
            return buildErrorResponse(e.getMessage());
        }
    }

    @Override
    public BaseResponse<Void> deleteProject(String projectId) {
        try {
            String url = buildApiUrl("/projects/" + projectId);
            
            HttpResponse response = sendRequest("DELETE", url, null);
            return parseResponse(response, Void.class);
        } catch (Exception e) {
            log.error("删除项目失败，项目ID: {}", projectId, e);
            return buildErrorResponse(e.getMessage());
        }
    }

    @Override
    public BaseResponse<ProjectListResponse> listProjects(ProjectQueryRequest request) {
        try {
            String url = buildApiUrl("/projects");
            if (request != null) {
                url += buildQueryString(request);
            }
            
            HttpResponse response = sendRequest("GET", url, null);
            return parseResponse(response, ProjectListResponse.class);
        } catch (Exception e) {
            log.error("查询项目列表失败", e);
            return buildErrorResponse(e.getMessage());
        }
    }

    /**
     * 构建项目查询字符串
     */
    private String buildQueryString(ProjectQueryRequest request) {
        StringBuilder query = new StringBuilder("?");
        
        if (StrUtil.isNotBlank(request.getName())) {
            query.append("name=").append(request.getName()).append("&");
        }
        if (StrUtil.isNotBlank(request.getStatus())) {
            query.append("status=").append(request.getStatus()).append("&");
        }
        if (StrUtil.isNotBlank(request.getType())) {
            query.append("type=").append(request.getType()).append("&");
        }
        if (StrUtil.isNotBlank(request.getVisibility())) {
            query.append("visibility=").append(request.getVisibility()).append("&");
        }
        if (StrUtil.isNotBlank(request.getCreatorId())) {
            query.append("creatorId=").append(request.getCreatorId()).append("&");
        }
        if (request.getPageNumber() != null) {
            query.append("pageNumber=").append(request.getPageNumber()).append("&");
        }
        if (request.getPageSize() != null) {
            query.append("pageSize=").append(request.getPageSize()).append("&");
        }
        if (StrUtil.isNotBlank(request.getOrderBy())) {
            query.append("orderBy=").append(request.getOrderBy()).append("&");
        }
        if (StrUtil.isNotBlank(request.getOrderDirection())) {
            query.append("orderDirection=").append(request.getOrderDirection()).append("&");
        }
        
        String result = query.toString();
        return result.endsWith("&") ? result.substring(0, result.length() - 1) : result;
    }
}
