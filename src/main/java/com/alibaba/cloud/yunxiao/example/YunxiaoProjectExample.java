package com.alibaba.cloud.yunxiao.example;

import com.alibaba.cloud.yunxiao.client.ProjectClient;
import com.alibaba.cloud.yunxiao.client.RequirementClient;
import com.alibaba.cloud.yunxiao.client.TaskClient;
import com.alibaba.cloud.yunxiao.client.BugClient;
import com.alibaba.cloud.yunxiao.dto.BaseResponse;
import com.alibaba.cloud.yunxiao.dto.project.*;
import com.alibaba.cloud.yunxiao.dto.requirement.*;

import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 云效API使用示例
 * 
 * @author yunxiao-sdk
 * @since 1.0.0
 */
@Slf4j
@Component
public class YunxiaoProjectExample {

    @Autowired
    private ProjectClient projectClient;

    @Autowired
    private RequirementClient requirementClient;

    @Autowired
    private TaskClient taskClient;

    @Autowired
    private BugClient bugClient;

    /**
     * 创建项目示例
     */
    public void createProjectExample() {
        log.info("=== 创建项目示例 ===");
        
        // 构建创建项目请求
        CreateProjectRequest request = new CreateProjectRequest();
        request.setName("我的测试项目");
        request.setDescription("这是一个通过SDK创建的测试项目");
        request.setIdentifier("my-test-project");
        request.setType("DevOps");
        request.setVisibility("Private");
        request.setTags(Arrays.asList("测试", "SDK", "示例"));
        
        // 配置项目功能
        CreateProjectRequest.ProjectConfig config = new CreateProjectRequest.ProjectConfig();
        config.setCodeManagementEnabled(true);
        config.setPipelineEnabled(true);
        config.setTestManagementEnabled(true);
        config.setArtifactManagementEnabled(true);
        request.setConfig(config);
        
        // 调用API创建项目
        BaseResponse<ProjectInfo> response = projectClient.createProject(request);
        
        if (response.isSuccess()) {
            ProjectInfo project = response.getData();
            log.info("项目创建成功！");
            log.info("项目ID: {}", project.getId());
            log.info("项目名称: {}", project.getName());
            log.info("项目标识符: {}", project.getIdentifier());
        } else {
            log.error("项目创建失败: {} - {}", response.getErrorCode(), response.getErrorMessage());
        }
    }

    /**
     * 获取项目信息示例
     */
    public void getProjectExample(String projectId) {
        log.info("=== 获取项目信息示例 ===");
        
        BaseResponse<ProjectInfo> response = projectClient.getProject(projectId);
        
        if (response.isSuccess()) {
            ProjectInfo project = response.getData();
            log.info("项目信息获取成功！");
            log.info("项目ID: {}", project.getId());
            log.info("项目名称: {}", project.getName());
            log.info("项目描述: {}", project.getDescription());
            log.info("项目状态: {}", project.getStatus());
            log.info("创建时间: {}", project.getCreateTime());
        } else {
            log.error("获取项目信息失败: {} - {}", response.getErrorCode(), response.getErrorMessage());
        }
    }

    /**
     * 更新项目信息示例
     */
    public void updateProjectExample(String projectId) {
        log.info("=== 更新项目信息示例 ===");
        
        UpdateProjectRequest request = new UpdateProjectRequest();
        request.setId(projectId);
        request.setName("更新后的项目名称");
        request.setDescription("这是更新后的项目描述");
        request.setStatus("Active");
        request.setTags(Arrays.asList("更新", "测试", "SDK"));
        
        BaseResponse<ProjectInfo> response = projectClient.updateProject(request);
        
        if (response.isSuccess()) {
            ProjectInfo project = response.getData();
            log.info("项目信息更新成功！");
            log.info("更新后的项目名称: {}", project.getName());
            log.info("更新后的项目描述: {}", project.getDescription());
        } else {
            log.error("更新项目信息失败: {} - {}", response.getErrorCode(), response.getErrorMessage());
        }
    }

