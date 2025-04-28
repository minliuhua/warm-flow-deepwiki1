<p align="center">
	<img alt="logo" src="https://foruda.gitee.com/images/1726820610127990120/c8c5f3a4_2218307.png" width="100">
</p>
<h1 align="center" style="margin: 30px 0 30px; font-weight: bold;">Dromara Warm-Flow工作流</h1>
<p align="center">
	<a href="https://gitee.com/dromara/warm-flow/stargazers"><img src="https://gitee.com/dromara/warm-flow/badge/star.svg?theme=dark"></a>
        <a href='https://gitee.com/dromara/warm-flow/members'><img src='https://gitee.com/dromara/warm-flow/badge/fork.svg?theme=dark' alt='fork'> 
        </img></a>
</p>

**项目代码、文档 均开源免费可商用 遵循开源协议即可**

**过去、现在和未来都不会有商业版！！！**

## 介绍

Dromara Warm-Flow，一款国产的工作流引擎，以其简洁轻量、五脏俱全、灵活扩展性强的特点，成为了众多开发者的首选。它不仅可以通过jar包快速集成设计器，还具备以下显著优势：

- **简洁易用**‌：仅包含7张表，代码量少，上手和集成速度快。
- **审批功能全面**‌：支持通过、退回、驳回到上一个任务、撤销、拿回、任意跳转、转办、终止、会签、票签、委派、加减签、互斥和并行网关等多种审批操作，以及条件表达式、办理人表达和监听器等高级功能。
- **流程设计器**‌：通过jar包形式快速集成到项目，支持节点属性扩展，适配SpringBoot和Solon，减少繁琐代码搬运和适配。
- **条件表达式**‌：内置常见的和spel条件表达式，支持自定义扩展。
- **办理人变量表达式**‌：内置${handler}和spel格式的表达式，满足不同场景需求，灵活可扩展。
- **监听器**‌：提供四种监听器，支持不同作用范围和spel表达式，参数传递灵活，支持动态权限。
- **流程变量**‌：在整个流程办理过程起到重要的角色，如办理人表达式中，传入变量进行动态指定办理人。
- **流程图**‌：自带流程图功能，数据入库，支持在流程图上追加文字，自定义流程图节点颜色等扩展。
- **ORM框架支持**‌：支持MyBatis、Mybatis-Plus、Mybatis-Flex、Jpa、Easy-Query和BeetlSql，后续将扩展支持其他框架。
- **数据库支持**‌：支持MySQL、Oracle、PostgreSQL和SQL Server，其他数据库只需要转换表结构即可支持。
- **多租户与软删除**‌：流程引擎自身维护多租户和软删除实现，也可使用对应ORM框架的实现方式。
- **兼容性**‌：同时支持Spring和Solon，兼容Java8、Java17、Java21。
- **实战项目**‌：官方提供基于Ruoyi-Vue封装的实战项目，极具参考价值。
>  **希望一键三连此项目和工作流项目，你的⭐️ Star ⭐️是我持续开发的动力，项目也活的更长**  
>
>  **工作流地址**： **[gitee地址](https://gitee.com/dromara/warm-flow.git  )** |**[github地址](https://github.com/dromara/warm-flow.git)**


## 部署流程
- 导入[warm-flow-all.sql](https://gitee.com/min290/hh-vue/blob/master/sql/warm/warm-flow-all.sql)
- 其他按照ruoyi-vue部署流程即可  
- 导入[官网流程定义案例xml](https://gitee.com/dromara/warm-flow-test/tree/master/warm-flow-core-test/src/main/resources)


## 工作流

### warm-flow

🎉国产自研工作流，其特点简单易用轻量，易阅读，五脏俱全，组件独立，可扩展，可满足中小项目的组件。

>   **联系方式：qq群：778470567， 微信：warm-houhou**
>   **工作流地址**：https://gitee.com/warm_4/warm-flow.git

### 演示地址

- admin/admin123

演示地址：http://www.hhzai.top

### 演示图

<table>
    <tr>
        <td><img src="https://foruda.gitee.com/images/1697704379975758657/558474f6_2218307.png"/></td>
        <td><img src="https://foruda.gitee.com/images/1703576997421577844/a1dc2737_2218307.png"/></td>
    </tr>
    <tr>
        <td><img src="https://foruda.gitee.com/images/1703577051212751284/203a05b0_2218307.png"/></td>
        <td><img src="https://foruda.gitee.com/images/1703577120823449150/ba952a84_2218307.png"/></td>
    </tr>
    <tr>
        <td><img src="https://foruda.gitee.com/images/1703577416508497463/863d8da1_2218307.png"/></td>
        <td><img src="https://foruda.gitee.com/images/1703641952765512992/dc187080_2218307.png"/></td>
    </tr>
    <tr>
        <td><img src="https://foruda.gitee.com/images/1703639870569018221/453a0e0e_2218307.png"/></td>
        <td><img src="https://foruda.gitee.com/images/1703639949778635820/34a6c14e_2218307.png"/></td>
    </tr>
    <tr>
        <td><img src="https://foruda.gitee.com/images/1703640045465410604/c14affda_2218307.png"/></td>
        <td><img src="https://foruda.gitee.com/images/1703641581976369452/e4629da5_2218307.png"/></td>
    </tr>
    <tr>
        <td><img src="https://foruda.gitee.com/images/1703640080823852176/bdf9a360_2218307.png"/></td>
        <td><img src="https://foruda.gitee.com/images/1703640099939146504/b19b2b85_2218307.png"/></td>
    </tr>
    <tr>
        <td><img src="https://foruda.gitee.com/images/1703641659022331552/cc4e0af2_2218307.png"/></td>
        <td><img src="https://foruda.gitee.com/images/1703641675840058630/3430da37_2218307.png"/></td>
    </tr>
    <tr>
        <td><img src="https://foruda.gitee.com/images/1703641687716655707/62a8b20c_2218307.png"/></td>
        <td><img src="https://foruda.gitee.com/images/1703641702939748288/6da6c4f6_2218307.png"/></td>
    </tr>
</table>
