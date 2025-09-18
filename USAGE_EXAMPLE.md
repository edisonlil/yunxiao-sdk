# 云效SDK使用示例

本文档提供了云效SDK的详细使用示例，帮助开发者快速集成和使用。

## 1. 基础配置

### 1.1 Maven依赖配置

```xml
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>yunxiao-sdk</artifactId>
    <version>1.0.0</version>
</dependency>
```

### 1.2 配置文件

#### application.yml
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

logging:
  level:
    com.alibaba.cloud.yunxiao: INFO
```

#### application.properties
```properties
yunxiao.enabled=true
yunxiao.gateway-url=https://devops.cn-hangzhou.aliyuncs.com
yunxiao.access-key-id=${YUNXIAO_ACCESS_KEY_ID:your-access-key-id}
yunxiao.access-key-secret=${YUNXIAO_ACCESS_KEY_SECRET:your-access-key-secret}
yunxiao.timeout=30000
yunxiao.connect-timeout=10000
yunxiao.ssl-enabled=true
yunxiao.retry-count=3
yunxiao.retry-interval=1000
```

## 2. 基础使用

### 2.1 注入客户端

```java
@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private YunxiaoProjectClient yunxiaoProjectClient;

    // 其他方法...
}
```

### 2.2 创建项目

```java
@PostMapping
public ResponseEntity<ProjectInfo> createProject(@RequestBody CreateProjectRequest request) {
    BaseResponse<ProjectInfo> response = yunxiaoProjectClient.createProject(request);
    
    if (response.isSuccess()) {
        return ResponseEntity.ok(response.getData());
    } else {
        throw new RuntimeException("创建项目失败: " + response.getErrorMessage());
    }
}
```

### 2.3 获取项目信息

```java
@GetMapping("/{projectId}")
public ResponseEntity<ProjectInfo> getProject(@PathVariable String projectId) {
    BaseResponse<ProjectInfo> response = yunxiaoProjectClient.getProject(projectId);
    
    if (response.isSuccess()) {
        return ResponseEntity.ok(response.getData());
    } else {
        return ResponseEntity.notFound().build();
    }
}
```

### 2.4 更新项目信息

```java
@PutMapping("/{projectId}")
public ResponseEntity<ProjectInfo> updateProject(
        @PathVariable String projectId,
        @RequestBody UpdateProjectRequest request) {
    
    request.setId(projectId);
    BaseResponse<ProjectInfo> response = yunxiaoProjectClient.updateProject(request);
    
    if (response.isSuccess()) {
        return ResponseEntity.ok(response.getData());
    } else {
        throw new RuntimeException("更新项目失败: " + response.getErrorMessage());
    }
}
```

### 2.5 删除项目

```java
@DeleteMapping("/{projectId}")
public ResponseEntity<Void> deleteProject(@PathVariable String projectId) {
    BaseResponse<Void> response = yunxiaoProjectClient.deleteProject(projectId);
    
    if (response.isSuccess()) {
        return ResponseEntity.ok().build();
    } else {
        throw new RuntimeException("删除项目失败: " + response.getErrorMessage());
    }
}
```

### 2.6 查询项目列表

```java
@GetMapping
public ResponseEntity<ProjectListResponse> listProjects(
        @RequestParam(defaultValue = "1") int pageNumber,
        @RequestParam(defaultValue = "20") int pageSize,
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String status) {
    
    ProjectQueryRequest request = new ProjectQueryRequest();
    request.setPageNumber(pageNumber);
    request.setPageSize(pageSize);
    request.setName(name);
    request.setStatus(status);
    
    BaseResponse<ProjectListResponse> response = yunxiaoProjectClient.listProjects(request);
    
    if (response.isSuccess()) {
        return ResponseEntity.ok(response.getData());
    } else {
        throw new RuntimeException("查询项目列表失败: " + response.getErrorMessage());
    }
}
```

## 3. 需求管理API使用

### 3.1 创建需求

```java
@PostMapping("/requirements")
public ResponseEntity<RequirementInfo> createRequirement(@RequestBody CreateRequirementRequest request) {
    BaseResponse<RequirementInfo> response = yunxiaoProjectClient.createRequirement(request);
    
    if (response.isSuccess()) {
        return ResponseEntity.ok(response.getData());
    } else {
        throw new RuntimeException("创建需求失败: " + response.getErrorMessage());
    }
}
```

### 3.2 获取需求信息

```java
@GetMapping("/requirements/{requirementId}")
public ResponseEntity<RequirementInfo> getRequirement(@PathVariable String requirementId) {
    BaseResponse<RequirementInfo> response = yunxiaoProjectClient.getRequirement(requirementId);
    
    if (response.isSuccess()) {
        return ResponseEntity.ok(response.getData());
    } else {
        return ResponseEntity.notFound().build();
    }
}
```

### 3.3 更新需求信息

```java
@PutMapping("/requirements/{requirementId}")
public ResponseEntity<RequirementInfo> updateRequirement(
        @PathVariable String requirementId,
        @RequestBody UpdateRequirementRequest request) {
    
    request.setId(requirementId);
    BaseResponse<RequirementInfo> response = yunxiaoProjectClient.updateRequirement(request);
    
    if (response.isSuccess()) {
        return ResponseEntity.ok(response.getData());
    } else {
        throw new RuntimeException("更新需求失败: " + response.getErrorMessage());
    }
}
```

### 3.4 删除需求

```java
@DeleteMapping("/requirements/{requirementId}")
public ResponseEntity<Void> deleteRequirement(@PathVariable String requirementId) {
    BaseResponse<Void> response = yunxiaoProjectClient.deleteRequirement(requirementId);
    
    if (response.isSuccess()) {
        return ResponseEntity.ok().build();
    } else {
        throw new RuntimeException("删除需求失败: " + response.getErrorMessage());
    }
}
```

### 3.5 查询需求列表

```java
@GetMapping("/requirements")
public ResponseEntity<RequirementListResponse> listRequirements(
        @RequestParam(required = false) String projectId,
        @RequestParam(defaultValue = "1") int pageNumber,
        @RequestParam(defaultValue = "20") int pageSize,
        @RequestParam(required = false) String title,
        @RequestParam(required = false) String status,
        @RequestParam(required = false) String priority) {
    
    RequirementQueryRequest request = new RequirementQueryRequest();
    request.setProjectId(projectId);
    request.setPageNumber(pageNumber);
    request.setPageSize(pageSize);
    request.setTitle(title);
    request.setStatus(status);
    request.setPriority(priority);
    
    BaseResponse<RequirementListResponse> response = yunxiaoProjectClient.listRequirements(request);
    
    if (response.isSuccess()) {
        return ResponseEntity.ok(response.getData());
    } else {
        throw new RuntimeException("查询需求列表失败: " + response.getErrorMessage());
    }
}
```

## 4. 高级使用

### 4.1 自定义配置

```java
@Configuration
public class YunxiaoConfig {