    /**
     * 查询项目列表示例
     */
    public void listProjectsExample() {
        log.info("=== 查询项目列表示例 ===");
        
        ProjectQueryRequest request = new ProjectQueryRequest();
        request.setPageNumber(1);
        request.setPageSize(10);
        request.setOrderBy("createTime");
        request.setOrderDirection("DESC");
        request.setStatus("Active");
        
        BaseResponse<ProjectListResponse> response = projectClient.listProjects(request);
        
        if (response.isSuccess()) {
            ProjectListResponse listResponse = response.getData();
            log.info("项目列表查询成功！");
            log.info("总项目数: {}", listResponse.getTotalCount());
            log.info("当前页项目数: {}", listResponse.getProjects().size());
            
            for (ProjectInfo project : listResponse.getProjects()) {
                log.info("项目: {} - {} ({})", project.getName(), project.getDescription(), project.getStatus());
            }
        } else {
            log.error("查询项目列表失败: {} - {}", response.getErrorCode(), response.getErrorMessage());
        }
    }

    /**
     * 删除项目示例
     */
    public void deleteProjectExample(String projectId) {
        log.info("=== 删除项目示例 ===");
        
        BaseResponse<Void> response = projectClient.deleteProject(projectId);
        
        if (response.isSuccess()) {
            log.info("项目删除成功！项目ID: {}", projectId);
        } else {
            log.error("删除项目失败: {} - {}", response.getErrorCode(), response.getErrorMessage());
        }
    }

    // ==================== 需求管理API示例 ====================

    /**
     * 创建需求示例
     */
    public void createRequirementExample() {
        log.info("=== 创建需求示例 ===");
        
        // 构建创建需求请求
        CreateRequirementRequest request = new CreateRequirementRequest();
        request.setSubject("用户登录功能需求");
        request.setDescription("实现用户登录功能，支持用户名密码登录和手机号验证码登录");
        request.setWorkitemTypeId("requirement-type-id");
        request.setSpaceId("your-project-id");
        request.setAssignedTo("developer-id");
        request.setPlannedStartTime(LocalDateTime.now());
        request.setPlannedEndTime(LocalDateTime.now().plusDays(14));
        request.setLabels(Arrays.asList("label-id-1", "label-id-2", "label-id-3"));
        request.setSource("产品规划");
        request.setBusinessValue("提升用户体验，保障系统安全");
        request.setAcceptanceCriteria("1. 支持用户名密码登录\n2. 支持手机号验证码登录\n3. 登录失败次数限制\n4. 密码强度验证");
        request.setCategory("功能需求");
        request.setModule("用户管理");
        request.setVersion("v1.0");
        request.setComplexity("Medium");
        request.setEstimatedEffort(8.0);
        
        // 调用API创建需求
        BaseResponse<RequirementInfo> response = requirementClient.createRequirement(request);
        
        if (response.isSuccess()) {
            RequirementInfo requirement = response.getData();
            log.info("需求创建成功！");
            log.info("需求ID: {}", requirement.getId());
            log.info("需求标题: {}", requirement.getTitle());
            log.info("需求编号: {}", requirement.getRequirementNumber());
        } else {
            log.error("需求创建失败: {} - {}", response.getErrorCode(), response.getErrorMessage());
        }
    }

    /**
     * 获取需求信息示例
     */
    public void getRequirementExample(String requirementId) {
        log.info("=== 获取需求信息示例 ===");
        
        BaseResponse<RequirementInfo> response = requirementClient.getRequirement(requirementId);
        
        if (response.isSuccess()) {
            RequirementInfo requirement = response.getData();
            log.info("需求信息获取成功！");
            log.info("需求ID: {}", requirement.getId());
            log.info("需求标题: {}", requirement.getTitle());
            log.info("需求描述: {}", requirement.getDescription());
            log.info("需求状态: {}", requirement.getStatus());
            log.info("优先级: {}", requirement.getPriority());
            log.info("指派人: {}", requirement.getAssigneeName());
            log.info("创建时间: {}", requirement.getCreateTime());
        } else {
            log.error("获取需求信息失败: {} - {}", response.getErrorCode(), response.getErrorMessage());
        }
    }

