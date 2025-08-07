<template>
  <div class="login-container">
    <div class="login-card">
      <h1 class="title">欢迎回来</h1>
      <p class="subtitle">登录您的账户</p>
      <el-form
          ref="loginFormRef"
          :model="loginForm"
          :rules="rules"
          class="login-form"
          @submit.prevent="handleLogin"
      >
        <el-form-item prop="username">
          <el-input
              v-model="loginForm.username"
              placeholder="用户名"
              prefix-icon="User"
              clearable
              size="large"
          />
        </el-form-item>
        <el-form-item prop="password">
          <el-input
              v-model="loginForm.password"
              type="password"
              placeholder="密码"
              prefix-icon="Lock"
              clearable
              show-password
              size="large"
          />
        </el-form-item>
        <el-form-item prop="role">
          <el-select v-model="loginForm.role" placeholder="请选择角色">
            <el-option label="管理员" value="ADMIN"></el-option>
            <el-option label="员工" value="EMP"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item>
          <el-checkbox v-model="loginForm.remember" label="记住我" />
        </el-form-item>
        <el-form-item>
          <el-button
              type="primary"
              size="large"
              :loading="loading"
              class="login-button"
              @click="handleLogin"
          >
            登录
          </el-button>
        </el-form-item>
      </el-form>
      <div class="footer-links">
        <el-link type="primary" :underline="false">忘记密码?</el-link>
        <el-link type="primary" :underline="false" @click="() => { this.$router.push('/register') } ">创建账户</el-link>
      </div>
    </div>
  </div>
</template>

<script setup>
import {ref} from 'vue'
import {ElMessage} from 'element-plus'
import request from "@/utils/request.js";
import Register from "@/views/Register.vue";

// 表单数据
const loginForm = ref({
  username: '',
  password: '',
  remember: false,
  role:['ADMIN']
})

// 表单引用
const loginFormRef = ref(null)

// 加载状态
const loading = ref(false)

// 表单验证规则
const rules = {
  username: [
    {required: true, message: '请输入用户名', trigger: 'blur'},
    {min: 3, message: '用户名至少3个字符', trigger: 'blur'}
  ],
  password: [
    {required: true, message: '请输入密码', trigger: 'blur'},
    {min: 5, message: '密码至少5个字符', trigger: 'blur'}
  ]
}

// 处理登录
const handleLogin = () => {
  loginFormRef.value.validate((valid) => {
    if (valid) {
      loading.value = true
      request.post('/login', loginForm.value).then(res => {
        if(res.code === '200')
        {
          localStorage.setItem('xm-pro-user', JSON.stringify(res.data))
          console.log(res.data)
          setTimeout(
              () => {
                loading.value = false
                location.href = '/manager/home'
              },
              1000
          )

        }
        else
          ElMessage.error(res.message)
      })
    }
  })
}
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background-color: #E6F0FA;
  padding: 20px;
}

.login-card {
  background: #FFFFFF;
  border-radius: 16px;
  padding: 40px;
  width: 100%;
  max-width: 400px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  border: 1px solid #D3E0EA;
  animation: fadeIn 0.5s ease-in-out;
}

.title {
  font-size: 28px;
  font-weight: bold;
  color: #0078D4;
  text-align: center;
  margin-bottom: 10px;
}

.subtitle {
  font-size: 16px;
  color: #666666;
  text-align: center;
  margin-bottom: 30px;
}

.login-form {
  margin-top: 20px;
}

.login-button {
  width: 100%;
  background-color: #0078D4;
  border: none;
  transition: transform 0.3s ease;
}

.login-button:hover {
  transform: translateY(-2px);
  background-color: #005BB5;
}

.footer-links {
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
}

.footer-links .el-link {
  color: #0078D4;
  font-size: 14px;
}

.footer-links .el-link:hover {
  color: #FFC107;
}

@keyframes fadeIn {
  from {
    opacity: 0;
    transform: translateY(20px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

/* 响应式设计 */
@media (max-width: 480px) {
  .login-card {
    padding: 20px;
  }
}
</style>