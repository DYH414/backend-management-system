<template>
  <div>
    <div class="card" style="margin-bottom: 5px">
      <el-input style="width: 240px;border:1px  #ccc " placeholder="Please input" v-model="data.name"
                prefix-icon="Search"></el-input>
      <el-button style="margin: 10px" type="primary" @click="load()">查询</el-button>
      <el-button style="margin: 10px" type="warning" @click="reset()">重置</el-button>
    </div>

    <div class="card" style="margin-bottom: 5px">
      <el-button style="margin: 10px" type="primary" @click="handleAdd">新增</el-button>
      <el-button style="margin: 10px" type="danger" @click="delBatch()">批量删除</el-button>
      <!--        <el-button style="margin: 10px" type="success">导出</el-button>-->
      <!--        <el-button style="margin: 10px" type="warning">导入</el-button>-->
    </div>
    <div class="card" style="margin-bottom: 5px">
      <el-table :data="data.tableData" stripe @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="50"/>
        <el-table-column label="账号" prop="username"/>
        <el-table-column label="名称" prop="name"/>
        <el-table-column label="操作">
          <template #default="scope">
            <el-button link type="primary" :icon="Edit" @click="handleEdit(scope.row)">编辑</el-button>
            <el-button link type="danger" :icon="Delete" @click="del(scope.row.id,scope.row.name)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div style="margin-top:10px">
        <el-pagination
            @size-change="load"
            @current-change="load"
            v-model:current-page="data.pageNumber"
            v-model:page-size="data.pageSize"
            :page-sizes="[5,10,15,20]"
            background
            layout="total, sizes, prev, pager, next, jumper"
            :total="data.total">
        </el-pagination>
      </div>
    </div>

    <el-dialog v-model="data.formVisible" title="管理员信息" width="500" destroy-on-close>
      <el-form :model="data.form" label-width="80px" :rules="rules" ref="formRef">
        <el-form-item label="账号" prop="username">
          <el-input v-model="data.form.username" autocomplete="off" placeholder="请输入账号"/>
        </el-form-item>
        <el-form-item label="名称" prop="name">
          <el-input v-model="data.form.name" autocomplete="off" placeholder="请输入名称"/>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="data.formVisible = false">取 消</el-button>
          <el-button type="primary" @click="save">保 存</el-button>
        </div>
      </template>
    </el-dialog>

  </div>

</template>

<script setup>
import {reactive, ref} from 'vue'
import request from "@/utils/request.js";
import {ElMessage, ElMessageBox} from "element-plus";
import {Delete, Edit} from "@element-plus/icons-vue";

const data = reactive({
  name: null,
  pageNumber: 1,
  pageSize: 10,
  total: 0,
  tableData: [],
  formVisible: false,
  form: {},
  ids: []
})
const load = () => {
  request.get("/admin/selectPage", {
    params: {
      pageNum: data.pageNumber,
      pageSize: data.pageSize,
      name: data.name
    }
  }).then(res => {
    data.tableData = res.data.list
    data.total = res.data.total
  })
}
load()
const reset = () => {
  data.name = null
  load()
}

const handleAdd = () => {
  data.formVisible = true
  data.form = {}
}
const save = () => {
  data.form.id ? update() : add()
}
const add = () => {
  // formRef.value 是对 <el-form> 组件实例的引用
  // validate 方法会触发整个表单的校验
  formRef.value.validate((valid) => {
    // valid 是一个布尔值，表示校验是否通过
    if (valid) {
      // 校验通过，执行保存逻辑
      request.post("/admin/add", data.form).then(res => {
        if (res.code === '200') {
          data.formVisible = false
          ElMessage.success('操作成功')
          load()
        } else {
          ElMessage.error('操作失败')
        }
      })
    } else {
      // 校验失败，不执行保存逻辑
      ElMessage.warning('请填写必填项');
      return false;
    }
  });
}

const update = () => {
  // formRef.value 是对 <el-form> 组件实例的引用
  // validate 方法会触发整个表单的校验
  formRef.value.validate((valid) => {
    // valid 是一个布尔值，表示校验是否通过
    if (valid) {
      // 校验通过，执行保存逻辑
      request.put("/admin/update", data.form).then(res => {
        if (res.code === '200') {
          data.formVisible = false
          ElMessage.success('操作成功')
          load()
        } else {
          ElMessage.error('操作失败')
        }
      })
    } else {
      // 校验失败，不执行保存逻辑
      ElMessage.warning('请填写必填项');
      return false;
    }
  });
}
const del = (id, name) => {
  ElMessageBox.confirm(`删除数据后无法恢复,您确认删除
<span style="color: red; font-weight: bold;">${name}</span>吗?`
      , "删除确认", {type: "warning", dangerouslyUseHTMLString: true}).then(() => {
    request.delete("/admin/deleteById/" + id).then(res => {
      if (res.code === '200') {
        ElMessage.success('操作成功')
        load()
      } else {
        ElMessage.error('操作失败')
      }
    })
  })
}
const delBatch = () => {
  if (data.ids.length === 0) {
    ElMessage.warning('请选择要删除的数据')
    return
  }
  ElMessageBox.confirm(`删除数据后无法恢复,您确认删除
<span style="color: red; font-weight: bold;">${data.ids.length}</span>条数据吗?`
      , "删除确认", {type: "warning", dangerouslyUseHTMLString: true}).then(() => {
    request.delete("/admin/deleteBatch", {data: data.ids}).then(res => {
      if (res.code === '200') {
        ElMessage.success('操作成功')
        load()
      } else {
        ElMessage.error('操作失败')
      }
    })

  })

}

const handleEdit = (row) => {
  data.form = {...row}
  data.formVisible = true
}
const handleSelectionChange = (rows) => {
  data.ids = rows.map(row => row.id)
  console.log(data.ids)

}

const formRef = ref(null); // 定义表单的引用

// 定义校验规则
const rules = reactive({
  name: [
    {required: true, message: '名称不能为空', trigger: 'blur'},
    {min: 2, max: 20, message: '名称长度应在2到20个字符之间', trigger: 'blur'}
  ],
  username: [
    {required: true, message: '请填写账号', trigger: ['blur', 'change']}
  ],

});
</script>