    /**
     * 更新需求信息示例
     */
    public void updateRequirementExample(String requirementId) {
        log.info("=== 更新需求信息示例 ===");
        
        UpdateRequirementRequest request = new UpdateRequirementRequest();
        request.setId(requirementId);
        request.setSubject("用户登录功能需求（更新版）");
        request.setDescription("实现用户登录功能，支持多种登录方式，增强安全性");
        request.setStatus("In Progress");
        request.setPriority("High");
        request.setAssignedTo("senior-developer-id");
        request.setActualStartTime(LocalDateTime.now());
        request.setActualEffort(10.0);
        request.setUpdateReason("需求变更，增加安全性要求");
        
        BaseResponse<RequirementInfo> response = requirementClient.updateRequirement(request);
        
        if (response.isSuccess()) {
            RequirementInfo requirement = response.getData();
            log.info("需求信息更新成功！");
            log.info("更新后的需求标题: {}", requirement.getTitle());
            log.info("更新后的需求状态: {}", requirement.getStatus());
            log.info("实际开始时间: {}", requirement.getActualStartTime());
        } else {
            log.error("更新需求信息失败: {} - {}", response.getErrorCode(), response.getErrorMessage());
        }
    }

    /**
     * 查询需求列表示例
     */
    public void listRequirementsExample() {
        log.info("=== 查询需求列表示例 ===");
        
        RequirementQueryRequest request = new RequirementQueryRequest();
        request.setSpaceId("your-project-id");
        request.setPageNumber(1);
        request.setPageSize(10);
        request.setOrderBy("createTime");
        request.setOrderDirection("DESC");
        request.setStatus("Open");
        request.setPriority("High");
        request.setType("Feature");
        request.setIncludeChildren(true);
        
        BaseResponse<RequirementListResponse> response = requirementClient.listRequirements(request);
        
        if (response.isSuccess()) {
            RequirementListResponse listResponse = response.getData();
            log.info("需求列表查询成功！");
            log.info("总需求数: {}", listResponse.getTotalCount());
            log.info("当前页需求数: {}", listResponse.getItems().size());
            
            // 显示统计信息
            if (listResponse.getStatistics() != null) {
                RequirementListResponse.RequirementStatistics stats = listResponse.getStatistics();
                log.info("需求统计信息:");
                log.info("  待处理: {}", stats.getPendingCount());
                log.info("  进行中: {}", stats.getInProgressCount());
                log.info("  已完成: {}", stats.getCompletedCount());
                log.info("  高优先级: {}", stats.getHighPriorityCount());
            }
            
            for (RequirementInfo requirement : listResponse.getItems()) {
                log.info("需求: {} - {} ({}) [{}]", 
                    requirement.getTitle(), 
                    requirement.getDescription(), 
                    requirement.getStatus(),
                    requirement.getPriority());
            }
        } else {
            log.error("查询需求列表失败: {} - {}", response.getErrorCode(), response.getErrorMessage());
        }
    }

    /**
     * 删除需求示例
     */
    public void deleteRequirementExample(String requirementId) {
        log.info("=== 删除需求示例 ===");
        
        BaseResponse<Void> response = requirementClient.deleteRequirement(requirementId);
        
        if (response.isSuccess()) {
            log.info("需求删除成功！需求ID: {}", requirementId);
        } else {
            log.error("删除需求失败: {} - {}", response.getErrorCode(), response.getErrorMessage());
        }
    }

    /**
     * 创建带依赖关系的需求示例
     */
    public void createRequirementWithDependenciesExample() {
        log.info("=== 创建带依赖关系的需求示例 ===");
        
        CreateRequirementRequest request = new CreateRequirementRequest();
        request.setSubject("用户注册功能需求");
        request.setDescription("实现用户注册功能，依赖用户登录功能");
        request.setType("Feature");
        request.setPriority("Medium");
        request.setSpaceId("your-project-id");
        request.setAssignedTo("developer-id");
        
        // 添加依赖关系
        CreateRequirementRequest.RequirementDependency dependency = 
            new CreateRequirementRequest.RequirementDependency();
        dependency.setRequirementId("login-requirement-id");
        dependency.setDependencyType("BLOCKED_BY");
        dependency.setDescription("需要先完成用户登录功能");
        request.setDependencies(Arrays.asList(dependency));
        
        BaseResponse<RequirementInfo> response = requirementClient.createRequirement(request);
        
        if (response.isSuccess()) {
            RequirementInfo requirement = response.getData();
            log.info("带依赖关系的需求创建成功！");
            log.info("需求ID: {}", requirement.getId());
            log.info("需求标题: {}", requirement.getTitle());
        } else {
            log.error("创建带依赖关系的需求失败: {} - {}", response.getErrorCode(), response.getErrorMessage());
        }
    }

