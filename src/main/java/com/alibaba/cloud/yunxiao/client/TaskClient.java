package com.alibaba.cloud.yunxiao.client;

import com.alibaba.cloud.yunxiao.dto.BaseResponse;
import com.alibaba.cloud.yunxiao.dto.requirement.*;

/**
 * 任务管理客户端接口
 * 
 * @author yunxiao-sdk
 * @since 1.0.0
 */
public interface TaskClient {

    /**
     * 创建任务
     */
    BaseResponse<RequirementInfo> createTask(CreateRequirementRequest request);

    /**
     * 获取任务信息
     */
    BaseResponse<RequirementInfo> getTask(String taskId);

    /**
     * 更新任务信息
     */
    BaseResponse<RequirementInfo> updateTask(UpdateRequirementRequest request);

    /**
     * 删除任务
     */
    BaseResponse<Void> deleteTask(String taskId);

    /**
     * 查询任务列表
     */
    BaseResponse<RequirementListResponse> listTasks(RequirementQueryRequest request);

    /**
     * 修改任务状态
     */
    BaseResponse<RequirementInfo> updateTaskStatus(UpdateStatusRequest request);

    /**
     * 批量修改任务状态
     */
    BaseResponse<BatchUpdateStatusResponse> batchUpdateTaskStatus(BatchUpdateStatusRequest request);
}
