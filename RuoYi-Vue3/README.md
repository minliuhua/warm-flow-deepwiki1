<p align="center">
	<img alt="logo" src="https://foruda.gitee.com/images/1726820610127990120/c8c5f3a4_2218307.png" width="100">
</p>
<h1 align="center" style="margin: 30px 0 30px; font-weight: bold;">Dromara Warm-Flow工作流</h1>
<p align="center">
	<a href="https://gitee.com/dromara/warm-flow/stargazers"><img src="https://gitee.com/dromara/warm-flow/badge/star.svg?theme=dark"></a>
        <a href='https://gitee.com/dromara/warm-flow/members'><img src='https://gitee.com/dromara/warm-flow/badge/fork.svg?theme=dark' alt='fork'> 
        </img></a>
</p>

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
- **ORM框架支持**‌：支持MyBatis、Mybatis-Plus、Mybatis-Flex、Jpa和Easy-Query，后续将扩展支持其他框架。
- **数据库支持**‌：支持MySQL、Oracle、PostgreSQL和SQL Server，其他数据库只需要转换表结构即可支持。
- **多租户与软删除**‌：流程引擎自身维护多租户和软删除实现，也可使用对应ORM框架的实现方式。
- **兼容性**‌：同时支持Spring和Solon，兼容Java8、Java17、Java21。
- **实战项目**‌：官方提供基于Ruoyi-Vue封装的实战项目，极具参考价值。

## 前端运行

```bash
# 克隆项目
git clone https://github.com/yangzongzhuan/RuoYi-Vue3.git

# 进入项目目录
cd RuoYi-Vue3

# 安装依赖
yarn --registry=https://registry.npmmirror.com

# 启动服务
yarn dev

# 构建测试环境 yarn build:stage
# 构建生产环境 yarn build:prod
# 前端访问地址 http://localhost:80
```

## 内置功能

1.  用户管理：用户是系统操作者，该功能主要完成系统用户配置。
2.  部门管理：配置系统组织机构（公司、部门、小组），树结构展现支持数据权限。
3.  岗位管理：配置系统用户所属担任职务。
4.  菜单管理：配置系统菜单，操作权限，按钮权限标识等。
5.  角色管理：角色菜单权限分配、设置角色按机构进行数据范围权限划分。
6.  字典管理：对系统中经常使用的一些较为固定的数据进行维护。
7.  参数管理：对系统动态配置常用参数。
8.  通知公告：系统通知公告信息发布维护。
9.  操作日志：系统正常操作日志记录和查询；系统异常信息日志记录和查询。
10. 登录日志：系统登录日志记录查询包含登录异常。
11. 在线用户：当前系统中活跃用户状态监控。
12. 定时任务：在线（添加、修改、删除)任务调度包含执行结果日志。
13. 代码生成：前后端代码的生成（java、html、xml、sql）支持CRUD下载 。
14. 系统接口：根据业务代码自动生成相关的api接口文档。
15. 服务监控：监视当前系统CPU、内存、磁盘、堆栈等相关信息。
16. 缓存监控：对系统的缓存信息查询，命令统计等。
17. 在线构建器：拖动表单元素生成相应的HTML代码。
18. 连接池监视：监视当前系统数据库连接池状态，可进行分析SQL找出系统性能瓶颈。

## 在线体验

- admin/admin123
- 陆陆续续收到一些打赏，为了更好的体验已用于演示服务器升级。谢谢各位小伙伴。

演示地址：http://vue.ruoyi.vip  
文档地址：http://doc.ruoyi.vip

## 演示图

<table>
    <tr>
        <td><img src="https://oscimg.oschina.net/oscnet/cd1f90be5f2684f4560c9519c0f2a232ee8.jpg"/></td>
        <td><img src="https://oscimg.oschina.net/oscnet/1cbcf0e6f257c7d3a063c0e3f2ff989e4b3.jpg"/></td>
    </tr>
    <tr>
        <td><img src="https://oscimg.oschina.net/oscnet/up-8074972883b5ba0622e13246738ebba237a.png"/></td>
        <td><img src="https://oscimg.oschina.net/oscnet/up-9f88719cdfca9af2e58b352a20e23d43b12.png"/></td>
    </tr>
    <tr>
        <td><img src="https://oscimg.oschina.net/oscnet/up-39bf2584ec3a529b0d5a3b70d15c9b37646.png"/></td>
        <td><img src="https://oscimg.oschina.net/oscnet/up-936ec82d1f4872e1bc980927654b6007307.png"/></td>
    </tr>
	<tr>
        <td><img src="https://oscimg.oschina.net/oscnet/up-b2d62ceb95d2dd9b3fbe157bb70d26001e9.png"/></td>
        <td><img src="https://oscimg.oschina.net/oscnet/up-d67451d308b7a79ad6819723396f7c3d77a.png"/></td>
    </tr>	 
    <tr>
        <td><img src="https://oscimg.oschina.net/oscnet/5e8c387724954459291aafd5eb52b456f53.jpg"/></td>
        <td><img src="https://oscimg.oschina.net/oscnet/644e78da53c2e92a95dfda4f76e6d117c4b.jpg"/></td>
    </tr>
	<tr>
        <td><img src="https://oscimg.oschina.net/oscnet/up-8370a0d02977eebf6dbf854c8450293c937.png"/></td>
        <td><img src="https://oscimg.oschina.net/oscnet/up-49003ed83f60f633e7153609a53a2b644f7.png"/></td>
    </tr>
	<tr>
        <td><img src="https://oscimg.oschina.net/oscnet/up-d4fe726319ece268d4746602c39cffc0621.png"/></td>
        <td><img src="https://oscimg.oschina.net/oscnet/up-c195234bbcd30be6927f037a6755e6ab69c.png"/></td>
    </tr>
    <tr>
        <td><img src="https://oscimg.oschina.net/oscnet/b6115bc8c31de52951982e509930b20684a.jpg"/></td>
        <td><img src="https://oscimg.oschina.net/oscnet/up-5e4daac0bb59612c5038448acbcef235e3a.png"/></td>
    </tr>
