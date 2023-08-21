## API 接口开放平台



### 项目介绍

一个提供 API 接口供开发者调用的平台。

管理员可以接入并发布接口，统计分析各接口调用情况；用户可以注册登录并开通接口调用权限，然后可以浏览接口及在线调试，还能使用客户端 SDK 轻松在代码中调用接口。

主页（浏览接口）：

![image-20230713000210615](https://typora-1314662469.cos.ap-shanghai.myqcloud.com/img/202307130017755.png)

接口管理：

![image-20230713000336525](https://typora-1314662469.cos.ap-shanghai.myqcloud.com/img/202307130017853.png)

在线调试：

![image-20230713000456896](https://typora-1314662469.cos.ap-shanghai.myqcloud.com/img/202307130017880.png)

使用自己开发的客户端 SDK，一行代码调用接口：

![image-20230713001023165](https://typora-1314662469.cos.ap-shanghai.myqcloud.com/img/202307130017904.png)



### 技术选型

前端

- React 18
- Ant Design Pro 5.x 脚手架
- Procomponents 组件库
- OpenAPI 前端代码生成

后端

- SpringBoot 2.7.5
- MySQL
- MyBatis-Plus
- Dubbo 3.0.9
- Nacos 2.1.2
- Spring Cloud Gateway 3.1.6



### 业务流程

![image-20230812133534471](https://typora-1314662469.cos.ap-shanghai.myqcloud.com/img/202308121335056.png)


1. 根据业务流程，将整个项目后端划分为 web 系统、模拟接口、公共模块、客户端 SDK、API 网关这 5 个子项目
2. 前端：后端使用 Swagger + Knife4j 自动生成 OpenAPI 规范的接口文档，前端在此基础上使用插件自动生成接口请求代码，降低前后端协作成本
3. 为防止接口被恶意调用，设计 API 签名认证算法，为用户分配唯一 ak / sk 以鉴权，保障调用的安全性
4. 为解决开发者调用成本过高的问题（须自己使用 HTTP + 封装签名去调用接口），基于 Spring Boot Starter 开发了客户端 SDK，一行代码即可调用接口，提高开发体验
5. 选用 Spring Cloud Gateway 作为 API 网关，实现了路由转发、访问控制，并集中处理签名校验、请求参数校验、接口调用统计等业务逻辑，提高安全性的同时、便于系统开发维护
6. 为解决多个子系统内代码大量重复的问题，抽象模型层和业务层代码为公共模块，并使用 Dubbo RPC 框架实现子系统间的高性能接口调用

