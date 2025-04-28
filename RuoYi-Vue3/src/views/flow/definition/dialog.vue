<template>
  <div class="app-container">
    <!-- 添加或修改流程定义对话框 -->
    <el-dialog :title="title" v-model="open" width="800px" v-if="open" append-to-body>
      <el-tabs type="border-card" class="Tabs" v-model="tabsValue">
        <el-tab-pane label="基础设置" name="1"></el-tab-pane>
        <el-tab-pane label="监听器" name="2"></el-tab-pane>
      </el-tabs>
      <el-form ref="definitionRef" class="dialogForm" :model="form" :rules="rules" label-width="150px" :disabled="disabled">
        <div v-show="tabsValue === '1'">
          <el-form-item label="流程编码" prop="flowCode">
            <el-input v-model="form.flowCode" placeholder="请输入流程编码" maxlength="40" show-word-limit/>
          </el-form-item>
          <el-form-item label="流程名称" prop="flowName">
            <el-input v-model="form.flowName" placeholder="请输入流程名称" maxlength="100" show-word-limit/>
          </el-form-item>
          <el-form-item label="流程类别" prop="category">
            <el-input v-model="form.category" placeholder="请输入流程类别" maxlength="20" show-word-limit/>
          </el-form-item>
          <el-form-item label="是否发布" prop="isPublish" v-if="disabled">
            <el-select v-model="form.isPublish" placeholder="请选择是否开启流程">
              <el-option
                v-for="dict in is_publish"
                :key="dict.value"
                :label="dict.label"
                :value="parseInt(dict.value)"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="审批表单是否自定义" prop="formCustom">
            <el-select v-model="form.formCustom">
              <el-option label="表单路径" value="N"></el-option>
              <!--TODO form 开发中-->
              <!-- <el-option label="流程表单" value="Y"></el-option> -->
            </el-select>
          </el-form-item>
          <el-form-item label="审批表单路径" prop="formPath" v-if="form.formCustom === 'N'">
            <el-input v-model="form.formPath" placeholder="请输入审批表单路径" maxlength="100" show-word-limit/>
          </el-form-item>
          <el-form-item label="审批流程表单" v-else-if="form.formCustom === 'Y'">
            <el-select v-model="form.formPath">
              <el-option v-for="item in definitionList" :key="item.id" :label="`${item.formName} - v${item.version}`" :value="item.id"></el-option>
            </el-select>
          </el-form-item>
        </div>
        <div v-show="tabsValue === '2'">
          <el-form-item prop="listenerRows" class="listenerItem">
            <el-table :data="form.listenerRows" style="width: 100%">
              <el-table-column prop="listenerType" width="150" label="类型">
                <template #default="scope">
                  <el-form-item :prop="'listenerRows.' + scope.$index + '.listenerType'" :rules="rules.listenerType">
                    <el-select v-model="scope.row.listenerType" placeholder="请选择类型">
                      <el-option label="开始" value="start"></el-option>
                      <el-option label="分派" value="assignment"></el-option>
                      <el-option label="完成" value="finish"></el-option>
                      <el-option label="创建" value="create"></el-option>
                    </el-select>
                  </el-form-item>
                </template>
              </el-table-column>
              <el-table-column prop="listenerPath" label="路径">
                <template #default="scope">
                  <el-form-item :prop="'listenerRows.' + scope.$index + '.listenerPath'" :rules="rules.listenerPath">
                    <el-input v-model="scope.row.listenerPath" placeholder="请输入路径"></el-input>
                  </el-form-item>
                </template>
              </el-table-column>
              <el-table-column label="操作" width="65" v-if="!disabled">
                <template #default="scope">
                  <el-button size="small" type="danger" :icon="Delete" @click="handleDeleteRow(scope.$index)"/>
                </template>
              </el-table-column>
            </el-table>
            <el-button v-if="!disabled" style="margin-top: 10px;" type="primary" @click="handleAddRow">增加行</el-button>
          </el-form-item>
        </div>
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
// import { publishedList } from "@/api/form/definition";
import { getDefinition, addDefinition, updateDefinition } from "@/api/flow/definition";
import { Delete } from "@element-plus/icons-vue";