    /**
     * 完整的使用流程示例
     */
    public void completeWorkflowExample() {
        log.info("=== 完整使用流程示例 ===");
        
        try {
            // 1. 创建项目
            createProjectExample();
            
            // 2. 查询项目列表
            listProjectsExample();
            
            // 注意：以下操作需要真实的项目ID，这里仅作演示
            String testProjectId = "your-project-id";
            
            // 3. 获取项目信息
            getProjectExample(testProjectId);
            
            // 4. 更新项目信息
            updateProjectExample(testProjectId);
            
            // 5. 创建需求
            createRequirementExample();
            
            // 6. 查询需求列表
            listRequirementsExample();
            
            // 7. 创建带依赖关系的需求
            createRequirementWithDependenciesExample();
            
            // 注意：以下操作需要真实的需求ID，这里仅作演示
            String testRequirementId = "your-requirement-id";
            
            // 8. 获取需求信息
            getRequirementExample(testRequirementId);
            
            // 9. 更新需求信息
            updateRequirementExample(testRequirementId);
            
            // 10. 删除需求（谨慎操作）
            // deleteRequirementExample(testRequirementId);
            
            // 11. 删除项目（谨慎操作）
            // deleteProjectExample(testProjectId);
            
        } catch (Exception e) {
            log.error("执行示例流程时发生异常", e);
        }
    }

    /**
     * 创建任务示例
     */
    public void createTaskExample() {
        log.info("=== 创建任务示例 ===");
        
        CreateRequirementRequest request = new CreateRequirementRequest();
        request.setSubject("实现用户登录功能");
        request.setDescription("开发用户登录模块，包括用户名密码验证");
        request.setSpaceId("test-project-id");
        request.setPriority("High");
        request.setAssignedTo("developer-id");
        request.setPlannedStartTime(LocalDateTime.now());
        request.setPlannedEndTime(LocalDateTime.now().plusDays(3));
        request.setEstimatedEffort(16.0);
        request.setModule("用户管理");
        request.setVersion("v1.0");
        request.setComplexity("Medium");
        
        BaseResponse<RequirementInfo> response = taskClient.createTask(request);
        
        if (response.isSuccess()) {
            RequirementInfo task = response.getData();
            log.info("任务创建成功！");
            log.info("任务ID: {}", task.getId());
            log.info("任务标题: {}", task.getTitle());
        } else {
            log.error("创建任务失败: {} - {}", response.getErrorCode(), response.getErrorMessage());
        }
    }

    /**
     * 创建缺陷示例
     */
    public void createBugExample() {
        log.info("=== 创建缺陷示例 ===");
        
        CreateRequirementRequest request = new CreateRequirementRequest();
        request.setSubject("登录页面样式显示异常");
        request.setDescription("在Chrome浏览器中，登录按钮样式显示不正确");
        request.setSpaceId("test-project-id");
        request.setPriority("Medium");
        request.setAssignedTo("frontend-developer-id");
        request.setPlannedStartTime(LocalDateTime.now());
        request.setPlannedEndTime(LocalDateTime.now().plusDays(1));
        request.setEstimatedEffort(4.0);
        request.setModule("用户界面");
        request.setVersion("v1.0");
        request.setComplexity("Low");
        request.setSource("测试发现");
        
        BaseResponse<RequirementInfo> response = bugClient.createBug(request);
        
        if (response.isSuccess()) {
            RequirementInfo bug = response.getData();
            log.info("缺陷创建成功！");
            log.info("缺陷ID: {}", bug.getId());
            log.info("缺陷标题: {}", bug.getTitle());
        } else {
            log.error("创建缺陷失败: {} - {}", response.getErrorCode(), response.getErrorMessage());
        }
    }

    /**
     * 查询任务列表示例
     */
    public void listTasksExample() {
        log.info("=== 查询任务列表示例 ===");
        
        RequirementQueryRequest request = new RequirementQueryRequest();
        request.setSpaceId("test-project-id");
        request.setPageNumber(1);
        request.setPageSize(10);
        request.setStatus("Open");
        request.setPriority("High");
        request.setIncludeChildren(true);
        
        BaseResponse<RequirementListResponse> response = taskClient.listTasks(request);
        
        if (response.isSuccess()) {
            RequirementListResponse listResponse = response.getData();
            log.info("任务列表查询成功！");
            log.info("总任务数: {}", listResponse.getTotalCount());
            log.info("当前页任务数: {}", listResponse.getItems().size());
        } else {
            log.error("查询任务列表失败: {} - {}", response.getErrorCode(), response.getErrorMessage());
        }
    }

