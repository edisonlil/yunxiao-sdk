package com.alibaba.cloud.yunxiao.dto.requirement;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 工作项状态修改请求
 * 
 * @author yunxiao-sdk
 * @since 1.0.0
 */
@Data
public class UpdateStatusRequest {

    /**
     * 工作项ID
     */
    private String workItemId;

    /**
     * 新状态
     */
    private String status;

    /**
     * 状态修改原因
     */
    private String reason;

    /**
     * 状态修改备注
     */
    private String comment;

    /**
     * 状态修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 操作人ID
     */
    private String operatorId;

    /**
     * 操作人姓名
     */
    private String operatorName;
}
