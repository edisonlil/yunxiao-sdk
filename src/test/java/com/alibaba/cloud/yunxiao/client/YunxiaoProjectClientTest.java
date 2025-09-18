package com.alibaba.cloud.yunxiao.client;

import com.alibaba.cloud.yunxiao.client.impl.YunxiaoProjectClient;
import com.alibaba.cloud.yunxiao.config.YunxiaoProperties;
import com.alibaba.cloud.yunxiao.dto.BaseResponse;
import com.alibaba.cloud.yunxiao.dto.project.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 云效项目客户端测试类
 * 
 * @author yunxiao-sdk
 * @since 1.0.0
 */
@ExtendWith(MockitoExtension.class)
class YunxiaoProjectClientTest {

    private YunxiaoProjectClient client;
    private YunxiaoProperties properties;

    @BeforeEach
    void setUp() {
        properties = new YunxiaoProperties();
        properties.setGatewayUrl("https://devops.cn-hangzhou.aliyuncs.com");
        properties.setAccessKeyId("test-access-key-id");
        properties.setAccessKeySecret("test-access-key-secret");
        properties.setTimeout(30000);
        properties.setConnectTimeout(10000);
        
        client = new YunxiaoProjectClient(properties);
    }

    @Test
    void testCreateProject() {
        // 准备测试数据
        CreateProjectRequest request = new CreateProjectRequest();
        request.setName("测试项目");
        request.setDescription("这是一个测试项目");
        request.setIdentifier("test-project");
        request.setType("DevOps");
        request.setVisibility("Private");

        // 执行测试（注意：这里会实际发送HTTP请求，需要真实的AccessKey）
        // 在实际测试中，建议使用Mock或者测试环境
        BaseResponse<ProjectInfo> response = client.createProject(request);

        // 验证结果
        assertNotNull(response);
        assertNotNull(response.getRequestId());
        
        // 由于没有真实的AccessKey，这里主要验证方法调用不会抛出异常
        // 在实际环境中，应该验证具体的业务逻辑
    }

    @Test
    void testGetProject() {
        // 准备测试数据
        String projectId = "test-project-id";

        // 执行测试
        BaseResponse<ProjectInfo> response = client.getProject(projectId);

        // 验证结果
        assertNotNull(response);
        assertNotNull(response.getRequestId());
    }

    @Test
    void testUpdateProject() {
        // 准备测试数据
        UpdateProjectRequest request = new UpdateProjectRequest();
        request.setId("test-project-id");
        request.setName("更新后的项目名称");
        request.setDescription("更新后的项目描述");

        // 执行测试
        BaseResponse<ProjectInfo> response = client.updateProject(request);

        // 验证结果
        assertNotNull(response);
        assertNotNull(response.getRequestId());
    }

    @Test
    void testDeleteProject() {
        // 准备测试数据
        String projectId = "test-project-id";

        // 执行测试
        BaseResponse<Void> response = client.deleteProject(projectId);

        // 验证结果
        assertNotNull(response);
        assertNotNull(response.getRequestId());
    }

    @Test
    void testListProjects() {
        // 准备测试数据
        ProjectQueryRequest request = new ProjectQueryRequest();
        request.setPageNumber(1);
        request.setPageSize(10);
        request.setOrderBy("createTime");
        request.setOrderDirection("DESC");

        // 执行测试
        BaseResponse<ProjectListResponse> response = client.listProjects(request);

        // 验证结果
        assertNotNull(response);
        assertNotNull(response.getRequestId());
    }

    @Test
    void testListProjectsWithNullRequest() {
        // 执行测试（传入null）
        BaseResponse<ProjectListResponse> response = client.listProjects(null);

        // 验证结果
        assertNotNull(response);
        assertNotNull(response.getRequestId());
    }

    @Test
    void testCreateProjectRequestValidation() {
        // 测试CreateProjectRequest的默认值
        CreateProjectRequest request = new CreateProjectRequest();
        
        assertEquals("DevOps", request.getType());
        assertEquals("Private", request.getVisibility());
        
        // 测试配置默认值
        CreateProjectRequest.ProjectConfig config = new CreateProjectRequest.ProjectConfig();
        assertTrue(config.getCodeManagementEnabled());
        assertTrue(config.getPipelineEnabled());
        assertTrue(config.getTestManagementEnabled());
        assertTrue(config.getArtifactManagementEnabled());
    }

    @Test
    void testProjectQueryRequestValidation() {
        // 测试ProjectQueryRequest的默认值
        ProjectQueryRequest request = new ProjectQueryRequest();
        
        assertEquals(1, request.getPageNumber());
        assertEquals(20, request.getPageSize());
        assertEquals("createTime", request.getOrderBy());
        assertEquals("DESC", request.getOrderDirection());
    }
}
