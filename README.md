# 云效SDK (Yunxiao SDK)

阿里云云效项目API的Java SDK，基于Spring Boot 3和JDK 17开发，提供简洁易用的API调用接口。

## 特性

- 🚀 基于Spring Boot 3和JDK 17
- 🔧 使用Maven构建管理
- 🛠️ 集成Hutool工具库和Fastjson
- 🔐 自动处理API鉴权和签名
- 📦 开箱即用的自动配置
- 🧪 完整的单元测试覆盖
- 📚 详细的使用示例和文档

## 快速开始

### 1. 添加依赖

在你的项目中添加以下Maven依赖：

```xml
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>yunxiao-sdk</artifactId>
    <version>1.0.0</version>
</dependency>
```

### 2. 配置参数

在`application.yml`中添加云效配置：

```yaml
yunxiao:
  enabled: true
  gateway-url: https://devops.cn-hangzhou.aliyuncs.com
  access-key-id: ${YUNXIAO_ACCESS_KEY_ID:your-access-key-id}
  access-key-secret: ${YUNXIAO_ACCESS_KEY_SECRET:your-access-key-secret}
  timeout: 30000
  connect-timeout: 10000
  ssl-enabled: true
  retry-count: 3
  retry-interval: 1000
```

### 3. 使用示例

```java
@Autowired
private ProjectClient projectClient;

@Autowired
private RequirementClient requirementClient;

@Autowired
private TaskClient taskClient;

@Autowired
private BugClient bugClient;

// 创建项目
CreateProjectRequest createRequest = new CreateProjectRequest();
createRequest.setName("我的项目");
createRequest.setDescription("项目描述");
createRequest.setIdentifier("my-project");

BaseResponse<ProjectInfo> createResponse = projectClient.createProject(createRequest);
if (createResponse.isSuccess()) {
    ProjectInfo project = createResponse.getData();
    System.out.println("项目创建成功: " + project.getName());
}

// 获取项目信息
BaseResponse<ProjectInfo> getResponse = projectClient.getProject("project-id");
if (getResponse.isSuccess()) {
    ProjectInfo project = getResponse.getData();
    System.out.println("项目名称: " + project.getName());
}

// 查询项目列表
ProjectQueryRequest queryRequest = new ProjectQueryRequest();
queryRequest.setPageNumber(1);
queryRequest.setPageSize(10);

BaseResponse<ProjectListResponse> listResponse = projectClient.listProjects(queryRequest);
if (listResponse.isSuccess()) {
    ProjectListResponse data = listResponse.getData();
    System.out.println("项目总数: " + data.getTotalCount());
}

// 创建需求
CreateRequirementRequest createReqRequest = new CreateRequirementRequest();
createReqRequest.setTitle("用户登录功能");
createReqRequest.setDescription("实现用户登录功能");
createReqRequest.setProjectId("project-id");
createReqRequest.setPriority("High");

BaseResponse<RequirementInfo> createReqResponse = requirementClient.createRequirement(createReqRequest);
if (createReqResponse.isSuccess()) {
    RequirementInfo requirement = createReqResponse.getData();
    System.out.println("需求创建成功: " + requirement.getTitle());
}

// 查询需求列表
RequirementQueryRequest reqQueryRequest = new RequirementQueryRequest();
reqQueryRequest.setProjectId("project-id");
reqQueryRequest.setPageNumber(1);
reqQueryRequest.setPageSize(10);

BaseResponse<RequirementListResponse> reqListResponse = requirementClient.listRequirements(reqQueryRequest);
if (reqListResponse.isSuccess()) {
    RequirementListResponse data = reqListResponse.getData();
    System.out.println("需求总数: " + data.getTotalCount());
}

// 创建任务
CreateRequirementRequest createTaskRequest = new CreateRequirementRequest();
createTaskRequest.setTitle("实现用户登录功能");
createTaskRequest.setDescription("开发用户登录模块");
createTaskRequest.setProjectId("project-id");
createTaskRequest.setPriority("High");

BaseResponse<RequirementInfo> createTaskResponse = taskClient.createTask(createTaskRequest);
if (createTaskResponse.isSuccess()) {
    RequirementInfo task = createTaskResponse.getData();
    System.out.println("任务创建成功: " + task.getTitle());
}

// 创建缺陷
CreateRequirementRequest createBugRequest = new CreateRequirementRequest();
createBugRequest.setTitle("登录页面样式显示异常");
createBugRequest.setDescription("在Chrome浏览器中，登录按钮样式显示不正确");
createBugRequest.setProjectId("project-id");
createBugRequest.setPriority("Medium");

BaseResponse<RequirementInfo> createBugResponse = bugClient.createBug(createBugRequest);
if (createBugResponse.isSuccess()) {
    RequirementInfo bug = createBugResponse.getData();
    System.out.println("缺陷创建成功: " + bug.getTitle());
}

// 修改需求状态
UpdateStatusRequest updateStatusRequest = new UpdateStatusRequest();
updateStatusRequest.setWorkItemId("requirement-id");
updateStatusRequest.setStatus("In Progress");
updateStatusRequest.setReason("开始开发");
updateStatusRequest.setComment("需求已确认，开始进入开发阶段");
updateStatusRequest.setUpdateTime(LocalDateTime.now());
updateStatusRequest.setOperatorId("developer-id");
updateStatusRequest.setOperatorName("开发人员");

BaseResponse<RequirementInfo> updateStatusResponse = requirementClient.updateRequirementStatus(updateStatusRequest);
if (updateStatusResponse.isSuccess()) {
    RequirementInfo requirement = updateStatusResponse.getData();
    System.out.println("需求状态修改成功: " + requirement.getStatus());
}

// 批量修改任务状态
BatchUpdateStatusRequest batchUpdateRequest = new BatchUpdateStatusRequest();
batchUpdateRequest.setWorkItemIds(Arrays.asList("task-1", "task-2", "task-3"));
batchUpdateRequest.setStatus("Completed");
batchUpdateRequest.setReason("任务完成");
batchUpdateRequest.setComment("所有任务已完成");
batchUpdateRequest.setUpdateTime(LocalDateTime.now());
batchUpdateRequest.setOperatorId("project-manager-id");
batchUpdateRequest.setOperatorName("项目经理");

BaseResponse<BatchUpdateStatusResponse> batchUpdateResponse = taskClient.batchUpdateTaskStatus(batchUpdateRequest);
if (batchUpdateResponse.isSuccess()) {
    BatchUpdateStatusResponse batchResponse = batchUpdateResponse.getData();
    System.out.println("批量修改任务状态成功，成功数量: " + batchResponse.getSuccessCount());
}
```

