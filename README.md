# 客户数据管理系统

基于 Java 17 + Spring Boot + H2 数据库开发的客户数据管理系统。

## 技术栈

- Java 17
- Spring Boot 3.2.0
- Spring Data JPA
- H2 内存数据库
- Thymeleaf 模板引擎
- Lombok

## 功能特性

- ✅ 客户信息管理（增删改查）
- ✅ 身份证信息管理
- ✅ 数据搜索（支持姓名、手机号、身份证号搜索）
- ✅ 数据验证（手机号、邮箱、身份证号格式验证）
- ✅ 美观的 Web 界面
- ✅ H2 数据库控制台

## 快速开始

### 环境要求

- JDK 17 或更高版本
- Maven 3.6+

### 安装和运行

1. 克隆项目
```bash
git clone [你的Git仓库地址]
cd customer-management-system
```

2. 运行项目
```bash
mvn spring-boot:run
```

或者使用 IDE 运行 `CustomerManagementApplication` 类

3. 访问应用

- 应用主页: http://localhost:8080
- 客户列表: http://localhost:8080/customers
- H2 控制台: http://localhost:8080/h2-console

## H2 数据库配置

访问 H2 控制台时，使用以下连接信息：

- JDBC URL: `jdbc:h2:mem:customerdb`
- 用户名: `sa`
- 密码: (留空)

## 数据结构

### Customer 表

| 字段 | 类型 | 说明 |
|-----|------|------|
| id | Long | 主键 |
| name | String | 姓名 |
| phone | String | 手机号（唯一） |
| email | String | 邮箱 |
| address | String | 地址 |
| birthday | LocalDate | 出生日期 |
| idCardNumber | String | 身份证号（唯一） |
| idCardIssuePlace | String | 身份证签发地 |
| idCardIssueDate | LocalDate | 身份证签发日期 |
| idCardExpiryDate | LocalDate | 身份证有效期 |
| createdAt | LocalDate | 创建时间 |
| updatedAt | LocalDate | 更新时间 |

## API 接口

- `GET /customers` - 获取客户列表
- `GET /customers/new` - 显示添加客户表单
- `POST /customers/save` - 保存新客户
- `GET /customers/edit/{id}` - 显示编辑客户表单
- `POST /customers/update/{id}` - 更新客户信息
- `GET /customers/view/{id}` - 查看客户详情
- `GET /customers/delete/{id}` - 删除客户

## 注意事项

1. H2 数据库是内存数据库，重启应用后数据会丢失
2. 如需持久化数据，可以修改 `application.properties` 中的数据库配置
3. 手机号和身份证号字段设置了唯一约束
4. 所有必填字段都进行了数据验证

## 开发者

- 技术支持：Vibe Coding
- 版本：1.0.0

## 许可证

MIT License
