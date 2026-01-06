# Maven 国内镜像配置指南

## 📋 说明

为了让 Maven 下载依赖更快，推荐配置国内镜像（阿里云 Maven 仓库）。

## 🔧 配置方法

### 方法一：配置全局 Maven settings.xml（推荐）

在你的本地机器上，找到或创建 Maven 配置文件：

**Windows:**
```
C:\Users\你的用户名\.m2\settings.xml
```

**Mac/Linux:**
```
~/.m2/settings.xml
```

将以下内容复制到 `settings.xml` 文件中：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
          http://maven.apache.org/xsd/settings-1.0.0.xsd">

    <!-- 本地仓库路径 -->
    <localRepository>${user.home}/.m2/repository</localRepository>

    <!-- 阿里云镜像配置 -->
    <mirrors>
        <mirror>
            <id>aliyunmaven</id>
            <mirrorOf>*</mirrorOf>
            <name>阿里云公共仓库</name>
            <url>https://maven.aliyun.com/repository/public</url>
        </mirror>
        <mirror>
            <id>aliyun-central</id>
            <mirrorOf>central</mirrorOf>
            <name>阿里云中央仓库</name>
            <url>https://maven.aliyun.com/repository/central</url>
        </mirror>
        <mirror>
            <id>aliyun-spring</id>
            <mirrorOf>spring</mirrorOf>
            <name>阿里云 Spring 仓库</name>
            <url>https://maven.aliyun.com/repository/spring</url>
        </mirror>
    </mirrors>

    <!-- 仓库配置 -->
    <profiles>
        <profile>
            <id>aliyun</id>
            <repositories>
                <repository>
                    <id>aliyun-central</id>
                    <url>https://maven.aliyun.com/repository/central</url>
                    <releases>
                        <enabled>true</enabled>
                    </releases>
                    <snapshots>
                        <enabled>false</enabled>
                    </snapshots>
                </repository>
                <repository>
                    <id>aliyun-public</id>
                    <url>https://maven.aliyun.com/repository/public</url>
                    <releases>
                        <enabled>true</enabled>
                    </releases>
                    <snapshots>
                        <enabled>false</enabled>
                    </snapshots>
                </repository>
                <repository>
                    <id>aliyun-spring</id>
                    <url>https://maven.aliyun.com/repository/spring</url>
                    <releases>
                        <enabled>true</enabled>
                    </releases>
                    <snapshots>
                        <enabled>false</enabled>
                    </snapshots>
                </repository>
            </repositories>
            <pluginRepositories>
                <pluginRepository>
                    <id>aliyun-plugin</id>
                    <url>https://maven.aliyun.com/repository/public</url>
                    <releases>
                        <enabled>true</enabled>
                    </releases>
                    <snapshots>
                        <enabled>false</enabled>
                    </snapshots>
                </pluginRepository>
            </pluginRepositories>
        </profile>
    </profiles>

    <!-- 激活 profile -->
    <activeProfiles>
        <activeProfile>aliyun</activeProfile>
    </activeProfiles>

</settings>
```

### 方法二：在项目 pom.xml 中配置（仅对当前项目生效）

在 `pom.xml` 的 `<repositories>` 和 `<pluginRepositories>` 标签中添加：

```xml
<repositories>
    <repository>
        <id>aliyun-public</id>
        <url>https://maven.aliyun.com/repository/public</url>
        <releases>
            <enabled>true</enabled>
        </releases>
        <snapshots>
            <enabled>false</enabled>
        </snapshots>
    </repository>
    <repository>
        <id>aliyun-spring</id>
        <url>https://maven.aliyun.com/repository/spring</url>
        <releases>
            <enabled>true</enabled>
        </releases>
        <snapshots>
            <enabled>false</enabled>
        </snapshots>
    </repository>
</repositories>

<pluginRepositories>
    <pluginRepository>
        <id>aliyun-plugin</id>
        <url>https://maven.aliyun.com/repository/public</url>
        <releases>
            <enabled>true</enabled>
        </releases>
        <snapshots>
            <enabled>false</enabled>
        </snapshots>
    </pluginRepository>
</pluginRepositories>
```

## ✅ 验证配置

配置完成后，运行以下命令验证：

```bash
mvn help:effective-settings
```

查看输出中是否包含阿里云镜像配置。

## 📊 阿里云 Maven 仓库地址

| 仓库名称 | 地址 |
|---------|------|
| 公共仓库 | https://maven.aliyun.com/repository/public |
| 中央仓库 | https://maven.aliyun.com/repository/central |
| Spring 仓库 | https://maven.aliyun.com/repository/spring |
| Google 仓库 | https://maven.aliyun.com/repository/google |
| Gradle Plugin | https://maven.aliyun.com/repository/gradle-plugin |
| Apache Snapshots | https://maven.aliyun.com/repository/apache-snapshots |

## 🔍 常见问题

### Q1: 配置后下载速度还是很慢？
A: 检查网络连接，或者尝试清除本地缓存：
```bash
mvn clean
```

### Q2: 找不到某些依赖？
A: 阿里云镜像可能同步有延迟，可以临时使用中央仓库：
```xml
<mirror>
    <id>central</id>
    <mirrorOf>*</mirrorOf>
    <name>Maven Central</name>
    <url>https://repo.maven.apache.org/maven2</url>
</mirror>
```

### Q3: 如何切换回默认配置？
A: 删除或重命名 `settings.xml` 文件，或者删除 `<mirrors>` 配置。

## 📝 其他国内镜像

除了阿里云，还可以使用：

**华为云 Maven 镜像：**
```xml
<mirror>
    <id>huaweicloud</id>
    <mirrorOf>*</mirrorOf>
    <url>https://repo.huaweicloud.com/repository/maven/</url>
</mirror>
```

**清华大学开源镜像站：**
```xml
<mirror>
    <id>tsinghua</id>
    <mirrorOf>*</mirrorOf>
    <url>https://mirrors.tuna.tsinghua.edu.cn/maven/</url>
</mirror>
```

## 💡 提示

- 推荐使用方法一（全局配置），对所有 Maven 项目都生效
- 镜像配置可以提高依赖下载速度，但可能存在同步延迟
- 遇到问题时可以临时切换回中央仓库排查

---

**相关链接：**
- 阿里云 Maven 仓库：https://developer.aliyun.com/mvn/guide
- Maven 官方文档：https://maven.apache.org/settings.html
