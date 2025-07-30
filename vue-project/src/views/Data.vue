<template>
  <div>
    <div>
      <div class="card" style="margin-bottom: 5px">
        <el-input style="width: 240px;border:1px  #ccc " placeholder="Please input" v-model="data.name"
                  prefix-icon="Search"></el-input>
        <el-button style="margin: 10px" type="primary">查询</el-button>
        <el-button style="margin: 10px" type="warning">重置</el-button>
      </div>
      <div class="card" style="margin-bottom: 5px">
        <el-button style="margin: 10px" type="primary">新增</el-button>
        <el-button style="margin: 10px" type="danger">批量删除</el-button>
        <el-button style="margin: 10px" type="success">导出</el-button>
        <el-button style="margin: 10px" type="warning">导入</el-button>
      </div>
      <div class="card" style="margin-bottom: 5px">
        <el-table :data="data.tableData" stripe>
          <el-table-column label="日期" prop="date"/>
          <el-table-column label="姓名" prop="name"/>
          <el-table-column label="地址" prop="address"/>
        </el-table>
        <div style="margin-top:10px">
          <el-pagination
            v-model:current-page="data.pageNumber"
            v-model:page-size="data.pageSize"
            :page-sizes="[5,10,15,20]"
             background
              layout="total, sizes, prev, pager, next, jumper"
            :total="data.total">

          </el-pagination>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import {reactive} from 'vue'

const data = reactive({
  name: '',
  pageNumber: 1,
  pageSize: 10,
  total:47,
  tableData: [
    {
      date: '2016-05-02',
      name: '王小虎',
      address: '上海市普陀区金沙江路 1518 弄'
    },
    {
      date: '2016-05-04',
      name: '王小虎',
      address: '上海市普陀区金沙江路 1517 弄'
    },
    {
      date: '2016-05-01',
      name: '王小虎',
      address: '上海市普陀区金沙江路 1519 弄'
    }
  ]
})
</script>