    /**
     * 查询缺陷列表示例
     */
    public void listBugsExample() {
        log.info("=== 查询缺陷列表示例 ===");
        
        RequirementQueryRequest request = new RequirementQueryRequest();
        request.setSpaceId("test-project-id");
        request.setPageNumber(1);
        request.setPageSize(10);
        request.setStatus("Open");
        request.setPriority("High");
        request.setIncludeChildren(true);
        
        BaseResponse<RequirementListResponse> response = bugClient.listBugs(request);
        
        if (response.isSuccess()) {
            RequirementListResponse listResponse = response.getData();
            log.info("缺陷列表查询成功！");
            log.info("总缺陷数: {}", listResponse.getTotalCount());
            log.info("当前页缺陷数: {}", listResponse.getItems().size());
        } else {
            log.error("查询缺陷列表失败: {} - {}", response.getErrorCode(), response.getErrorMessage());
        }
    }

    /**
     * 修改需求状态示例
     */
    public void updateRequirementStatusExample(String requirementId) {
        log.info("=== 修改需求状态示例 ===");
        
        UpdateStatusRequest request = new UpdateStatusRequest();
        request.setWorkItemId(requirementId);
        request.setStatus("In Progress");
        request.setReason("开始开发");
        request.setComment("需求已确认，开始进入开发阶段");
        request.setUpdateTime(LocalDateTime.now());
        request.setOperatorId("developer-id");
        request.setOperatorName("开发人员");
        
        BaseResponse<RequirementInfo> response = requirementClient.updateRequirementStatus(request);
        
        if (response.isSuccess()) {
            RequirementInfo requirement = response.getData();
            log.info("需求状态修改成功！");
            log.info("需求ID: {}", requirement.getId());
            log.info("新状态: {}", requirement.getStatus());
        } else {
            log.error("修改需求状态失败: {} - {}", response.getErrorCode(), response.getErrorMessage());
        }
    }

    /**
     * 批量修改需求状态示例
     */
    public void batchUpdateRequirementStatusExample() {
        log.info("=== 批量修改需求状态示例 ===");
        
        BatchUpdateStatusRequest request = new BatchUpdateStatusRequest();
        request.setWorkItemIds(Arrays.asList("req-1", "req-2", "req-3"));
        request.setStatus("Completed");
        request.setReason("开发完成");
        request.setComment("所有需求开发完成，进入测试阶段");
        request.setUpdateTime(LocalDateTime.now());
        request.setOperatorId("project-manager-id");
        request.setOperatorName("项目经理");
        
        BaseResponse<BatchUpdateStatusResponse> response = requirementClient.batchUpdateRequirementStatus(request);
        
        if (response.isSuccess()) {
            BatchUpdateStatusResponse batchResponse = response.getData();
            log.info("批量修改需求状态成功！");
            log.info("总数量: {}", batchResponse.getTotalCount());
            log.info("成功数量: {}", batchResponse.getSuccessCount());
            log.info("失败数量: {}", batchResponse.getFailureCount());
            log.info("成功的工作项ID: {}", batchResponse.getSuccessIds());
            if (batchResponse.getFailedIds() != null && !batchResponse.getFailedIds().isEmpty()) {
                log.info("失败的工作项ID: {}", batchResponse.getFailedIds());
            }
        } else {
            log.error("批量修改需求状态失败: {} - {}", response.getErrorCode(), response.getErrorMessage());
        }
    }

    /**
     * 修改任务状态示例
     */
    public void updateTaskStatusExample(String taskId) {
        log.info("=== 修改任务状态示例 ===");
        
        UpdateStatusRequest request = new UpdateStatusRequest();
        request.setWorkItemId(taskId);
        request.setStatus("Done");
        request.setReason("任务完成");
        request.setComment("任务已按计划完成，代码已提交");
        request.setUpdateTime(LocalDateTime.now());
        request.setOperatorId("developer-id");
        request.setOperatorName("开发人员");
        
        BaseResponse<RequirementInfo> response = taskClient.updateTaskStatus(request);
        
        if (response.isSuccess()) {
            RequirementInfo task = response.getData();
            log.info("任务状态修改成功！");
            log.info("任务ID: {}", task.getId());
            log.info("新状态: {}", task.getStatus());
        } else {
            log.error("修改任务状态失败: {} - {}", response.getErrorCode(), response.getErrorMessage());
        }
    }

