// @ts-ignore
import { defineClientConfig } from "vuepress/client";
// 全量引入element-plus
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

export default defineClientConfig({
  enhance({ app, router, siteData }) {
    app.use(ElementPlus);
    router.beforeEach((to, from, next) => {
      //触发百度的pv统计
      // @ts-ignore
      if (typeof _hmt != "undefined") {
        if (to.path) {
          // @ts-ignore
          _hmt.push(["_trackPageview", to.fullPath]);
        }
      }

      // continue
      next();
    });
  },
});
