import {navbar} from "vuepress-theme-hope";

export default navbar([
    {
        text: '首页',
        link: '/',
    },
    {
        text: '文档',
        link: '/master/introduction/introduction.md',
        children: [
            {
                text: 'v1.7.0(当前版本)',
                link: '/master/introduction/introduction.md'
            },
            {
                text: '历史版本',
                children: [
                    { text: 'v1.6.10', link: '/v1.6.10/introduction/introduction.md' },
                    { text: 'v1.6.8', link: '/v1.6.8/introduction/introduction.md' },
                    { text: 'v1.6.7', link: '/v1.6.7/introduction/introduction.md' },
                    { text: 'v1.6.6', link: '/v1.6.6/introduction/introduction.md' },
                    { text: 'v1.3.8', link: '/v1.3.5/introduction/introduction.md' },
                    // { text: 'v1.3.7', link: '/v1.3.5/introduction/introduction.md' },
                    // { text: 'v1.3.6', link: '/v1.3.5/introduction/introduction.md' },
                    // { text: 'v1.3.5', link: '/v1.3.5/introduction/introduction.md' },
                    // { text: 'v1.3.4', link: '/v1.3.4/guide/started.md' },
                    // { text: 'v1.3.3', link: '/v1.3.3/guide/started.md' },
                    // { text: 'v1.3.1', link: '/v1.3.1/guide/started.md' },
                    // { text: 'v1.3.0', link: '/v1.3.0/guide/started.md' },
                    // { text: 'v1.2.8', link: '/v1.2.8/guide/started.md' },
                    // { text: 'v1.2.7', link: '/v1.2.7/guide/started.md' },
                    // { text: 'v1.2.6', link: '/v1.2.6/guide/started.md' },
                    // { text: 'v1.2.4', link: '/v1.2.4/guide/started.md' },
                    // { text: 'v1.2.3', link: '/v1.2.3/guide/started.md' },
                ]
            },
            {
                text: '升级指南',
                link: '/master/other/upgrade_guide.md',
            },
        ]
    },
    {
        text: "加入群聊",
        link: "/master/other/jionqun.md",
    },
    {
        text: '新闻',
        link: '/master/other/news.md'
    },
    {
        text: '团队',
        link: '/master/other/team.md',
    },
    {
        text: "有偿服务",
        link: "/master/other/paidservice.md",
    },
    {
        text: "教学视频",
        link: "/master/other/videos.md",
    },
    {
        text: '常见问题',
        link: '/master/other/troubleshooting.md'
    },
    {
        text: '计划/日志',
        link: '/master/other/update.md'
    },
]);
