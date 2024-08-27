# 部署说明

## 项目简介

这是一个基于Vue.js作为前端框架，Spring Boot作为后端框架的Web项目。

## 环境要求

- **Java 8 或更高版本**
- **Maven 3.6.0 或更高版本**
- **Node.js 14.x 或更高版本**
- **MySQL 5.7 或更高版本**

## 前后端部署

1. **克隆仓库到本地**：

    ```bash
    git clone https://github.com/Sophia911/Web.git
    cd backend
    ```

2. **配置数据库**：

    修改`src/main/resources/application.properties`文件，配置数据库连接信息：

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name?useSSL=false&serverTimezone=UTC
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    ```

3. **构建并运行后端**：

    使用Maven构建项目并启动Spring Boot应用：
    运行SeBackEndApplication.java


4. **访问后端服务**：

    在浏览器中访问 `http://localhost:8080`，验证后端服务是否正常启动。

## 前端部署

1. **安装依赖**：

    进入前端目录并安装项目依赖：

    ```bash
    cd ../frontend
    npm install
    ```

2. **运行前端开发服务器**：

    启动前端开发服务器：

    ```bash
    npm run dev
    ```

    前端应用将在`http://localhost:443`上运行。
