<template>
  <div class="app-container">
    <!-- 添加或修改表单定义对话框 -->
    <el-dialog :title="title" v-model="open" width="400px" v-if="open" append-to-body>
      <el-form ref="definitionRef" :model="form" :rules="rules" label-width="77.45px" :disabled="disabled">
        <el-form-item label="表单编码" prop="formCode">
          <el-input v-model="form.formCode" placeholder="请输入表单编码" maxlength="40" show-word-limit/>
        </el-form-item>
        <el-form-item label="表单名称" prop="formName">
          <el-input v-model="form.formName" placeholder="请输入表单名称" maxlength="100" show-word-limit/>
        </el-form-item>
        <el-form-item label="表单版本" prop="version">
          <el-input v-model="form.version" placeholder="请输入表单版本" maxlength="20" show-word-limit/>
        </el-form-item>
        <el-form-item label="表单类型" prop="formType">
          <el-select v-model="form.formType" placeholder="请选择表单类型">
            <el-option label="内置表单" :value="0"></el-option>
            <el-option label="外挂表单" :value="1"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="表单路径" v-if="form.formType === 1" prop="formPath">
          <el-input v-model="form.formPath" placeholder="请输入表单路径" maxlength="100" show-word-limit/>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" v-if="!disabled" @click="submitForm">确 定</el-button>
          <el-button @click="cancel" v-if="!disabled">取 消</el-button>
          <el-button @click="cancel" v-if="disabled">关 闭</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Dialog">
import { getDefinition, addDefinition, updateDefinition } from "@/api/form/definition";
const { proxy } = getCurrentInstance();
const open = ref(false); // 是否显示弹出层
const disabled = ref(false); // 是否禁用表单
const title = ref(""); // 弹出层标题
const data = reactive({
  // 表单参数
  form: {},
  // 表单校验
  rules: {
    formCode: [{ required: true, message: "表单编码不能为空", trigger: "blur" }],
    formName: [{ required: true, message: "表单名称不能为空", trigger: "blur" }],
    version: [{ required: true, message: "表单版本不能为空", trigger: "blur" }],
    formType: [{ required: true, message: "表单类型不能为空", trigger: "blur" }],
    formPath: [{ required: true, message: "表单路径不能为空", trigger: "blur" }]
  }
});
const emit = defineEmits(["refresh"]);
const { form, rules } = toRefs(data);

/** 打开表单定义弹框 */
async function show(id, _disabled) {
  reset();
  disabled.value = _disabled
  if (id) {
    await getDefinition(id).then(response => {
      form.value = response.data;
    });
  }
  open.value = true
  if (disabled.value) {
    title.value = "详情"
  } else if (id) {
    title.value = "修改"
  } else {
    title.value = "新增"
  }
}
// 取消按钮
function cancel() {
  open.value = false;
  reset();
}
// 表单重置
function reset() {
  form.value = {
    id: null,
    formCode: null,
    formName: null,
    version: null,
    formType: null,
    formPath: null
  };
  proxy.resetForm("definitionRef");
}
/** 提交按钮 */
function submitForm() {
  proxy.$refs["definitionRef"].validate(valid => {
    if (valid) {
      if (form.value.formType === 0) form.value.formPath = "";
      if (form.value.id != null) {
        updateDefinition(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          emit('refresh');
        });
      } else {
        delete form.value.id;
        addDefinition(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          emit('refresh');
        });
      }
    }
  });
}
defineExpose({
  show
});
</script>
