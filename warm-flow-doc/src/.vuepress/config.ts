import {defineUserConfig} from "vuepress";

import {viteBundler} from '@vuepress/bundler-vite'
import {path} from "vuepress/utils";

import theme from "./theme.js";

export default defineUserConfig({
    base: "/",
    port: 8081,
    locales: {
        "/": {
            lang: "zh-CN",
            title: "Warm-Flow官网",
        },
    },
    theme,
    alias: {
        "@theme-hope/layouts/Layout": path.resolve(
            __dirname,
            "./layouts/Layout.vue",
        )
    },
    shouldPrefetch: true,
    bundler: viteBundler({
        viteOptions: {},
        vuePluginOptions: {},
    }),
    head: [
        ['script', {}, `       
            var _hmt = _hmt || [];
            (function() {
              var hm = document.createElement("script");
              hm.src = "https://hm.baidu.com/hm.js?65be70db3d38cbd58e6c4710a5a774ae";
              var s = document.getElementsByTagName("script")[0]; 
              s.parentNode.insertBefore(hm, s);
            })();
        `],
        ['script', { type: 'text/javascript', charset: 'UTF-8'
            , src: 'https://cdn.wwads.cn/js/makemoney.js', async: '' }, ''
        ],
    ],
});