    /**
     * 批量修改任务状态示例
     */
    public void batchUpdateTaskStatusExample() {
        log.info("=== 批量修改任务状态示例 ===");
        
        BatchUpdateStatusRequest request = new BatchUpdateStatusRequest();
        request.setWorkItemIds(Arrays.asList("task-1", "task-2", "task-3"));
        request.setStatus("In Progress");
        request.setReason("开始执行");
        request.setComment("所有任务开始执行");
        request.setUpdateTime(LocalDateTime.now());
        request.setOperatorId("team-lead-id");
        request.setOperatorName("团队负责人");
        
        BaseResponse<BatchUpdateStatusResponse> response = taskClient.batchUpdateTaskStatus(request);
        
        if (response.isSuccess()) {
            BatchUpdateStatusResponse batchResponse = response.getData();
            log.info("批量修改任务状态成功！");
            log.info("总数量: {}", batchResponse.getTotalCount());
            log.info("成功数量: {}", batchResponse.getSuccessCount());
            log.info("失败数量: {}", batchResponse.getFailureCount());
        } else {
            log.error("批量修改任务状态失败: {} - {}", response.getErrorCode(), response.getErrorMessage());
        }
    }

    /**
     * 修改缺陷状态示例
     */
    public void updateBugStatusExample(String bugId) {
        log.info("=== 修改缺陷状态示例 ===");
        
        UpdateStatusRequest request = new UpdateStatusRequest();
        request.setWorkItemId(bugId);
        request.setStatus("Fixed");
        request.setReason("缺陷已修复");
        request.setComment("缺陷已修复并通过测试验证");
        request.setUpdateTime(LocalDateTime.now());
        request.setOperatorId("tester-id");
        request.setOperatorName("测试人员");
        
        BaseResponse<RequirementInfo> response = bugClient.updateBugStatus(request);
        
        if (response.isSuccess()) {
            RequirementInfo bug = response.getData();
            log.info("缺陷状态修改成功！");
            log.info("缺陷ID: {}", bug.getId());
            log.info("新状态: {}", bug.getStatus());
        } else {
            log.error("修改缺陷状态失败: {} - {}", response.getErrorCode(), response.getErrorMessage());
        }
    }

    /**
     * 批量修改缺陷状态示例
     */
    public void batchUpdateBugStatusExample() {
        log.info("=== 批量修改缺陷状态示例 ===");
        
        BatchUpdateStatusRequest request = new BatchUpdateStatusRequest();
        request.setWorkItemIds(Arrays.asList("bug-1", "bug-2", "bug-3"));
        request.setStatus("Closed");
        request.setReason("缺陷已关闭");
        request.setComment("所有缺陷已修复并关闭");
        request.setUpdateTime(LocalDateTime.now());
        request.setOperatorId("qa-manager-id");
        request.setOperatorName("质量保证经理");
        
        BaseResponse<BatchUpdateStatusResponse> response = bugClient.batchUpdateBugStatus(request);
        
        if (response.isSuccess()) {
            BatchUpdateStatusResponse batchResponse = response.getData();
            log.info("批量修改缺陷状态成功！");
            log.info("总数量: {}", batchResponse.getTotalCount());
            log.info("成功数量: {}", batchResponse.getSuccessCount());
            log.info("失败数量: {}", batchResponse.getFailureCount());
        } else {
            log.error("批量修改缺陷状态失败: {} - {}", response.getErrorCode(), response.getErrorMessage());
        }
    }

