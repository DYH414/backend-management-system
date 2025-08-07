<template xmlns="http://www.w3.org/1999/html">
  <div class="card" style="width: 50%;padding-top: 50px;padding-right: 30px">
      <el-form :model="data.form" label-width="80px" :rules="rules" ref="formRef">
        <el-form-item label="账号" prop="username">
          <el-input disabled v-model="data.form.username" autocomplete="off" placeholder="请输入账号"/>
        </el-form-item>
        <el-form-item label="名称" prop="name">
          <el-input v-model="data.form.name" autocomplete="off" placeholder="请输入名称"/>
        </el-form-item>
        <div v-if="data.user.role==='EMP'">
          <el-form-item label="性别" prop="sex">
            <el-radio-group v-model="data.form.sex">
              <el-radio value="男">男</el-radio>
              <el-radio value="女">女</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="工号" prop="no">
            <el-input disabled v-model="data.form.no" autocomplete="off" placeholder="请输入工号"/>
          </el-form-item>
          <el-form-item label="年龄" prop="age">
            <el-input-number style="width: 200px;" v-model="data.form.age" autocomplete="off" placeholder="请输入年龄"/>
          </el-form-item>
          <el-form-item label="个人介绍" prop="description">
            <el-input type="textarea" :rows="5" v-model="data.form.description" autocomplete="off"
                      placeholder="请输入描述"/>
          </el-form-item>
        </div>
        <div style="text-align: center;padding-bottom: 20px">
          <el-button type="primary" @click="updatePerson">更新个人信息</el-button>
        </div>
      </el-form>
  </div>

</template>


<script setup>
import {reactive, ref, onMounted} from "vue";
import request from "@/utils/request.js";
import {ElMessage} from "element-plus";
const formRef = ref(null); // 定义表单的引用

const data = reactive({
  user:JSON.parse(localStorage.getItem('xm-pro-user') || '{}'),
  name: null,
  pageNumber: 1,
  pageSize: 10,
  total: 0,
  tableData: [],
  formVisible: false,
  form: {
    id: null,
    username: '',
    name: '',
    sex: '',
    no: '',
    age: null,
    description: ''
  },
  ids: []
})

const rules = reactive({
  name: [
    {required: true, message: '名称不能为空', trigger: 'blur'},
    {min: 2, max: 20, message: '名称长度应在2到20个字符之间', trigger: 'blur'}
  ],
  sex: [
    {required: true, message: '请选择性别', trigger: 'change'}
  ],
  age: [
    {required: true, message: '请填写年龄', trigger: ['blur', 'change']}
  ],
  no: [
    {required: true, message: '请填写工号', trigger: ['blur', 'change']}
  ],
  username: [
    {required: true, message: '请填写账号', trigger: ['blur', 'change']}
  ],
});

// 页面加载时获取用户信息
onMounted(() => {
  if(data.user && data.user.id) {
    if (data.user.role === 'ADMIN') {
      // 管理员获取个人信息
      request.get('/admin/selectById/' + data.user.id).then(res => {
        if(res && res.data) {
          data.form = res.data
        }
      })
    } else {
      // 员工获取个人信息
      request.get('/employee/selectById/' + data.user.id).then(res => {
        if(res && res.data) {
          data.form = res.data
        }
      })
    }
  }
})

const emit=defineEmits(['updateUser'])
// 更新个人信息方法
const updatePerson = () => {
  formRef.value.validate((valid) => {
    if (valid) {
      if (data.user.role === 'ADMIN') {
        // 管理员更新个人信息
        request.put('/admin/update', data.form).then(res => {
          if (res.code === '200') {
            ElMessage.success('更新成功')
            //更新缓存数据
            localStorage.setItem('xm-pro-user', JSON.stringify(data.form))
            emit('updateUser')
          } else {
            ElMessage.error(res.msg || '更新失败')
          }
        })
      } else {
        // 员工更新个人信息
        request.put('/employee/update', data.form).then(res => {
          if (res.code === '200') {
            //更新缓存数据
            localStorage.setItem('xm-pro-user', JSON.stringify(data.form))
            emit('updateUser')
            ElMessage.success('更新成功')
          } else {
            ElMessage.error(res.msg || '更新失败')
          }
        })
      }
    }
  })
}

</script>