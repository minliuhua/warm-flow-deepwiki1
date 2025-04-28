import { hopeTheme } from "vuepress-theme-hope";
import navbar from "./navbar.js";
import sidebar from "./sidebar.js";

export default hopeTheme(
    {
        hostname: "https://gitee.com/warm_4/warm-flow-doc",
        author: {
            name: "Dromara Warm-Flow工作流",
            url: "https://gitee.com/warm_4/warm-flow-doc",
        },
        logo: "/logo.png",
        docsDir: "src",
        print: false,
        locales: {
            "/": {
                navbar,
                sidebar,
                displayFooter: true,
                metaLocales: {
                    editLink: "前往 Gitee 编辑此页",
                },
            },
        },
        editLink: false,

        markdown: {
            spoiler: true,
            mark: true,
            codeTabs: true,
            include: true,
            component: true,
        },

        plugins: {
            components: {
                // 你想使用的组件
                components: [
                    "BiliBili",
                    "VPCard",
                    "SiteInfo",
                ],
            },
            icon: {
                assets: "https://at.alicdn.com/t/c/font_4043253_v7nldr3uv7.css",
                prefix: "iconfont icon-",
            },
            comment: {
                provider: "Giscus",
                repo: "dromara/warm-flow",
                repoId: "R_kgDOK_2R_w",
                category: "Announcements",
                categoryId: "DIC_kwDOK_2R_84ClNey",
            },
            notice: [
                {
                    path: "/master",
                    title: "公告: 1.7.0（正式版）",
                    content:
                        '<div>\n' +
                        '     <span onclick="window.location.href=\'/master/other/news/news/8.html\'" class="link-style">' +
                        '       🔵 过去、现在和未来都不会有商业版' +
                        '     </span>\n' +
                        '</div>' +
                        '<div>\n' +
                        '     <span onclick="window.location.href=\'/master/other/videos.html\'" class="link-style">' +
                        '       🔵 新增从零精通教学视频' +
                        '     </span>\n' +
                        '</div>' +
                        '<div>\n' +
                        '     <span onclick="window.location.href=\'/master/other/news/upgrade/8.html\'" class="link-style">' +
                        '       🔵 国产免费工作流引擎star 5.9k，Warm-Flow版本升级1.7.0（新增n多好用功能）' +
                        '     </span>\n' +
                        '</div>',
                    actions: [
                        {
                            text: "学习视频地址",
                            link: "'/master/other/videos.html",
                            type: "primary",
                        },
                    ],
                    showOnce: false,
                },
            ],
            slimsearch: true,
            mdEnhance: {
                align: true,
                attrs: true,
                codetabs: true,
                demo: true,
                figure: true,
                flowchart: true,
                gfm: true,
                imgLazyload: true,
                imgSize: true,
                include: true,
                katex: true,
                mark: true,
                mermaid: true,
                stylize: [
                    {
                        matcher: "Recommended",
                        replacer: ({ tag }) => {
                            if (tag === "em")
                                return {
                                    tag: "Badge",
                                    attrs: { type: "tip" },
                                    content: "Recommended",
                                };
                        },
                    },
                ],
                sub: true,
                sup: true,
                tabs: true,
                vPre: true,
            },
        }
    },
    { custom: true }
);

