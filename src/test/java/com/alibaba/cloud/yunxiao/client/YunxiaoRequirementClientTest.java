package com.alibaba.cloud.yunxiao.client;

import com.alibaba.cloud.yunxiao.client.impl.YunxiaoWorkItemClient;
import com.alibaba.cloud.yunxiao.config.YunxiaoProperties;
import com.alibaba.cloud.yunxiao.dto.BaseResponse;
import com.alibaba.cloud.yunxiao.dto.requirement.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 云效需求客户端测试类
 * 
 * @author yunxiao-sdk
 * @since 1.0.0
 */
@ExtendWith(MockitoExtension.class)
class YunxiaoRequirementClientTest {

    private YunxiaoWorkItemClient client;
    private YunxiaoProperties properties;

    @BeforeEach
    void setUp() {
        properties = new YunxiaoProperties();
        properties.setGatewayUrl("https://devops.cn-hangzhou.aliyuncs.com");
        properties.setAccessKeyId("test-access-key-id");
        properties.setAccessKeySecret("test-access-key-secret");
        properties.setTimeout(30000);
        properties.setConnectTimeout(10000);
        
        client = new YunxiaoWorkItemClient(properties);
    }

    @Test
    void testCreateRequirement() {
        // 准备测试数据
        CreateRequirementRequest request = new CreateRequirementRequest();
        request.setTitle("测试需求");
        request.setDescription("这是一个测试需求");
        request.setType("Feature");
        request.setPriority("High");
        request.setProjectId("test-project-id");
        request.setAssigneeId("test-assignee-id");
        request.setPlannedStartTime(LocalDateTime.now());
        request.setPlannedEndTime(LocalDateTime.now().plusDays(7));
        request.setTags(Arrays.asList("测试", "需求", "示例"));
        request.setSource("用户反馈");
        request.setBusinessValue("提升用户体验");
        request.setAcceptanceCriteria("功能正常运行，无bug");

        // 执行测试
        BaseResponse<RequirementInfo> response = client.createWorkItem(request);

        // 验证结果
        assertNotNull(response);
        assertNotNull(response.getRequestId());
    }

    @Test
    void testGetRequirement() {
        // 准备测试数据
        String requirementId = "test-requirement-id";

        // 执行测试
        BaseResponse<RequirementInfo> response = client.getWorkItem(requirementId);

        // 验证结果
        assertNotNull(response);
        assertNotNull(response.getRequestId());
    }

    @Test
    void testUpdateRequirement() {
        // 准备测试数据
        UpdateRequirementRequest request = new UpdateRequirementRequest();
        request.setId("test-requirement-id");
        request.setTitle("更新后的需求标题");
        request.setDescription("更新后的需求描述");
        request.setStatus("In Progress");
        request.setPriority("Medium");
        request.setAssigneeId("new-assignee-id");

        // 执行测试
        BaseResponse<RequirementInfo> response = client.updateWorkItem(request);

        // 验证结果
        assertNotNull(response);
        assertNotNull(response.getRequestId());
    }

    @Test
    void testDeleteRequirement() {
        // 准备测试数据
        String requirementId = "test-requirement-id";

        // 执行测试
        BaseResponse<Void> response = client.deleteWorkItem(requirementId);

        // 验证结果
        assertNotNull(response);
        assertNotNull(response.getRequestId());
    }

    @Test
    void testListRequirements() {
        // 准备测试数据
        RequirementQueryRequest request = new RequirementQueryRequest();
        request.setProjectId("test-project-id");
        request.setPageNumber(1);
        request.setPageSize(10);
        request.setOrderBy("createTime");
        request.setOrderDirection("DESC");
        request.setStatus("Open");
        request.setPriority("High");

        // 执行测试
        BaseResponse<RequirementListResponse> response = client.listWorkItems(request);

        // 验证结果
        assertNotNull(response);
        assertNotNull(response.getRequestId());
    }

    @Test
    void testListRequirementsWithNullRequest() {
        // 执行测试（传入null）
        BaseResponse<RequirementListResponse> response = client.listRequirements(null);

        // 验证结果
        assertNotNull(response);
        assertNotNull(response.getRequestId());
    }

