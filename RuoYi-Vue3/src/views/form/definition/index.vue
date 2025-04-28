<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="100px">
      <el-form-item label="表单编码" prop="flowCode">
        <el-input
          v-model="queryParams.flowCode"
          placeholder="请输入表单编码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="表单名称" prop="flowName">
        <el-input
          v-model="queryParams.flowName"
          placeholder="请输入表单名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="表单版本" prop="version">
        <el-input
          v-model="queryParams.version"
          placeholder="请输入表单版本"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['form:definition:add']"
        >新增</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="definitionList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" fixed />
      <el-table-column label="序号" width="50" align="center">
        <template #default="scope">
          {{ scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column label="表单编码" align="center" prop="formCode" />
      <el-table-column label="表单名称" align="center" prop="formName" />
      <el-table-column label="表单版本" align="center" prop="version">
        <template #default="scope">
          <el-tag>{{scope.row.version}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="是否发布" align="center" prop="isPublish">
        <template #default="scope">
          <dict-tag :options="is_publish" :value="scope.row.isPublish"/>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime">
        <template #default="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="230" fixed="right" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button
            v-if="scope.row.formType === 0"
            size="small"
            type="text"
            @click="handleDesign(scope.row)"
            v-hasPermi="['form:design:queryDesign']"
          >表单设计</el-button>
          <el-button
            size="small"
            type="text"
            v-if="scope.row.isPublish === 0"
            @click="handlePublish(scope.row.id)"
            v-hasPermi="['form:definition:publish']"
          >发布</el-button>
          <el-button
            size="small"
            type="text"
            v-if="scope.row.isPublish === 1"
            @click="handleUpPublish(scope.row.id)"
            v-hasPermi="['form:definition:upPublish']"
          >取消发布</el-button>
          <el-button
            size="small"
            type="text"
            @click="handleCopyDef(scope.row.id)"
            v-hasPermi="['form:definition:upPublish']"
          >复制表单</el-button>
          <el-button
            size="small"
            type="text"
            v-if="scope.row.isPublish === 0"
            @click="handleUpdate(scope.row.id)"
            v-hasPermi="['form:definition:edit']"
          >修改</el-button>
          <el-button
            size="small"
            type="text"
            v-if="scope.row.isPublish === 0"
            @click="handleDelete(scope.row)"
            v-hasPermi="['form:definition:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />
    <Dialog ref="dialog" @refresh="getList"></Dialog>
    <formDialog v-if="showFormDialog" v-model:visible="showFormDialog" :type="'2'" :formName="formName" :formId="formId"></formDialog>
  </div>
</template>

<script setup name="formDefinition">
import {
  listDefinition,
  delDefinition,
  publish,
  unPublish,
  copyDef
} from "@/api/form/definition";
import Dialog from "./dialog";
import formDialog from "./formDialog";
import router from "@/router";
import { onActivated } from "vue";

const { proxy } = getCurrentInstance();
const { is_publish } = proxy.useDict('is_publish', 'activity_status');

const definitionList = ref([]);
const loading = ref(true);
const showSearch = ref(true);
const uniqueId = ref("");
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const imgUrl = ref("");
const flowChart = ref(false);
const formName = ref("");
const formId = ref("");
const showFormDialog = ref(false);

const data = reactive({
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    formCode: null,
    formName: null,
    version: null,
  }
});

const { queryParams } = toRefs(data);

onActivated(() => {
  const time = proxy.$route.query.t;
  if (time != null && time != uniqueId.value) {
    uniqueId.value = time;
    queryParams.value.pageNum = Number(proxy.$route.query.pageNum);
    getList();
  }
});

/** 查询表单定义列表 */
function getList() {
  loading.value = true;
  listDefinition(queryParams.value).then(response => {
    definitionList.value = response.rows;
    total.value = response.total;
    loading.value = false;
  });
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryForm");
  handleQuery();
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.id)
  single.value = selection.length!==1
  multiple.value = !selection.length
}

/** 新增按钮操作 */
function handleAdd() {
  proxy.$refs["dialog"].show();
}

/** 表单设计按钮操作 */
function handleDesign(row) {
  if (row.isPublish === 1) {
    formName.value = row.formName;
    formId.value = row.id;
    showFormDialog.value = true;
  } else {
    const params = { pageNum: queryParams.value.pageNum };
    router.push({ path: "/form/form-design/index/" + row.id, query: params });
  }
}

/** 发布按钮操作 */
function handlePublish(id) {
  proxy.$modal.confirm('是否确认发布表单定义编号为"' + id + '"的数据项？').then(function() {
    return publish(id);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("发布成功");
  }).catch(() => {});
}

/** 取消发布按钮操作 */
function handleUpPublish(id) {
  proxy.$modal.confirm('是否确认取消发布表单定义编号为"' + id + '"的数据项？').then(function() {
    return unPublish(id);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("取消成功");
  }).catch(() => {});
}

/** 修改按钮操作 */
function handleUpdate(id) {
  proxy.$refs["dialog"].show(id);
}

/** 删除按钮操作 */
function handleDelete(row) {
  const ids = row.id || ids.value;
  proxy.$modal.confirm('是否确认删除表单定义编号为"' + ids + '"的数据项？').then(function() {
    return delDefinition(ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 复制表单按钮操作 */
function handleCopyDef(id) {
  proxy.$modal.confirm('是否确认复制表单定义编号为"' + id + '"的数据项？').then(function() {
    return copyDef(id);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("复制成功");
  }).catch(() => {});
}

getList();
</script>
