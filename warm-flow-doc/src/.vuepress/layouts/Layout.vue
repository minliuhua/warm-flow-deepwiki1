<script setup lang="ts">
import {usePageData, usePageFrontmatter} from "vuepress/client";
import {ref, watch} from "vue";

import CommonWrapper from "vuepress-theme-hope/components/CommonWrapper.js";
import HopeHomePage from "vuepress-theme-hope/components/HomePage.js";
import NormalPage from "vuepress-theme-hope/components/NormalPage.js";
import SkipLink from "vuepress-theme-hope/components/SkipLink.js";
import { FadeSlideY } from "vuepress-theme-hope/components/transitions/FadeSlideY.js";

import type {ThemePageFrontmatter} from "vuepress-theme-hope";

const page = usePageData();
const frontmatter = usePageFrontmatter<ThemePageFrontmatter>();

const sidebarTopArrayLift = [
  `<a href="https://www.maxkey.top" target="_blank">
    <img className="no-zoom" height="60px" width="200px" src="/ggw/MaxKey.png" class="9999">
  </a>`,
  `<a href="https://ccflow.org/index.html?frm=warmflow" target="_blank">
    <img className="no-zoom" height="60px" width="200px" src="/ggw/ccflow.png" class="2025-03-03">
  </a>`,
  `<a href="/master/other/paidservice.html#私人服务" target="_blank">
    <img className="no-zoom" height="60px" width="200px" src="/ggw/yuui.jpg">
  </a>`,
];

const sidebarTopArrayRight = [
  `<a href="https://www.maxkey.top" target="_blank">
    <img className="no-zoom" height="60px" width="200px" src="/ggw/MaxKey.png" class="9999">
  </a>`,
  `<a href="https://ccflow.org/index.html?frm=warmflow" target="_blank">
    <img className="no-zoom" height="60px" width="200px" src="/ggw/ccflow.png" class="2025-03-03">
  </a>`,
  `<a href="/master/other/paidservice.html#私人服务" target="_blank">
    <img className="no-zoom" height="60px" width="200px" src="/ggw/yuui.jpg">
  </a>`,
];

const sidebarContentLift = ref("");
const sidebarContentRight = ref("");

function shuffle(arr) {
  var l = arr.length;
  var index, temp;
  while (l > 0) {
    index = Math.floor(Math.random() * l);
    temp = arr[l - 1];
    arr[l - 1] = arr[index];
    arr[index] = temp;
    l--;
  }
  return arr;
}

watch(
  () => page.value.path,
  () => {
    if (page.value.path.startsWith("/en/")) {
      sidebarContentLift.value = "";
      sidebarContentRight.value = "";
      return;
    }
    shuffle(sidebarTopArrayLift);
    shuffle(sidebarTopArrayRight);

    sidebarContentLift.value = `\
      <div>
          <br>
            <span style="color: gray;font-size: smaller;">广告采用随机轮播方式显示</span>
            <span style="color: #E01E5A;font-size: smaller;font-weight: bolder;float: right">❤️<a href="/master/other/paidservice.html#赞助商广告">成为赞助商</a></span>
          <br>
      </div>
      <div style="width:230px;margin:5px auto;">
        ${sidebarTopArrayLift.slice(0, sidebarTopArrayLift.length).join("\n  ")}
      </div>
    `;
    sidebarContentRight.value = `\
      <div>
          <br>
            <span style="color: #E01E5A;font-size: smaller;font-weight: bolder;">❤️<a href="/master/other/paidservice.html#赞助商广告">成为赞助商</a></span>
          <br>
      </div>
      <div style="width:230px;margin:5px auto;">
        ${sidebarTopArrayRight.slice(0, sidebarTopArrayRight.length).join("\n  ")}
      </div>
    `;

  },
);
</script>
<template>
  <SkipLink />
  <CommonWrapper>
    <template #default>
      <HopeHomePage v-if="frontmatter.home">
        <template #center>
          <div content="content"></div>
        </template>
      </HopeHomePage>
      <FadeSlideY v-else>
        <NormalPage :key="page.path">
          <template #contentBefore>
            <div content="content"></div>
          </template>
<!--         <template #tocBefore>-->
<!--           <div v-html="sidebarContentRight" />-->
<!--         </template>-->
        </NormalPage>
      </FadeSlideY>
    </template>
    <template v-if="!frontmatter.home" #sidebarTop>
      <div v-html="sidebarContentLift" />
    </template>
  </CommonWrapper>
</template>

<style lang="scss">
.vp-toc-placeholder {
  top: calc(var(--navbar-height));
}
.vp-toc-header {
  margin-top: 10px;
}
</style>