## API 文档

### ProjectClient

项目管理API客户端接口，提供以下方法：

#### 创建项目
```java
BaseResponse<ProjectInfo> createProject(CreateProjectRequest request)
```

#### 获取项目信息
```java
BaseResponse<ProjectInfo> getProject(String projectId)
```

#### 更新项目信息
```java
BaseResponse<ProjectInfo> updateProject(UpdateProjectRequest request)
```

#### 删除项目
```java
BaseResponse<Void> deleteProject(String projectId)
```

#### 查询项目列表
```java
BaseResponse<ProjectListResponse> listProjects(ProjectQueryRequest request)
```

### RequirementClient

需求管理API客户端接口，提供以下方法：

#### 创建需求
```java
BaseResponse<RequirementInfo> createRequirement(CreateRequirementRequest request)
```

#### 获取需求信息
```java
BaseResponse<RequirementInfo> getRequirement(String requirementId)
```

#### 更新需求信息
```java
BaseResponse<RequirementInfo> updateRequirement(UpdateRequirementRequest request)
```

#### 删除需求
```java
BaseResponse<Void> deleteRequirement(String requirementId)
```

#### 查询需求列表
```java
BaseResponse<RequirementListResponse> listRequirements(RequirementQueryRequest request)
```

#### 修改需求状态
```java
BaseResponse<RequirementInfo> updateRequirementStatus(UpdateStatusRequest request)
```

#### 批量修改需求状态
```java
BaseResponse<BatchUpdateStatusResponse> batchUpdateRequirementStatus(BatchUpdateStatusRequest request)
```

### TaskClient

任务管理API客户端接口，提供以下方法：

#### 创建任务
```java
BaseResponse<RequirementInfo> createTask(CreateRequirementRequest request)
```

#### 获取任务信息
```java
BaseResponse<RequirementInfo> getTask(String taskId)
```

#### 更新任务信息
```java
BaseResponse<RequirementInfo> updateTask(UpdateRequirementRequest request)
```

#### 删除任务
```java
BaseResponse<Void> deleteTask(String taskId)
```

