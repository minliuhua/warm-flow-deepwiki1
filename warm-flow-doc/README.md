# [Dromara Warm-Flow工作流]()
 
## 项目结构

本项目使用 [VuePress](https://vuepress.vuejs.org/zh/) 框架，辅以
[vuepress-hope-theme](https://theme-hope.vuejs.press/zh/) 主题完成整体构建。

```tree
├─.vuepress
│  ├─dist  // 自动生成的静态页面
│  ├─public  // 网页资源，这里是一些图标
│  ├─styles  // 样式
│  ├─config.ts  // 配置文件
│  ├─navbar.ts  // 顶栏配置
│  ├─sidebar.ts  // 侧栏配置
│  └─theme.ts  // 主题配置
│
├─docs  // 文档目录
└─tutorials  // 教程目录
    └─lang  // 语言教程
```

其中最为重要的两个目录是 `docs` 和 `guide` ，你应该把文档或教程分别放置在那里，
如果有必要，可以单独分出一个子文件夹。若要配置顶部导航栏请修改 `navbar.ts` ，侧
边栏则修改 `sidebar.ts`。

## 本地测试

### 第一次安装

- 安装 [NodeJS 18](https://nodejs.org/en/download/)
- 使用 [GitHub Desktop](https://desktop.github.com/) 或者 Git 命令行来克隆本库 (`git clone`)
- 在**项目根目录**运行 `pnpm i` 命令

### 本地测试

- 在**项目根目录**运行 `pnpm docs:dev` 命令
  - 站点会默认运行在 `http://localhost:8080` 

### 打包
-  `pnpm docs:build` 命令
