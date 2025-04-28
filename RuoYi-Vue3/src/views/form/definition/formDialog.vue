<template>
  <div class="formDialog">
    <!-- 添加或修改OA 自定义流程表单对话框 -->
    <el-dialog :title="formName" v-model="showFormDialog" width="500px" v-if="showFormDialog" append-to-body @close="cancel">
      <iframe ref="FormCreate" :src="url" :style="`width: 100%; max-height: 60vh; height: ${offsetHeight}px; border: none;`"/>
    </el-dialog>
  </div>
</template>

<script setup name="formDialog">
import { getToken } from "@/utils/auth";
import { onMounted, onUnmounted } from "vue";
const props = defineProps({
  // 来源：0待办-办理 1已办-流程历史记录 2已发布的表单设计
  type: {
    type: String,
    default: "0"
  },
  /* 业务id */
  modelValue: {
    type: String,
    default: "",
  },
  /* 实例id */
  taskId: {
    type: String,
    default: "",
  },
  /* 是否可以标编辑 */
  disabled: {
    type: Boolean,
    default: false,
  },
  // 是否显示弹出层
  visible: {
    type: Boolean,
    default: true
  },
  // 表单名称
  formName: {
    type: String,
    default: "办理"
  },
  // 表单id，查找表单设计内容
  formId: {
    type: String,
    default: ""
  }
});
const url = ref("");
const offsetHeight = ref(null); // iframe高度
const showFormDialog = ref(true);
const emit = defineEmits(["refresh"]);
const { proxy } = getCurrentInstance();
onMounted(() => {
  url.value = import.meta.env.VITE_APP_FLOW_API + `/warm-flow-ui/index.html?type=formCreate&Authorization=Bearer ` + getToken();
  // url.value = `http://localhost:81/warm-flow-ui/index.html?type=formCreate&Authorization=Bearer ` + getToken();
  window.addEventListener("message", handleMessage);
});
onUnmounted(() => {
  window.removeEventListener("message", handleMessage);
});
function handleMessage(event) {
  switch (event.data.method) {
    case "formInit":
      let data = {
        type: props.type,
        formId: props.formId,
        taskId: props.taskId,
        disabled: props.disabled
      };
      proxy.$refs.FormCreate.contentWindow.postMessage({ method: "formInit", data }, '*');
      break;
    case "getOffsetHeight":
      // 获取子页面内容高度
      offsetHeight.value = event.data.offsetHeight;
      break;
    case "submitSuccess":
      proxy.$modal.msgSuccess("办理成功");
      cancel();
      break;
  }
};
// 取消按钮
function cancel() {
  showFormDialog.value = false;
  emit("update:visible", false);
  proxy.$refs.FormCreate.contentWindow.postMessage({ method: "reset" }, '*');
  emit('refresh');
};
</script>
