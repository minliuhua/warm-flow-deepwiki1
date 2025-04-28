---
home: true
icon: home
title: 首页
bgImage: /bg.jpg
heroFullScreen: true
breadcrumbExclude: true
heroText: Warm-Flow工作流 
tagline:
  Dromara Warm-Flow国产工作流引擎，简洁轻量，五脏俱全，灵活扩展，可快速集成设计器!  
actions:

highlights:
  - header: Warm-Flow 特性
    features:
      - title: 🔅 简洁易用
        link: /master/primary/table.md
        details: 只有7张表，代码量少，可快速上手和集成

      - title: 🤏 审批功能
        link: /master/primary/started.html#_4%E3%80%81%E4%BB%A3%E7%A0%81%E7%A4%BA%E4%BE%8B
        details: 支持通过、退回、驳回到上一个任务、撤销、拿回、任意跳转、转办、终止、会签、票签、委派和加减签、互斥和并行网关

      - title: 🎐 流程变量
        link: /master/primary/variable.md
        details: 流程变量，map类型，用于流程执行中的数据转递
        
      - title: 🦻 监听器
        link: /master/advanced/listener.md
        details: 支持四种监听器和不同颗粒的的作用范围，支持spel表达式，灵活可扩展，参数传递，动态权限

      - title: 💯 流程设计器
        link: /master/primary/designerIntroduced.md
        details: 可通过jar包形式快速集成到项目，支持节点属性扩展，减少繁琐代码搬运和适配，支持solon和springboot

      - title: 👍 流程图
        link: /master/advanced/chart_manage.md
        details: 流程引擎自带流程图，数据入库，并支持追加文字和自定义流程图节点颜色等扩展

      - title: 🔦 条件表达式
        link: /master/primary/condition.md
        details: 内置常见的和spel条件表达式，并且支持自定义扩展

      - title: ↔️ 办理人表达式
        link: /master/advanced/variableStategy.md
        details: 内置${handler}和spel格式的表达式，可满足不同场景，灵活可扩展

      - title: 🌎 orm框架支持
        link: /master/introduction/introduction.html#_6%E3%80%81%E6%94%AF%E6%8C%81orm%E6%A1%86%E6%9E%B6%E7%B1%BB%E5%9E%8B
        details: 目前支持MyBatis、Mybatis-Plus、Mybatis-Flex、Jpa、Easy-Query和BeetlSql，后续会由社区提供其他支持，扩展方便

      - title: 🎬 数据库支持
        link: /master/introduction/introduction.html#_5%E3%80%81%E6%94%AF%E6%8C%81%E6%95%B0%E6%8D%AE%E5%BA%93%E7%B1%BB%E5%9E%8B
        details: 目前支持MySQL 、Oracle、PostgreSQL和SQL Server，其他数据库只需要转换表结构，使用兼容性强的orm框架即可

      - title: 🏋 多租户
        link: /master/advanced/tenant.md
        details: 流程引擎自身维护多租户实现，也可使用对应orm框架的实现方式
        
      - title: ✖️ 软删除
        link: /master/advanced/logicdelete.md
        details: 流程引擎自身维护软删除实现，也可使用对应orm框架的实现方式


copyright: false
footer: © 2024 Warm-Flow Project. All Rights Reserved Designed by <a href="https://gitee.com/min290">xiaohua</a> Member of <a href="https://dromara.org.cn/">Dromara</a> <br><a href="https://beian.miit.gov.cn/">赣ICP备2021008655号-3</a>
---

  <div class="sponsorList">
    <strong style="font-size: 30px;">❤️特别赞助</strong><br><br><br>
    <div class="cardList">
      <div 
        class="cardItem"
        :style="`background-color: ${item.bgColor}`"
        v-for="item in sponsorshis" 
        :key="item.href"
      >
	    <a :href="item.href" target="_blank" style="text-decoration: none; color: white; " >
          <div class="flex-hv">
            <div class="cardImg" :style="`background-image: url(${item.src});`" alt="项目示例图片"></div>
            <div style="text-align: center">
              <span  style="font-size: 18px;">{{ item.title }}</span>
              <p style="font-size: 15px; color: white; text-align: center;">{{ item.description }}</p>
            </div>
          </div>
        </a> 
      </div>
    </div>
    <div style="height: 10px; clear: both;"></div>
    <p style="text-align: center;">
    	<el-button type="danger" plain round style="width: 200px; height: 50px; font-size: 20px">️成为赞助商</el-button>
    </p>
  </div>

