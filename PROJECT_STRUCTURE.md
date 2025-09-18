# 云效SDK项目结构

## 项目目录结构

```
yunxiao-sdk/
├── pom.xml                                    # Maven配置文件
├── README.md                                  # 项目说明文档
├── USAGE_EXAMPLE.md                          # 使用示例文档
├── PROJECT_STRUCTURE.md                      # 项目结构说明
├── .gitignore                                # Git忽略文件配置
└── src/
    ├── main/
    │   ├── java/
    │   │   └── com/
    │   │       └── alibaba/
    │   │           └── cloud/
    │   │               └── yunxiao/
    │   │                   ├── YunxiaoSdkApplication.java          # Spring Boot启动类
    │   │                   ├── client/
    │   │                   │   └── YunxiaoProjectClient.java       # 核心API客户端
    │   │                   ├── config/
    │   │                   │   ├── YunxiaoProperties.java          # 配置属性类
    │   │                   │   └── YunxiaoAutoConfiguration.java   # 自动配置类
    │   │                   ├── dto/
    │   │                   │   ├── BaseResponse.java               # 基础响应类
│   │                   │   ├── project/
│   │                   │   │   ├── ProjectInfo.java            # 项目信息DTO
│   │                   │   │   ├── CreateProjectRequest.java   # 创建项目请求DTO
│   │                   │   │   ├── UpdateProjectRequest.java   # 更新项目请求DTO
│   │                   │   │   ├── ProjectListResponse.java    # 项目列表响应DTO
│   │                   │   │   └── ProjectQueryRequest.java    # 项目查询请求DTO
│   │                   │   └── requirement/
│   │                   │       ├── RequirementInfo.java        # 需求信息DTO
│   │                   │       ├── CreateRequirementRequest.java # 创建需求请求DTO
│   │                   │       ├── UpdateRequirementRequest.java # 更新需求请求DTO
│   │                   │       ├── RequirementListResponse.java # 需求列表响应DTO
│   │                   │       └── RequirementQueryRequest.java # 需求查询请求DTO
    │   │                   └── example/
    │   │                       └── YunxiaoProjectExample.java      # 使用示例类
    │   └── resources/
    │       ├── META-INF/
    │       │   └── spring.factories           # Spring Boot自动配置文件
    │       └── application.yml                # 默认配置文件
    └── test/
        ├── java/
        │   └── com/
        │       └── alibaba/
        │           └── cloud/
        │               └── yunxiao/
        │                   ├── client/
        │                   │   └── YunxiaoProjectClientTest.java   # 客户端测试类
        │                   └── config/
        │                       └── YunxiaoPropertiesTest.java      # 配置测试类
        └── resources/
            └── application-test.yml           # 测试配置文件
```

## 核心组件说明

### 1. 配置层 (config)

- **YunxiaoProperties**: 配置属性类，定义所有可配置的参数
- **YunxiaoAutoConfiguration**: 自动配置类，负责Bean的创建和配置

### 2. 客户端层 (client)

- **YunxiaoProjectClient**: 核心API客户端，封装所有云效项目和需求相关的API调用

### 3. 数据传输层 (dto)

- **BaseResponse**: 统一的API响应格式
- **project包**: 项目相关的请求和响应DTO类
- **requirement包**: 需求相关的请求和响应DTO类

### 4. 示例层 (example)

- **YunxiaoProjectExample**: 详细的使用示例，展示各种API调用方式

### 5. 测试层 (test)

- **YunxiaoProjectClientTest**: 客户端功能测试
- **YunxiaoPropertiesTest**: 配置类测试

## 技术架构

### 依赖关系图

```
YunxiaoSdkApplication
    ↓
YunxiaoAutoConfiguration
    ↓
YunxiaoProjectClient ← YunxiaoProperties
    ↓
HTTP Request (Hutool) → 云效API
    ↓
JSON Response (Fastjson) → DTO Objects
```

### 核心流程

1. **配置加载**: Spring Boot自动加载YunxiaoProperties配置
2. **Bean创建**: YunxiaoAutoConfiguration创建YunxiaoProjectClient Bean
3. **API调用**: 客户端处理请求签名、发送HTTP请求
4. **响应处理**: 解析JSON响应为Java对象
5. **错误处理**: 统一的错误处理和响应格式

## 扩展点

### 1. 添加新的API方法

在`YunxiaoProjectClient`中添加新的方法：

```java
public BaseResponse<NewResponseType> newApiMethod(NewRequestType request) {
    // 实现新的API调用逻辑
}
```

### 2. 添加新的需求类型

如果需要支持其他类型的工作项（如任务、缺陷等），可以扩展需求相关的DTO类：

```java
@Data
public class TaskInfo extends RequirementInfo {
    // 任务特有的字段
}

@Data
public class BugInfo extends RequirementInfo {
    // 缺陷特有的字段
}
```

### 3. 添加新的DTO类

在相应的包下创建新的DTO类：

```java
@Data
public class NewRequestType {
    // 定义请求字段
}

@Data
public class NewResponseType {
    // 定义响应字段
}
```

### 4. 自定义配置

扩展`YunxiaoProperties`类：

```java
@Data
@ConfigurationProperties(prefix = "yunxiao")
public class YunxiaoProperties {
    // 现有配置...
    
    /**
     * 新增配置项
     */
    private String newConfig;
}
```

## 部署和使用

### 1. 构建SDK

```bash
mvn clean package
```

### 2. 发布到Maven仓库

```bash
mvn deploy
```

### 3. 其他项目引用

```xml
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>yunxiao-sdk</artifactId>
    <version>1.0.0</version>
</dependency>
```

## 维护指南

### 1. 版本管理

- 遵循语义化版本控制
- 主版本号：不兼容的API修改
- 次版本号：向下兼容的功能性新增
- 修订号：向下兼容的问题修正

### 2. 测试策略

- 单元测试：覆盖所有核心逻辑
- 集成测试：验证API调用流程
- 性能测试：确保SDK性能满足要求

### 3. 文档维护

- 及时更新API文档
- 保持示例代码的准确性
- 记录重要的变更日志

---

这个项目结构设计遵循了Spring Boot的最佳实践，具有良好的可扩展性和维护性。
