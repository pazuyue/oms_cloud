# oms_cloud OMS 微服务

## Platform Management 平台管理服务 ##
> * 暂无

## Sentinel 限流管理 ##
> * 限流管理

## CommonSecurity 用户管理服务 ##
> * 用户登录
> * 用户登出
> * 用户信息获取
> * 用户注册
> * 用户校验
> * 品牌信息获取
## Commodity 供应链管理服务 ##
> * 实仓管理
> * 货主管理
> * 虚仓管理
> * 采购单管理
> * 采购入库单管理
> * 用户权限校验

### 公共模块 ###
> * JWT用户信息解析
> * 根据用户品牌对照信息，切换用户库
 


### 仓库关系 ###

* 实仓
* 货主
* 虚仓

> 实仓->货主->虚仓

![img.png](img.png)


### 技术栈 ###
> * JWT token交互
> * Redis 缓存管理
> * SpringBoot 底层框架
> * Nacos 配置与服务管理
> * OpenFeign 远程调度
> * lombok 工具类
> * hutool 工具类
> * mybatis-plus 数据库组件
> * dynamic-datasource 多数据源
> * mybatis-plus-generator-ui 根据数据表代码自动生成器
> * easypoi Excel管理组件
> * validation 校验组件
> * fastjson2 json管理组件
> * starter-actuator 优雅关机组件
> * Security 微服务权限管理组件