    /**
     * 批量创建需求示例
     */
    public void batchCreateRequirementsExample() {
        log.info("=== 批量创建需求示例 ===");
        
        // 创建多个需求
        CreateRequirementRequest req1 = new CreateRequirementRequest();
        req1.setSubject("用户登录功能");
        req1.setDescription("实现用户登录功能，包括用户名密码验证");
        req1.setSpaceId("test-project-id");
        req1.setPriority("High");
        req1.setAssignedTo("developer-1");
        req1.setModule("用户管理");
        req1.setVersion("v1.0");
        req1.setComplexity("Medium");
        req1.setEstimatedEffort(8.0);
        
        CreateRequirementRequest req2 = new CreateRequirementRequest();
        req2.setSubject("用户注册功能");
        req2.setDescription("实现用户注册功能，包括邮箱验证");
        req2.setSpaceId("test-project-id");
        req2.setPriority("High");
        req2.setAssignedTo("developer-2");
        req2.setModule("用户管理");
        req2.setVersion("v1.0");
        req2.setComplexity("Medium");
        req2.setEstimatedEffort(6.0);
        
        CreateRequirementRequest req3 = new CreateRequirementRequest();
        req3.setSubject("密码重置功能");
        req3.setDescription("实现密码重置功能，通过邮箱发送重置链接");
        req3.setSpaceId("test-project-id");
        req3.setPriority("Medium");
        req3.setAssignedTo("developer-1");
        req3.setModule("用户管理");
        req3.setVersion("v1.0");
        req3.setComplexity("Low");
        req3.setEstimatedEffort(4.0);
        
        // 创建批量请求
        BatchCreateRequest batchRequest = new BatchCreateRequest();
        batchRequest.setItems(Arrays.asList(req1, req2, req3));
        
        // 设置批量操作选项
        BatchCreateRequest.BatchOptions options = new BatchCreateRequest.BatchOptions();
        options.setContinueOnError(true);
        options.setReturnDetailedResults(true);
        options.setValidateAll(false);
        batchRequest.setOptions(options);
        
        BaseResponse<BatchCreateResponse> response = requirementClient.batchCreateRequirements(batchRequest);
        
        if (response.isSuccess()) {
            BatchCreateResponse batchResponse = response.getData();
            log.info("批量创建需求成功！");
            log.info("总数量: {}", batchResponse.getTotalCount());
            log.info("成功数量: {}", batchResponse.getSuccessCount());
            log.info("失败数量: {}", batchResponse.getFailureCount());
            
            if (batchResponse.getSuccessItems() != null) {
                log.info("成功创建的需求:");
                batchResponse.getSuccessItems().forEach(item -> 
                    log.info("  - ID: {}, 标题: {}", item.getId(), item.getTitle())
                );
            }
            
            if (batchResponse.getFailedItems() != null && !batchResponse.getFailedItems().isEmpty()) {
                log.info("创建失败的需求:");
                batchResponse.getFailedItems().forEach(failure -> 
                    log.info("  - 索引: {}, 错误: {}", failure.getIndex(), failure.getErrorMessage())
                );
            }
        } else {
            log.error("批量创建需求失败: {} - {}", response.getErrorCode(), response.getErrorMessage());
        }
    }

    /**
     * 批量创建任务示例
     */
    public void batchCreateTasksExample() {
        log.info("=== 批量创建任务示例 ===");
        
        // 创建多个任务
        CreateRequirementRequest task1 = new CreateRequirementRequest();
        task1.setSubject("设计用户登录界面");
        task1.setDescription("设计用户登录页面的UI界面");
        task1.setSpaceId("test-project-id");
        task1.setPriority("High");
        task1.setAssignedTo("ui-designer");
        task1.setModule("用户界面");
        task1.setVersion("v1.0");
        task1.setComplexity("Low");
        task1.setEstimatedEffort(4.0);
        
        CreateRequirementRequest task2 = new CreateRequirementRequest();
        task2.setSubject("实现登录API接口");
        task2.setDescription("实现用户登录的后端API接口");
        task2.setSpaceId("test-project-id");
        task2.setPriority("High");
        task2.setAssignedTo("backend-developer");
        task2.setModule("后端服务");
        task2.setVersion("v1.0");
        task2.setComplexity("Medium");
        task2.setEstimatedEffort(8.0);
        
        CreateRequirementRequest task3 = new CreateRequirementRequest();
        task3.setSubject("编写登录功能测试用例");
        task3.setDescription("编写用户登录功能的单元测试和集成测试");
        task3.setSpaceId("test-project-id");
        task3.setPriority("Medium");
        task3.setAssignedTo("qa-engineer");
        task3.setModule("测试");
        task3.setVersion("v1.0");
        task3.setComplexity("Medium");
        task3.setEstimatedEffort(6.0);
        
        // 创建批量请求
        BatchCreateRequest batchRequest = new BatchCreateRequest();
        batchRequest.setItems(Arrays.asList(task1, task2, task3));
        
        // 设置批量操作选项
        BatchCreateRequest.BatchOptions options = new BatchCreateRequest.BatchOptions();
        options.setContinueOnError(true);
        options.setReturnDetailedResults(true);
        batchRequest.setOptions(options);
        
        BaseResponse<BatchCreateResponse> response = taskClient.batchCreateTasks(batchRequest);
        
        if (response.isSuccess()) {
            BatchCreateResponse batchResponse = response.getData();
            log.info("批量创建任务成功！");
            log.info("总数量: {}", batchResponse.getTotalCount());
            log.info("成功数量: {}", batchResponse.getSuccessCount());
            log.info("失败数量: {}", batchResponse.getFailureCount());
            
            if (batchResponse.getSuccessItems() != null) {
                log.info("成功创建的任务:");
                batchResponse.getSuccessItems().forEach(item -> 
                    log.info("  - ID: {}, 标题: {}", item.getId(), item.getTitle())
                );
            }
        } else {
            log.error("批量创建任务失败: {} - {}", response.getErrorCode(), response.getErrorMessage());
        }
    }

