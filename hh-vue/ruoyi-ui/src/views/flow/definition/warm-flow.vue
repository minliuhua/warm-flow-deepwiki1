<template>
  <div :style="'height:' + height">
    <iframe :src="url" style="width: 100%; height: 100%"/>
  </div>
</template>
<script>
import {getToken} from "@/utils/auth";

export default {
  name: "WarmFlow",
  data() {
    return {
      height: document.documentElement.clientHeight - 94.5 + "px;",
      url: ""
    };
  },
  mounted() {
    this.url = `${process.env.VUE_APP_FLOW_API}/warm-flow-ui/index.html?id=${this.$route.params.id}&disabled=${this.$route.query.disabled}&Authorization=Bearer ` + getToken();
    window.addEventListener("message", this.handleMessage);
  },
  beforeDestroy() {
    window.removeEventListener("message", this.handleMessage);
  },
  methods: {
    handleMessage(event) {
      console.log(event.data.method, event);
      switch (event.data.method) {
        case "close":
          this.close();
          break;
      }
    },
    close() {
      const obj = { path: "/flow/definition" };
      this.$tab.closeOpenPage(obj);
    }
  }
};
</script>