---

<div class="com-box-f">
    <br><strong style="font-size: 30px;">优秀开源集成案例</strong><br><br><br>
    <div style="display: flex; gap: 20px; flex-wrap: wrap;">
        <el-card style="max-width: 33%; flex: 1 1 calc(33% - 20px); padding: 0px;" shadow="hover"
          v-for="item in kyProjectList" :key="item.href">
          <el-image style="width: 100%;height: 190px;cursor: pointer" :src="item.src"></el-image>
          <div class="s-case-h3">
            <a :href="item.href" target="_blank"><span class="s-case-title" style="font-size: 23px; font-weight: 500;">{{ item.title }}</span></a>
            <span class="s-author"> {{ item.author }} </span>
          </div>
          <div>
			<p class="s-case-intro">{{ item.intro }}</p>
		  </div>
        </el-card>
    </div>
    <div style="height: 10px; clear: both;"></div>
    <p>
    	（如果您的开源项目也使用了 Warm-Flow，您可以 <a href="https://gitee.com/dromara/warm-flow/issues/IBB37F" target="_blank">在此</a> 提交）
    </p>
    <p>
    	（更多开源项目详情，您可以 <a href="/master/introduction/projectexample.html">在此</a> 查看）
    </p>
</div>

---
<div class="com-box-img flex1">
    <br><strong style="font-size: 30px;">正在使用 Warm-Flow 的企业 / 个人（34家）</strong><br><br><br>
    <div style="display: flex; flex-wrap: wrap;">
      <el-card shadow="hover" v-for="item in qyProjectList" :key="item.href">
        <a :href="item.href" target="_blank">
          <el-tooltip :content="item.title" placement="top" popper-class="imgTip">
            <img style="width: 100%" :title="item.title" :src="item.src">
          </el-tooltip>
        </a>
      </el-card>
    </div>
    <div style="height: 10px; clear: both;"></div>
    <p>
    	（如果您的企业也使用了 Warm-Flow，您可以 <a href="https://gitee.com/dromara/warm-flow/issues/I7Y57D" target="_blank">在此</a> 提交）
    </p>
    <p>
    	（更多使用企业/个人详情，您可以 <a href="/master/introduction/companyintegration.html">在此</a> 查看）
    </p>
</div>

---
<div class="com-box-img">
    <br><strong style="font-size: 30px;">Dromara 成员项目</strong><br><br><br>
    <div style="display: flex; flex-wrap: wrap;">
      <el-card shadow="hover" v-for="item in dromaraList" :key="item.href">
        <a :href="item.href" target="_blank">
          <el-tooltip :content="item.title" placement="top" popper-class="imgTip">
            <img style="width: 100%" :title="item.title" :src="item.src">
          </el-tooltip>
        </a>
      </el-card>
    </div>
    <div style="height: 10px; clear: both;"></div>
    <p>
    	为往圣继绝学，一个人或许能走的更快，但一群人会走的更远。
    </p>
</div>


---
<div style="padding: 1em 1em; padding-bottom: 30px; text-align: center;max-width: var(--content-width, 740px);margin: 0 auto;">
	<br><strong style="font-size: 30px;">👍友情链接</strong><br><br><br>
    <div class="links ">
            <a :href="item.href" target="_blank" v-for="item in projectList" :key="item.href">
              <img :src="item.src" :alt="item.alt" :title="item.title">
            </a>
    </div>
</div>

<script>

import { ref, onMounted } from 'vue'; 

