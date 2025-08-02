<template>
    <div>
      <div class="card" style="margin-bottom: 5px">
        <el-input style="width: 240px;border:1px  #ccc " placeholder="Please input" v-model="data.name"
                  prefix-icon="Search"></el-input>
        <el-button style="margin: 10px" type="primary" @click="load()">查询</el-button>
        <el-button style="margin: 10px" type="warning" @click="reset()">重置</el-button>
      </div>

      <div class="card" style="margin-bottom: 5px">
        <el-button style="margin: 10px" type="primary">新增</el-button>
        <el-button style="margin: 10px" type="danger">批量删除</el-button>
        <!--        <el-button style="margin: 10px" type="success">导出</el-button>-->
        <!--        <el-button style="margin: 10px" type="warning">导入</el-button>-->
      </div>
      <div class="card" style="margin-bottom: 5px">
        <el-table :data="data.tableData" stripe>
          <el-table-column label="名称" prop="name"/>
          <el-table-column label="性别" prop="sex"/>
          <el-table-column label="工号" prop="no"/>
          <el-table-column label="年龄" prop="age"/>
          <el-table-column label="个人介绍" prop="description" show-overflow-tooltip/>
          <el-table-column label="部门" prop="departmentName"/>
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
      <el-dialog  v-model="data.formVisible" title="员工信息" width="500">
        <el-form :model="form">
          <el-form-item label="Promotion name" :label-width="formLabelWidth">
            <el-input v-model="form.name" autocomplete="off" />
          </el-form-item>
          <el-form-item label="Zones" :label-width="formLabelWidth">
            <el-select v-model="form.region" placeholder="Please select a zone">
              <el-option label="Zone No.1" value="shanghai" />
              <el-option label="Zone No.2" value="beijing" />
            </el-select>
          </el-form-item>
        </el-form>
        <template #footer>
          <div class="dialog-footer">
            <el-button @click="dialogFormVisible = false">Cancel</el-button>
            <el-button type="primary" @click="dialogFormVisible = false">
              Confirm
            </el-button>
          </div>
        </template>
      </el-dialog>
    </div>

</template>

<script setup>
import {reactive} from 'vue'
import request from "@/utils/request.js";

const data = reactive({
  name: null,
  pageNumber: 1,
  pageSize: 10,
  total: 0,
  tableData: [],
  formVisible: false
})
const load = () => {
  request.get("/employee/selectPage", {
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
</script>