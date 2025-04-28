# 介绍
## 1、项目介绍
::: tip 
- Dromara Warm-Flow国产工作流引擎🎉，其特点简洁轻量，五脏俱全，灵活扩展性强，是一个可通过jar引入设计器的工作流。
:::

1. 简洁易用：只有7张表，代码量少，可快速上手和集成
1. 审批功能：支持通过、退回、任意跳转、转办、终止、会签、票签、委派和加减签、互斥和并行网关
1. 监听器与流程变量：支持四种监听器和不同颗粒的的作用范围，支持spel表达式，灵活可扩展，参数传递，动态权限
1. 流程图：流程引擎自带流程图，数据入库，并行支持扩展
1. 流程设计器：可通过jar包形式快速集成到项目，减少繁琐代码搬运和适配，支持solon和springboot
1. 条件表达式：内置常见的和spel条件表达式，并且支持自定义扩展
1. 办理人表达式：内置${handler}和spel格式的表达式，可满足不同场景，灵活可扩展
1. orm框架支持：目前支持MyBatis、Mybatis-Plus、Mybatis-Flex、Jpa和Easy-Query，后续会由社区提供其他支持，扩展方便
1. 数据库支持：目前支持MySQL 、Oracle、PostgreSQL和SQL Server，后续会继续支持其他数据库或者国产数据库
1. 多租户与软删除：流程引擎自身维护多租户和软删除实现，也可使用对应orm框架的实现方式
1. 同时支持spring和solon
1. 兼容java8和java17,理论11也可以 
1. 官方提供基于ruoyi-vue封装实战项目，很实用

<img src="https://foruda.gitee.com/images/1737617259247546863/ad0eb5ab_2218307.png"/>

```shell
希望一键三连，你的⭐️ Star ⭐️是我持续开发的动力，项目也活的更长
```
## 2、演示地址

- admin/admin123

演示地址：[http://www.hhzai.top](http://www.hhzai.top)  
项目地址：[https://gitee.com/min290/hh-vue.git](https://gitee.com/min290/hh-vue.git)

## 3、应用场景

Dromara Warm-Flow作为一个国产的工作流引擎，其设计简洁轻量但功能全面，适用于多种应用场景，尤其是针对中小型项目。以下是一些典型的应用场景：

1. 企业内部流程管理：用于管理企业的日常业务流程，如请假、报销、采购审批等。
2. 项目管理：在项目管理中，Dromara Warm-Flow可以用来跟踪项目任务的状态，管理项目流程，确保项目按计划进行。
3. 办公自动化：通过Warm-Flow，企业可以实现办公流程的自动化，提高工作效率，减少人为错误。
4. 客户服务流程：用于管理客户服务请求，如客户咨询、投诉处理、售后服务等。
5. 人力资源管理：在人力资源管理中，Warm-Flow可用于员工招聘、培训、绩效评估等流程的管理。
6. 财务和会计流程：管理财务审批流程，如发票审核、预算审批等。
7. IT服务管理：用于IT服务请求的处理，如IT支持请求、系统变更管理等。
8. 业务流程优化：企业可以利用Dromara Warm-Flow来分析和优化现有业务流程，提高业务效率。
9. 合规性和风险管理：帮助企业在遵守法规和标准的同时，管理风险和合规流程。
10. 跨部门协作：Dromara Warm-Flow支持跨部门的流程协作，帮助不同部门之间更好地协调工作。

## 4、支持数据库类型
::: tip 目前支持MySQL 、Oracle、PostgreSQL和SQL Server，其他数据库只需要转换表结构，使用Mybatis-Plus、Mybatis-Flex和Easy-Query即可兼容
:::
* [x] MySQL
* [x] Oracle
* [x] PostgreSQL
* [x] SQL Server
* [ ] ......


## 5、支持orm框架类型
* [x] mybatis
* [x] mybatis-plus
* [x] jpa
* [x] easy-query
* [x] mybatis-flex
* [ ] ......
