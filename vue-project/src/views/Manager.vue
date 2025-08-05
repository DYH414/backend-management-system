<template>
  <div>
    <!--  头部开始-->
    <div style="height: 60px; background-color: #60b4fd; display: flex; align-items: center; padding: 0 10px;">

      <div style="width: 200px;display: flex;align-items: center;padding-left:5px">
        <img src="@/assets/flower1.png" alt="" style="width: 40px;">
        <span style="color: white; font-size: 24px;">后台管理系统</span>
      </div>
      <div style="flex: 1;"></div>
      <div style="width:fit-content;display: flex;align-items: center;padding-right: 20px">
        <img src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png"style="width: 35px;height: 35px;" alt="">
        <span style="margin-left: 10px; color: white;">{{data.user.name}}</span>
      </div>

    </div>
    <!--  头部结束-->

    <!--  下面部分开始-->
    <div>
      <div style="display: flex">
        <!--    左侧导航菜单开始-->
        <div style="width: 200px;border-right: 1px solid #ddd ;min-height: calc(100vh - 60px)">
          <el-menu router :default-active="router.currentRoute.value.path" style="border: 0" :default-openeds="['1']"
                   background-color="#fff" text-color="#333" active-text-color="#60b4fd">
            <el-menu-item index="/Manager/Home">
              <el-icon>
                <House/>
              </el-icon>
              系统首页
            </el-menu-item>

            <el-sub-menu index="1">
              <template #title>
                <el-icon>
                  <User/>
                </el-icon>
                <span>用户管理</span>
              </template>
              <el-menu-item>管理员信息</el-menu-item>
              <el-menu-item index="/manager/employee">员工信息</el-menu-item>
            </el-sub-menu>

            <el-menu-item index="/Manager/Data">
              <el-icon>
                <DataAnalysis/>
              </el-icon>
              数据统计</el-menu-item>
            <el-menu-item index="/Manager/Data">
              <el-icon>
                <InfoFilled/>
              </el-icon>
              个人信息</el-menu-item>
            <el-menu-item index="/Login">
              <el-icon>
                <SwitchButton/>
              </el-icon >
              退出登录</el-menu-item>
          </el-menu>

        </div>
        <!-- 左侧导航菜单结束-->

        <!--    右侧主体区域开始-->
        <div style="flex: 1;width: 0;background-color: #eaf6fb;padding: 10px">
          <RouterView/>

        </div>
        <!--    右侧主题区域结束-->

      </div>

    </div>
    <!--  下面部分结束-->
  </div>

</template>
<script setup>
import router from "@/router/index.js";
import {DataAnalysis, InfoFilled, Switch, SwitchButton} from "@element-plus/icons-vue";
import {reactive} from "vue";
const data=reactive( {
  user:JSON.parse(localStorage.getItem('xm-pro-user'))
})
</script>
<style>
.el-menu .is-active{
  background-color: #e2eef8;
}
</style>