    /**
     * 批量创建缺陷示例
     */
    public void batchCreateBugsExample() {
        log.info("=== 批量创建缺陷示例 ===");
        
        // 创建多个缺陷
        CreateRequirementRequest bug1 = new CreateRequirementRequest();
        bug1.setSubject("登录页面样式显示异常");
        bug1.setDescription("在Chrome浏览器中，登录按钮样式显示不正确");
        bug1.setSpaceId("test-project-id");
        bug1.setPriority("Medium");
        bug1.setAssignedTo("frontend-developer");
        bug1.setModule("用户界面");
        bug1.setVersion("v1.0");
        bug1.setComplexity("Low");
        bug1.setEstimatedEffort(2.0);
        bug1.setSource("测试发现");
        
        CreateRequirementRequest bug2 = new CreateRequirementRequest();
        bug2.setSubject("登录接口返回错误状态码");
        bug2.setDescription("当用户名不存在时，接口返回500错误而不是400错误");
        bug2.setSpaceId("test-project-id");
        bug2.setPriority("High");
        bug2.setAssignedTo("backend-developer");
        bug2.setModule("后端服务");
        bug2.setVersion("v1.0");
        bug2.setComplexity("Medium");
        bug2.setEstimatedEffort(4.0);
        bug2.setSource("用户反馈");
        
        CreateRequirementRequest bug3 = new CreateRequirementRequest();
        bug3.setSubject("密码重置邮件发送失败");
        bug3.setDescription("在某些情况下，密码重置邮件无法正常发送");
        bug3.setSpaceId("test-project-id");
        bug3.setPriority("High");
        bug3.setAssignedTo("backend-developer");
        bug3.setModule("邮件服务");
        bug3.setVersion("v1.0");
        bug3.setComplexity("High");
        bug3.setEstimatedEffort(6.0);
        bug3.setSource("生产环境");
        
        // 创建批量请求
        BatchCreateRequest batchRequest = new BatchCreateRequest();
        batchRequest.setItems(Arrays.asList(bug1, bug2, bug3));
        
        // 设置批量操作选项
        BatchCreateRequest.BatchOptions options = new BatchCreateRequest.BatchOptions();
        options.setContinueOnError(true);
        options.setReturnDetailedResults(true);
        batchRequest.setOptions(options);
        
        BaseResponse<BatchCreateResponse> response = bugClient.batchCreateBugs(batchRequest);
        
        if (response.isSuccess()) {
            BatchCreateResponse batchResponse = response.getData();
            log.info("批量创建缺陷成功！");
            log.info("总数量: {}", batchResponse.getTotalCount());
            log.info("成功数量: {}", batchResponse.getSuccessCount());
            log.info("失败数量: {}", batchResponse.getFailureCount());
            
            if (batchResponse.getSuccessItems() != null) {
                log.info("成功创建的缺陷:");
                batchResponse.getSuccessItems().forEach(item -> 
                    log.info("  - ID: {}, 标题: {}", item.getId(), item.getTitle())
                );
            }
        } else {
            log.error("批量创建缺陷失败: {} - {}", response.getErrorCode(), response.getErrorMessage());
        }
    }
}