    @Bean
    @Primary
    public YunxiaoProperties customYunxiaoProperties() {
        YunxiaoProperties properties = new YunxiaoProperties();
        properties.setGatewayUrl("https://custom-devops.example.com");
        properties.setTimeout(60000);
        properties.setRetryCount(5);
        return properties;
    }
}
```

### 4.2 错误处理

```java
@Service
public class ProjectService {

    @Autowired
    private YunxiaoProjectClient yunxiaoProjectClient;

    public ProjectInfo createProjectSafely(CreateProjectRequest request) {
        try {
            BaseResponse<ProjectInfo> response = yunxiaoProjectClient.createProject(request);
            
            if (response.isSuccess()) {
                return response.getData();
            } else {
                log.error("创建项目失败: {} - {}", response.getErrorCode(), response.getErrorMessage());
                throw new ProjectCreationException(response.getErrorMessage());
            }
        } catch (Exception e) {
            log.error("创建项目时发生异常", e);
            throw new ProjectCreationException("创建项目失败", e);
        }
    }
}

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProjectCreationException extends RuntimeException {
    public ProjectCreationException(String message) {
        super(message);
    }
    
    public ProjectCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
```

### 4.3 批量操作

```java
@Service
public class BatchProjectService {

    @Autowired
    private YunxiaoProjectClient yunxiaoProjectClient;

    public List<ProjectInfo> createMultipleProjects(List<CreateProjectRequest> requests) {
        List<ProjectInfo> createdProjects = new ArrayList<>();
        
        for (CreateProjectRequest request : requests) {
            BaseResponse<ProjectInfo> response = yunxiaoProjectClient.createProject(request);
            
            if (response.isSuccess()) {
                createdProjects.add(response.getData());
            } else {
                log.warn("创建项目失败: {} - {}", request.getName(), response.getErrorMessage());
            }
        }
        
        return createdProjects;
    }

    public void deleteMultipleProjects(List<String> projectIds) {
        for (String projectId : projectIds) {
            BaseResponse<Void> response = yunxiaoProjectClient.deleteProject(projectId);
            
            if (!response.isSuccess()) {
                log.warn("删除项目失败: {} - {}", projectId, response.getErrorMessage());
            }
        }
    }
}
```

### 4.4 异步操作

```java
@Service
public class AsyncProjectService {

    @Autowired
    private YunxiaoProjectClient yunxiaoProjectClient;

    @Async
    public CompletableFuture<ProjectInfo> createProjectAsync(CreateProjectRequest request) {
        BaseResponse<ProjectInfo> response = yunxiaoProjectClient.createProject(request);
        
        if (response.isSuccess()) {
            return CompletableFuture.completedFuture(response.getData());
        } else {
            return CompletableFuture.failedFuture(
                new RuntimeException("创建项目失败: " + response.getErrorMessage()));
        }
    }

    @Async
    public CompletableFuture<List<ProjectInfo>> listProjectsAsync(ProjectQueryRequest request) {
        BaseResponse<ProjectListResponse> response = yunxiaoProjectClient.listProjects(request);
        
        if (response.isSuccess()) {
            return CompletableFuture.completedFuture(response.getData().getProjects());
        } else {
            return CompletableFuture.failedFuture(
                new RuntimeException("查询项目列表失败: " + response.getErrorMessage()));
        }
    }
}
```

## 5. 测试示例

### 5.1 单元测试

```java
@SpringBootTest
class YunxiaoProjectClientTest {

    @Autowired
    private YunxiaoProjectClient yunxiaoProjectClient;

    @Test
    void testCreateProject() {
        CreateProjectRequest request = new CreateProjectRequest();
        request.setName("测试项目");
        request.setDescription("这是一个测试项目");
        request.setIdentifier("test-project");

        BaseResponse<ProjectInfo> response = yunxiaoProjectClient.createProject(request);

        assertThat(response.isSuccess()).isTrue();
        assertThat(response.getData()).isNotNull();
        assertThat(response.getData().getName()).isEqualTo("测试项目");
    }

    @Test
    void testGetProject() {
        String projectId = "existing-project-id";
        
        BaseResponse<ProjectInfo> response = yunxiaoProjectClient.getProject(projectId);

        assertThat(response.isSuccess()).isTrue();
        assertThat(response.getData()).isNotNull();
        assertThat(response.getData().getId()).isEqualTo(projectId);
    }
}
```

### 5.2 集成测试

```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {
    "yunxiao.access-key-id=test-key-id",
    "yunxiao.access-key-secret=test-key-secret"
})
class ProjectControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testCreateProjectEndpoint() {
        CreateProjectRequest request = new CreateProjectRequest();
        request.setName("集成测试项目");
        request.setDescription("集成测试项目描述");

