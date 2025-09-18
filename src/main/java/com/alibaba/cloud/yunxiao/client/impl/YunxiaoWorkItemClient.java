package com.alibaba.cloud.yunxiao.client.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpResponse;
import com.alibaba.cloud.yunxiao.client.AbstractYunxiaoClient;
import com.alibaba.cloud.yunxiao.client.WorkItemClient;
import com.alibaba.cloud.yunxiao.config.YunxiaoProperties;
import com.alibaba.cloud.yunxiao.dto.BaseResponse;
import com.alibaba.cloud.yunxiao.dto.requirement.*;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * 云效工作项客户端实现
 * 
 * @author yunxiao-sdk
 * @since 1.0.0
 */
@Slf4j
public class YunxiaoWorkItemClient extends AbstractYunxiaoClient implements WorkItemClient {

    public YunxiaoWorkItemClient(YunxiaoProperties properties) {
        super(properties);
    }

    @Override
    public BaseResponse<RequirementInfo> createWorkItem(CreateRequirementRequest request) {
        try {
            String url = buildApiUrl("/workitems");
            String requestBody = JSON.toJSONString(request);
            
            HttpResponse response = sendRequest("POST", url, requestBody);
            return parseResponse(response, RequirementInfo.class);
        } catch (Exception e) {
            log.error("创建工作项失败", e);
            return buildErrorResponse(e.getMessage());
        }
    }

    @Override
    public BaseResponse<RequirementInfo> getWorkItem(String workItemId) {
        try {
            String url = buildApiUrl("/workitems/" + workItemId);
            
            HttpResponse response = sendRequest("GET", url, null);
            return parseResponse(response, RequirementInfo.class);
        } catch (Exception e) {
            log.error("获取工作项信息失败，工作项ID: {}", workItemId, e);
            return buildErrorResponse(e.getMessage());
        }
    }

    @Override
    public BaseResponse<RequirementInfo> updateWorkItem(UpdateRequirementRequest request) {
        try {
            String url = buildApiUrl("/workitems/" + request.getId());
            String requestBody = JSON.toJSONString(request);
            
            HttpResponse response = sendRequest("PUT", url, requestBody);
            return parseResponse(response, RequirementInfo.class);
        } catch (Exception e) {
            log.error("更新工作项信息失败，工作项ID: {}", request.getId(), e);
            return buildErrorResponse(e.getMessage());
        }
    }

    @Override
    public BaseResponse<Void> deleteWorkItem(String workItemId) {
        try {
            String url = buildApiUrl("/workitems/" + workItemId);
            
            HttpResponse response = sendRequest("DELETE", url, null);
            return parseResponse(response, Void.class);
        } catch (Exception e) {
            log.error("删除工作项失败，工作项ID: {}", workItemId, e);
            return buildErrorResponse(e.getMessage());
        }
    }

    @Override
    public BaseResponse<RequirementListResponse> listWorkItems(RequirementQueryRequest request) {
        try {
            String url = buildApiUrl("/workitems");
            if (request != null) {
                url += buildRequirementQueryString(request);
            }
            
            HttpResponse response = sendRequest("GET", url, null);
            return parseResponse(response, RequirementListResponse.class);
        } catch (Exception e) {
            log.error("查询工作项列表失败", e);
            return buildErrorResponse(e.getMessage());
        }
    }

    /**
     * 构建需求查询字符串
     */
    private String buildRequirementQueryString(RequirementQueryRequest request) {
        StringBuilder query = new StringBuilder("?");
        
        if (StrUtil.isNotBlank(request.getProjectId())) {
            query.append("projectId=").append(request.getProjectId()).append("&");
        }
        if (StrUtil.isNotBlank(request.getTitle())) {
            query.append("title=").append(request.getTitle()).append("&");
        }
        if (StrUtil.isNotBlank(request.getType())) {
            query.append("type=").append(request.getType()).append("&");
        }
        if (StrUtil.isNotBlank(request.getStatus())) {
            query.append("status=").append(request.getStatus()).append("&");
        }
        if (StrUtil.isNotBlank(request.getPriority())) {
            query.append("priority=").append(request.getPriority()).append("&");
        }
        if (StrUtil.isNotBlank(request.getCreatorId())) {
            query.append("creatorId=").append(request.getCreatorId()).append("&");
        }
        if (StrUtil.isNotBlank(request.getAssigneeId())) {
            query.append("assigneeId=").append(request.getAssigneeId()).append("&");
        }
        if (StrUtil.isNotBlank(request.getCategory())) {
            query.append("category=").append(request.getCategory()).append("&");
        }
        if (StrUtil.isNotBlank(request.getModule())) {
            query.append("module=").append(request.getModule()).append("&");
        }
        if (StrUtil.isNotBlank(request.getVersion())) {
            query.append("version=").append(request.getVersion()).append("&");
        }
        if (StrUtil.isNotBlank(request.getComplexity())) {
            query.append("complexity=").append(request.getComplexity()).append("&");
        }
        if (StrUtil.isNotBlank(request.getSource())) {
            query.append("source=").append(request.getSource()).append("&");
        }
        if (StrUtil.isNotBlank(request.getParentId())) {
            query.append("parentId=").append(request.getParentId()).append("&");
        }
        if (request.getOnlyChildren() != null) {
            query.append("onlyChildren=").append(request.getOnlyChildren()).append("&");
        }
        if (request.getOnlyParents() != null) {
            query.append("onlyParents=").append(request.getOnlyParents()).append("&");
        }
        if (request.getCreateTimeStart() != null) {
            query.append("createTimeStart=").append(request.getCreateTimeStart()).append("&");
        }
        if (request.getCreateTimeEnd() != null) {
            query.append("createTimeEnd=").append(request.getCreateTimeEnd()).append("&");
        }
        if (request.getPlannedStartTimeStart() != null) {
            query.append("plannedStartTimeStart=").append(request.getPlannedStartTimeStart()).append("&");
        }
        if (request.getPlannedStartTimeEnd() != null) {
            query.append("plannedStartTimeEnd=").append(request.getPlannedStartTimeEnd()).append("&");
        }
        if (request.getPlannedEndTimeStart() != null) {
            query.append("plannedEndTimeStart=").append(request.getPlannedEndTimeStart()).append("&");
        }
        if (request.getPlannedEndTimeEnd() != null) {
            query.append("plannedEndTimeEnd=").append(request.getPlannedEndTimeEnd()).append("&");
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
        if (request.getIncludeDeleted() != null) {
            query.append("includeDeleted=").append(request.getIncludeDeleted()).append("&");
        }
        if (request.getIncludeChildren() != null) {
            query.append("includeChildren=").append(request.getIncludeChildren()).append("&");
        }
        if (request.getIncludeRelated() != null) {
            query.append("includeRelated=").append(request.getIncludeRelated()).append("&");
        }
        
        String result = query.toString();
        return result.endsWith("&") ? result.substring(0, result.length() - 1) : result;
    }
}