const { proxy } = getCurrentInstance();
const { is_publish } = proxy.useDict('is_publish');

const open = ref(false);
const tabsValue = ref("1");
const disabled = ref(false);
const title = ref("");
const definitionList = ref([]); // 流程表单列表

const data = reactive({
  // 表单参数
  form: {
    listenerRows: [] // 新增的表格数据
  },
  // 表单校验
  rules: {
    flowCode: [
      { required: true, message: "流程编码不能为空", trigger: "blur" }
    ],
    flowName: [
      { required: true, message: "流程名称不能为空", trigger: "blur" }
    ],
    isPublish: [
      { required: true, message: "是否开启流程不能为空", trigger: "change" }
    ],
    formCustom: [
      { required: true, message: "请选择审批表单是否自定义", trigger: "change" }
    ],
    listenerType: [{ required: true, message: '监听器不能为空', trigger: ['change', 'blur'] }],
    listenerPath: [{ required: true, message: '监听器不能为空', trigger: ['change', 'blur'] }]
  }
});

const emit = defineEmits(["refresh"]);
const { form, rules } = toRefs(data);

/** 打开流程定义弹框 */
async function show(id, _disabled) {
  reset();
  disabled.value = _disabled;
  // TODO form 开发中
  // getDefinitionList();
  if (id) {
    await getDefinition(id).then(response => {
      form.value = response.data;
      if (form.value.listenerType) {
        const listenerTypes = form.value.listenerType.split(",");
        const listenerPaths = form.value.listenerPath.split("@@");
        form.value.listenerRows = listenerTypes.map((type, index) => ({
          listenerType: type,
          listenerPath: listenerPaths[index]
        }));
      } else {
        form.value.listenerRows = [];
      }
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
/** 查询表单定义列表 */
// function getDefinitionList() {
//   publishedList().then(response => {
//     definitionList.value = response.data;
//   });
// };
// 取消按钮
function cancel() {
  open.value = false;
  reset();
}
// 表单重置
function reset() {
  form.value = {
    id: null,
    flowCode: null,
    flowName: null,
    version: null,
    isPublish: null,
    formCustom: null,
    formPath: null,
    createTime: null,
    updateTime: null,
    delFlag: null,
    listenerRows: [] // 初始化表格数据
  };
  proxy.resetForm("definitionRef");
  tabsValue.value = "1";
}
/** 提交按钮 */
function submitForm() {
  proxy.$refs["definitionRef"].validate((valid, err) => {
    if (valid) {
      form.value.listenerType = form.value.listenerRows.map(row => row.listenerType).join(",")
      form.value.listenerPath = form.value.listenerRows.map(row => row.listenerPath).join("@@")
      if (form.value.id != null) {
        updateDefinition(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          emit('refresh');
        });
      } else {
        addDefinition(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          emit('refresh');
        });
      }
    } else {
      let errItems = Object.keys(err);
      if (errItems.length > 0) {
        tabsValue.value = "1";
        if (errItems.every(e => e.includes("listenerRows"))) tabsValue.value = "2";
      }
    }
  });
}
// 增加行
function handleAddRow() {
  form.value.listenerRows.push({ listenerType: '', listenerPath: '' });
  proxy.$refs.form.clearValidate("listenerRows");
};
// 删除行
function handleDeleteRow(index) {
  form.value.listenerRows.splice(index, 1);
};


defineExpose({
  show,
});
</script>

<style scoped lang="scss">
::v-deep.Tabs {
  margin-top: -20px;
  box-shadow: none;
  border-bottom: 0;
  .el-tabs__content {
    display: none;
  }
  .el-tabs__item.is-active {
    margin-left: 0;
    border-top: 1px solid var(--el-border-color);
    margin-top: 0;
  }
}
.dialogForm {
  border: 1px solid #e4e7ed;
  border-top: 0;
  padding: 15px;
}
::v-deep.listenerItem {
  .el-form-item__label {
    float: none;
    display: inline-block;
    text-align: left;
  }
  .el-form-item__content {
    margin-left: 0 !important;
  }
}
</style>