#### 查询任务列表
```java
BaseResponse<RequirementListResponse> listTasks(RequirementQueryRequest request)
```

#### 修改任务状态
```java
BaseResponse<RequirementInfo> updateTaskStatus(UpdateStatusRequest request)
```

#### 批量修改任务状态
```java
BaseResponse<BatchUpdateStatusResponse> batchUpdateTaskStatus(BatchUpdateStatusRequest request)
```

### BugClient

缺陷管理API客户端接口，提供以下方法：

#### 创建缺陷
```java
BaseResponse<RequirementInfo> createBug(CreateRequirementRequest request)
```

#### 获取缺陷信息
```java
BaseResponse<RequirementInfo> getBug(String bugId)
```

#### 更新缺陷信息
```java
BaseResponse<RequirementInfo> updateBug(UpdateRequirementRequest request)
```

#### 删除缺陷
```java
BaseResponse<Void> deleteBug(String bugId)
```

#### 查询缺陷列表
```java
BaseResponse<RequirementListResponse> listBugs(RequirementQueryRequest request)
```

#### 修改缺陷状态
```java
BaseResponse<RequirementInfo> updateBugStatus(UpdateStatusRequest request)
```

#### 批量修改缺陷状态
```java
BaseResponse<BatchUpdateStatusResponse> batchUpdateBugStatus(BatchUpdateStatusRequest request)
```

### 请求和响应对象

#### CreateProjectRequest
- `name`: 项目名称
- `description`: 项目描述
- `identifier`: 项目标识符
- `type`: 项目类型（默认：DevOps）
- `visibility`: 项目可见性（默认：Private）
- `tags`: 项目标签
- `config`: 项目配置

#### UpdateProjectRequest
- `id`: 项目ID
- `name`: 项目名称
- `description`: 项目描述
- `status`: 项目状态
- `visibility`: 项目可见性
- `tags`: 项目标签
- `config`: 项目配置

#### ProjectQueryRequest
- `name`: 项目名称（模糊查询）
- `status`: 项目状态
- `type`: 项目类型
- `visibility`: 项目可见性
- `creatorId`: 创建者ID
- `pageNumber`: 页码（默认：1）
- `pageSize`: 每页大小（默认：20）
- `orderBy`: 排序字段（默认：createTime）
- `orderDirection`: 排序方向（默认：DESC）

#### ProjectInfo
- `id`: 项目ID
- `name`: 项目名称
- `description`: 项目描述
- `identifier`: 项目标识符
- `type`: 项目类型
- `status`: 项目状态
- `visibility`: 项目可见性
- `creatorId`: 创建者ID
- `creatorName`: 创建者名称
- `createTime`: 创建时间
- `updateTime`: 更新时间
- `memberCount`: 项目成员数量
- `tags`: 项目标签
- `config`: 项目配置

#### CreateRequirementRequest
- `title`: 需求标题
- `description`: 需求描述
- `type`: 需求类型（默认：Feature）
- `priority`: 优先级（默认：Medium）
- `projectId`: 项目ID
- `assigneeId`: 指派人ID
- `plannedStartTime`: 计划开始时间
- `plannedEndTime`: 计划结束时间
- `tags`: 需求标签
- `customFields`: 自定义字段
- `parentId`: 父需求ID
- `relatedRequirementIds`: 关联的需求ID列表
- `source`: 需求来源
- `businessValue`: 业务价值
- `acceptanceCriteria`: 验收标准
- `dependencies`: 需求依赖

#### UpdateRequirementRequest
- `id`: 需求ID
- `title`: 需求标题
- `description`: 需求描述
- `type`: 需求类型
- `status`: 需求状态
- `priority`: 优先级
- `assigneeId`: 指派人ID
- `plannedStartTime`: 计划开始时间
- `plannedEndTime`: 计划结束时间
- `actualStartTime`: 实际开始时间
- `actualEndTime`: 实际结束时间
- `tags`: 需求标签
- `customFields`: 自定义字段
- `updateReason`: 更新原因

