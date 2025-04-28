<p align="center">
	<img alt="logo" src="https://foruda.gitee.com/images/1726820610127990120/c8c5f3a4_2218307.png" width="100">
</p>
<h1 align="center" style="margin: 30px 0 30px; font-weight: bold;">Warm-Flow工作流</h1>
<p align="center">
	<a href="https://gitee.com/dromara/warm-flow/stargazers"><img src="https://gitee.com/dromara/warm-flow/badge/star.svg?theme=dark"></a>
        <a href='https://gitee.com/dromara/warm-flow/members'><img src='https://gitee.com/dromara/warm-flow/badge/fork.svg?theme=dark' alt='fork'> 
        </img></a>
</p>


## 介绍

Dromara Warm-Flow，一款国产的工作流引擎，以其简洁轻量、五脏俱全、灵活扩展性强的特点，成为了众多开发者的首选。它不仅可以通过jar包快速集成设计器，还具备以下显著优势：

- **简洁易用**‌：仅包含7张表，代码量少，上手和集成速度快。
- **审批功能全面**‌：支持通过、退回、驳回到上一个任务、撤销、任意跳转、转办、终止、会签、票签、委派、加减签、互斥和并行网关等多种审批操作，以及条件表达式、办理人表达和监听器等高级功能。
- **流程设计器**‌：通过jar包形式快速集成到项目，支持节点属性扩展，适配SpringBoot和Solon，减少繁琐代码搬运和适配。
- **条件表达式**‌：内置常见的和spel条件表达式，支持自定义扩展。
- **办理人变量表达式**‌：内置${handler}和spel格式的表达式，满足不同场景需求，灵活可扩展。
- **监听器**‌：提供四种监听器，支持不同作用范围和spel表达式，参数传递灵活，支持动态权限。
- **流程变量**‌：在整个流程办理过程起到重要的角色，如办理人表达式中，传入变量进行动态指定办理人。
- **流程图**‌：自带流程图功能，数据入库，支持在流程图上追加文字，自定义流程图节点颜色等扩展。
- **ORM框架支持**‌：支持MyBatis、Mybatis-Plus、Mybatis-Flex、Jpa、Easy-Query和BeetlSql，后续将扩展支持其他框架
- **数据库支持**‌：支持MySQL、Oracle、PostgreSQL和SQL Server，其他数据库只需要转换表结构即可支持。
- **多租户与软删除**‌：流程引擎自身维护多租户和软删除实现，也可使用对应ORM框架的实现方式。
- **兼容性**‌：同时支持Spring和Solon，兼容Java8、Java17、Java21。
- **实战项目**‌：官方提供基于Ruoyi-Vue封装的实战项目，极具参考价值。

```shell
希望一键三连，你的⭐️ Star ⭐️是我持续开发的动力，项目也活的更长
```

>   **[github地址](https://github.com/dromara/warm-flow.git)** | **[gitee地址](https://gitee.com/dromara/warm-flow.git)** | **[gitCode地址](https://gitcode.com/dromara/warm-flow)**

## 演示地址

- admin/admin123

演示地址：http://www.hhzai.top

## 使用文档与联系方式
https://warm-flow.dromara.org/

## 组件所需脚本

- 首次导入，先创建数据库，找到对应数据库的全量脚本[warm-flow-all.sql](https://gitee.com/dromara/warm-flow/tree/master/sql/mysql)，执行
- 如果版本更新，找到对应数据库的更新版本，比如xx-upgrade，[warm-flow_x.x.x.sql](https://gitee.com/dromara/warm-flow/tree/master/sql/mysql/v1-upgrade)，执行

## 官网流程定义xml案例

[官网九套流程定义xml](https://gitee.com/dromara/warm-flow-test/tree/master/warm-flow-core-test/src/main/resources)

## 测试代码

> 测试代码[warm-flow-test](https://gitee.com/dromara/warm-flow-test)项目中，warm-flow-xxx-test模块的测类


### 支持数据库类型

* [x] mysql
* [x] oracle
* [x] postgresql
* [ ] 达梦
* [ ] 人大金仓
* [ ] GaussDB
* [ ] oceanbase
* [ ] sqlserver
* [ ] ......


### orm扩展包

* [x] mybatis
* [x] mybatis-plus
* [x] jpa
* [x] mybatis-flex
* [x] easy-query
* [ ] ......




> **有想扩展其他orm框架和数据库的可加qq群联系群主**


## 你可以请作者喝杯咖啡表示鼓励

![输入图片说明](https://foruda.gitee.com/images/1697770422557390406/7efa04d6_2218307.png "屏幕截图")


### 特别感谢JetBrains对开源项目支持

<a href="https://jb.gg/OpenSourceSupport">
  <img src="https://user-images.githubusercontent.com/8643542/160519107-199319dc-e1cf-4079-94b7-01b6b8d23aa6.png" align="left" height="100" width="100"  alt="JetBrains">
</a>
