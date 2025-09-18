package com.alibaba.cloud.yunxiao.client;

import com.alibaba.cloud.yunxiao.dto.BaseResponse;
import com.alibaba.cloud.yunxiao.dto.requirement.*;

/**
 * 工作项客户端接口
 * 
 * @author yunxiao-sdk
 * @since 1.0.0
 */
public interface WorkItemClient {

    /**
     * 创建工作项
     */
    BaseResponse<RequirementInfo> createWorkItem(CreateRequirementRequest request);

    /**
     * 获取工作项信息
     */
    BaseResponse<RequirementInfo> getWorkItem(String workItemId);

    /**
     * 更新工作项信息
     */
    BaseResponse<RequirementInfo> updateWorkItem(UpdateRequirementRequest request);

    /**
     * 删除工作项
     */
    BaseResponse<Void> deleteWorkItem(String workItemId);

    /**
     * 查询工作项列表
     */
    BaseResponse<RequirementListResponse> listWorkItems(RequirementQueryRequest request);
}