    @Test
    void testCreateRequirementRequestValidation() {
        // 测试CreateRequirementRequest的默认值
        CreateRequirementRequest request = new CreateRequirementRequest();
        
        assertEquals("Feature", request.getType());
        assertEquals("Medium", request.getPriority());
        assertTrue(request.getAutoGenerateNumber());
    }

    @Test
    void testRequirementQueryRequestValidation() {
        // 测试RequirementQueryRequest的默认值
        RequirementQueryRequest request = new RequirementQueryRequest();
        
        assertEquals(1, request.getPageNumber());
        assertEquals(20, request.getPageSize());
        assertEquals("createTime", request.getOrderBy());
        assertEquals("DESC", request.getOrderDirection());
        assertFalse(request.getIncludeDeleted());
        assertFalse(request.getIncludeChildren());
        assertFalse(request.getIncludeRelated());
    }

    @Test
    void testCreateRequirementWithDependencies() {
        // 准备测试数据
        CreateRequirementRequest request = new CreateRequirementRequest();
        request.setTitle("带依赖关系的需求");
        request.setDescription("这个需求有依赖关系");
        request.setProjectId("test-project-id");

        // 添加依赖关系
        CreateRequirementRequest.RequirementDependency dependency = 
            new CreateRequirementRequest.RequirementDependency();
        dependency.setRequirementId("dependency-requirement-id");
        dependency.setDependencyType("BLOCKED_BY");
        dependency.setDescription("依赖其他需求");
        request.setDependencies(Arrays.asList(dependency));

        // 执行测试
        BaseResponse<RequirementInfo> response = client.createWorkItem(request);

        // 验证结果
        assertNotNull(response);
        assertNotNull(response.getRequestId());
    }

    @Test
    void testUpdateRequirementWithComplexFields() {
        // 准备测试数据
        UpdateRequirementRequest request = new UpdateRequirementRequest();
        request.setId("test-requirement-id");
        request.setTitle("复杂更新需求");
        request.setDescription("包含复杂字段的更新");
        request.setStatus("Completed");
        request.setPriority("Low");
        request.setCategory("功能需求");
        request.setModule("用户管理");
        request.setVersion("v1.0");
        request.setComplexity("Medium");
        request.setEstimatedEffort(5.0);
        request.setActualEffort(6.0);
        request.setUpdateReason("需求变更");

        // 执行测试
        BaseResponse<RequirementInfo> response = client.updateWorkItem(request);

        // 验证结果
        assertNotNull(response);
        assertNotNull(response.getRequestId());
    }

    @Test
    void testListRequirementsWithComplexQuery() {
        // 准备测试数据
        RequirementQueryRequest request = new RequirementQueryRequest();
        request.setProjectId("test-project-id");
        request.setTitle("测试");
        request.setType("Feature");
        request.setStatus("Open");
        request.setPriority("High");
        request.setCreatorId("creator-id");
        request.setAssigneeId("assignee-id");
        request.setCategory("功能需求");
        request.setModule("用户管理");
        request.setVersion("v1.0");
        request.setComplexity("Medium");
        request.setSource("用户反馈");
        request.setParentId("parent-requirement-id");
        request.setOnlyChildren(true);
        request.setCreateTimeStart(LocalDateTime.now().minusDays(30));
        request.setCreateTimeEnd(LocalDateTime.now());
        request.setPlannedStartTimeStart(LocalDateTime.now());
        request.setPlannedStartTimeEnd(LocalDateTime.now().plusDays(30));
        request.setPlannedEndTimeStart(LocalDateTime.now().plusDays(7));
        request.setPlannedEndTimeEnd(LocalDateTime.now().plusDays(30));
        request.setPageNumber(1);
        request.setPageSize(50);
        request.setOrderBy("priority");
        request.setOrderDirection("ASC");
        request.setIncludeDeleted(false);
        request.setIncludeChildren(true);
        request.setIncludeRelated(true);

        // 执行测试
        BaseResponse<RequirementListResponse> response = client.listWorkItems(request);

        // 验证结果
        assertNotNull(response);
        assertNotNull(response.getRequestId());
    }
}