export default {
  setup() {
    const projectList = ref([]);
    const qyProjectList = ref([]);
    const kyProjectList = ref([]);
    const dromaraList = ref([]);
    const sponsorshis = ref([]);

    const fetchData = async () => {

        qyProjectList.value = [
            { href: "https://www.bankoffs.com.cn/", title: "抚顺银行", src: "https://foruda.gitee.com/images/1724129406609614381/b3e2d2aa_2218307.png" },
            { href: "https://www.damanjinfu.com/", title: "湖南达漫电子商务有限公司", src: "https://cdn-oss.damanjinfu.com/2025-04/25a5649871724e339cfdc5efe7696e59.png" },
            { href: "https://www.kmbit.cn", title: "昆明倍特技术服务", src: "https://foruda.gitee.com/images/1741657442203556781/90c14a90_2218307.png" },
            { href: "www.hxhorae.com.cn", title: "华夏好瑞（天津）科技有限公司", src: "https://zhengxin-pub.cdn.bcebos.com/mark/54cdfdc0fcd3afd8f31c457803bcaa36.jpg?x-bce-process=image/resize,m_lfit,w_112" },
            { href: "http://www.bjruike.com/", title: "北京瑞科科技", src: "https://foruda.gitee.com/images/1740658272131167588/49903c01_2218307.png" },
            { href: "https://www.jrkjsoft.com/", title: "哈尔滨金睿科技有限公司", src: "https://foruda.gitee.com/images/1740295290063383498/42b11c67_2218307.png" },
            { href: "https://www.sneb.com.cn/zhgj/index_2578.html", title: "中交武汉智行国际", src: "https://foruda.gitee.com/images/1732083419998818655/3e444f08_2218307.png" },
            { href: "https://www.xly-net.com/login", title: "新理益智慧网络科技（重庆）有限公司", src: "https://foruda.gitee.com/images/1732083517105041838/b685e15c_2218307.png" },
            { href: "https://www.ctcemti.com", title: "安徽数智建造研究院有限公司", src: "https://foruda.gitee.com/images/1724128444763892376/f5925815_2218307.png" },
            { href: "http://www.3into1.cn", title: "杭州三之一智联科技有限公司", src: "https://foruda.gitee.com/images/1724128656910849672/05712913_2218307.png" },
            { href: "https://ruyangkeji.com/", title: "郑州如阳科技有限公司", src: "https://foruda.gitee.com/images/1724128729136918262/f79703a0_2218307.png" },
            { href: "https://www.runyoucloud.com", title: "山东融佑信息科技有限公司", src: "https://foruda.gitee.com/images/1724129195385753446/c9b9b908_2218307.png" },
            { href: "http://www.aiwld.com.cn", title: "陕西物联达智能科技有限公司", src: "https://foruda.gitee.com/images/1724129259885472852/d538bd26_2218307.png" },
            { href: "http://www.h5ve.com", title: "H5VE团队", src: "https://foruda.gitee.com/images/1724129316656246511/9f588786_2218307.png" },
            { href: "https://www.bjhccx.net/", title: "北京海诚创想信息技术有限公司", src: "https://foruda.gitee.com/images/1745745608943588585/b939d228_2218307.png" },
            { href: "", title: "昆明世科计算机网络有限公司", src: "https://foruda.gitee.com/images/1745746151355846840/0207b2aa_2218307.png" },
            { href: "", title: "湖北公众信息产业有限责任公司", src: "https://foruda.gitee.com/images/1745745804459175481/fdf305da_2218307.png" },
            { href: "", title: "Warm-Flow", src: "logo.png" },
        ];

        kyProjectList.value = [
            { href: "https://gitee.com/min290/hh-vue", title: "hh-vue", src: "/warm-flow.png", author: "晓华/Zhen", intro: "官方集成案例：springboot2+vue2" },
            { href: "https://gitee.com/dromara/RuoYi-Vue-Plus", title: "RuoYi-Vue-Plus", src: "https://foruda.gitee.com/images/1737523204017735425/51f23421_2218307.png", author: "疯狂的狮子Li", intro: "多租户后台管理系统 重写RuoYi-Vue所有功能 集成 Sa-Token、Mybatis-Plus、WarmFlow、SpringDoc、Hutool、OSS 定期同步" },
            { href: "https://gitee.com/dapppp/ruoyi-plus-vben5", title: "ruoyi-plus-vben5", src: "https://foruda.gitee.com/images/1737523316123951650/1dec0eeb_2218307.png", author: "玲娜贝er", intro: "基于vben最新版本v5 & ant-design-vue 的 RuoYi-Vue-Plus 前端项目" },
            { href: "https://gitee.com/qq75547276/seaflow", title: "seaflow", src: "https://foruda.gitee.com/images/1734131229064035715/ef07a979_2218307.png", author: "seven", intro: "seaflow仿钉钉工作流平台，vue3、elementPlus，实现流程设计和审批功能" },
            { href: "https://gitee.com/iyhk_0/smart-flow", title: "SmartFlow", src: "https://foruda.gitee.com/images/1744616063484299166/1c9ce726_2218307.png", author: "lovefawn", intro: "SmartFlow 基于SmartAdmin、Warm-Flow和Ruoyi-Vue-Plus开源项目，实现了流程设计、流程实例、流程审批、流程监控等功能。" }, 
            { href: "https://gitee.com/liangliyun/RuoYi-Cloud", title: "Ruoyi-Cloud", src: "/warm-flow.png", author: "梁小梁/Zhen", intro: "基于Ruoyi-Cloud集成的跑批系统：spring-cloud(nacos)+vue3" },
        ];
    
        projectList.value = [
            { href: "https://item.jd.com/13928958.html", src: "/yqlj/flowableHb.jpg", alt: "open-capacity-platform", title: "对flowable有兴趣的朋友可以购买贺波老师的书《深入flowable流程引擎》" },
            { href: "http://www.easy-query.com/easy-query-doc/", src: "/yqlj/easy-query.png", alt: "open-capacity-platform", title: "java下唯一一款同时支持强类型对象关系查询和强类型SQL语法查询的ORM,拥有对象模型筛选、隐式子查询、隐式join、显式子查询、显式join,支持Java/Kotlin" },
        ];
    
        dromaraList.value = [
            {title: "一个轻量级的分布式日志标记追踪神器，10分钟即可接入，自动对日志打标签完成微服务的链路追踪", href: "https://gitee.com/dromara/TLog", src: "https://oss.dev33.cn/sa-token/link/tlog.png"},
            {title: "轻量，快速，稳定，可编排的组件式流程引擎", href: "https://gitee.com/dromara/liteFlow", src: "https://oss.dev33.cn/sa-token/link/liteflow.png"},
            {title: "小而全的Java工具类库，使Java拥有函数式语言般的优雅，让Java语言也可以“甜甜的”。", href: "https://hutool.cn/", src: "https://oss.dev33.cn/sa-token/link/hutool.jpg"},
            {title: "一个轻量级 java 权限认证框架，让鉴权变得简单、优雅！", href: "https://sa-token.cc/", src: "https://oss.dev33.cn/sa-token/link/sa-token.png"},
            {title: "高性能一站式分布式事务解决方案。", href: "https://gitee.com/dromara/hmily", src: "https://oss.dev33.cn/sa-token/link/hmily.png"},
            {title: "强一致性分布式事务解决方案。", href: "https://gitee.com/dromara/Raincat", src: "https://oss.dev33.cn/sa-token/link/raincat.png"},
            {title: "可靠消息分布式事务解决方案。", href: "https://gitee.com/dromara/myth", src: "https://oss.dev33.cn/sa-token/link/myth.png"},
            {title: "一站式问题定位平台，以agent的方式无侵入接入应用，完整集成arthas功能模块，致力于应用级监控，帮助开发人员快速定位问题", href: "https://cubic.jiagoujishu.com/", src: "https://oss.dev33.cn/sa-token/link/cubic.png"},
            {title: "业界领先的身份管理和认证产品", href: "https://maxkey.top/", src: "https://oss.dev33.cn/sa-token/link/maxkey.png"},
            {title: "Forest能够帮助您使用更简单的方式编写Java的HTTP客户端", href: "http://forest.dtflyx.com/", src: "https://oss.dev33.cn/sa-token/link/forest-logo.png"},
            {title: "一款简而轻的低侵入式在线构建、自动部署、日常运维、项目监控软件", href: "https://jpom.top/", src: "https://oss.dev33.cn/sa-token/link/jpom.png"},
            {title: "面向 REST API 的高性能认证鉴权框架", href: "https://su.usthe.com/", src: "https://oss.dev33.cn/sa-token/link/sureness.png"},
            {title: "傻瓜级ElasticSearch搜索引擎ORM框架", href: "https://easy-es.cn/", src: "https://oss.dev33.cn/sa-token/link/easy-es2.png"},
            {title: "Northstar盈富量化交易平台", href: "https://gitee.com/dromara/northstar", src: "https://oss.dev33.cn/sa-token/link/northstar_logo.png"},
            {title: "Idea 版 Postman，为简化调试API而生", href: "https://dromara.gitee.io/fast-request/", src: "https://oss.dev33.cn/sa-token/link/fast-request.gif"},
            {title: "开源分布式云原生架构一站式解决方案", href: "https://www.jeesuite.com/", src: "https://oss.dev33.cn/sa-token/link/mendmix.png"},
            {title: "企业生产级百亿日PV高可用可拓展的RPC框架。", href: "https://gitee.com/dromara/koalas-rpc", src: "https://oss.dev33.cn/sa-token/link/koalas-rpc2.png"},
            {title: "配置极简功能强大的异步任务动态编排框架", href: "https://async.sizegang.cn/", src: "https://oss.dev33.cn/sa-token/link/gobrs-async.png"},
            {title: "基于配置中心的轻量级动态可监控线程池", href: "https://dynamictp.cn/", src: "https://oss.dev33.cn/sa-token/link/dynamic-tp.png"},
            {title: "一个用搭积木的方式构建pdf的框架（基于pdfbox）", href: "https://www.x-easypdf.cn", src: "https://oss.dev33.cn/sa-token/link/x-easypdf.png"},
            {title: "一个专门用于图片合成的工具，没有很复杂的功能，简单实用，却不失强大", href: "http://dromara.gitee.io/image-combiner", src: "https://oss.dev33.cn/sa-token/link/image-combiner.png"},
            {title: "Dante-Cloud 是一款企业级微服务架构和服务能力开发平台。", href: "https://www.herodotus.cn/", src: "https://oss.dev33.cn/sa-token/link/dante-cloud2.png"},
            {title: "低代码数据可视化开发平台", href: "http://www.mtruning.club", src: "https://oss.dev33.cn/sa-token/link/go-view.png"},
            {title: "微服务中后台快速开发平台，支持租户(SaaS)模式、非租户模式", href: "https://tangyh.top/", src: "https://oss.dev33.cn/sa-token/link/lamp-cloud.png"},
            {title: "RedisFront 是一款开源免费的跨平台 Redis 桌面客户端工具, 支持单机模式, 集群模式, 哨兵模式以及 SSH 隧道连接, 可轻松管理Redis缓存数据.", href: "https://www.redisfront.com/", src: "https://oss.dev33.cn/sa-token/link/redis-front.png"},
            {title: "一个入门简单、跨平台、企业级桌面软件开发框架", href: "https://www.yuque.com/u34495/mivcfg", src: "https://oss.dev33.cn/sa-token/link/electron-egg.png"},
            {title: "简称ocp是基于Spring Cloud的企业级微服务框架(用户权限管理，配置中心管理，应用管理，....)", href: "https://gitee.com/dromara/open-capacity-platform", src: "https://oss.dev33.cn/sa-token/link/open-capacity-platform.jpg"},
            {title: "Easy-Trans 一个注解搞定数据翻译,减少30%SQL代码量", href: "http://easy-trans.fhs-opensource.top/", src: "https://oss.dev33.cn/sa-token/link/easy_trans.png"},
            {title: "一款基于 Netty 的、开源的内网穿透神器。", href: "https://gitee.com/dromara/neutrino-proxy", src: "https://oss.dev33.cn/sa-token/link/neutrino-proxy.svg"},
            {title: "zyplayer-doc是一款适合团队和个人使用的WIKI文档管理工具，同时还包含数据库文档、Api接口文档。", href: "https://gitee.com/dromara/zyplayer-doc", src: "https://oss.dev33.cn/sa-token/link/zyplayer-doc.png"},
            {title: "最全最好用的微信支付V3 Spring Boot 组件。", href: "https://gitee.com/dromara/payment-spring-boot", src: "https://oss.dev33.cn/sa-token/link/payment-spring-boot.png"},
            {title: "J2eeFAST 是一个致力于中小企业 Java EE 企业级快速开发平台,我们永久开源!", href: "https://www.j2eefast.com/", src: "https://oss.dev33.cn/sa-token/link/j2eefast.png"},
            {title: "数据库比对工具：hive 表数据比对，mysql、Doris 数据比对，实现自动化配置进行数据比对，避免频繁写sql 进行处理，低代码(Low-Code) 平台", href: "https://gitee.com/dromara/data-compare", src: "https://oss.dev33.cn/sa-token/link/dataCompare.png"},
            {title: "giteye.net 是专为开源作者设计的数据图表服务工具类站点，提供了包括 Star 趋势图、贡献者列表、Gitee指数等数据图表服务。", href: "https://gitee.com/dromara/open-giteye-api", src: "https://oss.dev33.cn/sa-token/link/open-giteye-api.svg"},
            {title: "后台管理系统 重写 RuoYi-Vue 所有功能 集成 Sa-Token + Mybatis-Plus + Jackson + Xxl-Job + SpringDoc + Hutool + OSS 定期同步", href: "https://gitee.com/dromara/RuoYi-Vue-Plus", src: "https://oss.dev33.cn/sa-token/link/RuoYi-Vue-Plus.png"},
            {title: "微服务管理系统 重写RuoYi-Cloud所有功能 整合 SpringCloudAlibaba Dubbo3.0 Sa-Token Mybatis-Plus MQ OSS ES Xxl-Job Docker 全方位升级 定期同步", href: "https://gitee.com/dromara/RuoYi-Cloud-Plus", src: "https://oss.dev33.cn/sa-token/link/RuoYi-Cloud-Plus.png"},
            {title: "允许完全摆脱 Mapper 的 mybatis-plus 体验！封装 stream 和 lambda 操作进行数据返回处理。", href: "https://gitee.com/dromara/stream-query", src: "https://oss.dev33.cn/sa-token/link/stream-query.png"},
            {title: "短信聚合工具，让发送短信变的更简单。", href: "https://wind.kim/", src: "https://oss.dev33.cn/sa-token/link/sms4j.png"},
            {title: "简化kubernetes上大数据集群的运维管理", href: "https://cloudeon.top/", src: "https://oss.dev33.cn/sa-token/link/cloudeon.png"},
            {title: "Hodor是一个专注于任务编排和高可用性的分布式任务调度系统。", href: "https://github.com/dromara/hodor", src: "https://oss.dev33.cn/sa-token/link/hodor.png"},
            {title: "流程编排，插件驱动，测试无限可能", href: "http://nsrule.com/", src: "https://oss.dev33.cn/sa-token/link/test-hub.png"},
            {title: "Disjob是一个分布式的任务调度框架", href: "https://gitee.com/dromara/disjob", src: "https://oss.dev33.cn/sa-token/link/disjob-2.png"},
            {title: "轻量级 Mysql Binlog 客户端, 提供宕机续读, 高可用集群等特性", href: "https://gitee.com/dromara/binlog4j", src: "https://oss.dev33.cn/sa-token/link/Binlog4j.png"},
            {title: "基于 Canvas 的开源版 创客贴 支持导出json，svg, image文件。", href: "https://gitee.com/dromara/yft-design", src: "https://oss.dev33.cn/sa-token/link/yft-design.png"},
            {title: "在 SpringBoot 中通过简单的方式将文件存储到 本地、阿里云 OSS、腾讯云 COS、七牛云 Kodo等", href: "https://gitee.com/dromara/x-file-storage", src: "https://oss.dev33.cn/sa-token/link/x-file-storage.svg"},
            {title: "开源、高性能、安全、功能强大的物联网调试和管理解决方案。", href: "https://wemq.nicholasld.cn/", src: "https://oss.dev33.cn/sa-token/link/wemq.png"},
            {title: "web 版 linux(终端[终端回放] 文件 脚本 进程 计划任务)、数据库（mysql postgres）、redis(单机 哨兵 集群)、mongo 统一管理操作平台", href: "https://gitee.com/dromara/mayfly-go", src: "https://oss.dev33.cn/sa-token/link/mayfly-go.png"},
            {title: "Akali(阿卡丽)，轻量级本地化热点检测/降级框架，10秒钟即可接入使用！大流量下的神器", href: "https://akali.yomahub.com/", src: "https://oss.dev33.cn/sa-token/link/akali.png"},
            {title: "异构数据库迁移同步(搬家)工具。", href: "https://gitee.com/dromara/dbswitch", src: "https://oss.dev33.cn/sa-token/link/dbswitch.png"},
            {title: "Java 傻瓜式 AI 框架。", href: "https://gitee.com/dromara/easyAi", src: "https://oss.dev33.cn/sa-token/link/easyAI.png"},
            {title: "可能是java界最好的开源行为验证码 captcha、captcha、captcha、captcha、tianai-captcha [滑块验证码、点选验证码、行为验证码、旋转验证码， 滑动验证码]。", href: "https://gitee.com/dromara/tianai-captcha", src: "https://oss.dev33.cn/sa-token/link/tianai-captcha.png"},
            {title: "mybatis-plus 框架的增强拓展包。", href: "https://gitee.com/dromara/mybatis-plus-ext", src: "https://oss.dev33.cn/sa-token/link/mybatis-plus-ext.png"},
            {title: "免费开源的支付网关。", href: "https://gitee.com/dromara/dax-pay", src: "https://oss.dev33.cn/sa-token/link/dax-pay.png"},
            {title: "基于easyAi引擎的JAVA高性能，低成本，轻量级智能客服。", href: "https://gitee.com/dromara/sayOrder", src: "https://oss.dev33.cn/sa-token/link/sayorder.png"},
            {title: "扩展MyBatis JPA支持，简化CUID操作，增强SELECT分页查询", href: "https://gitee.com/dromara/mybatis-jpa-extra", src: "https://oss.dev33.cn/sa-token/link/mybatis-jpa-extra.png"},
            {title: "现代化的动画引擎", href: "https://newcar.js.org/zh/", src: "https://oss.dev33.cn/sa-token/link/newcar.png"},
            {title: "Dromara Warm-Flow国产工作流引擎🎉，简洁轻量，五脏俱全，灵活扩展性强，可通过jar引入设计器。解决flowable和activities复杂、学习成本高和集成难等痛点。", href: "https://warm-flow.dromara.org/", src: "https://oss.dev33.cn/sa-token/link/warm-flow.png", style: "max-width: 110%"},
            {title: "DyJava是一款功能强大的抖音Java开发工具包", href: "https://gitee.com/dromara/dy-java", src: "https://oss.dev33.cn/sa-token/link/dy-java.png"},
            {title: "MilvusPlus（简称 MP）是一个 Milvus 的操作工具，旨在简化与 Milvus 向量数据库的交互，为开发者提供类似 MyBatis-Plus 注解和方法调用风格的直观 API,提高效率而生。", href: "https://gitee.com/dromara/MilvusPlus", src: "https://oss.dev33.cn/sa-token/link/MilvusPlus-logo.png"},
            {title: "java下唯一一款同时支持强类型对象关系查询和强类型SQL语法查询的ORM,拥有对象模型筛选、隐式子查询、隐式join、显式子查询、显式join,支持Java/Kotlin", href: "http://www.easy-query.com/easy-query-doc/", src: "https://oss.dev33.cn/sa-token/link/easy-query.png"},
            {title: "一款高颜值、现代化的智能运维&轻量堡垒机平台。", href: "https://gitee.com/dromara/orion-visor", src: "https://oss.dev33.cn/sa-token/link/horizontal.png"},
            {title: "Java开源网站内容管理系统(java cms)。使用SpringBoot、MyBatis、Vue3、ElementPlus、Vite、TypeScript等技术开发。", href: "https://www.ujcms.com/", src: "https://oss.dev33.cn/sa-token/link/ujcms.png"},
            {title: "智能制造一体化，采用Springboot + winUI的低代码平台开发模式。包含30多个应用模块、50多种电子流程", href: "https://gitee.com/dromara/skyeye", src: "https://oss.dev33.cn/sa-token/link/skyeye-logo.png"},
            {title: "SSL证书监测平台，申请证书，自动续签，到期提醒。", href: "https://domain-admin.cn/", src: "https://oss.dev33.cn/sa-token/link/domain-admin.png"},
            {title: "轻量级、语义化、对开发者友好的 golang 时间处理库", href: "https://gitee.com/dromara/carbon", src: "https://oss.dev33.cn/sa-token/link/carbon.png"},
            {title: "java mqtt 基于 java aio 实现，开源、简单、易用、低延迟、高性能百万级 java mqtt client 组件和 java mqtt broker 服务。", href: "https://gitee.com/dromara/mica-mqtt", src: "https://oss.dev33.cn/sa-token/link/mica-mqtt.png"},
          ];
        sponsorshis.value = [
          {
            title: '驰骋工作流引擎',
            description: '驰骋BPM低代码，工作流、表单引擎!',
            href: 'https://ccflow.org/index.html?frm=warmflow',
            src: '/ggw/logo/ccflow.png',
            bgColor: "#ba4552"
          },
          {
            title: '全栈设计UI设计',
            description: '大屏数据|小程序生态|后台系统',
            href: '/master/other/paidservice.html#私人服务',
            src: '/ggw/logo/yuui.png',
            bgColor: "#A6A1F3"
          },
          {
            title: 'MaxKey单点登录认证系统',
            description: 'MaxKey单点登录认证系统是业界领先的IAM-IDaas身份管理和认证产',
            href: 'https://www.maxkey.top',
            src: '/ggw/logo/MaxKey.png',
            bgColor: "#302294"
          },
        ]
    };

    const navigateTo = () => {
      const pElement = document.querySelector('#main-description');

      var contentToAppend = `<p><a href="https://gitee.com/dromara/warm-flow.git" style="margin-left: 12px;"><img src="https://gitee.com/dromara/warm-flow/badge/star.svg?theme=dark"></a>
          <a href='https://gitee.com/dromara/warm-flow/members'><img src='https://gitee.com/dromara/warm-flow/badge/fork.svg?theme=dark' alt='fork'></a>
          <a href='https://github.com/dromara/warm-flow.git'><img src='https://img.shields.io/github/stars/dromara/warm-flow.svg' alt='fork'></a>
          <a href='https://github.com/dromara/warm-flow.git'><img src='https://img.shields.io/github/forks/dromara/warm-flow.svg' alt='fork'></a>
          <a href='https://gitcode.com/dromara/warm-flow'><img src='https://gitcode.com/dromara/warm-flow/star/badge.svg' alt='fork'></a>
          <a href='https://gitee.com/dromara/warm-flow/blob/master/LICENSE'><img src='https://img.shields.io/github/license/dromara/warm-flow' alt='fork'></a>
        </p>
      `;

      if (pElement) {
        pElement.insertAdjacentHTML('afterend', contentToAppend);
      } else {
        console.error('.vp-hero-actions 元素未找到');
      }

      const element = document.getElementById('main-description');
      const text = element.textContent;
      let index = 0;
    
      element.textContent = '';
    
      function typeWriter() {
        if (index < text.length) {
            element.innerHTML += `<span style=" color: #333 !important;">${text.charAt(index)}</span>`;
            index++;
            setTimeout(typeWriter, 60);
        } else {
            setTimeout(() => {
                index = 0;
                element.innerHTML = '';
                setTimeout(typeWriter, 60);
            }, 3000);
            
        }
     }
    
      typeWriter();
    };

    onMounted(() => {
      fetchData();
      navigateTo();
    });

    return {
      projectList,
      qyProjectList,
      kyProjectList,
      dromaraList,
      sponsorshis,
    };
  },
};
</script>