</table>


## 若依前后端分离交流群

QQ群： [![加入QQ群](https://img.shields.io/badge/已满-937441-blue.svg)](https://jq.qq.com/?_wv=1027&k=5bVB1og) [![加入QQ群](https://img.shields.io/badge/已满-887144332-blue.svg)](https://jq.qq.com/?_wv=1027&k=5eiA4DH) [![加入QQ群](https://img.shields.io/badge/已满-180251782-blue.svg)](https://jq.qq.com/?_wv=1027&k=5AxMKlC) [![加入QQ群](https://img.shields.io/badge/已满-104180207-blue.svg)](https://jq.qq.com/?_wv=1027&k=51G72yr) [![加入QQ群](https://img.shields.io/badge/已满-186866453-blue.svg)](https://jq.qq.com/?_wv=1027&k=VvjN2nvu) [![加入QQ群](https://img.shields.io/badge/已满-201396349-blue.svg)](https://jq.qq.com/?_wv=1027&k=5vYAqA05) [![加入QQ群](https://img.shields.io/badge/已满-101456076-blue.svg)](https://jq.qq.com/?_wv=1027&k=kOIINEb5) [![加入QQ群](https://img.shields.io/badge/已满-101539465-blue.svg)](https://jq.qq.com/?_wv=1027&k=UKtX5jhs) [![加入QQ群](https://img.shields.io/badge/已满-264312783-blue.svg)](https://jq.qq.com/?_wv=1027&k=EI9an8lJ) [![加入QQ群](https://img.shields.io/badge/已满-167385320-blue.svg)](https://jq.qq.com/?_wv=1027&k=SWCtLnMz) [![加入QQ群](https://img.shields.io/badge/已满-104748341-blue.svg)](https://jq.qq.com/?_wv=1027&k=96Dkdq0k) [![加入QQ群](https://img.shields.io/badge/已满-160110482-blue.svg)](https://jq.qq.com/?_wv=1027&k=0fsNiYZt) [![加入QQ群](https://img.shields.io/badge/已满-170801498-blue.svg)](https://jq.qq.com/?_wv=1027&k=7xw4xUG1) [![加入QQ群](https://img.shields.io/badge/已满-108482800-blue.svg)](https://jq.qq.com/?_wv=1027&k=eCx8eyoJ) [![加入QQ群](https://img.shields.io/badge/已满-101046199-blue.svg)](https://jq.qq.com/?_wv=1027&k=SpyH2875) [![加入QQ群](https://img.shields.io/badge/已满-136919097-blue.svg)](https://jq.qq.com/?_wv=1027&k=tKEt51dz) [![加入QQ群](https://img.shields.io/badge/已满-143961921-blue.svg)](http://qm.qq.com/cgi-bin/qm/qr?_wv=1027&k=0vBbSb0ztbBgVtn3kJS-Q4HUNYwip89G&authKey=8irq5PhutrZmWIvsUsklBxhj57l%2F1nOZqjzigkXZVoZE451GG4JHPOqW7AW6cf0T&noverify=0&group_code=143961921) [![加入QQ群](https://img.shields.io/badge/已满-174951577-blue.svg)](http://qm.qq.com/cgi-bin/qm/qr?_wv=1027&k=ZFAPAbp09S2ltvwrJzp7wGlbopsc0rwi&authKey=HB2cxpxP2yspk%2Bo3WKTBfktRCccVkU26cgi5B16u0KcAYrVu7sBaE7XSEqmMdFQp&noverify=0&group_code=174951577) [![加入QQ群](https://img.shields.io/badge/161281055-blue.svg)](http://qm.qq.com/cgi-bin/qm/qr?_wv=1027&k=Fn2aF5IHpwsy8j6VlalNJK6qbwFLFHat&authKey=uyIT%2B97x2AXj3odyXpsSpVaPMC%2Bidw0LxG5MAtEqlrcBcWJUA%2FeS43rsF1Tg7IRJ&noverify=0&group_code=161281055) 点击按钮入群。
QQ群： [![加入QQ群](https://img.shields.io/badge/已满-937441-blue.svg)](https://jq.qq.com/?_wv=1027&k=5bVB1og) [![加入QQ群](https://img.shields.io/badge/已满-887144332-blue.svg)](https://jq.qq.com/?_wv=1027&k=5eiA4DH) [![加入QQ群](https://img.shields.io/badge/已满-180251782-blue.svg)](https://jq.qq.com/?_wv=1027&k=5AxMKlC) [![加入QQ群](https://img.shields.io/badge/已满-104180207-blue.svg)](https://jq.qq.com/?_wv=1027&k=51G72yr) [![加入QQ群](https://img.shields.io/badge/已满-186866453-blue.svg)](https://jq.qq.com/?_wv=1027&k=VvjN2nvu) [![加入QQ群](https://img.shields.io/badge/已满-201396349-blue.svg)](https://jq.qq.com/?_wv=1027&k=5vYAqA05) [![加入QQ群](https://img.shields.io/badge/已满-101456076-blue.svg)](https://jq.qq.com/?_wv=1027&k=kOIINEb5) [![加入QQ群](https://img.shields.io/badge/已满-101539465-blue.svg)](https://jq.qq.com/?_wv=1027&k=UKtX5jhs) [![加入QQ群](https://img.shields.io/badge/已满-264312783-blue.svg)](https://jq.qq.com/?_wv=1027&k=EI9an8lJ) [![加入QQ群](https://img.shields.io/badge/已满-167385320-blue.svg)](https://jq.qq.com/?_wv=1027&k=SWCtLnMz) [![加入QQ群](https://img.shields.io/badge/已满-104748341-blue.svg)](https://jq.qq.com/?_wv=1027&k=96Dkdq0k) [![加入QQ群](https://img.shields.io/badge/已满-160110482-blue.svg)](https://jq.qq.com/?_wv=1027&k=0fsNiYZt) [![加入QQ群](https://img.shields.io/badge/已满-170801498-blue.svg)](https://jq.qq.com/?_wv=1027&k=7xw4xUG1) [![加入QQ群](https://img.shields.io/badge/已满-108482800-blue.svg)](https://jq.qq.com/?_wv=1027&k=eCx8eyoJ) [![加入QQ群](https://img.shields.io/badge/已满-101046199-blue.svg)](https://jq.qq.com/?_wv=1027&k=SpyH2875) [![加入QQ群](https://img.shields.io/badge/已满-136919097-blue.svg)](https://jq.qq.com/?_wv=1027&k=tKEt51dz) [![加入QQ群](https://img.shields.io/badge/已满-143961921-blue.svg)](http://qm.qq.com/cgi-bin/qm/qr?_wv=1027&k=0vBbSb0ztbBgVtn3kJS-Q4HUNYwip89G&authKey=8irq5PhutrZmWIvsUsklBxhj57l%2F1nOZqjzigkXZVoZE451GG4JHPOqW7AW6cf0T&noverify=0&group_code=143961921) [![加入QQ群](https://img.shields.io/badge/已满-174951577-blue.svg)](http://qm.qq.com/cgi-bin/qm/qr?_wv=1027&k=ZFAPAbp09S2ltvwrJzp7wGlbopsc0rwi&authKey=HB2cxpxP2yspk%2Bo3WKTBfktRCccVkU26cgi5B16u0KcAYrVu7sBaE7XSEqmMdFQp&noverify=0&group_code=174951577) [![加入QQ群](https://img.shields.io/badge/已满-161281055-blue.svg)](http://qm.qq.com/cgi-bin/qm/qr?_wv=1027&k=Fn2aF5IHpwsy8j6VlalNJK6qbwFLFHat&authKey=uyIT%2B97x2AXj3odyXpsSpVaPMC%2Bidw0LxG5MAtEqlrcBcWJUA%2FeS43rsF1Tg7IRJ&noverify=0&group_code=161281055) [![加入QQ群](https://img.shields.io/badge/138988063-blue.svg)](http://qm.qq.com/cgi-bin/qm/qr?_wv=1027&k=XIzkm_mV2xTsUtFxo63bmicYoDBA6Ifm&authKey=dDW%2F4qsmw3x9govoZY9w%2FoWAoC4wbHqGal%2BbqLzoS6VBarU8EBptIgPKN%2FviyC8j&noverify=0&group_code=138988063) 点击按钮入群。
