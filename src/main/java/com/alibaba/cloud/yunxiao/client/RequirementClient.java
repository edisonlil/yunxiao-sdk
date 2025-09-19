package com.alibaba.cloud.yunxiao.client;

import com.alibaba.cloud.yunxiao.dto.BaseResponse;
import com.alibaba.cloud.yunxiao.dto.requirement.*;

/**
 * 需求管理客户端接口
 * 
 * @author yunxiao-sdk
 * @since 1.0.0
 */
public interface RequirementClient {

    /**
     * 创建需求
     */
    BaseResponse<RequirementInfo> createRequirement(CreateRequirementRequest request);

    /**
     * 获取需求信息
     */
    BaseResponse<RequirementInfo> getRequirement(String requirementId);

    /**
     * 更新需求信息
     */
    BaseResponse<RequirementInfo> updateRequirement(UpdateRequirementRequest request);

    /**
     * 删除需求
     */
    BaseResponse<Void> deleteRequirement(String requirementId);

    /**
     * 查询需求列表
     */
    BaseResponse<RequirementListResponse> listRequirements(RequirementQueryRequest request);

    /**
     * 修改需求状态
     */
    BaseResponse<RequirementInfo> updateRequirementStatus(UpdateStatusRequest request);

    /**
     * 批量修改需求状态
     */
    BaseResponse<BatchUpdateStatusResponse> batchUpdateRequirementStatus(BatchUpdateStatusRequest request);

    /**
     * 批量创建需求
     */
    BaseResponse<BatchCreateResponse> batchCreateRequirements(BatchCreateRequest request);
}