<style lang="scss">
.com-box-img,
.com-box-f {
  padding: 1em 1em;
  padding-bottom: 30px;
  text-align: center;
  max-width: var(--content-width, 740px);
  margin: 0 auto;
}
.com-box-f .el-card__body {
  padding: 0px;
}
.com-box-img .el-card {
  display: flex;
  align-items: center;
  justify-content: center;
  max-height: 47px;
  flex: 1 1 calc(9% - 12px);
  flex-grow: 0;
  padding: 10px;
}
.flex1 .el-card {
  flex: 1 1 calc(16% - 20px);
  width: auto;
  max-height: 76px;
}
.com-box-img .el-card .el-card__body {
  height: 100%;
  padding: 0;
}
.com-box-img .el-card .el-card__body a {
  display: block;
  height: 100%;
}
.com-box-img .el-card .el-card__body a img {
  object-fit: contain;
  height: 100%;
}
.imgTip {
  padding: 10px;
  font-size: 14px;
  max-width: 300px;
} 
.links {
    display: flex;
    flex-wrap: wrap;
}

.links a {
    padding: 10px;
}

.links a img {
    width: 200px !important;
    height: 200px !important;
}
.s-case {
    position: relative;
    transition: all 0.2s;
    background-color: #FFF;
    border: 1px #e5e5e5 solid;
    flex: 0 0 31.5%;
    margin-top: 30px;
    text-align: left;
    box-sizing: border-box;
    overflow: hidden;
}
.s-case-h3 {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin: 10px 16px;
}
.s-case-title {
  font-size: 18px;
  font-weight: 400;
  color: #333;
  font-family: "microsoft yahei";
} 
.s-case-intro {
    padding: 9px 16px 0px 16px;
    word-break: break-all;
    color: #777;
    text-align: left;
}
.s-author {
    padding: 0 5px;
    font-size: 14px;
    line-height: 24px;
    color: #ff5722;
    border: 1px #ff5722 solid;
}
.sponsorList {
  .cardList {
    display: flex;
    gap: 10px;
    flex-wrap: wrap;
    justify-content: center;
    .cardItem {
      max-width: 33.33%;
      flex: 1 1 calc(33.33% - 38px);
      padding: 15px;
      border-radius: 4px;
      display: flex;
      align-items: center;
      .flex-hv {
        display: flex;
        align-items: center;
        .cardImg{
          width: 66px;
          height: 66px;
          background-repeat: round;
          margin-right: 10px;
          cursor: pointer;
          border-radius: 50%;
          flex-shrink: 0;
          box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
          border: 2px solid #fff;
        }
      }
    }
  }
}
</style>