#### RequirementQueryRequest
- `projectId`: 项目ID
- `title`: 需求标题（模糊查询）
- `type`: 需求类型
- `status`: 需求状态
- `priority`: 优先级
- `creatorId`: 创建者ID
- `assigneeId`: 指派人ID
- `category`: 需求分类
- `module`: 需求模块
- `version`: 需求版本
- `complexity`: 需求复杂度
- `source`: 需求来源
- `parentId`: 父需求ID
- `pageNumber`: 页码（默认：1）
- `pageSize`: 每页大小（默认：20）
- `orderBy`: 排序字段（默认：createTime）
- `orderDirection`: 排序方向（默认：DESC）

#### UpdateStatusRequest
- `workItemId`: 工作项ID
- `status`: 新状态
- `reason`: 状态修改原因
- `comment`: 状态修改备注
- `updateTime`: 状态修改时间
- `operatorId`: 操作人ID
- `operatorName`: 操作人姓名

#### BatchUpdateStatusRequest
- `workItemIds`: 工作项ID列表
- `status`: 新状态
- `reason`: 状态修改原因
- `comment`: 状态修改备注
- `updateTime`: 状态修改时间
- `operatorId`: 操作人ID
- `operatorName`: 操作人姓名

#### BatchUpdateStatusResponse
- `successIds`: 成功更新的工作项ID列表
- `failedIds`: 失败的工作项ID列表
- `failures`: 失败详情
- `totalCount`: 总数量
- `successCount`: 成功数量
- `failureCount`: 失败数量

#### RequirementInfo
- `id`: 需求ID
- `title`: 需求标题
- `description`: 需求描述
- `type`: 需求类型
- `status`: 需求状态
- `priority`: 优先级
- `projectId`: 项目ID
- `projectName`: 项目名称
- `creatorId`: 创建者ID
- `creatorName`: 创建者名称
- `assigneeId`: 指派人ID
- `assigneeName`: 指派人名称
- `createTime`: 创建时间
- `updateTime`: 更新时间
- `plannedStartTime`: 计划开始时间
- `plannedEndTime`: 计划结束时间
- `actualStartTime`: 实际开始时间
- `actualEndTime`: 实际结束时间
- `tags`: 需求标签
- `attachments`: 需求附件
- `customFields`: 自定义字段
- `requirementNumber`: 需求编号
- `parentId`: 父需求ID
- `children`: 子需求列表
- `relatedRequirementIds`: 关联的需求ID列表
- `source`: 需求来源
- `businessValue`: 业务价值
- `acceptanceCriteria`: 验收标准

## 配置说明

### YunxiaoProperties

| 属性 | 类型 | 默认值 | 说明 |
|------|------|--------|------|
| enabled | boolean | true | 是否启用云效SDK |
| gatewayUrl | String | https://devops.cn-hangzhou.aliyuncs.com | 云效API网关地址 |
| accessKeyId | String | - | 访问密钥ID |
| accessKeySecret | String | - | 访问密钥Secret |
| timeout | int | 30000 | 请求超时时间（毫秒） |
| connectTimeout | int | 10000 | 连接超时时间（毫秒） |
| sslEnabled | boolean | true | 是否启用SSL验证 |
| retryCount | int | 3 | 重试次数 |
| retryInterval | int | 1000 | 重试间隔（毫秒） |

## 环境变量

建议通过环境变量配置敏感信息：

```bash
export YUNXIAO_ACCESS_KEY_ID=your-access-key-id
export YUNXIAO_ACCESS_KEY_SECRET=your-access-key-secret
```

## 错误处理

所有API调用都返回`BaseResponse<T>`对象，包含以下字段：

- `requestId`: 请求ID
- `success`: 是否成功
- `errorCode`: 错误码
- `errorMessage`: 错误信息
- `data`: 响应数据

```java
BaseResponse<ProjectInfo> response = yunxiaoProjectClient.getProject("project-id");
if (response.isSuccess()) {
    // 处理成功响应
    ProjectInfo project = response.getData();
} else {
    // 处理错误响应
    log.error("API调用失败: {} - {}", response.getErrorCode(), response.getErrorMessage());
}
```

## 开发指南

### 构建项目

```bash
mvn clean compile
```

### 运行测试

```bash
mvn test
```

### 打包

```bash
mvn clean package
```

## 许可证

本项目采用 MIT 许可证。

## 贡献

欢迎提交 Issue 和 Pull Request 来改进这个项目。

## 联系方式

如有问题，请通过以下方式联系：

- 提交 Issue
- 发送邮件至项目维护者

---

**注意**: 使用前请确保已获得有效的阿里云云效API访问权限，并正确配置AccessKey和Secret。
