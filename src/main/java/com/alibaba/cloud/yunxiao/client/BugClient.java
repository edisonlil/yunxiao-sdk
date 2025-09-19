package com.alibaba.cloud.yunxiao.client;

import com.alibaba.cloud.yunxiao.dto.BaseResponse;
import com.alibaba.cloud.yunxiao.dto.requirement.*;

/**
 * 缺陷管理客户端接口
 * 
 * @author yunxiao-sdk
 * @since 1.0.0
 */
public interface BugClient {

    /**
     * 创建缺陷
     */
    BaseResponse<RequirementInfo> createBug(CreateRequirementRequest request);

    /**
     * 获取缺陷信息
     */
    BaseResponse<RequirementInfo> getBug(String bugId);

    /**
     * 更新缺陷信息
     */
    BaseResponse<RequirementInfo> updateBug(UpdateRequirementRequest request);

    /**
     * 删除缺陷
     */
    BaseResponse<Void> deleteBug(String bugId);

    /**
     * 查询缺陷列表
     */
    BaseResponse<RequirementListResponse> listBugs(RequirementQueryRequest request);

    /**
     * 修改缺陷状态
     */
    BaseResponse<RequirementInfo> updateBugStatus(UpdateStatusRequest request);

    /**
     * 批量修改缺陷状态
     */
    BaseResponse<BatchUpdateStatusResponse> batchUpdateBugStatus(BatchUpdateStatusRequest request);

    /**
     * 批量创建缺陷
     */
    BaseResponse<BatchCreateResponse> batchCreateBugs(BatchCreateRequest request);
}