        ResponseEntity<ProjectInfo> response = restTemplate.postForEntity(
            "/api/projects", request, ProjectInfo.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo("集成测试项目");
    }
}
```

## 6. 最佳实践

### 6.1 配置管理

- 使用环境变量管理敏感信息
- 为不同环境配置不同的API网关地址
- 合理设置超时时间和重试次数

### 6.2 错误处理

- 始终检查API响应的成功状态
- 记录详细的错误日志
- 提供用户友好的错误信息

### 6.3 性能优化

- 使用异步操作处理批量请求
- 合理设置分页参数
- 缓存频繁访问的项目信息

### 6.4 安全考虑

- 不要在代码中硬编码AccessKey
- 定期轮换访问密钥
- 使用HTTPS进行API通信

## 7. 常见问题

### 7.1 认证失败

**问题**: API调用返回认证失败错误

**解决方案**:
- 检查AccessKey和Secret是否正确
- 确认API网关地址是否正确
- 检查系统时间是否准确

### 7.2 超时问题

**问题**: API调用超时

**解决方案**:
- 增加timeout配置值
- 检查网络连接
- 考虑使用异步调用

### 7.3 项目不存在

**问题**: 获取项目信息时返回项目不存在

**解决方案**:
- 确认项目ID是否正确
- 检查项目是否已被删除
- 确认当前用户是否有访问权限

### 7.4 需求创建失败

**问题**: 创建需求时返回失败错误

**解决方案**:
- 检查项目ID是否存在
- 确认指派人ID是否有效
- 验证需求标题和描述是否符合要求
- 检查需求类型和优先级是否在允许范围内

### 7.5 需求查询无结果

**问题**: 查询需求列表时返回空结果

**解决方案**:
- 确认项目ID是否正确
- 检查查询条件是否过于严格
- 验证用户是否有访问该项目的权限
- 确认需求状态和类型筛选条件

---

更多详细信息请参考 [README.md](README.md) 和项目源码。
